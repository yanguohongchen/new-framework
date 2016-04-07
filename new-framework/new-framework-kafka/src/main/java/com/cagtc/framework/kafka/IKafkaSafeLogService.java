package com.cagtc.framework.kafka;


/**
 * 保证所有日志写入
 * 
 * @author sea
 *
 */
public interface IKafkaSafeLogService
{

	/**
	 * 发送信息
	 * 
	 * @param msg
	 */
	public void send(KafkaEvent msg);

}
