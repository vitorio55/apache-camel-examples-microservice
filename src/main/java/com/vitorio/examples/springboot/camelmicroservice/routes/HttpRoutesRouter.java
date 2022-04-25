package com.vitorio.examples.springboot.camelmicroservice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

//@Component
public class HttpRoutesRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
	    restConfiguration()
	      .host("localhost").port(8084)
	      .bindingMode(RestBindingMode.auto);

	    rest("/say")
	        .get("/hello").to("direct:hello")
	        .get("/bye").consumes("application/json").to("direct:bye")
	        .post("/bye").to("mock:update");
	
	    from("direct:hello")
	        .transform().constant("Hello World");
	
	    from("direct:bye")
	        .transform().constant("Bye World");
	}
}
