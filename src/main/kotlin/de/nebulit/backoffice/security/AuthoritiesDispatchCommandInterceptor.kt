package de.nebulit.backoffice.security

import java.util.function.BiFunction
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.messaging.MessageDispatchInterceptor
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

// open PR https://github.com/AxonFramework/AxonFramework/pull/3057/
@Component
class AuthoritiesDispatchCommandInterceptor : MessageDispatchInterceptor<CommandMessage<*>> {
  override fun handle(
      messages: MutableList<out CommandMessage<*>>
  ): BiFunction<Int, CommandMessage<*>, CommandMessage<*>> = BiFunction { _, message ->
    handle(message)
  }

  override fun handle(message: CommandMessage<*>): CommandMessage<*> {
    val authentication = SecurityContextHolder.getContext().getAuthentication() ?: return message
    val authenticationDetails = mutableMapOf<String, Any>()
    authenticationDetails.put("username", authentication.principal)
    authenticationDetails.put("authorities", authentication.authorities)
    return message.andMetaData(authenticationDetails)
  }
}
