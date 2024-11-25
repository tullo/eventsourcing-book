package de.nebulit.backoffice.domain.commands.cancelorder

import de.nebulit.backoffice.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606960617352
*/
data class CancelOrderCommand(@TargetAggregateIdentifier var orderId: UUID) : Command
