package com.vitorio.examples.springboot.camelmicroservice.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.vitorio.examples.springboot.camelmicroservice.models.Item;

//@Component
public class TimerToHttpGetRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("timer:first-timer?period=2s")
		    .setHeader(Exchange.HTTP_METHOD, HttpMethods.GET)
		    .to("http://localhost:8000/item/fetch")
		    .unmarshal().json(JsonLibrary.Jackson, Item.class)
		    .process(exchange -> {
		    	log.info("The response code is: {}", exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
		    	Item fetchedItem = (Item) exchange.getIn().getBody();    	
		    	log.info("The returned Item is: " + fetchedItem);
	    	});
	}
}
