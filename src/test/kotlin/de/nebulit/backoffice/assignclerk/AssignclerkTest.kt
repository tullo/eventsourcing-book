package de.nebulit.backoffice.assignclerk

import de.nebulit.backoffice.common.Event
import de.nebulit.backoffice.common.support.RandomData
import de.nebulit.backoffice.domain.BackofficeAggregate
import de.nebulit.backoffice.domain.commands.assignclerk.AssignClerkCommand
import de.nebulit.backoffice.events.ClerkAssignedEvent
import de.nebulit.backoffice.events.OrderPreparedEvent
import de.nebulit.backoffice.security.SecuredMethodMessageHandlerDefinition
import java.util.UUID
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.core.authority.SimpleGrantedAuthority

/** Boardlink: https://miro.com/app/board/uXjVLGjbeRk=/?moveToWidget=3458764608273296821 */
class AssignclerkTest {
  private lateinit var fixture: FixtureConfiguration<BackofficeAggregate>

  @BeforeEach
  fun setUp() {
    fixture =
        AggregateTestFixture(BackofficeAggregate::class.java)
            .registerHandlerEnhancerDefinition(SecuredMethodMessageHandlerDefinition())
  }

  @Test
  fun `Assignclerk Test`() {
    var orderId: UUID = RandomData.newInstance<UUID> {}

    // GIVEN
    val events = mutableListOf<Event>()

    events.add(
        RandomData.newInstance<OrderPreparedEvent> {
          city = RandomData.newInstance {}
          houseNumber = RandomData.newInstance {}
          paymentId = RandomData.newInstance {}
          street = RandomData.newInstance {}
          totalPrice = RandomData.newInstance {}
          zipCode = RandomData.newInstance {}
          name = RandomData.newInstance {}
          surname = RandomData.newInstance {}
          orderedProducts = RandomData.newInstance {}
          this.orderId = orderId
          customerId = RandomData.newInstance {}
        },
    )

    // WHEN
    val command =
        AssignClerkCommand(
            clerkId = RandomData.newInstance {},
            orderId = orderId,
        )

    // THEN
    val expectedEvents = mutableListOf<Event>()

    expectedEvents.add(
        RandomData.newInstance<ClerkAssignedEvent> {
          this.clerkId = command.clerkId
          this.orderId = command.orderId
        },
    )

    fixture
        .given(events)
        .`when`(
            command, mutableMapOf("authorities" to listOf(SimpleGrantedAuthority("ROLE_CLERK"))))
        .expectSuccessfulHandlerExecution()
        .expectEvents(*expectedEvents.toTypedArray())
  }
}
