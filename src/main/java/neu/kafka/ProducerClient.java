package neu.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Kafka生产端
 * @author Zhangyongliang
 */
public class ProducerClient {
    public static void main(String[] args){
        Properties props = new Properties();
        //kafka broker列表
        props.put("bootstrap.servers", "10.107.20.43:9092");
        //acks=1表示Broker接收到消息成功写入本地log文件后向Producer返回成功接收的信号，不需要等待所有的Follower全部同步完消息后再做回应
        props.put("acks", "1");
        //key和value的字符串序列化类
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        //用户产生随机数，模拟消息生成
        Random rand = new Random();
        for(int i = 0; i < 1; i++) {
            //通过随机数产生一个ip地址作为key发送出去
            String ip = "192.168.1." + rand.nextInt(255);
            long runtime = System.currentTimeMillis();
            //组装一条消息内容
            String msg = runtime + "---" + ip;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("send to kafka->key:" + ip + " value:" + msg);
            //向kafka topictest1主题发送消息
            producer.send(new ProducerRecord<String, String>("topicTest", ip, msg));
        }
        producer.close();
    }
}