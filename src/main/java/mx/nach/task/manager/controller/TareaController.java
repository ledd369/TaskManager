package mx.nach.task.manager.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import mx.nach.task.manager.business.TareaService;
import mx.nach.task.manager.dto.PageResult;
import mx.nach.task.manager.dto.TareaResponse;
import mx.nach.task.manager.dto.TareaRequest;
import mx.nach.task.manager.errormagament.ErrorManager;

@RestController
public class TareaController {
	
	@Autowired
    private ErrorManager errorManager;
	
	@Autowired
	private TareaService tareaService;
	
	@GetMapping(value = "/tareas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TareaResponse> getTareaById(@PathVariable Long id) {
		try {
			return new ResponseEntity<TareaResponse>(tareaService.getTareaById(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage());
		}
	}
	
	@GetMapping(value = "/tareas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageResult<TareaResponse>> getTareas(@PathParam(value = "page") Integer page, @PathParam(value = "size") Integer size) {
		try {
			return new ResponseEntity<PageResult<TareaResponse>>(tareaService.getTareas(page, size), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage());
		}
	}
	
	@PostMapping(value = "/tareas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TareaResponse> agregarTarea(@RequestBody TareaRequest request) {
		try {
			return new ResponseEntity<TareaResponse>(tareaService.createTarea(request), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage());
		}
	}
	
	@PutMapping(value = "/tareas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TareaResponse> updateTareaById(@PathVariable Long id, @RequestBody TareaRequest request) {
		try {
			return new ResponseEntity<TareaResponse>(tareaService.updateTarea(id, request), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/tareas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteTareaById(@PathVariable Long id) {
		try {
			tareaService.deleteTareaById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage());
		}
	}
}
