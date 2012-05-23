package org.chies.soa.camel.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.chies.soa.model.Person;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BodyTransformProcessor implements Processor {

	private static final String CAMEL_CXF_HEADER = "camelcxfmessage";
	private static final String WSA_HEADER = "javax.xml.ws.addressing.context.inbound";
	
	public void process(Exchange exchange) throws Exception {
		JsonObject jsonObject = getBodyAsJson(exchange);
		enrichWithWSA(jsonObject, exchange);
		transformBody(exchange, jsonObject);
	}

	private void transformBody(Exchange exchange, JsonObject jsonObject) {
		exchange.getIn().setBody(jsonObject.toString());
	}

	private JsonObject getBodyAsJson(Exchange exchange) {
		MessageContentsList messageContentsList = (MessageContentsList) exchange.getIn().getBody();
		Person person = (Person) messageContentsList.get(0);
		Gson gson = new Gson();
		String jsonString = gson.toJson(person);
		JsonObject jsonObject = (JsonObject)new JsonParser().parse(jsonString);
		return jsonObject;
	}

	private void enrichWithWSA(JsonObject jsonObject, Exchange exchange) {
		Map<String, Object> headers = exchange.getIn().getHeaders();
		SoapMessage soapMessage = (SoapMessage) headers.get(CAMEL_CXF_HEADER);
		AddressingProperties addressingProperties = (AddressingProperties) soapMessage.get(WSA_HEADER);
		jsonObject.addProperty("replyto", addressingProperties.getReplyTo().getAddress().getValue());
		jsonObject.addProperty("messageid", addressingProperties.getMessageID().getValue());
	}

}
