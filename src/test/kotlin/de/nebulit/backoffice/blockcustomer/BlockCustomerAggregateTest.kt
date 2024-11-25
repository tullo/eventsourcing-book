package de.nebulit.backoffice.blockcustomer

import de.nebulit.backoffice.common.Event
import de.nebulit.backoffice.common.support.RandomData
import de.nebulit.backoffice.domain.CustomerAggregate
import de.nebulit.backoffice.domain.commands.command.BlockCustomerCommand
import de.nebulit.backoffice.events.CustomerBlockedEvent
import de.nebulit.backoffice.security.SecuredMethodMessageHandlerDefinition
import java.util.UUID
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.core.authority.SimpleGrantedAuthority

/**
 * comment
 *
 * Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764608285938426
 */
class BlockCustomerAggregateTest {
  private lateinit var fixture: FixtureConfiguration<CustomerAggregate>

  @BeforeEach
  fun setUp() {
    fixture =
        AggregateTestFixture(CustomerAggregate::class.java)
            .registerHandlerEnhancerDefinition(SecuredMethodMessageHandlerDefinition())
  }

  @Test
  fun `Command Aggregate Test`() {
    var customerId: UUID = RandomData.newInstance<UUID> {}

    // GIVEN
    val events = mutableListOf<Event>()

    // WHEN
    val command =
        BlockCustomerCommand(
            customerId = customerId,
        )

    // THEN
    val expectedEvents = mutableListOf<Event>()

    expectedEvents.add(
        RandomData.newInstance<CustomerBlockedEvent> { this.customerId = command.customerId },
    )

    fixture
        .given(events)
        .`when`(
            command, mutableMapOf("authorities" to listOf(SimpleGrantedAuthority("ROLE_ADMIN"))))
        .expectSuccessfulHandlerExecution()
        .expectEvents(*expectedEvents.toTypedArray())
  }
}
