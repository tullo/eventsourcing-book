package de.eventsourcingbook.cart.additem.integration

import de.eventsourcingbook.cart.application.CartSessionIdProvider
import de.eventsourcingbook.cart.application.ThreadLocalCartSessionIdProvider
import de.eventsourcingbook.cart.common.support.BaseIntegrationTest
import de.eventsourcingbook.cart.common.support.RandomData
import de.eventsourcingbook.cart.common.support.StreamAssertions
import de.eventsourcingbook.cart.common.support.awaitUntilAssserted
import de.eventsourcingbook.cart.domain.commands.additem.AddItemCommand
import de.eventsourcingbook.cart.events.ItemAddedEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import org.axonframework.messaging.annotation.MetaDataValue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SampleEventHandler {
    @EventHandler
    fun on(
        event: ItemAddedEvent,
        @MetaDataValue(CartSessionIdProvider.SESSION_ID) cartSession: String,
    ) {
    }
}

class AddItemCorrelationItemTest : BaseIntegrationTest() {
    @Autowired
    private lateinit var commandGateway: CommandGateway

    @Autowired
    private lateinit var streamAssertions: StreamAssertions

    @Autowired
    private lateinit var cartSessionIdProvider: ThreadLocalCartSessionIdProvider

    @Test
    fun `adds item with correlation id`() {
        val aggregateId = UUID.randomUUID()
        val cartSessionId = UUID.randomUUID().toString()
        cartSessionIdProvider.setSessionId(cartSessionId)

        var addItemCommand =
            RandomData.newInstance<AddItemCommand> {
                this.aggregateId = aggregateId
            }

        commandGateway.sendAndWait<Any>(addItemCommand)

        awaitUntilAssserted {
            streamAssertions.assertMetaData(aggregateId.toString()) {
                it.containsKey(CartSessionIdProvider.SESSION_ID) &&
                    it.get(CartSessionIdProvider.SESSION_ID) == cartSessionId
            }
        }
    }
}
