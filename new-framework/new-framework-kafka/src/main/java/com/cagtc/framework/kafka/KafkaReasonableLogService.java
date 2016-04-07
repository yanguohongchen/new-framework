package com.cagtc.framework.kafka;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.annotation.PreDestroy;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaReasonableLogService implements IKafkaReasonableLogService
{

	private Producer<String, String> producer;

	public KafkaReasonableLogService(String brokerList)
	{
		Properties props = new Properties();
		props.put("metadata.broker.list", brokerList);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");
		ProducerConfig config = new ProducerConfig(props);
		producer = new Producer<String, String>(config);
	}

	@Override
	public void send(KafkaEvent msg)
	{
		try
		{
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(msg.getTopic(), msg.getKey(), URLEncoder.encode(msg.getValue(),
					"UTF-8"));
			producer.send(data);
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("编码不正确！");
		}
	}

	@PreDestroy
	public void close()
	{
		producer.close();
	}

}
