package com.vitorio.examples.springboot.camelmicroservice.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

//@Component
public class HttpForwardingRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
	    restConfiguration()
	      .host("localhost").port(8084)
	      .bindingMode(RestBindingMode.auto);

	    from("undertow:http://localhost:8086/myapp/myservice")
		    .removeHeaders("CamelHttp*")
		    .setHeader(Exchange.HTTP_METHOD, HttpMethods.GET)
		    .setHeader(Exchange.HTTP_QUERY, simple("secret=S3cr3t-P455w0rD"))
	    .to("http://localhost:8000/item/fetch-secret-item");
	}
}
