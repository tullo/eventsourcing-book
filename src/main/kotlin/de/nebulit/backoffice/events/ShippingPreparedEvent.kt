package de.nebulit.backoffice.events

import de.nebulit.backoffice.common.Event
import java.util.UUID

/*
Boardlink:
*/
data class ShippingPreparedEvent(var orderId: UUID) : Event
