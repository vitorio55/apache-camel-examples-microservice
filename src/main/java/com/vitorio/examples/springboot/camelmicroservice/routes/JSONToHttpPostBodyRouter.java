package com.vitorio.examples.springboot.camelmicroservice.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vitorio.examples.springboot.camelmicroservice.models.Item;

//@Component
public class JSONToHttpPostBodyRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		boolean dontEraseInputFiles = true;
	    
	    String inputDir = "output_files"; // See XMLToJSON router (it outputs JSON files)    

		from("file:" + inputDir + "?delay=5s&noop=" + dontEraseInputFiles)
			.log("-------------------------------------------------------------------------------------")
			.log("Input file ID: ${id}")
			.log("Input file name: ${file:name}")
			.log("Input file body:\n${body}")
		    .setHeader(Exchange.HTTP_METHOD, HttpMethods.POST)
		    .setHeader(Exchange.CONTENT_TYPE, constant("application/json;charset=UTF-8"))
		    .to("http://localhost:8000/item/receive")
		    .process(exchange -> log.info("The response code is: {}", exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE)));
	}
}
