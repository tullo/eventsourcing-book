package de.nebulit.backoffice.preparedorderstoship

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

class OrdersToShipReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606918579542
*/
@Entity
class OrdersToShipReadModelEntity {
  @Id @Column(name = "orderId") var orderId: UUID? = null
}

data class OrdersToShipReadModel(val data: List<OrdersToShipReadModelEntity>)
