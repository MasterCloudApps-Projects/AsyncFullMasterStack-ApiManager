package es.arf.mca.tfm.apimanager.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.arf.mca.tfm.apimanager.dto.TaskDTO;
import es.arf.mca.tfm.apimanager.entity.Task;
import es.arf.mca.tfm.apimanager.exception.TaskNotFoundException;
import es.arf.mca.tfm.apimanager.repository.TaskRepository;
import es.arf.mca.tfm.apimanager.service.TaskService;
import es.arf.mca.tfm.apimanager.utils.Converter;
import es.arf.mca.tfm.apimanager.utils.Status;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository repository;

	private final RabbitTemplate rabbit;

	@Value("${rabbitmq.queue-tasks}")
	private String queueTasks;

	@Override
	public TaskDTO getValueForSymbol(String symbol) throws Exception {

		Task task = this.repository.save(createTask(symbol));

		publishTask(task);

		return convertToDTO(task);
	}

	@Override
	public TaskDTO getResultForSymbol(String symbol, long id) throws TaskNotFoundException {
		return this.repository.findByIdAndSymbol(id, symbol)
				.map(t -> Converter.convertToClass(t, TaskDTO.class))	
				.orElseThrow(() -> new TaskNotFoundException(symbol, id));
	}
	
	private Task createTask(String symbol) {
		Task task = new Task();
		task.setSymbol(symbol);
		task.setStatus(Status.PENDING);

		return task;
	}

	private void publishTask(Task task) throws Exception {
		this.rabbit.convertAndSend(queueTasks, getJSONFormat(task));
	}

	private String getJSONFormat(Task task) throws Exception {

		try {
			return Converter.convertToJSON(task);
		} catch (JsonProcessingException e) {
			throw new Exception(e.getMessage());
		}
	}

	private TaskDTO convertToDTO(Task task) {
		return Converter.convertToClass(task, TaskDTO.class);
	}

}
