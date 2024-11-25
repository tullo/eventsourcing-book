package de.nebulit.backoffice.security

import org.axonframework.messaging.Message
import org.axonframework.messaging.annotation.HandlerEnhancerDefinition
import org.axonframework.messaging.annotation.MessageHandlingMember
import org.axonframework.messaging.annotation.WrappedMessageHandlingMember
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component

@Component
class SecuredMethodMessageHandlerDefinition : HandlerEnhancerDefinition {
  override fun <T> wrapHandler(original: MessageHandlingMember<T>): MessageHandlingMember<T> =
      original
          .annotationAttributes(Secured::class.java)
          .map { attr: Map<String?, Any?> ->
            SecuredMessageHandlingMember(
                original,
                attr,
            )
                as MessageHandlingMember<T>
          }
          .orElse(original)

  private class SecuredMessageHandlingMember<T>(
      delegate: MessageHandlingMember<T>?,
      annotationAttributes: Map<String?, Any?>,
  ) : WrappedMessageHandlingMember<T>(delegate) {
    private val requiredRoles: List<String> =
        (annotationAttributes["secured"] as Array<String>).toList()

    @Throws(Exception::class)
    override fun handle(
        message: Message<*>,
        target: T?,
    ): Any? {
      if (!hasRequiredRoles(message)) {
        throw AccessDeniedException("Unauthorized message " + message.identifier)
      }
      return super.handle(message, target)
    }

    fun hasRequiredRoles(message: Message<*>): Boolean {
      message.metaData.get("authorities")?.let {
        return (it as Collection<GrantedAuthority>).map { it.authority }.containsAll(requiredRoles)
      }
      return false
    }
  }
}
