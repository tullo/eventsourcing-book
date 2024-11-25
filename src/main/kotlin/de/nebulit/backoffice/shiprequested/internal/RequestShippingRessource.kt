package de.nebulit.backoffice.shiprequested.internal

import de.nebulit.backoffice.domain.commands.shiprequested.RequestShippingCommand
import java.util.UUID
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ShipRequestedPayload(var deliveryTrackingId: String, var orderId: UUID)

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606918579539
*/
@RestController
class RequestShippingRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/shiprequested")
  fun processDebugCommand(
      @RequestParam deliveryTrackingId: String,
      @RequestParam orderId: UUID
  ): CompletableFuture<Any> {
    return commandGateway.send(RequestShippingCommand(deliveryTrackingId, orderId))
  }

  @CrossOrigin
  @PostMapping("/shiprequested/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: ShipRequestedPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        RequestShippingCommand(
            deliveryTrackingId = payload.deliveryTrackingId, orderId = payload.orderId))
  }
}
