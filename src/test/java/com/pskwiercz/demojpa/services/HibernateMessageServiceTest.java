package com.pskwiercz.demojpa.services;

import com.pskwiercz.demojpa.entities.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateMessageServiceTest {

    private static final String TEST_MSG = "Test message for Hibernate";

    @Autowired
    private HibernateMessageService messageService;

    @Test
    public void saveMessageTest() {
        messageService.saveMessageHibernate(TEST_MSG);

        Message msg = messageService.findMessageHibernate(1L);

        assertEquals(TEST_MSG, msg.getText());
    }
}