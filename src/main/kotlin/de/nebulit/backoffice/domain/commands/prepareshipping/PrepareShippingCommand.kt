package de.nebulit.backoffice.domain.commands.prepareshipping

import de.nebulit.backoffice.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink:
*/
data class PrepareShippingCommand(@TargetAggregateIdentifier var orderId: UUID) : Command
