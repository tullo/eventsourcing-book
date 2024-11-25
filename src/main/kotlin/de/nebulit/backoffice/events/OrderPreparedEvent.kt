package de.nebulit.backoffice.events

import de.nebulit.backoffice.common.Event
import java.util.UUID

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606917198984
*/
data class OrderPreparedEvent(
    var city: String,
    var houseNumber: Int,
    var paymentId: UUID,
    var street: String,
    var totalPrice: Double,
    var zipCode: String,
    var name: String,
    var surname: String,
    var orderedProducts: List<UUID>,
    var orderId: UUID,
    var customerId: UUID
) : Event
