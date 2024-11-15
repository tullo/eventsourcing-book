package de.nebulit.backoffice.preparedorderstoship

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

class OrdersToShopReadModelQuery()

/*
Boardlink:
*/
@Entity
class OrdersToShopReadModelEntity {
  @Id @Column(name = "orderId") var orderId: UUID? = null
}

data class OrdersToShopReadModel(val data: List<OrdersToShopReadModelEntity>)
