package de.nebulit.backoffice.shippableorders.internal

import de.nebulit.backoffice.shippableorders.ShippableOrdersReadModel
import de.nebulit.backoffice.shippableorders.ShippableOrdersReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606919030967
*/
@Component
class ShippableOrdersReadModelQueryHandler(
    private val repository: ShippableOrdersReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: ShippableOrdersReadModelQuery): ShippableOrdersReadModel? {
    return ShippableOrdersReadModel(repository.findAll())
  }
}
