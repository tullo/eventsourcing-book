package de.nebulit.backoffice.preparedorderstoship.internal

import de.nebulit.backoffice.preparedorderstoship.OrdersToShipReadModel
import de.nebulit.backoffice.preparedorderstoship.OrdersToShipReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606918579542
*/
@Component
class OrdersToShipReadModelQueryHandler(private val repository: OrdersToShipReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: OrdersToShipReadModelQuery): OrdersToShipReadModel? {
    return OrdersToShipReadModel(repository.findAll())
  }
}
