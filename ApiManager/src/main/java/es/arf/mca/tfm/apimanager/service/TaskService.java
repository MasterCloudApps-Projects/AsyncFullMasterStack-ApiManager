package es.arf.mca.tfm.apimanager.service;

import es.arf.mca.tfm.apimanager.dto.TaskDTO;

public interface TaskService {

	public TaskDTO getValueForSymbol(String symbol) throws Exception;
}
