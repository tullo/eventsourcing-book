package de.nebulit.backoffice.shiporder.internal

import de.nebulit.backoffice.domain.commands.shiporder.ShipOrderCommand
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

data class ShipOrderPayload(var deliveryTrackingId: String, var orderId: UUID)

/*
Boardlink:
*/
@RestController
class ShipOrderRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/shiporder")
  fun processDebugCommand(
      @RequestParam deliveryTrackingId: String,
      @RequestParam orderId: UUID
  ): CompletableFuture<Any> {
    return commandGateway.send(ShipOrderCommand(deliveryTrackingId, orderId))
  }

  @CrossOrigin
  @PostMapping("/shiporder/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: ShipOrderPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        ShipOrderCommand(
            deliveryTrackingId = payload.deliveryTrackingId, orderId = payload.orderId))
  }
}
