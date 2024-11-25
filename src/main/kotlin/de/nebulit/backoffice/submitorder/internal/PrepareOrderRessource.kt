package de.nebulit.backoffice.submitorder.internal

import de.nebulit.backoffice.domain.commands.submitorder.PrepareOrderCommand
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

data class SubmitOrderPayload(
    var paymentId: UUID,
    var totalPrice: Double,
    var street: String,
    var houseNumber: Int,
    var zipCode: String,
    var city: String,
    var name: String,
    var surname: String,
    var orderedProducts: List<UUID>,
    var customerId: UUID,
    var orderId: UUID
)

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606916731730
*/
@RestController
class PrepareOrderRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/submitorder")
  fun processDebugCommand(
      @RequestParam paymentId: UUID,
      @RequestParam totalPrice: Double,
      @RequestParam street: String,
      @RequestParam houseNumber: Int,
      @RequestParam zipCode: String,
      @RequestParam city: String,
      @RequestParam name: String,
      @RequestParam surname: String,
      @RequestParam orderedProducts: List<UUID>,
      @RequestParam customerId: UUID,
      @RequestParam orderId: UUID
  ): CompletableFuture<Any> {
    return commandGateway.send(
        PrepareOrderCommand(
            paymentId,
            totalPrice,
            street,
            houseNumber,
            zipCode,
            city,
            name,
            surname,
            orderedProducts,
            customerId,
            orderId))
  }

  @CrossOrigin
  @PostMapping("/submitorder/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: SubmitOrderPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        PrepareOrderCommand(
            paymentId = payload.paymentId,
            totalPrice = payload.totalPrice,
            street = payload.street,
            houseNumber = payload.houseNumber,
            zipCode = payload.zipCode,
            city = payload.city,
            name = payload.name,
            surname = payload.surname,
            orderedProducts = payload.orderedProducts,
            customerId = payload.customerId,
            orderId = payload.orderId))
  }
}
