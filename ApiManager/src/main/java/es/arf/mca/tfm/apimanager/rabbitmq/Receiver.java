package es.arf.mca.tfm.apimanager.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import es.arf.mca.tfm.apimanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Receiver {
	
	private final TaskRepository repository;
	 
	@RabbitListener(queues = "${rabbitmq.queue-results}")
	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
	}
}
