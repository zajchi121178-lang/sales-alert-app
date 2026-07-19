import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class SimpleProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Отправляем два тестовых сообщения в топик purchases
        producer.send(new ProducerRecord<>("purchases", "key-1", "{\"id\":1,\"product\":\"laptop\",\"amount\":3000,\"country\":\"USA\"}"));
        producer.send(new ProducerRecord<>("purchases", "key-2", "{\"id\":2,\"product\":\"phone\",\"amount\":2500,\"country\":\"USA\"}"));

        System.out.println("Сообщения отправлены в топик purchases!");
        
        producer.close();
    }
}
