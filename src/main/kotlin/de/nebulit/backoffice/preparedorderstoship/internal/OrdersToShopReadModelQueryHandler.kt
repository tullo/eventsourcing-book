package de.nebulit.backoffice.preparedorderstoship.internal

import de.nebulit.backoffice.preparedorderstoship.OrdersToShopReadModel
import de.nebulit.backoffice.preparedorderstoship.OrdersToShopReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink:
*/
@Component
class OrdersToShopReadModelQueryHandler(private val repository: OrdersToShopReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: OrdersToShopReadModelQuery): OrdersToShopReadModel? {
    return OrdersToShopReadModel(repository.findAll())
  }
}
