package de.nebulit.backoffice.domain.commands.shiprequested

import de.nebulit.backoffice.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606918579539
*/
data class RequestShippingCommand(
    var deliveryTrackingId: String,
    @TargetAggregateIdentifier var orderId: UUID
) : Command
