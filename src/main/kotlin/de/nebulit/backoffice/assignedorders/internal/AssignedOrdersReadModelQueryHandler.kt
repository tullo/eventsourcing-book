package de.nebulit.backoffice.assignedorders.internal

import de.nebulit.backoffice.assignedorders.AssignedOrdersReadModel
import de.nebulit.backoffice.assignedorders.AssignedOrdersReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764608123111077
*/
@Component
class AssignedOrdersReadModelQueryHandler(
    private val repository: AssignedOrdersReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: AssignedOrdersReadModelQuery): AssignedOrdersReadModel? {
    return AssignedOrdersReadModel(repository.findAll())
  }
}
