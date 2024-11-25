package de.nebulit.backoffice.prepareshipping.internal

import de.nebulit.backoffice.domain.commands.prepareshipping.PrepareShippingCommand
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

data class PrepareShippingPayload(var orderId: UUID)

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606918335813
*/
@RestController
class PrepareShippingRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/prepareshipping")
  fun processDebugCommand(@RequestParam orderId: UUID): CompletableFuture<Any> {
    return commandGateway.send(PrepareShippingCommand(orderId))
  }

  @CrossOrigin
  @PostMapping("/prepareshipping/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: PrepareShippingPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(PrepareShippingCommand(orderId = payload.orderId))
  }
}
