package de.nebulit.backoffice.cancelorder.internal

import de.nebulit.backoffice.domain.commands.cancelorder.CancelOrderCommand
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

data class CancelOrderPayload(var orderId: UUID)

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606960617352
*/
@RestController
class CancelOrderRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/cancelorder")
  fun processDebugCommand(@RequestParam orderId: UUID): CompletableFuture<Any> {
    return commandGateway.send(CancelOrderCommand(orderId))
  }

  @CrossOrigin
  @PostMapping("/cancelorder/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: CancelOrderPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(CancelOrderCommand(orderId = payload.orderId))
  }
}
