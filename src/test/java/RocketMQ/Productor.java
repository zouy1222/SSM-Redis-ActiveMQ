package RocketMQ;

import java.util.Date;

import org.apache.camel.component.properties.SysPropertiesFunction;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class Productor {
	public static void main(String[] args) throws RemotingException, MQBrokerException, InterruptedException, MQClientException {
		DefaultMQProducer producer = new DefaultMQProducer("rmq-group");
		producer.setNamesrvAddr("192.168.86.128:9876");
		producer.setInstanceName("rmq-instance");
		producer.setRetryTimesWhenSendFailed(4);
		producer.start();
		try {
			for (int i = 0; i < 3; i++) {
				Message msg = new Message("Topic-test", // topic
						"TagA", // tag
						( "A-Hello RocketMQ" + i).getBytes()// body
				);
				SendResult sendResult = producer.send(msg);
			}
			
			System.out.println("A发送完成.....");
			
			for (int i = 0; i < 3; i++) {
				Message msg = new Message("Topic-test", // topic
						"TagB", // tag
						( "B-Hello RocketMQ" + i).getBytes()// body
				);
				SendResult sendResult = producer.send(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("发送完成");
		producer.shutdown();

	}
}
