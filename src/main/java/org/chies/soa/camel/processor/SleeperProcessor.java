package org.chies.soa.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SleeperProcessor implements Processor{

	public void process(Exchange exchange) throws Exception {
		Thread.sleep(10000);
	}

}
