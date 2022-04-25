package com.vitorio.examples.springboot.camelmicroservice.routes;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.vitorio.examples.springboot.camelmicroservice.models.Order;

//@Component
public class XMLToJSONXPathBasedFilterRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		boolean dontEraseInputFiles = true;
	    boolean prettyPrintOutputFile = true;

	    String inputDir = "input_xmls";
	    String outputDir = "output_files";
	    
	    // For this to work we must make sure the Order class also has @XmlRootElement annotated
	    // since after the filtering it becomes the root element
	    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(JAXBContext.newInstance(Order.class));

		from("file:" + inputDir + "?delay=5s&noop=" + dontEraseInputFiles)
			.split()
				.xpath("/order/items/item")
			.filter()
				.xpath("/item/format[text()='EBOOK']") // Only EBOOK items will be considered, and they will be the new root element
			.log("-------------------------------------------------------------------------------------")
			.log("Input file ID: ${id}")
			.log("Input file name: ${file:name}")
			.log("Input file body:\n${body}")
			.unmarshal(jaxbDataFormat)
			.setHeader(Exchange.FILE_NAME, simple("${file:name.noext}-${header.CamelSplitIndex}.json"))
			.marshal()
				.json(JsonLibrary.Jackson, prettyPrintOutputFile)
			.log("Output file name: ${file:name}")
			.log("Output file body:\n${body}")
			.to("file:" + outputDir);
	}

}
