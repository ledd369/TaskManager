package mx.nach.task.manager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import mx.nach.task.manager.enums.TaskStatus;
import mx.nach.task.manager.model.Tarea;

@Data
@Builder
public class TareaResponse {
	
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private TaskStatus estado;
	
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private ProyectoDto proyecto;

	public static TareaResponse fromEntity(Tarea tarea) {
		return TareaResponse.builder()
				.id(tarea.getId())
				.nombre(tarea.getNombre())
				.descripcion(tarea.getDescripcion())
				.estado(TaskStatus.valueOf(tarea.getEstado()))
				.proyecto(ProyectoDto.builder().id(tarea.getProyecto().getId()).nombre(tarea.getProyecto().getNombre()).build())
				.build();
	}
	
}
