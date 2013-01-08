package org.giwi.camel.kafka.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * @author Giwi Softwares
 * 
 */
public class SingleStringMessageTest extends CamelTestSupport {

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce(uri = "direct:start")
	protected ProducerTemplate template;

	/**
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		String expectedBody = "Hello from Giwi Softwares";
		template.sendBody(expectedBody);
		resultEndpoint.assertIsSatisfied();
		resultEndpoint.expectedBodiesReceived(expectedBody);
		resultEndpoint.expectedMessageCount(1);
		Thread.sleep(250);
		assertMockEndpointsSatisfied();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.test.junit4.CamelTestSupport#createRouteBuilder()
	 */
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start").to("kafka:TOPIC-TEST-SINGLE-STRING?zkConnect=localhost:2181");

				// Recieving
				from("kafka:TOPIC-TEST-SINGLE-STRING?groupId=camelTest&zkConnect=localhost:2181").to("mock:result");
			}
		};
	}
}
