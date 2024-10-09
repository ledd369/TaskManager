package mx.nach.task.manager.dto;

import lombok.Data;
import mx.nach.task.manager.enums.TaskStatus;

@Data
public class TareaRequest {
	
	private String nombre;
	
	private String descripcion;
	
	private Long proyectoId;
	
	private TaskStatus estado;

}
