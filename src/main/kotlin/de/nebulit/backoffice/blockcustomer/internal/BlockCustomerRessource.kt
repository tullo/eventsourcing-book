package de.nebulit.backoffice.blockcustomer.internal

import de.nebulit.backoffice.domain.commands.command.BlockCustomerCommand
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

data class CommandPayload(
    var customerId: UUID,
)

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764608106452194
*/
@RestController
class BlockCustomerRessource(
    private var commandGateway: CommandGateway,
) {
  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/command")
  fun processDebugCommand(
      @RequestParam customerId: UUID,
  ): CompletableFuture<Any> = commandGateway.send(BlockCustomerCommand(customerId))

  @CrossOrigin
  @PostMapping("/command/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: CommandPayload,
  ): CompletableFuture<Any> =
      commandGateway.send(BlockCustomerCommand(customerId = payload.customerId))
}
