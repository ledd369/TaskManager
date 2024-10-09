package mx.nach.task.manager.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import mx.nach.task.manager.enums.TaskStatus;
import mx.nach.task.manager.model.Proyecto;

@Data
@Builder
public class ProyectoResponse {
	
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private List<TareaResponse> tareas;

	public static ProyectoResponse fromEntity(Proyecto proyecto) {
		ProyectoResponse proyectoResponse = ProyectoResponse.builder()
				.id(proyecto.getId())
				.nombre(proyecto.getNombre())
				.descripcion(proyecto.getDescripcion())
				.build();
		List<TareaResponse> tareasResponse = new LinkedList<>();
		if(null != proyecto.getTareas()) {
			proyecto.getTareas().forEach(tarea -> {
				tareasResponse.add(TareaResponse.builder()
						.id(tarea.getId())
						.nombre(tarea.getNombre())
						.descripcion(tarea.getDescripcion())
						.estado(TaskStatus.valueOf(tarea.getEstado()))
						.build());
			});
		}
		
		proyectoResponse.setTareas(tareasResponse);
		
		return proyectoResponse;
	}

}
