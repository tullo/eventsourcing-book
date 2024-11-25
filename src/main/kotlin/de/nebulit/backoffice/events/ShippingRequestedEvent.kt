package de.nebulit.backoffice.events

import de.nebulit.backoffice.common.Event
import java.util.UUID

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606918579343
*/
data class ShippingRequestedEvent(var deliveryTrackingId: String, var orderId: UUID) : Event
