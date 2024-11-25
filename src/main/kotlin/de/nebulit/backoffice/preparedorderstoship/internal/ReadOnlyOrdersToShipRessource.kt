package de.nebulit.backoffice.preparedorderstoship.internal

import de.nebulit.backoffice.preparedorderstoship.OrdersToShipReadModel
import de.nebulit.backoffice.preparedorderstoship.OrdersToShipReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606918579542
*/
@RestController
class PreparedorderstoshipRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/preparedorderstoship")
  fun findReadModel(): CompletableFuture<OrdersToShipReadModel> {
    return queryGateway.query(OrdersToShipReadModelQuery(), OrdersToShipReadModel::class.java)
  }
}
