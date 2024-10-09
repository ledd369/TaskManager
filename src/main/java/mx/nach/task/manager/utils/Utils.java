package mx.nach.task.manager.utils;

import mx.nach.task.manager.dto.ProyectoRequest;
import mx.nach.task.manager.dto.TareaRequest;
import mx.nach.task.manager.errormagament.BadRequestException;

public class Utils {
	
	public static void validateMandatoryFieldsTarea(TareaRequest request) {
		
		if(null == request.getNombre() || request.getNombre().isBlank()) {
			throw new BadRequestException("El campo nombre es requerido y no puede estar vacío");
		}
		
		if(null == request.getDescripcion() || request.getDescripcion().isBlank()) {
			throw new BadRequestException("El campo descricpión es requerido y no puede estar vacío");
		}
		
		if(null == request.getEstado()) {
			throw new BadRequestException("El campo estado es requerido");
		}
		
		if(null == request.getProyectoId()) {
			throw new BadRequestException("El campo proyectId es requerido");
		}
	}
	
	public static void validateMandatoryFieldsProyecto(ProyectoRequest request) {
		
		if(null == request.getNombre() || request.getNombre().isBlank()) {
			throw new BadRequestException("El campo nombre es requerido y no puede estar vacío");
		}
		
		if(null == request.getDescripcion() || request.getDescripcion().isBlank()) {
			throw new BadRequestException("El campo descricpión es requerido y no puede estar vacío");
		}
	}
	
	public static void validatePageSize(Integer pageNumber, Integer pageSize) {
		if(null == pageNumber && null != pageSize) {
			throw new BadRequestException("El parámetro page es requerido");
		}
		
		if(null == pageSize && null != pageNumber) {
			throw new BadRequestException("El parámetro size es requerido");
		}
	}

}
