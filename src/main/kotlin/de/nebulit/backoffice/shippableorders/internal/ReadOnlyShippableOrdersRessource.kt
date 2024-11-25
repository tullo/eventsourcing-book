package de.nebulit.backoffice.shippableorders.internal

import de.nebulit.backoffice.shippableorders.ShippableOrdersReadModel
import de.nebulit.backoffice.shippableorders.ShippableOrdersReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606919030967
*/
@RestController
class ShippableordersRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/shippableorders")
  fun findReadModel(): CompletableFuture<ShippableOrdersReadModel> {
    return queryGateway.query(ShippableOrdersReadModelQuery(), ShippableOrdersReadModel::class.java)
  }
}
