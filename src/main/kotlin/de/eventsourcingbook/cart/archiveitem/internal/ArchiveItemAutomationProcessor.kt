package de.eventsourcingbook.cart.archiveitem.internal

import de.eventsourcingbook.cart.cartwithproducts.CartsWithProductsReadModel
import de.eventsourcingbook.cart.cartwithproducts.CartsWithProductsReadModelQuery
import de.eventsourcingbook.cart.common.Processor
import de.eventsourcingbook.cart.domain.commands.archiveitem.ArchiveItemCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.axonframework.eventhandling.EventHandler

import de.eventsourcingbook.cart.events.PriceChangedEvent
import org.axonframework.eventhandling.DisallowReplay

@DisallowReplay
@Component
class ArchiveItemAutomationProcessor : Processor {
    var logger = KotlinLogging.logger {}

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway


    @EventHandler
    fun on(event: PriceChangedEvent) {
        queryGateway.query(
            CartsWithProductsReadModelQuery(event.productId),
            CartsWithProductsReadModel::class.java
        ).thenAccept {
            it.data.forEach { cart ->
                commandGateway.send<ArchiveItemCommand>(
                    ArchiveItemCommand(
                        aggregateId = cart.aggregateId,
                        productId = cart.productId
                    )
                )
            }
        }
    }

}
