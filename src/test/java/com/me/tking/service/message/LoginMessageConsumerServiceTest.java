package com.me.tking.service.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginMessageConsumerServiceTest {
    @Autowired
    LoginMessageConsumerService loginMessageConsumerService;

    @Test
    public void receiveQueue() throws Exception {
        System.out.println("单元测试测试");
    }

}