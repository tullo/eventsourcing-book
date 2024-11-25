package de.nebulit.backoffice.events

import de.nebulit.backoffice.common.Event
import java.util.UUID

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764608122865417
*/
data class ClerkAssignedEvent(var clerkId: String, var orderId: UUID) : Event
