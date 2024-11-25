package de.nebulit.backoffice.domain.commands.assignclerk

import de.nebulit.backoffice.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764608122771892
*/
data class AssignClerkCommand(var clerkId: String, @TargetAggregateIdentifier var orderId: UUID) :
    Command
