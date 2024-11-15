package de.nebulit.backoffice.domain.commands.cancelorder

import de.nebulit.backoffice.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink:
*/
data class CancelOrderCommand(@TargetAggregateIdentifier var orderId: UUID) : Command
