package de.nebulit.backoffice.domain.commands.prepareshipping

import de.nebulit.backoffice.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764606918335813
*/
data class PrepareShippingCommand(@TargetAggregateIdentifier var orderId: UUID) : Command
