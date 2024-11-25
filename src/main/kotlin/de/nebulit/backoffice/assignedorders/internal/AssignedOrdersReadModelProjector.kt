package de.nebulit.backoffice.assignedorders.internal

import de.nebulit.backoffice.assignedorders.AssignedOrdersReadModelEntity
import de.nebulit.backoffice.events.ClerkAssignedEvent
import de.nebulit.backoffice.events.OrderPreparedEvent
import java.util.UUID
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface AssignedOrdersReadModelRepository : JpaRepository<AssignedOrdersReadModelEntity, UUID>

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764608123111077
*/
@Component
class AssignedOrdersReadModelProjector(var repository: AssignedOrdersReadModelRepository) {

  @EventHandler
  fun on(event: OrderPreparedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.orderId).orElse(AssignedOrdersReadModelEntity())
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

  @EventHandler
  fun on(event: ClerkAssignedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.orderId).orElse(AssignedOrdersReadModelEntity())
    entity.apply { orderId = event.orderId }.also { this.repository.save(it) }
  }
}
