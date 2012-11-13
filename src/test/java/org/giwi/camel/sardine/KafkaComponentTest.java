package org.giwi.camel.sardine;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class KafkaComponentTest extends CamelTestSupport {

	@Test
	public void testKafka() throws Exception {
		MockEndpoint mock = getMockEndpoint("mock:result");
		mock.expectedMinimumMessageCount(1);

		assertMockEndpointsSatisfied();
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() {
				from("timer://foo?fixedRate=true&period=5000").setBody(constant("hello from Giwi Softwares")).to("kafka:TOPIC-TEST?zkConnect=localhost:2181");

				// Recieving
				from("kafka:TOPIC-TEST?groupId=camelTest&zkConnect=localhost:2181").log("${body}");
			}
		};
	}
}
