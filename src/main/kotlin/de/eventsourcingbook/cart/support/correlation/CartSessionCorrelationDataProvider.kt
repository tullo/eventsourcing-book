package de.eventsourcingbook.cart.support.correlation

import de.eventsourcingbook.cart.application.CartSessionIdProvider
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.messaging.Message
import org.axonframework.messaging.annotation.ParameterResolver
import org.axonframework.messaging.correlation.CorrelationDataProvider
import org.springframework.stereotype.Component

@Component
class CartSessionCorrelationDataProvider(
    val cartSessionIdProvider: CartSessionIdProvider,
) : CorrelationDataProvider {
    override fun correlationDataFor(message: Message<*>?): MutableMap<String, *> {
        if (message is CommandMessage) {
            return mutableMapOf(CartSessionIdProvider.SESSION_ID to cartSessionIdProvider.provideCartSession())
        }
        return mutableMapOf<String, String>()
    }
}

@Component
class CartSessionParamterResolver : ParameterResolver<String?> {
    override fun resolveParameterValue(message: Message<*>?): String? = message?.metaData?.get(CartSessionIdProvider.SESSION_ID)?.toString()

    override fun matches(message: Message<*>?): Boolean = true
}
