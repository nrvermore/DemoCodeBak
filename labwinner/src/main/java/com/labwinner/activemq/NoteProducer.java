package com.labwinner.activemq;

import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description 随手记消息生产者
 * @author liuhq
 * @version V1.0
 * @date 2017年7月5日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Component
public class NoteProducer implements CommandLineRunner {

	private static Logger logger = LoggerFactory
			.getLogger(NoteProducer.class);
	
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private Queue queue;

	@Override
	public void run(String... args) throws Exception {
		send("LabWinner Note Message");
		logger.info("LabWinner Note Message was sent to the Queue");
	}

	public void send(String msg) {
		logger.info("NoteProducer send: " + msg);
		this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
	}

}
