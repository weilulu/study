package producer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * @Author:weilu
 * @Date:2019/11/11 15:41
 * @Description: kafka消息生产者
 */
public class ProducerTest {
    //消息topic
    public static String topic = "test";

    public static void main(String[] args) throws InterruptedException{
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<>(props);
        Future<RecordMetadata> future = kafkaProducer.send(new ProducerRecord<>(topic,"title","test333"));
        System.out.println("future result:"+future.isDone());
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
