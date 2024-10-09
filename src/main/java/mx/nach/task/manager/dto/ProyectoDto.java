package mx.nach.task.manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProyectoDto {

	private Long id;
	
	private String nombre;
}
