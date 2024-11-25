package de.nebulit.backoffice.assignedorders.internal

import de.nebulit.backoffice.assignedorders.AssignedOrdersReadModel
import de.nebulit.backoffice.assignedorders.AssignedOrdersReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764608123111077
*/
@RestController
class AssignedordersRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/assignedorders")
  fun findReadModel(): CompletableFuture<AssignedOrdersReadModel> {
    return queryGateway.query(AssignedOrdersReadModelQuery(), AssignedOrdersReadModel::class.java)
  }
}
