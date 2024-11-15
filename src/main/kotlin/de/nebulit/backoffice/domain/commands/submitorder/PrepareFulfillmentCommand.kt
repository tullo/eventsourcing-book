package de.nebulit.backoffice.domain.commands.submitorder

import de.nebulit.backoffice.common.Command
import java.util.UUID

/*
Boardlink:
*/
data class PrepareFulfillmentCommand(
    var orderId: UUID,
    var paymentId: UUID,
    var totalPrice: Double,
    var street: String,
    var houseNumber: Int,
    var zipCode: String,
    var city: String,
    var name: String,
    var surname: String,
    var orderedProducts: List<UUID>
) : Command
