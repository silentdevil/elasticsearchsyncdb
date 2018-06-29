package com.gofluent.elasticsearch.elasticsearchsyncdb.jms;


import com.gofluent.elasticsearch.elasticsearchsyncdb.model.SearchObject;
import com.gofluent.elasticsearch.elasticsearchsyncdb.service.SearchObjectService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Map;


@Slf4j
@Component
public class MessageReceiver implements MessageListener {

    @Autowired
    SearchObjectService searchObjectService;

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private Gson gson;

    @Autowired
    KafkaProducer producer;

    @Override
    public void onMessage(Message message) {
        try {
            String response = (String) messageConverter.fromMessage(message);
            System.out.println(response);
            Map<String, String> map = gson.fromJson(response, Map.class);
            SearchObject searchObject = new SearchObject();
            searchObject.setId(map.get("id"));
            searchObject.setDisplay(map.get("display"));
            searchObject.setKeyword(map.get("keyword"));
            searchObject.setReturnId(map.get("returnid"));
            searchObject.setType(map.get("type"));
           searchObjectService.save(searchObject);
           producer.send(response);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}