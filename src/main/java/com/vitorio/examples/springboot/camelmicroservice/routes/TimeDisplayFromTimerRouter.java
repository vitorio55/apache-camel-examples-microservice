package com.vitorio.examples.springboot.camelmicroservice.routes;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeDisplayFromTimerRouter extends RouteBuilder {

	@Autowired
	private GetCurrentTimeBean getCurrentTimeBean;
	
	@Autowired
	private SimpleLoggingProcessingBean simpleLoggingProcessingBean;

	@Override
	public void configure() throws Exception {
		from("timer:time-display")
			.routeId("time-display")
			.bean(getCurrentTimeBean)
			.bean(simpleLoggingProcessingBean)
			// Equivalent to the above, but using a processor
			 .process(new SimpleLoggingProcessor())
			.log("${body}") // Default way of logging
		.to("log:time-display");
	}

}

@Component
class GetCurrentTimeBean {
	public String getCurrentTime() {
		return "<GetCurrentTimeBean> Time now is: " + LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProcessingBean {

	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingBean.class);
	
	public void process(String message) {
		logger.info("<SimpleLoggingProcessingBean> {}", message);
	}
}

class SimpleLoggingProcessor implements Processor {

	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("<SimpleLoggingProcessor> body: {}", exchange.getMessage().getBody());
	}

}
