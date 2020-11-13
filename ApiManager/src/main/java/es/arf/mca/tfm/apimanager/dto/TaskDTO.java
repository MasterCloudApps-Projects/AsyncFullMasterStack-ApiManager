package es.arf.mca.tfm.apimanager.dto;

import es.arf.mca.tfm.apimanager.utils.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaskDTO {
	
	private long id;
	
	private Status status;
	
	private String symbol;
	
	private float value;
}
