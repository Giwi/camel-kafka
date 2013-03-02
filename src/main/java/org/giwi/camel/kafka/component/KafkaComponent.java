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
package org.giwi.camel.kafka.component;

import java.net.URI;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.apache.camel.util.URISupport;

/**
 * @author Giwi Softwares
 * 
 */
public class KafkaComponent extends DefaultComponent {

	/**
	 * header name for the topic
	 */
	public static String TOPIC_NAME = "topicNameHeader";
	private Map<String, Object> parameters;

	public KafkaComponent() {
	}

	public KafkaComponent(final CamelContext context) {
		super(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.impl.DefaultComponent#createEndpoint(java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	protected Endpoint createEndpoint(final String addressUri, final String remaining, final Map<String, Object> parameters) throws Exception {
		final URI endpointUri = URISupport.createRemainingURI(new URI(addressUri), parameters);
		final Endpoint endpoint = new KafkaEndpoint(addressUri, this, endpointUri);
		setProperties(endpoint, parameters);
		setParameters(parameters);
		return endpoint;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(final Map<String, Object> parameters) {
		this.parameters = parameters;
	}

}
