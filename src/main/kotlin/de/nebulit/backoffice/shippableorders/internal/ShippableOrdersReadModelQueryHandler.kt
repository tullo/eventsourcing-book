package de.nebulit.backoffice.shippableorders.internal

import de.nebulit.backoffice.shippableorders.ShippableOrdersReadModel
import de.nebulit.backoffice.shippableorders.ShippableOrdersReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink:
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
