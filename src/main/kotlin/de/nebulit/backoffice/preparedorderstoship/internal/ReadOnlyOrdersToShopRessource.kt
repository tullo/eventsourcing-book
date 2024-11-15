package de.nebulit.backoffice.preparedorderstoship.internal

import de.nebulit.backoffice.preparedorderstoship.OrdersToShopReadModel
import de.nebulit.backoffice.preparedorderstoship.OrdersToShopReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink:
*/
@RestController
class PreparedorderstoshipRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/preparedorderstoship")
  fun findReadModel(): CompletableFuture<OrdersToShopReadModel> {
    return queryGateway.query(OrdersToShopReadModelQuery(), OrdersToShopReadModel::class.java)
  }
}
