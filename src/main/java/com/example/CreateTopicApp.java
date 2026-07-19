package com.example;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import java.util.Collections;
import java.util.Properties;

public class CreateTopicApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");

        try (AdminClient adminClient = AdminClient.create(props)) {
            NewTopic topic = new NewTopic("purchases", 1, (short) 1);
            adminClient.createTopics(Collections.singleton(topic)).all().get();
            System.out.println("Тема 'purchases' успешно создана.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
