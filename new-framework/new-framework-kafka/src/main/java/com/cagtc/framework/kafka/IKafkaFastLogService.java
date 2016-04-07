package com.cagtc.framework.kafka;


/**
 * 快速写入，允许部分日志丢失
 * @author sea
 *
 */
public interface IKafkaFastLogService
{

	/**
	 * 发送信息
	 * 
	 * @param msg
	 */
	public void send(KafkaEvent msg);

}
