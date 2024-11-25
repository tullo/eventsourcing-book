package de.nebulit.backoffice.domain

import de.nebulit.backoffice.domain.commands.assignclerk.AssignClerkCommand
import de.nebulit.backoffice.domain.commands.cancelorder.CancelOrderCommand
import de.nebulit.backoffice.domain.commands.prepareshipping.PrepareShippingCommand
import de.nebulit.backoffice.domain.commands.shiporder.ShipOrderCommand
import de.nebulit.backoffice.domain.commands.shiprequested.RequestShippingCommand
import de.nebulit.backoffice.domain.commands.submitorder.PrepareOrderCommand
import de.nebulit.backoffice.events.ClerkAssignedEvent
import de.nebulit.backoffice.events.OrderCancelledEvent
import de.nebulit.backoffice.events.OrderPreparedEvent
import de.nebulit.backoffice.events.OrderShippedEvent
import de.nebulit.backoffice.events.ShippingPreparedEvent
import de.nebulit.backoffice.events.ShippingRequestedEvent
import java.util.UUID
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.security.access.annotation.Secured

@Aggregate
class BackofficeAggregate {
  @AggregateIdentifier var orderId: UUID? = null

  @Secured("ROLE_CLERK")
  @CommandHandler
  fun handle(command: AssignClerkCommand) {
    AggregateLifecycle.apply(
        ClerkAssignedEvent(
            clerkId = command.clerkId,
            orderId = command.orderId,
        ),
    )
  }

  @EventSourcingHandler
  fun on(event: ClerkAssignedEvent) {
    // handle event
    orderId = event.orderId
  }

  @CommandHandler
  fun handle(command: CancelOrderCommand) {
    AggregateLifecycle.apply(OrderCancelledEvent(orderId = command.orderId))
  }

  @EventSourcingHandler
  fun on(event: OrderCancelledEvent) {
    // handle event
    orderId = event.orderId
  }

  @CommandHandler
  fun handle(command: ShipOrderCommand) {
    AggregateLifecycle.apply(
        OrderShippedEvent(
            deliveryTrackingId = command.deliveryTrackingId,
            orderId = command.orderId,
        ),
    )
  }

  @EventSourcingHandler
  fun on(event: OrderShippedEvent) {
    // handle event
    orderId = event.orderId
  }

  @CommandHandler
  fun handle(command: RequestShippingCommand) {
    AggregateLifecycle.apply(
        ShippingRequestedEvent(
            deliveryTrackingId = command.deliveryTrackingId,
            orderId = command.orderId,
        ),
    )
  }

  @EventSourcingHandler
  fun on(event: ShippingRequestedEvent) {
    // handle event
    orderId = event.orderId
  }

  @CommandHandler
  fun handle(command: PrepareShippingCommand) {
    AggregateLifecycle.apply(ShippingPreparedEvent(orderId = command.orderId))
  }

  @EventSourcingHandler
  fun on(event: ShippingPreparedEvent) {
    // handle event
    orderId = event.orderId
  }

  @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(command: PrepareOrderCommand) {
    AggregateLifecycle.apply(
        OrderPreparedEvent(
            paymentId = command.paymentId,
            totalPrice = command.totalPrice,
            street = command.street,
            houseNumber = command.houseNumber,
            zipCode = command.zipCode,
            city = command.city,
            name = command.name,
            surname = command.surname,
            orderedProducts = command.orderedProducts,
            customerId = command.customerId,
            orderId = command.orderId,
        ),
    )
  }

  @EventSourcingHandler
  fun on(event: OrderPreparedEvent) {
    // handle event
    orderId = event.orderId
  }
}
