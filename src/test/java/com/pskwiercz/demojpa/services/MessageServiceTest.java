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
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void saveMessageTest() {
        messageService.saveMessage("Test message");

        Message msg = messageService.findMessage(1L);

        assertEquals("Test message", msg.getText());
    }

}