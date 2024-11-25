package de.nebulit.backoffice.domain

import de.nebulit.backoffice.domain.commands.command.BlockCustomerCommand
import de.nebulit.backoffice.events.CustomerBlockedEvent
import java.util.UUID
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.security.access.annotation.Secured

@Aggregate
class CustomerAggregate {
  @AggregateIdentifier var aggregateId: UUID? = null

  @Secured("ROLE_ADMIN")
  @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(command: BlockCustomerCommand) {
    AggregateLifecycle.apply(CustomerBlockedEvent(customerId = command.customerId))
  }

  @EventSourcingHandler
  fun on(event: CustomerBlockedEvent) {
    // handle event
    this.aggregateId = event.customerId
  }
}
