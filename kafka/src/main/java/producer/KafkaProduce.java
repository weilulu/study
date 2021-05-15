package producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.util.Properties;

/**
 * @Author:weilu
 * @Date:2019/11/11 17:34
 * @Description:${Description}
 */
public class KafkaProduce extends Thread {
    private String topic;
    public KafkaProduce(String topic){
        super();
        this.topic = topic;
    }
    private Producer createProducer(){
        Properties properties = new Properties();
        properties.put("zookeeper.connect","localhost:2181");
        properties.put("serializer.class", StringEncoder.class.getName());
        properties.put("bootstrap.servers","localhost:9092");
        return null;
    }
}
