package de.eventsourcingbook.cart.domain.commands.archiveitem

import de.eventsourcingbook.cart.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.UUID

data class ArchiveItemCommand(
    @TargetAggregateIdentifier override var aggregateId: UUID,
    var productId: UUID,
) : Command
