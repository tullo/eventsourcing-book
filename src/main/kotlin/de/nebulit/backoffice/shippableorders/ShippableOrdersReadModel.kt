package de.nebulit.backoffice.shippableorders

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

class ShippableOrdersReadModelQuery()

/*
Boardlink:
*/
@Entity
class ShippableOrdersReadModelEntity {
  @Column(name = "deliveryTrackingId") var deliveryTrackingId: String? = null

  @Column(name = "status") var status: String? = null

  @Id @Column(name = "orderId") var orderId: UUID? = null
}

data class ShippableOrdersReadModel(val data: List<ShippableOrdersReadModelEntity>)
