package es.arf.mca.tfm.apimanager.rabbitmq;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import es.arf.mca.tfm.apimanager.entity.Task;
import es.arf.mca.tfm.apimanager.repository.TaskRepository;
import es.arf.mca.tfm.apimanager.utils.Converter;
import es.arf.mca.tfm.apimanager.utils.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Receiver {

	private final TaskRepository repository;

	@RabbitListener(queues = "${rabbitmq.queue-results}")
	public void receiveMessage(String message) {
		try {
			log.debug("Received <{}>", message);
			updateTask(Converter.convertToObject(message, Task.class));
		} catch (Exception e) {
			log.error("Error receiving a message: {}", e.getMessage());
		}

	}

	private Optional<Task> updateTask(Task task) {
		return this.repository.findById(task.getId()).map(t -> {
			t.setStatus(Status.FINISHED);
			t.setValue(task.getValue());
			return this.repository.save(t);
		});
	}
}
