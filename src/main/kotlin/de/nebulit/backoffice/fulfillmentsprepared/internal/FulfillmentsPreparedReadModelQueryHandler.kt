package de.nebulit.backoffice.fulfillmentsprepared.internal

import de.nebulit.backoffice.fulfillmentsprepared.FulfillmentsPreparedReadModel
import de.nebulit.backoffice.fulfillmentsprepared.FulfillmentsPreparedReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink:
*/
@Component
class FulfillmentsPreparedReadModelQueryHandler(
    private val repository: FulfillmentsPreparedReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: FulfillmentsPreparedReadModelQuery): FulfillmentsPreparedReadModel? {
    return FulfillmentsPreparedReadModel(repository.findAll())
  }
}
