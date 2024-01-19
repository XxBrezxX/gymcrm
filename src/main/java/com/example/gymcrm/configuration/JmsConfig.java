// package com.example.gymcrm.configuration;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class JmsConfig {

//     @Value("${spring.activemq.broker-url}")
//     private String brokerUrl;

//     @Value("${spring.activemq.user}")
//     private String user;

//     @Value("${spring.activemq.password}")
//     private String pass;

//     @Bean
//     public ActiveMQConnectionFactory connectionFactory() {
//         ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//         connectionFactory.setBrokerURL(brokerUrl);
//         connectionFactory.setUserName(user);
//         connectionFactory.setPassword(pass);
//         RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
//         redeliveryPolicy.setMaximumRedeliveries(5); // cantidad de reintentos antes de enviar al DLQ
//         connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
//         return connectionFactory;
//     }
// }