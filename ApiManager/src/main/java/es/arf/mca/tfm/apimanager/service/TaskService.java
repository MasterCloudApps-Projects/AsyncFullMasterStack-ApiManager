package es.arf.mca.tfm.apimanager.service;

import es.arf.mca.tfm.apimanager.dto.TaskDTO;
import es.arf.mca.tfm.apimanager.exception.TaskNotFoundException;

public interface TaskService {

	public TaskDTO getValueForSymbol(String symbol) throws Exception;

	public TaskDTO getResultForSymbol(String symbol, long id) throws TaskNotFoundException;
}
