package de.nebulit.backoffice.fulfillmentsprepared.internal

import de.nebulit.backoffice.fulfillmentsprepared.OrdersToProcessReadModel
import de.nebulit.backoffice.fulfillmentsprepared.OrdersToProcessReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606917717283
*/
@Component
class OrdersToProcessReadModelQueryHandler(
    private val repository: OrdersToProcessReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: OrdersToProcessReadModelQuery): OrdersToProcessReadModel? {
    return OrdersToProcessReadModel(repository.findAll())
  }
}
