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

import mx.nach.task.manager.business.ProyectoService;
import mx.nach.task.manager.dto.PageResult;
import mx.nach.task.manager.dto.ProyectoRequest;
import mx.nach.task.manager.dto.ProyectoResponse;
import mx.nach.task.manager.errormagament.ErrorManager;

@RestController
public class ProyectoController {
	
	@Autowired
    private ErrorManager errorManager;
	
	@Autowired
	private ProyectoService proyectoService;
	
	@GetMapping(value = "/proyectos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProyectoResponse> getProyectoById(@PathVariable Long id) {
		try {
			return new ResponseEntity<ProyectoResponse>(proyectoService.getProyectoById(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage());
		}
	}
	
	@GetMapping(value = "/proyectos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageResult<ProyectoResponse>> getProyectos(@PathParam(value = "page") Integer page, @PathParam(value = "size") Integer size) {
		try {
			return new ResponseEntity<PageResult<ProyectoResponse>>(proyectoService.getProyectos(page, size), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage());
		}
	}
	
	@PostMapping(value = "/proyectos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProyectoResponse> agregarProyecto(@RequestBody ProyectoRequest request) {
		try {
			return new ResponseEntity<ProyectoResponse>(proyectoService.createProyecto(request), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage());
		}
	}
	
	@PutMapping(value = "/proyectos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProyectoResponse> updateProyectoById(@PathVariable Long id, @RequestBody ProyectoRequest request) {
		try {
			return new ResponseEntity<ProyectoResponse>(proyectoService.updateProyecto(id, request), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/proyectos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteProyectoById(@PathVariable Long id) {
		try {
			proyectoService.deleteProyectoById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage());
		}
	}
}
