package de.nebulit.backoffice.events

import de.nebulit.backoffice.common.Event
import java.util.UUID

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606917199903
*/
data class OrderShippedEvent(var deliveryTrackingId: String, var orderId: UUID) : Event
