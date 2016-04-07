package com.cagtc.framework.kafka;


/**
 * 相对安全的与快速的方式
 * @author sea
 *
 */
public interface IKafkaReasonableLogService
{

	/**
	 * 发送信息
	 * 
	 * @param msg
	 */
	public void send(KafkaEvent msg);

}
