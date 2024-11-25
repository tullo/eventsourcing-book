package de.nebulit.backoffice.domain.commands.shiporder

import de.nebulit.backoffice.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606919031406
*/
data class ShipOrderCommand(
    var deliveryTrackingId: String,
    @TargetAggregateIdentifier var orderId: UUID
) : Command