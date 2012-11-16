package org.giwi.camel.kafka.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;

import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giwi Softwares
 * 
 */
public class KafkaConsumer extends DefaultConsumer {
	private ExecutorService executor;
	private final List<KafkaStream<Message>> streams;
	private final KafkaEndpoint endpoint;
	private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

	/**
	 * @param endpoint
	 * @param processor
	 */
	public KafkaConsumer(final KafkaEndpoint endpoint, final Processor processor) {
		super(endpoint, processor);
		this.endpoint = endpoint;

		final String topic = endpoint.getTopicName();
		final Properties props = new Properties();
		props.put("zk.connect", endpoint.getZkConnect());
		props.put("groupid", endpoint.getGroupId());
		props.put("socket.timeout.ms", endpoint.getSocketTimeoutMs());
		props.put("socket.buffersize", endpoint.getSocketBuffersize());
		props.put("fetch.size", endpoint.getFetchSize());
		props.put("backoff.increment.ms", endpoint.getBackoffIncrementMs());
		props.put("queuedchunks.max", endpoint.getQueuedchunksMax());
		props.put("autocommit.enable", endpoint.isAutocommitEnable());
		props.put("autocommit.interval.ms", endpoint.getAutocommitIntervalMs());
		props.put("autooffset.reset", endpoint.getAutooffsetReset());
		props.put("consumer.timeout.ms", endpoint.getConsumerTimeoutMs());
		props.put("rebalance.retries.max", endpoint.getRebalanceRetriesMax());
		props.put("mirror.topics.whitelist", endpoint.getMirrorTopicsWhitelist());
		props.put("mirror.topics.blacklist", endpoint.getMirrorTopicsBlacklist());
		props.put("mirror.consumer.numthreads", endpoint.getMirrorConsumerNumthreads());

		final ConsumerConfig config = new ConsumerConfig(props);
		final ConsumerConnector connector = Consumer.createJavaConsumerConnector(config);

		final Map<String, Integer> topicmap = new HashMap<String, Integer>() {
			private static final long serialVersionUID = 1L;
			{
				put(topic, endpoint.getConcurrentConsumers());
			}
		};
		streams = connector.createMessageStreams(topicmap).get(topic);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Kafka Consumer Component initialized");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.impl.DefaultConsumer#doStart()
	 */
	@Override
	protected void doStart() throws Exception {
		super.doStart();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Kafka Consumer Component started");
		}
		// TODO find a better way
		executor = endpoint.getCamelContext().getExecutorServiceStrategy().newFixedThreadPool(this, endpoint.getEndpointUri(), endpoint.getConcurrentConsumers());
		// consume the messages in the threads
		for (final KafkaStream<Message> stream : streams) {
			final Klistener kl = new Klistener();
			kl.setConsumer(this);
			kl.setStream(stream);
			kl.setEndpoint(endpoint);
			executor.submit(kl);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.impl.DefaultConsumer#doStop()
	 */
	@Override
	protected void doStop() throws Exception {
		// TODO find a better way
		endpoint.getCamelContext().getExecutorServiceStrategy().shutdownNow(executor);
		executor = null;
		if (LOG.isDebugEnabled()) {
			LOG.debug("Kafka Consumer Component stoped");
		}
		super.doStop();

	}

}
