package com.pskwiercz.demojpa.criteria;

import com.pskwiercz.demojpa.entities.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteriaMessageServiceTest {

    private static final String TEST_MSG1 = "First test message";
    private static final String TEST_MSG2 = "Second test message";

    @Autowired
    private CriteriaMessageService messageService;

    @Test
    public void findMessagesTest() {
        messageService.saveMessages(TEST_MSG1);
        messageService.saveMessages(TEST_MSG2);

        List<Message> messages = messageService.findMessages();

        assertEquals(2, messages.size());
        assertEquals(TEST_MSG1, messages.get(0).getText());
    }

    @Test
    public void findMessageTest() {
        messageService.saveMessages(TEST_MSG1);
        messageService.saveMessages(TEST_MSG2);

        Message message1 = messageService.findMessage(1L);
        Message message2 = messageService.findMessage(2L);
        Message message3 = messageService.findMessage(3L);

        assertEquals(TEST_MSG1, message1.getText());
        assertEquals(TEST_MSG2, message2.getText());
        assertNull(message3);
    }

}