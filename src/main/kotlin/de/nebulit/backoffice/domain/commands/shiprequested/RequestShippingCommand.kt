package de.nebulit.backoffice.domain.commands.shiprequested

import de.nebulit.backoffice.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink:
*/
data class RequestShippingCommand(
    var deliveryTrackingId: String,
    @TargetAggregateIdentifier var orderId: UUID
) : Command
