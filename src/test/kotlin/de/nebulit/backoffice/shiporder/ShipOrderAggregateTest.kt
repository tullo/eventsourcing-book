package de.nebulit.backoffice.shiporder

import de.nebulit.backoffice.common.Event
import de.nebulit.backoffice.common.support.RandomData
import de.nebulit.backoffice.domain.BackofficeAggregate
import de.nebulit.backoffice.domain.commands.shiporder.ShipOrderCommand
import de.nebulit.backoffice.events.OrderShippedEvent
import de.nebulit.backoffice.events.ShippingPreparedEvent
import de.nebulit.backoffice.events.ShippingRequestedEvent
import java.util.UUID
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * comment
 *
 * Boardlink:
 */
class ShipOrderAggregateTest {

  private lateinit var fixture: FixtureConfiguration<BackofficeAggregate>

  @BeforeEach
  fun setUp() {
    fixture = AggregateTestFixture(BackofficeAggregate::class.java)
  }

  @Test
  fun `Ship Order Aggregate Test`() {

    var orderId: UUID = RandomData.newInstance<UUID> {}

    // GIVEN
    val events = mutableListOf<Event>()

    events.add(RandomData.newInstance<ShippingPreparedEvent> { this.orderId = orderId })
    events.add(
        RandomData.newInstance<ShippingRequestedEvent> {
          deliveryTrackingId = RandomData.newInstance {}
          this.orderId = orderId
        })

    // WHEN
    val command =
        ShipOrderCommand(deliveryTrackingId = RandomData.newInstance {}, orderId = orderId)

    // THEN
    val expectedEvents = mutableListOf<Event>()

    expectedEvents.add(
        RandomData.newInstance<OrderShippedEvent> {
          this.deliveryTrackingId = command.deliveryTrackingId
          this.orderId = command.orderId
        })

    fixture
        .given(events)
        .`when`(command)
        .expectSuccessfulHandlerExecution()
        .expectEvents(*expectedEvents.toTypedArray())
  }
}
