package de.nebulit.backoffice.assignclerk.internal

import de.nebulit.backoffice.domain.commands.assignclerk.AssignClerkCommand
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

data class AssignClerkPayload(var clerkId: String, var orderId: UUID)

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764608122771892
*/
@RestController
class AssignClerkRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/assignclerk")
  fun processDebugCommand(
      @RequestParam clerkId: String,
      @RequestParam orderId: UUID
  ): CompletableFuture<Any> {
    return commandGateway.send(AssignClerkCommand(clerkId, orderId))
  }

  @CrossOrigin
  @PostMapping("/assignclerk/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: AssignClerkPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        AssignClerkCommand(clerkId = payload.clerkId, orderId = payload.orderId))
  }
}
