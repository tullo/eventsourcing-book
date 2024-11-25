package de.nebulit.backoffice.fulfillmentsprepared

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import java.util.UUID

class OrdersToProcessReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606917717283
*/
@Entity
class OrdersToProcessReadModelEntity {
  @Column(name = "city") var city: String? = null

  @Column(name = "houseNumber") var houseNumber: Int? = null

  @Column(name = "paymentId") var paymentId: UUID? = null

  @Column(name = "street") var street: String? = null

  @Column(name = "totalPrice") var totalPrice: Double? = null

  @Column(name = "zipCode") var zipCode: String? = null

  @Column(name = "status") var status: String? = null

  @Column(name = "name") var name: String? = null

  @Column(name = "surname") var surname: String? = null

  // TODO review type mapping
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      name = "fulfillmentsprepared_ordered_products",
      joinColumns = [JoinColumn(name = "aggregateId")])
  var orderedProducts: List<UUID> = mutableListOf()

  @Id @Column(name = "orderId") var orderId: UUID? = null

  @Column(name = "customerId") var customerId: UUID? = null
}

data class OrdersToProcessReadModel(val data: List<OrdersToProcessReadModelEntity>)