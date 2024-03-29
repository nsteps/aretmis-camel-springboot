package ru.step.artemismqcamel.routing

import org.apache.camel.LoggingLevel
import org.apache.camel.builder.RouteBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Routes: RouteBuilder() {
    val logger = LoggerFactory.getLogger(javaClass)

    @Value("\${app.queue.receive}")
    val receiveQueue: String = ""

    @Value("\${app.queue.produce}")
    val produceQueue: String = ""

    override fun configure() {
        from("jms:$receiveQueue")
                .log(LoggingLevel.INFO, logger,"Msg received \${body}")
                .to("jms:$produceQueue")
    }
}
