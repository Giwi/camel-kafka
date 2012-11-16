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
	private int socketTimeoutMs = 30000;
	private int socketBuffersize = 64 * 1024;
	private int fetchSize = 300 * 1024;
	private int backoffIncrementMs = 1000;
	private int queuedchunksMax = 100;
	private boolean autocommitEnable = true;
	private int autocommitIntervalMs = 10000;
	private String autooffsetReset = "smallest";
	private int consumerTimeoutMs = -1;
	private int rebalanceRetriesMax = 4;
	private String mirrorTopicsWhitelist = "";
	private String mirrorTopicsBlacklist = "";
	private int mirrorConsumerNumthreads = 4;
	private String serializerClass = null;
	private String partitionerClass = null;
	private String producerType = "sync";
	private String brokerList = null;
	private int bufferSize = 102400;
	private int connectTimeoutMs = 5000;
	private int reconnectInterval = 30000;
	private int maxMessageSize = 1000000;
	private int compressionCodec = 0;
	private String compressedTopics = null;
	private int zkReadNumRetries = 3;
	private int queueTime = 5000;
	private int queueSize = 10000;
	private int batchSize = 200;
	private String eventHandler = null;
	private String eventHandlerProps = null;
	private String callbackHandler = null;
	private String callbackHandlerProps = null;

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
	@Override
	public Producer createProducer() throws Exception {
		return new KafkaProducer(this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.IsSingleton#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getZkConnect() {
		return zkConnect;
	}

	public void setZkConnect(String zkConnect) {
		this.zkConnect = zkConnect;
	}

	/**
	 * @return the socketTimeoutMs
	 */
	public int getSocketTimeoutMs() {
		return socketTimeoutMs;
	}

	/**
	 * @param socketTimeoutMs
	 *            the socketTimeoutMs to set
	 */
	public void setSocketTimeoutMs(int socketTimeoutMs) {
		this.socketTimeoutMs = socketTimeoutMs;
	}

	/**
	 * @return the socketBuffersize
	 */
	public int getSocketBuffersize() {
		return socketBuffersize;
	}

	/**
	 * @param socketBuffersize
	 *            the socketBuffersize to set
	 */
	public void setSocketBuffersize(int socketBuffersize) {
		this.socketBuffersize = socketBuffersize;
	}

	/**
	 * @return the fetchSize
	 */
	public int getFetchSize() {
		return fetchSize;
	}

	/**
	 * @param fetchSize
	 *            the fetchSize to set
	 */
	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	/**
	 * @return the backoffIncrementMs
	 */
	public int getBackoffIncrementMs() {
		return backoffIncrementMs;
	}

	/**
	 * @param backoffIncrementMs
	 *            the backoffIncrementMs to set
	 */
	public void setBackoffIncrementMs(int backoffIncrementMs) {
		this.backoffIncrementMs = backoffIncrementMs;
	}

	/**
	 * @return the queuedchunksMax
	 */
	public int getQueuedchunksMax() {
		return queuedchunksMax;
	}

	/**
	 * @param queuedchunksMax
	 *            the queuedchunksMax to set
	 */
	public void setQueuedchunksMax(int queuedchunksMax) {
		this.queuedchunksMax = queuedchunksMax;
	}

	/**
	 * @return the autocommitEnable
	 */
	public boolean isAutocommitEnable() {
		return autocommitEnable;
	}

	/**
	 * @param autocommitEnable
	 *            the autocommitEnable to set
	 */
	public void setAutocommitEnable(boolean autocommitEnable) {
		this.autocommitEnable = autocommitEnable;
	}

	/**
	 * @return the autocommitIntervalMs
	 */
	public int getAutocommitIntervalMs() {
		return autocommitIntervalMs;
	}

	/**
	 * @param autocommitIntervalMs
	 *            the autocommitIntervalMs to set
	 */
	public void setAutocommitIntervalMs(int autocommitIntervalMs) {
		this.autocommitIntervalMs = autocommitIntervalMs;
	}

	/**
	 * @return the autooffsetReset
	 */
	public String getAutooffsetReset() {
		return autooffsetReset;
	}

	/**
	 * @param autooffsetReset
	 *            the autooffsetReset to set
	 */
	public void setAutooffsetReset(String autooffsetReset) {
		this.autooffsetReset = autooffsetReset;
	}

	/**
	 * @return the consumerTimeoutMs
	 */
	public int getConsumerTimeoutMs() {
		return consumerTimeoutMs;
	}

	/**
	 * @param consumerTimeoutMs
	 *            the consumerTimeoutMs to set
	 */
	public void setConsumerTimeoutMs(int consumerTimeoutMs) {
		this.consumerTimeoutMs = consumerTimeoutMs;
	}

	/**
	 * @return the rebalanceRetriesMax
	 */
	public int getRebalanceRetriesMax() {
		return rebalanceRetriesMax;
	}

	/**
	 * @param rebalanceRetriesMax
	 *            the rebalanceRetriesMax to set
	 */
	public void setRebalanceRetriesMax(int rebalanceRetriesMax) {
		this.rebalanceRetriesMax = rebalanceRetriesMax;
	}

	/**
	 * @return the mirrorTopicsWhitelist
	 */
	public String getMirrorTopicsWhitelist() {
		return mirrorTopicsWhitelist;
	}

	/**
	 * @param mirrorTopicsWhitelist
	 *            the mirrorTopicsWhitelist to set
	 */
	public void setMirrorTopicsWhitelist(String mirrorTopicsWhitelist) {
		this.mirrorTopicsWhitelist = mirrorTopicsWhitelist;
	}

	/**
	 * @return the mirrorTopicsBlacklist
	 */
	public String getMirrorTopicsBlacklist() {
		return mirrorTopicsBlacklist;
	}

	/**
	 * @param mirrorTopicsBlacklist
	 *            the mirrorTopicsBlacklist to set
	 */
	public void setMirrorTopicsBlacklist(String mirrorTopicsBlacklist) {
		this.mirrorTopicsBlacklist = mirrorTopicsBlacklist;
	}

	/**
	 * @return the mirrorConsumerNumthreads
	 */
	public int getMirrorConsumerNumthreads() {
		return mirrorConsumerNumthreads;
	}

	/**
	 * @param mirrorConsumerNumthreads
	 *            the mirrorConsumerNumthreads to set
	 */
	public void setMirrorConsumerNumthreads(int mirrorConsumerNumthreads) {
		this.mirrorConsumerNumthreads = mirrorConsumerNumthreads;
	}

	/**
	 * @return the serializerClass
	 */
	public String getSerializerClass() {
		return serializerClass;
	}

	/**
	 * @param serializerClass
	 *            the serializerClass to set
	 */
	public void setSerializerClass(String serializerClass) {
		this.serializerClass = serializerClass;
	}

	/**
	 * @return the partitionerClass
	 */
	public String getPartitionerClass() {
		return partitionerClass;
	}

	/**
	 * @param partitionerClass
	 *            the partitionerClass to set
	 */
	public void setPartitionerClass(String partitionerClass) {
		this.partitionerClass = partitionerClass;
	}

	/**
	 * @return the producerType
	 */
	public String getProducerType() {
		return producerType;
	}

	/**
	 * @param producerType
	 *            the producerType to set
	 */
	public void setProducerType(String producerType) {
		this.producerType = producerType;
	}

	/**
	 * @return the brokerList
	 */
	public String getBrokerList() {
		return brokerList;
	}

	/**
	 * @param brokerList
	 *            the brokerList to set
	 */
	public void setBrokerList(String brokerList) {
		this.brokerList = brokerList;
	}

	/**
	 * @return the bufferSize
	 */
	public int getBufferSize() {
		return bufferSize;
	}

	/**
	 * @param bufferSize
	 *            the bufferSize to set
	 */
	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	/**
	 * @return the connectTimeoutMs
	 */
	public int getConnectTimeoutMs() {
		return connectTimeoutMs;
	}

	/**
	 * @param connectTimeoutMs
	 *            the connectTimeoutMs to set
	 */
	public void setConnectTimeoutMs(int connectTimeoutMs) {
		this.connectTimeoutMs = connectTimeoutMs;
	}

	/**
	 * @return the reconnectInterval
	 */
	public int getReconnectInterval() {
		return reconnectInterval;
	}

	/**
	 * @param reconnectInterval
	 *            the reconnectInterval to set
	 */
	public void setReconnectInterval(int reconnectInterval) {
		this.reconnectInterval = reconnectInterval;
	}

	/**
	 * @return the maxMessageSize
	 */
	public int getMaxMessageSize() {
		return maxMessageSize;
	}

	/**
	 * @param maxMessageSize
	 *            the maxMessageSize to set
	 */
	public void setMaxMessageSize(int maxMessageSize) {
		this.maxMessageSize = maxMessageSize;
	}

	/**
	 * @return the compressionCodec
	 */
	public int getCompressionCodec() {
		return compressionCodec;
	}

	/**
	 * @param compressionCodec
	 *            the compressionCodec to set
	 */
	public void setCompressionCodec(int compressionCodec) {
		this.compressionCodec = compressionCodec;
	}

	/**
	 * @return the compressedTopics
	 */
	public String getCompressedTopics() {
		return compressedTopics;
	}

	/**
	 * @param compressedTopics
	 *            the compressedTopics to set
	 */
	public void setCompressedTopics(String compressedTopics) {
		this.compressedTopics = compressedTopics;
	}

	/**
	 * @return the zkReadNumRetries
	 */
	public int getZkReadNumRetries() {
		return zkReadNumRetries;
	}

	/**
	 * @param zkReadNumRetries
	 *            the zkReadNumRetries to set
	 */
	public void setZkReadNumRetries(int zkReadNumRetries) {
		this.zkReadNumRetries = zkReadNumRetries;
	}

	/**
	 * @return the queueTime
	 */
	public int getQueueTime() {
		return queueTime;
	}

	/**
	 * @param queueTime
	 *            the queueTime to set
	 */
	public void setQueueTime(int queueTime) {
		this.queueTime = queueTime;
	}

	/**
	 * @return the queueSize
	 */
	public int getQueueSize() {
		return queueSize;
	}

	/**
	 * @param queueSize
	 *            the queueSize to set
	 */
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	/**
	 * @return the batchSize
	 */
	public int getBatchSize() {
		return batchSize;
	}

	/**
	 * @param batchSize
	 *            the batchSize to set
	 */
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	/**
	 * @return the eventHandler
	 */
	public String getEventHandler() {
		return eventHandler;
	}

	/**
	 * @param eventHandler
	 *            the eventHandler to set
	 */
	public void setEventHandler(String eventHandler) {
		this.eventHandler = eventHandler;
	}

	/**
	 * @return the eventHandlerProps
	 */
	public String getEventHandlerProps() {
		return eventHandlerProps;
	}

	/**
	 * @param eventHandlerProps
	 *            the eventHandlerProps to set
	 */
	public void setEventHandlerProps(String eventHandlerProps) {
		this.eventHandlerProps = eventHandlerProps;
	}

	/**
	 * @return the callbackHandler
	 */
	public String getCallbackHandler() {
		return callbackHandler;
	}

	/**
	 * @param callbackHandler
	 *            the callbackHandler to set
	 */
	public void setCallbackHandler(String callbackHandler) {
		this.callbackHandler = callbackHandler;
	}

	/**
	 * @return the callbackHandlerProps
	 */
	public String getCallbackHandlerProps() {
		return callbackHandlerProps;
	}

	/**
	 * @param callbackHandlerProps
	 *            the callbackHandlerProps to set
	 */
	public void setCallbackHandlerProps(String callbackHandlerProps) {
		this.callbackHandlerProps = callbackHandlerProps;
	}

}
