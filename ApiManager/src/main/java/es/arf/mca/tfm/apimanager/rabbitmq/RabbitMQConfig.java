package es.arf.mca.tfm.apimanager.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.queue-tasks}")
	private String queueTasks;

	@Value("${rabbitmq.queue-results}")
	private String queueResults;

	@Bean
	Queue queueTasks() {
		return new Queue(queueTasks, false);
	}

	@Bean
	Queue queueResults() {
		return new Queue(queueResults, false);
	}
}
