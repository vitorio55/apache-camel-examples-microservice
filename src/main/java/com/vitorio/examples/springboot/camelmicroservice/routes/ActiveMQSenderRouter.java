package com.vitorio.examples.springboot.camelmicroservice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQSenderRouter extends RouteBuilder {

	private Logger logger = LoggerFactory.getLogger(ActiveMQSenderRouter.class);

	@Override
	public void configure() throws Exception {
		logger.info("Initiating ActiveMQSenderRouter");
		
		// Docker container used for ActiveMQ: rmohr/activemq

		from("timer:active-mq-timer?period=5000")
			.transform().constant("My message for Active MQ")
			.log("Sending ActiveMQ message with body: ${body}")
		.to("activemq:my-activemq-queue");
	}
}
