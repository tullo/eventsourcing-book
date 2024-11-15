package de.nebulit.backoffice.fulfillmentsprepared.internal

import de.nebulit.backoffice.events.FulfillmentPreparedEvent
import de.nebulit.backoffice.events.ShippingPreparedEvent
import de.nebulit.backoffice.fulfillmentsprepared.FulfillmentsPreparedReadModelEntity
import java.util.UUID
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface FulfillmentsPreparedReadModelRepository :
    JpaRepository<FulfillmentsPreparedReadModelEntity, UUID>

/*
Boardlink:
*/
@Component
class FulfillmentsPreparedReadModelProjector(
    var repository: FulfillmentsPreparedReadModelRepository,
) {
  @EventHandler
  fun on(event: FulfillmentPreparedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.orderId).orElse(FulfillmentsPreparedReadModelEntity())
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
          orderedProducts = event.orderedProducts.toMutableList()
          orderId = event.orderId
        }
        .also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: ShippingPreparedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.orderId).orElse(FulfillmentsPreparedReadModelEntity())
    entity.apply { orderId = event.orderId }.also { this.repository.save(it) }
  }
}
