package de.nebulit.backoffice.preparedorderstoship.internal

import de.nebulit.backoffice.events.ShippingPreparedEvent
import de.nebulit.backoffice.preparedorderstoship.OrdersToShipReadModelEntity
import java.util.UUID
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface OrdersToShipReadModelRepository : JpaRepository<OrdersToShipReadModelEntity, UUID>

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606918579542
*/
@Component
class OrdersToShipReadModelProjector(var repository: OrdersToShipReadModelRepository) {

  @EventHandler
  fun on(event: ShippingPreparedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.orderId).orElse(OrdersToShipReadModelEntity())
    entity.apply { orderId = event.orderId }.also { this.repository.save(it) }
  }
}
