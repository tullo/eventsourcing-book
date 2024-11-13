package de.eventsourcingbook.cart.support.correlation

import org.axonframework.messaging.correlation.CorrelationDataProvider
import org.axonframework.messaging.correlation.MessageOriginProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CorrelationConfig {
    @Bean
    fun messageOriginProvider(): CorrelationDataProvider = MessageOriginProvider()
}
