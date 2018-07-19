package com.labwinner.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Description 随手记消息消费者
 * @author liuhq
 * @version V1.0
 * @date 2017年7月5日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Component
public class NoteConsumer {
	
	private static Logger logger = LoggerFactory
			.getLogger(NoteConsumer.class);

//	@JmsListener(destination = "labwinner.note.queue")
	public void receiveQueue(String text) {
		logger.info("-----------------------");
		logger.info("NoteConsumer　receive: " + text);
		logger.info("-----------------------");
	}

}
