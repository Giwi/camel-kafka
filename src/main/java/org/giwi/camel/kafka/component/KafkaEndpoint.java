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
	private String socketTimeoutMs = "30000";
	private String socketBuffersize = "65536";
	private String fetchSize = "307200";
	private String backoffIncrementMs = "1000";
	private String queuedchunksMax = "100";
	private String autocommitEnable = "true";
	private String autocommitIntervalMs = "10000";
	private String autooffsetReset = "smallest";
	private String consumerTimeoutMs = "-1";
	private String rebalanceRetriesMax = "4";
	private String mirrorTopicsWhitelist = "";
	private String mirrorTopicsBlacklist = "";
	private String mirrorConsumerNumthreads = "4";
	private String serializerClass = "";
	private String partitionerClass = "";
	private String producerType = "sync";
	private String brokerList = "";
	private String bufferSize = "102400";
	private String connectTimeoutMs = "5000";
	private String reconnectInterval = "30000";
	private String maxMessageSize = "1000000";
	private String compressionCodec = "0";
	private String compressedTopics = "";
	private String zkReadNumRetries = "3";
	private String queueTime = "5000";
	private String queueSize = "10000";
	private String batchSize = "200";
	private String eventHandler = "";
	private String eventHandlerProps = "";
	private String callbackHandler = "";
	private String callbackHandlerProps = "";

	/**
	 * @param uri
	 *            uri of the component
	 * @param component
	 *            THE comonent
	 */
	public KafkaEndpoint(final String endPoStringURI, final KafkaComponent component, final URI httpURI) throws URISyntaxException {
		super(endPoStringURI, component);
		topicName = httpURI.getHost();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.EndpoString#createConsumer(org.apache.camel.Processor)
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
	 * @see org.apache.camel.EndpoString#createProducer()
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
	public String getSocketTimeoutMs() {
		return socketTimeoutMs;
	}

	/**
	 * @param socketTimeoutMs
	 *            the socketTimeoutMs to set
	 */
	public void setSocketTimeoutMs(String socketTimeoutMs) {
		this.socketTimeoutMs = socketTimeoutMs;
	}

	/**
	 * @return the socketBuffersize
	 */
	public String getSocketBuffersize() {
		return socketBuffersize;
	}

	/**
	 * @param socketBuffersize
	 *            the socketBuffersize to set
	 */
	public void setSocketBuffersize(String socketBuffersize) {
		this.socketBuffersize = socketBuffersize;
	}

	/**
	 * @return the fetchSize
	 */
	public String getFetchSize() {
		return fetchSize;
	}

	/**
	 * @param fetchSize
	 *            the fetchSize to set
	 */
	public void setFetchSize(String fetchSize) {
		this.fetchSize = fetchSize;
	}

	/**
	 * @return the backoffIncrementMs
	 */
	public String getBackoffIncrementMs() {
		return backoffIncrementMs;
	}

	/**
	 * @param backoffIncrementMs
	 *            the backoffIncrementMs to set
	 */
	public void setBackoffIncrementMs(String backoffIncrementMs) {
		this.backoffIncrementMs = backoffIncrementMs;
	}

	/**
	 * @return the queuedchunksMax
	 */
	public String getQueuedchunksMax() {
		return queuedchunksMax;
	}

	/**
	 * @param queuedchunksMax
	 *            the queuedchunksMax to set
	 */
	public void setQueuedchunksMax(String queuedchunksMax) {
		this.queuedchunksMax = queuedchunksMax;
	}

	/**
	 * @return the autocommitIntervalMs
	 */
	public String getAutocommitIntervalMs() {
		return autocommitIntervalMs;
	}

	/**
	 * @param autocommitIntervalMs
	 *            the autocommitIntervalMs to set
	 */
	public void setAutocommitStringervalMs(String autocommitIntervalMs) {
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
	public String getConsumerTimeoutMs() {
		return consumerTimeoutMs;
	}

	/**
	 * @param consumerTimeoutMs
	 *            the consumerTimeoutMs to set
	 */
	public void setConsumerTimeoutMs(String consumerTimeoutMs) {
		this.consumerTimeoutMs = consumerTimeoutMs;
	}

	/**
	 * @return the rebalanceRetriesMax
	 */
	public String getRebalanceRetriesMax() {
		return rebalanceRetriesMax;
	}

	/**
	 * @param rebalanceRetriesMax
	 *            the rebalanceRetriesMax to set
	 */
	public void setRebalanceRetriesMax(String rebalanceRetriesMax) {
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
	public String getMirrorConsumerNumthreads() {
		return mirrorConsumerNumthreads;
	}

	/**
	 * @param mirrorConsumerNumthreads
	 *            the mirrorConsumerNumthreads to set
	 */
	public void setMirrorConsumerNumthreads(String mirrorConsumerNumthreads) {
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
	public String getBufferSize() {
		return bufferSize;
	}

	/**
	 * @param bufferSize
	 *            the bufferSize to set
	 */
	public void setBufferSize(String bufferSize) {
		this.bufferSize = bufferSize;
	}

	/**
	 * @return the connectTimeoutMs
	 */
	public String getConnectTimeoutMs() {
		return connectTimeoutMs;
	}

	/**
	 * @param connectTimeoutMs
	 *            the connectTimeoutMs to set
	 */
	public void setConnectTimeoutMs(String connectTimeoutMs) {
		this.connectTimeoutMs = connectTimeoutMs;
	}

	/**
	 * @return the reconnectInterval
	 */
	public String getReconnectInterval() {
		return reconnectInterval;
	}

	/**
	 * @param reconnectStringerval
	 *            the reconnectInterval to set
	 */
	public void setReconnectInterval(String reconnectInterval) {
		this.reconnectInterval = reconnectInterval;
	}

	/**
	 * @return the maxMessageSize
	 */
	public String getMaxMessageSize() {
		return maxMessageSize;
	}

	/**
	 * @param maxMessageSize
	 *            the maxMessageSize to set
	 */
	public void setMaxMessageSize(String maxMessageSize) {
		this.maxMessageSize = maxMessageSize;
	}

	/**
	 * @return the compressionCodec
	 */
	public String getCompressionCodec() {
		return compressionCodec;
	}

	/**
	 * @param compressionCodec
	 *            the compressionCodec to set
	 */
	public void setCompressionCodec(String compressionCodec) {
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
	public String getZkReadNumRetries() {
		return zkReadNumRetries;
	}

	/**
	 * @param zkReadNumRetries
	 *            the zkReadNumRetries to set
	 */
	public void setZkReadNumRetries(String zkReadNumRetries) {
		this.zkReadNumRetries = zkReadNumRetries;
	}

	/**
	 * @return the queueTime
	 */
	public String getQueueTime() {
		return queueTime;
	}

	/**
	 * @param queueTime
	 *            the queueTime to set
	 */
	public void setQueueTime(String queueTime) {
		this.queueTime = queueTime;
	}

	/**
	 * @return the queueSize
	 */
	public String getQueueSize() {
		return queueSize;
	}

	/**
	 * @param queueSize
	 *            the queueSize to set
	 */
	public void setQueueSize(String queueSize) {
		this.queueSize = queueSize;
	}

	/**
	 * @return the batchSize
	 */
	public String getBatchSize() {
		return batchSize;
	}

	/**
	 * @param batchSize
	 *            the batchSize to set
	 */
	public void setBatchSize(String batchSize) {
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

	/**
	 * @return the autocommitEnable
	 */
	public String getAutocommitEnable() {
		return autocommitEnable;
	}

	/**
	 * @param autocommitEnable
	 *            the autocommitEnable to set
	 */
	public void setAutocommitEnable(String autocommitEnable) {
		this.autocommitEnable = autocommitEnable;
	}

}
