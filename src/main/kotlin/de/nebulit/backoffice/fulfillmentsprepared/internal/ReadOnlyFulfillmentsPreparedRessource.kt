package de.nebulit.backoffice.fulfillmentsprepared.internal

import de.nebulit.backoffice.fulfillmentsprepared.FulfillmentsPreparedReadModel
import de.nebulit.backoffice.fulfillmentsprepared.FulfillmentsPreparedReadModelQuery
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
class FulfillmentspreparedRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/fulfillmentsprepared")
  fun findReadModel(): CompletableFuture<FulfillmentsPreparedReadModel> {
    return queryGateway.query(
        FulfillmentsPreparedReadModelQuery(), FulfillmentsPreparedReadModel::class.java)
  }
}
