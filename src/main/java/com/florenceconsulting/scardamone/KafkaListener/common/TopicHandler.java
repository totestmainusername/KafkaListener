package com.florenceconsulting.scardamone.KafkaListener.common;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class TopicHandler {


    private KafkaAdmin kafkaAdmin;

    public void createTopicIfNotExists(String topicName) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");

        AdminClient adminClient = AdminClient.create(props);
        //Getter Topic List
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        Set<String> existingTopics = listTopicsResult.names().get();

        //Check
        if (!existingTopics.contains(topicName)) {
            NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
            adminClient.createTopics(Collections.singletonList(newTopic));
        }
    }


}
