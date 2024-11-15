package de.nebulit.backoffice.prepareshipping

import de.nebulit.backoffice.common.Event
import de.nebulit.backoffice.common.support.RandomData
import de.nebulit.backoffice.domain.BackofficeAggregate
import de.nebulit.backoffice.domain.commands.prepareshipping.PrepareShippingCommand
import de.nebulit.backoffice.events.FulfillmentPreparedEvent
import de.nebulit.backoffice.events.ShippingPreparedEvent
import java.util.UUID
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Boardlink: */
class PrepareShippingTest {

  private lateinit var fixture: FixtureConfiguration<BackofficeAggregate>

  @BeforeEach
  fun setUp() {
    fixture = AggregateTestFixture(BackofficeAggregate::class.java)
  }

  @Test
  fun `Prepare Shipping Test`() {

    var orderId: UUID = RandomData.newInstance<UUID> {}

    // GIVEN
    val events = mutableListOf<Event>()

    events.add(
        RandomData.newInstance<FulfillmentPreparedEvent> {
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
        })

    // WHEN
    val command = PrepareShippingCommand(orderId = orderId)

    // THEN
    val expectedEvents = mutableListOf<Event>()

    expectedEvents.add(
        RandomData.newInstance<ShippingPreparedEvent> { this.orderId = command.orderId })

    fixture
        .given(events)
        .`when`(command)
        .expectSuccessfulHandlerExecution()
        .expectEvents(*expectedEvents.toTypedArray())
  }
}
