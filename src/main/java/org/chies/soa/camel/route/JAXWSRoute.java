package org.chies.soa.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.chies.soa.camel.processor.BodyTransformProcessor;
import org.chies.soa.camel.processor.SleeperProcessor;
import org.chies.soa.jaxws.JAXWSService;

public class JAXWSRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("cxf:/jaxwsservice?serviceClass=" + JAXWSService.class.getName())
		.process(new BodyTransformProcessor())
		.process(new SleeperProcessor())
		.to("log:org.chies?level=INFO");
	}

}
