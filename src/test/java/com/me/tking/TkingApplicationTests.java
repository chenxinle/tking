package com.me.tking;

import com.me.tking.service.message.MessageProducerService;
import com.me.tking.dao.ActivityInfoDao;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TkingApplicationTests {

    @Autowired
    ActivityInfoDao activityInfoDao;

    @Autowired
    MessageProducerService messageProducer;

	@Test
	public void contextLoads() {
//        activityInfoDao.getAll();
	}

	@Test
    public void deleteAll() {
	    activityInfoDao.deleteAll();
    }

    @Test
    public void messageProduce() {
	    messageProducer.sendMessage("test", "login.queue", "topic");
    }

}
