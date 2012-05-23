package org.chies.soa.main;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.ws.addressing.AddressingBuilder;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.chies.soa.jaxws.JAXWSService;
import org.chies.soa.model.Person;


/* This class tests the JAX-WS Asynchronous Service using @OneWay. 
 * Service will return a 202 (accepted) response.
 * Service has a Thread.sleep(10000) to prove the asynchrony.
 */
public class JAXWSMain {

	private static final String REPLY_TO_URL = "http://replyTourl";

	public static void main(String[] args) {
		JAXWSServiceClient client = new JAXWSServiceClient();
		JAXWSService service = client.getJAXWSServicePort();
		insertWSAOnService(service);
		Person person = createPerson();
		service.dummy(person);
		System.out.println("Client call has finished in async way. Server is processing and will reply to: " + REPLY_TO_URL );
	}

	private static Person createPerson() {
		Person person = new Person();
		person.setName("Rafael Chies");
		person.setEmail("rafachies@gmail.com");
		person.setDocument(999999999L);
		return person;
	}

	private static void insertWSAOnService(JAXWSService service) {
		AddressingBuilder addressingBuilder = AddressingBuilder.getAddressingBuilder();
		AddressingProperties addressingProperties = addressingBuilder.newAddressingProperties();
		AttributedURIType replyTo = new AttributedURIType();
		replyTo.setValue(REPLY_TO_URL);
		EndpointReferenceType replyToRef = new EndpointReferenceType();
		replyToRef.setAddress(replyTo);
		addressingProperties.setReplyTo(replyToRef);
		Map<String, Object> requestContext = ((BindingProvider) service).getRequestContext();
		requestContext.put(JAXWSAConstants.NS_WSA, addressingProperties);
	}
}
