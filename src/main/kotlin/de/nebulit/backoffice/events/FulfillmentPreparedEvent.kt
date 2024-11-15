package de.nebulit.backoffice.events

import de.nebulit.backoffice.common.Event
import java.util.UUID

/*
Boardlink:
*/
data class FulfillmentPreparedEvent(
    var city: String,
    var houseNumber: Int,
    var paymentId: UUID,
    var street: String,
    var totalPrice: Double,
    var zipCode: String,
    var name: String,
    var surname: String,
    var orderedProducts: List<UUID>,
    var orderId: UUID
) : Event
