// package com.example.gymcrm.services.messaging;

// import static org.mockito.Mockito.atLeastOnce;
// import static org.mockito.Mockito.verify;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.jms.core.JmsTemplate;

// import com.example.gymcrm.services.web.JwtTokenProvider;

// @SpringBootTest
// public class MessageSenderServiceTest {

//     @MockBean
//     private JwtTokenProvider jTokenProvider;

//     @MockBean
//     private JmsTemplate jmsTemplate;

//     @Autowired
//     private MessageSenderService messageSenderService;

//     @Test
//     public void testSendMessage() {
//         messageSenderService.sendMessage("destination", "message");
//         verify(jmsTemplate, atLeastOnce()).convertAndSend("destination", "message");
//     }
// }
