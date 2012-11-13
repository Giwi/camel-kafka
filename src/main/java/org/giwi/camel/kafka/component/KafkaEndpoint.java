package org.giwi.camel.kafka.component;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultPollingEndpoint;

/**
 * @author Giwi Softwares
 * 
 */
/**
 * @author xavier
 * 
 */
public class KafkaEndpoint extends DefaultPollingEndpoint {
	private String groupId;
	private String topicName;
	private String zkConnect;
	private int concurrentConsumers = 1;

	/**
	 * @param uri
	 *            uri of the component
	 * @param component
	 *            THE comonent
	 */
	public KafkaEndpoint(final String endPointURI, final KafkaComponent component, final URI httpURI) throws URISyntaxException {
		super(endPointURI, component);
		topicName = httpURI.getHost();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.Endpoint#createConsumer(org.apache.camel.Processor)
	 */
	@Override
	public Consumer createConsumer(final Processor processor) throws Exception {
		return new KafkaConsumer(this, processor);
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @return the topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(final String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @param topicName
	 *            the topicName to set
	 */
	public void setTopicName(final String topicName) {
		this.topicName = topicName;
	}

	/**
	 * @return the concurrentConsumers
	 */
	public int getConcurrentConsumers() {
		return concurrentConsumers;
	}

	/**
	 * @param concurrentConsumers
	 *            the concurrentConsumers to set
	 */
	public final void setConcurrentConsumers(final int concurrentConsumers) {
		this.concurrentConsumers = concurrentConsumers;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.Endpoint#createProducer()
	 */
	public Producer createProducer() throws Exception {
		return new KafkaProducer(this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.IsSingleton#isSingleton()
	 */
	public boolean isSingleton() {
		return true;
	}

	public String getZkConnect() {
		return zkConnect;
	}

	public void setZkConnect(String zkConnect) {
		this.zkConnect = zkConnect;
	}

}
