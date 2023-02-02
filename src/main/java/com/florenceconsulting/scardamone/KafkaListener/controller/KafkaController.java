package com.florenceconsulting.scardamone.KafkaListener.controller;

import com.florenceconsulting.scardamone.KafkaListener.common.TopicHandler;
import com.google.gson.Gson;
import com.florenceconsulting.scardamone.KafkaListener.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private KafkaTemplate<String, Content> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate<String, Content> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void post(@RequestBody Content content){
        kafkaTemplate.send("exampleTopic", content);
    }
    @KafkaListener(topics = "exampleTopic", groupId = "group-id")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }

}
