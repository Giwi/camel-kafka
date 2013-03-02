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

import kafka.consumer.KafkaStream;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;

import org.apache.camel.Exchange;
import org.giwi.camel.kafka.helpers.BinaryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giwi Softwares
 * 
 */
public class Klistener implements Runnable {

	private KafkaStream<Message> stream;
	private KafkaEndpoint endpoint;
	private KafkaConsumer consumer;
	private static final Logger LOG = LoggerFactory.getLogger(Klistener.class);

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (final MessageAndMetadata<Message> message : stream) {
			final Exchange exchange = endpoint.createExchange();
			final byte[] bytes = new byte[message.message().payload().remaining()];
			message.message().payload().get(bytes);
			try {
				final Object evt = BinaryHelper.getInstance().getObject(bytes);
				exchange.getIn().setBody(evt);
				if (LOG.isInfoEnabled()) {
					LOG.info("Kafka Consumer Message recieved : " + evt);
				}
				consumer.getProcessor().process(exchange);
			} catch (final Exception e) {
				exchange.setException(e);
				LOG.error(e.getMessage(), e);
			} finally {
				if (exchange.getException() != null) {
					consumer.getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
				}
			}
		}

	}

	/**
	 * @return the stream
	 */
	public final KafkaStream<Message> getStream() {
		return stream;
	}

	/**
	 * @param stream
	 *            the stream to set
	 */
	public final void setStream(final KafkaStream<Message> stream) {
		this.stream = stream;
	}

	/**
	 * @return the consumer
	 */
	public final KafkaConsumer getConsumer() {
		return consumer;
	}

	/**
	 * @param consumer
	 *            the consumer to set
	 */
	public final void setConsumer(final KafkaConsumer consumer) {
		this.consumer = consumer;
	}

	/**
	 * @return the endpoint
	 */
	public final KafkaEndpoint getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint
	 *            the endpoint to set
	 */
	public final void setEndpoint(final KafkaEndpoint endpoint) {
		this.endpoint = endpoint;
	}

}
