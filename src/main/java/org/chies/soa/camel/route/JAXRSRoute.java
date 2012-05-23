package org.chies.soa.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.chies.soa.camel.processor.SleeperProcessor;
import org.chies.soa.jaxrs.JAXRSService;

public class JAXRSRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("cxfrs:/?resourceClasses=" + JAXRSService.class.getName())
		.process(new SleeperProcessor())
		.to("log:org.chies?level=INFO");
	}

}
