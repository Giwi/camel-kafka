package org.giwi.camel.kafka.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.javaapi.producer.ProducerData;
import kafka.message.Message;
import kafka.producer.ProducerConfig;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.giwi.camel.kafka.helpers.BinaryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giwi Softwares
 * 
 */
public class KafkaProducer extends DefaultProducer {

	private final KafkaEndpoint endpoint;
	private final Producer<String, Message> producer;
	private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

	/**
	 * @param endpoint
	 */
	public KafkaProducer(final KafkaEndpoint endpoint) {
		super(endpoint);
		this.endpoint = endpoint;
		final Properties props = new Properties();
		props.put("zk.connect", ((KafkaComponent) endpoint.getComponent()).getZkConnect());

		// TODO: Accept all kafka parameters
		// final Map<String, Object> params = ((KafkaComponent) endpoint.getComponent()).getParameters();

		final ProducerConfig config = new ProducerConfig(props);
		producer = new kafka.javaapi.producer.Producer<String, Message>(config);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.impl.DefaultConsumer#doStart()
	 */
	@Override
	protected void doStart() throws Exception {
		super.doStart();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Kafka Producer Component started");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.impl.DefaultProducer#doStop()
	 */
	@Override
	protected void doStop() throws Exception {
		super.doStop();
		producer.close();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Kafka Producer Component stoped");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@SuppressWarnings("unchecked")
	public void process(final Exchange exchange) throws Exception {
		String topicName;
		if (exchange.getIn().getHeaders().containsKey(KafkaComponent.TOPIC_NAME)) {
			topicName = exchange.getIn().getHeader(KafkaComponent.TOPIC_NAME, String.class);
		} else {
			topicName = endpoint.getTopicName();
		}
		final Object evt = exchange.getIn().getBody();
		if (evt != null) {
			final ProducerData<String, Message> data = new ProducerData<String, Message>(topicName, new Message(BinaryHelper.getInstance().getBytes(evt)));
			producer.send(data);
			if (LOG.isDebugEnabled()) {
				LOG.debug("Kafka Producer send : " + evt);
			}
		} else {
			final List<Object> evts = exchange.getIn().getBody(List.class);
			if (evts != null) {
				final List<ProducerData<String, Message>> datas = new ArrayList<ProducerData<String, Message>>();
				for (final Object obj : evts) {
					final ProducerData<String, Message> data = new ProducerData<String, Message>(topicName, new Message(BinaryHelper.getInstance().getBytes(obj)));
					datas.add(data);
				}
				producer.send(datas);
				if (LOG.isDebugEnabled()) {
					LOG.debug("Kafka Producer multiple send : " + evts);
				}
			}
		}
	}
}
