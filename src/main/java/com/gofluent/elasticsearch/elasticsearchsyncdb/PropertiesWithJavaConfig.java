package com.gofluent.elasticsearch.elasticsearchsyncdb;

import com.gofluent.elasticsearch.elasticsearchsyncdb.jms.MessageReceiver;
import com.google.gson.Gson;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import javax.jms.ConnectionFactory;
import java.util.Arrays;

@Configuration
@ComponentScan(value = "com.gofluent.elasticsearch")
public class PropertiesWithJavaConfig {

    @Value("${queue.url}")
    private String ACTIVEMQ_QUEUE_URL;

    @Value("${queue.title.consume}")
    private String QUEUE_LISTEN;

    @Autowired
    MessageReceiver messageReceiver;

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(ACTIVEMQ_QUEUE_URL);
        connectionFactory.setTrustedPackages(Arrays.asList("com.gofluent.msg.syncmessage.messages"));
        return connectionFactory;
    }

    @Bean
    public ConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setTargetConnectionFactory(connectionFactory());
        connectionFactory.setSessionCacheSize(10);
        return connectionFactory;
    }

    @Bean
    public MessageListenerContainer getContainer(){
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setDestinationName(QUEUE_LISTEN);
        container.setMessageListener(messageReceiver);
        container.setSessionTransacted(true);
        return container;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    MessageConverter messageConverter(){
        return new SimpleMessageConverter();
    }
}
