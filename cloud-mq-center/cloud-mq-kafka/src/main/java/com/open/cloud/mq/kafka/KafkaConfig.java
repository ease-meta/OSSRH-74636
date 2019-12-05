package com.open.cloud.mq.kafka;

import kafka.admin.AdminUtils;
import kafka.admin.TopicCommand;
import kafka.utils.ZKStringSerializer$;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Configuration
public class KafkaConfig {

	@Autowired
	private KafkaProperties kafkaProperties;

	/**
	 * setFatalIfBrokerNotAvailable(true)：默认这个值是False的，在Broker不可用时，不影响Spring 上下文的初始化。如果你觉得Broker不可用影响正常业务需要显示的将这个值设置为True。整编：微信公众号，搜云库技术团队，ID：souyunku
	 * <p>
	 * setAutoCreate(false) : 默认值为True，也就是Kafka实例化后会自动创建已经实例化的NewTopic对象
	 * <p>
	 * initialize()：当setAutoCreate为false时，需要我们程序显示的调用admin的initialize()方法来初始化NewTopic对象
	 *
	 * @param properties
	 * @return
	 */
	public KafkaAdmin admin(KafkaProperties properties) {
		KafkaAdmin admin = new KafkaAdmin(properties.buildAdminProperties());
		admin.setFatalIfBrokerNotAvailable(true);
		admin.setAutoCreate(true);
		return admin;

	}

	@Bean
	public NewTopic topic2() {
		return new NewTopic("topic_input", 1, (short) 1);
	}

	//新版kafka
	public void testCreateTopic() {
		AdminClient adminClient = AdminClient.create(kafkaProperties.buildAdminProperties());
		if (Objects.nonNull(adminClient)) {
			try {
				Collection<NewTopic> newTopics = new ArrayList<>(1);
				((ArrayList<NewTopic>) newTopics).add(new NewTopic("topic-k1", 1, (short) 1));
				adminClient.createTopics(newTopics);
			} catch (Exception e) {
				log.error("{},{}", e, e.getMessage());
			} finally {
				adminClient.close();
			}
		}
	}

	//使用命令创建
	public void testCreateTopicCommand() {
		String[] options = new String[]{
						"--create",
						"--zookeeper",
						"127.0.0.1:2181",
						"--replication-factor",
						"3",
						"--partitions",
						"3",
						"--topic",
						"topic-kl"};

		TopicCommand.main(options);
	}

	/*public void testCreateTopic() throws Exception {
		ZkClient zkClient = new ZkClient("127.0.0.1:2181", 3000, 3000, ZKStringSerializer$.MODULE$);
		String topicName = "topic-kl";
		int partitions = 1;
		int replication = 1;
		AdminUtils.createTopic(zkClient, topicName, partitions, replication, new Properties());
	}*/
}