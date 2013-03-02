/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright
 * ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under the License.
 */
package org.giwi.camel.kafka.test;

import java.util.ArrayList;
import java.util.List;

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
public class MultipleStringMessageTest extends CamelTestSupport {
	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce(uri = "direct:start")
	protected ProducerTemplate template;

	@Test
	public void test() throws Exception {

		List<String> expectedBody = new ArrayList<String>();

		expectedBody.add("Hello from Giwi Softwares");
		expectedBody.add("Hello from Giwi Softwares");
		expectedBody.add("Hello from Giwi Softwares");

		template.sendBody(expectedBody);
		resultEndpoint.assertIsSatisfied();
		resultEndpoint.expectedMessageCount(3);
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
				from("direct:start").to("kafka:TOPIC-TEST-MULT-STRING?zkConnect=localhost:2181");

				// Recieving
				from("kafka:TOPIC-TEST-MULT-STRING?groupId=camelTest&zkConnect=localhost:2181").to("mock:result");
			}
		};
	}

}
