package es.arf.mca.tfm.apimanager.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.arf.mca.tfm.apimanager.dto.TaskDTO;
import es.arf.mca.tfm.apimanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/symbols")
@RequiredArgsConstructor
public class TaskController {

	private final TaskService service;

	@Operation(summary = "Start the task for getting the value for a symbol")
	@GetMapping("/{symbol}")
	public ResponseEntity<TaskDTO> getSymbolValue(@PathVariable String symbol) throws Exception {
		return new ResponseEntity<>(this.service.getValueForSymbol(symbol), HttpStatus.OK);
	}

}
