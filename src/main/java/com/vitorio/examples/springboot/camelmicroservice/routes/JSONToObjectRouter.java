package com.vitorio.examples.springboot.camelmicroservice.routes;

import org.apache.camel.Body;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.vitorio.examples.springboot.camelmicroservice.models.Item;

//@Component
public class JSONToObjectRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		boolean dontEraseInputFiles = true;

	    String inputDir = "output_files"; // See XMLToJSON router (it outputs JSON files)    

		from("file:" + inputDir + "?delay=5s&noop=" + dontEraseInputFiles)
			.log("-------------------------------------------------------------------------------------")
			.log("Input file ID: ${id}")
			.log("Input file name: ${file:name}")
			.log("Input file body:\n${body}")
			.unmarshal().json(JsonLibrary.Jackson, Item.class)
			.bean(ItemHelper.class, "print");
	}
}

class ItemHelper {

	public void print (@Body Item item) {
		System.out.println("Helper class printing item from JSON --------------");
		System.out.println(item);
	}
}
