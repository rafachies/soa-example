package org.chies.soa.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.Oneway;

@Path("/jaxrsservice")
public class JAXRSService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Oneway
	public void dummy(String json){}
	
}
