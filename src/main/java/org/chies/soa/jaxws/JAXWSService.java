package org.chies.soa.jaxws;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.soap.Addressing;

import org.chies.soa.model.Person;

@WebService(targetNamespace = "org.chies.soa.jaxwsservice", serviceName = "JAXWSService")
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use=SOAPBinding.Use.LITERAL, parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
@Addressing(enabled = true, required = true)
public interface JAXWSService {

	@Oneway
	@WebMethod
	public void dummy(Person person);
	
}
