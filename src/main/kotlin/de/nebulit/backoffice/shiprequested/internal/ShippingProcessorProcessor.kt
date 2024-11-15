package de.nebulit.backoffice.shiprequested.internal

import de.nebulit.backoffice.common.Processor
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/*
Boardlink:
*/
@Component
class ShippingProcessorProcessor : Processor {
  var logger = KotlinLogging.logger {}

  @Autowired lateinit var commandGateway: CommandGateway
  @Autowired lateinit var queryGateway: QueryGateway
}
