package de.nebulit.backoffice.domain.commands.submitorder

import de.nebulit.backoffice.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606916731730
*/
data class PrepareOrderCommand(
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
    @TargetAggregateIdentifier var orderId: UUID
) : Command
