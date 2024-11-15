package de.nebulit.backoffice.preparedorderstoship.internal

import de.nebulit.backoffice.events.ShippingPreparedEvent
import de.nebulit.backoffice.preparedorderstoship.OrdersToShopReadModelEntity
import java.util.UUID
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface OrdersToShopReadModelRepository : JpaRepository<OrdersToShopReadModelEntity, UUID>

/*
Boardlink:
*/
@Component
class OrdersToShopReadModelProjector(var repository: OrdersToShopReadModelRepository) {

  @EventHandler
  fun on(event: ShippingPreparedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.orderId).orElse(OrdersToShopReadModelEntity())
    entity.apply { orderId = event.orderId }.also { this.repository.save(it) }
  }
}
