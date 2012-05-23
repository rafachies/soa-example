package org.chies.soa.main;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/* This class tests the JAX-RS Asynchronous Service using @OneWay from cxf. 
 * Service will return a 202 (accepted) response.
 * Service has a Thread.sleep(10000) to prove the asynchrony.
 */
public class JAXRSMain {

	private static final String REPLY_TO_URL = "http://otherapp/service";
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		String json = "{\"name\":\"Rafael Chies\",\"email\":\"rafachies@gmail.com\",\"document\":999999999,\"replyto\":\"http://otherapp/service\",\"messageid\":\"messageId\"}";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter("content-type", "application/json");
		PostMethod postMethod = new PostMethod("http://localhost:8080/soa-example/webservices/jaxrsservice");
		postMethod.setRequestBody(json);
		httpClient.executeMethod(postMethod);
		System.out.println("Client call has finished in async way. Server is processing and will reply to: " + REPLY_TO_URL );
	}
}
