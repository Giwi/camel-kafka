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
	private String zkConnect;

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
	 * @return the zkConnect
	 */
	public String getZkConnect() {
		return zkConnect;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(final Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @param zkConnect
	 *            the zkConnect to set
	 */
	public void setZkConnect(final String zkConnect) {
		this.zkConnect = zkConnect;
	}
}
