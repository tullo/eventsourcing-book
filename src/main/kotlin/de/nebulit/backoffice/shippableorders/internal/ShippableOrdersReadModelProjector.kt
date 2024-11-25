package de.nebulit.backoffice.shippableorders.internal

import de.nebulit.backoffice.events.OrderShippedEvent
import de.nebulit.backoffice.events.ShippingPreparedEvent
import de.nebulit.backoffice.events.ShippingRequestedEvent
import de.nebulit.backoffice.shippableorders.ShippableOrdersReadModelEntity
import java.util.UUID
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ShippableOrdersReadModelRepository : JpaRepository<ShippableOrdersReadModelEntity, UUID>

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606919030967
*/
@Component
class ShippableOrdersReadModelProjector(var repository: ShippableOrdersReadModelRepository) {

  @EventHandler
  fun on(event: OrderShippedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.orderId).orElse(ShippableOrdersReadModelEntity())
    entity
        .apply {
          deliveryTrackingId = event.deliveryTrackingId
          orderId = event.orderId
        }
        .also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: ShippingPreparedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.orderId).orElse(ShippableOrdersReadModelEntity())
    entity.apply { orderId = event.orderId }.also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: ShippingRequestedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.orderId).orElse(ShippableOrdersReadModelEntity())
    entity
        .apply {
          deliveryTrackingId = event.deliveryTrackingId
          orderId = event.orderId
        }
        .also { this.repository.save(it) }
  }
}
