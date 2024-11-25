package de.nebulit.backoffice.fulfillmentsprepared.internal

import de.nebulit.backoffice.events.OrderPreparedEvent
import de.nebulit.backoffice.fulfillmentsprepared.OrdersToProcessReadModelEntity
import java.util.UUID
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface OrdersToProcessReadModelRepository : JpaRepository<OrdersToProcessReadModelEntity, UUID>

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606917717283
*/
@Component
class OrdersToProcessReadModelProjector(var repository: OrdersToProcessReadModelRepository) {

  @EventHandler
  fun on(event: OrderPreparedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.orderId).orElse(OrdersToProcessReadModelEntity())
    entity
        .apply {
          city = event.city
          houseNumber = event.houseNumber
          paymentId = event.paymentId
          street = event.street
          totalPrice = event.totalPrice
          zipCode = event.zipCode
          name = event.name
          surname = event.surname
          orderedProducts = event.orderedProducts
          orderId = event.orderId
          customerId = event.customerId
        }
        .also { this.repository.save(it) }
  }
}
