package de.nebulit.backoffice.domain.commands.command

import de.nebulit.backoffice.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764608106452194
*/
data class BlockCustomerCommand(@TargetAggregateIdentifier var customerId: UUID) : Command
