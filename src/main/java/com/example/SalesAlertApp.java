package com.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;  // <-- ЭТОТ ИМПОРТ ИСПРАВЛЯЕТ ОШИБКУ
import java.util.Collections;
import java.util.Properties;

public class SalesAlertApp {

    public static void main(String[] args) {
        // Настройка свойств потребителя
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sales-alert-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Создаём потребителя
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Подписываемся на тему с покупками (убедись, что тема существует)
        consumer.subscribe(Collections.singletonList("purchases"));

        System.out.println("SalesAlertApp запущен. Ожидание сообщений из темы 'purchases'...");

        try {
            while (true) {
                // poll с Duration (теперь компилируется, потому что есть import java.time.Duration)
                ConsumerRecords<String, String> recs = consumer.poll(Duration.ofMillis(200));

                for (ConsumerRecord<String, String> record : recs) {
                    String key = record.key();
                    String value = record.value();

                    // Пример простой логики алерта: если в сообщении есть слово "HIGH" — выводим алерт
                    if (value != null && value.contains("HIGH")) {
                        System.out.println("[ALERT] Высокая сумма покупки! Ключ: " + key + ", Значение: " + value);
                    } else {
                        // Обычный лог (для отладки)
                        System.out.println("Получено сообщение: ключ=" + key + ", значение=" + value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
