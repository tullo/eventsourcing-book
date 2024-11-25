package de.nebulit.backoffice.fulfillmentsprepared.internal

import de.nebulit.backoffice.fulfillmentsprepared.OrdersToProcessReadModel
import de.nebulit.backoffice.fulfillmentsprepared.OrdersToProcessReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606917717283
*/
@RestController
class FulfillmentspreparedRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/fulfillmentsprepared")
  fun findReadModel(): CompletableFuture<OrdersToProcessReadModel> {
    return queryGateway.query(OrdersToProcessReadModelQuery(), OrdersToProcessReadModel::class.java)
  }
}
