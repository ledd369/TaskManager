package mx.nach.task.manager.business;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.nach.task.manager.dto.PageResult;
import mx.nach.task.manager.dto.TareaResponse;
import mx.nach.task.manager.dto.TareaRequest;
import mx.nach.task.manager.errormagament.BadRequestException;
import mx.nach.task.manager.errormagament.NotFoundException;
import mx.nach.task.manager.model.Proyecto;
import mx.nach.task.manager.model.Tarea;
import mx.nach.task.manager.persistence.ProyectoRepository;
import mx.nach.task.manager.persistence.TareaRepository;
import mx.nach.task.manager.utils.Utils;

@Service
public class TareaService {
	
	@Autowired
	private TareaRepository tareaRepository;
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	public TareaResponse getTareaById(Long id) {
		Optional<Tarea> tareaOpt = tareaRepository.findById(id); 
		
		if(tareaOpt.isEmpty()) {
			throw new NotFoundException("No se encontró la tarea con el id solicitado");
		}
		
		Tarea tarea = tareaOpt.get();
		
		return TareaResponse.fromEntity(tarea);
	}
	
	public PageResult<TareaResponse> getTareas(Integer pageNumber, Integer pageSize) {
		
		Utils.validatePageSize(pageNumber, pageSize);
		
		PageResult<TareaResponse> pageResult = new PageResult<>();
		
		List<TareaResponse> tareas = new LinkedList<>();
		
		if( pageNumber != null && pageSize != null) {
			
			Pageable paging = PageRequest.of(pageNumber, pageSize);
			
			Page<Tarea> page = tareaRepository.findAll(paging);
			
			page.get().forEach(tarea -> {
				tareas.add(TareaResponse.fromEntity(tarea));
			});
			
			pageResult.setCurrentPage(page.getNumber());
			pageResult.setResultsInPage(page.getNumberOfElements());
			pageResult.setTotalPages(page.getTotalPages());
			pageResult.setTotalResults(page.getTotalElements());
			pageResult.setResults(tareas);
		} else {
			Iterable<Tarea> tareasEntity = tareaRepository.findAll();
			
			tareasEntity.forEach(tarea -> {
				tareas.add(TareaResponse.fromEntity(tarea));
			});
			
			pageResult.setCurrentPage(0);
			pageResult.setResultsInPage(tareas.size());
			pageResult.setTotalPages(1);
			pageResult.setTotalResults(Long.valueOf(tareas.size()));
			pageResult.setResults(tareas);
		}
		
		return pageResult;
	}
	
	public TareaResponse createTarea(TareaRequest request) {
		
		Utils.validateMandatoryFieldsTarea(request);
		
		Optional<Proyecto> proyectoOpt = proyectoRepository.findById(request.getProyectoId());
		
		if(proyectoOpt.isEmpty()) {
			throw new BadRequestException("El proyecto no existe");
		}
		
		Tarea tarea =  Tarea.builder()
				.nombre(request.getNombre())
				.descripcion(request.getDescripcion())
				.estado(request.getEstado().name())
				.proyecto(proyectoOpt.get())
				.build();
		
		tarea =  tareaRepository.save(tarea);
		
		return TareaResponse.fromEntity(tarea);
	}
	
	public TareaResponse updateTarea(Long tareaId, TareaRequest request) {
		
		Utils.validateMandatoryFieldsTarea(request);
		
		if(null == tareaId) {
			throw new BadRequestException("El id de la tarea es requerido");
		}
		
		Optional<Tarea> tareaOpt = tareaRepository.findById(tareaId);
		
		if(tareaOpt.isEmpty()) {
			throw new BadRequestException("La tarea no existe");
		}
		
		Optional<Proyecto> proyectoOpt = proyectoRepository.findById(request.getProyectoId());
		
		if(proyectoOpt.isEmpty()) {
			throw new BadRequestException("El proyecto no existe");
		}
		
		Tarea tarea =  tareaOpt.get();
		tarea.setNombre(request.getNombre());
		tarea.setDescripcion(request.getDescripcion());
		tarea.setEstado(request.getEstado().name());
		tarea.setProyecto(proyectoOpt.get());
		
		tarea =  tareaRepository.save(tarea);
		
		return TareaResponse.fromEntity(tarea);
	}
	
	public void deleteTareaById(Long id) {
		Optional<Tarea> tareaOpt = tareaRepository.findById(id); 
		
		if(tareaOpt.isEmpty()) {
			throw new NotFoundException("No se encontró la tarea con el id solicitado");
		}
		
		Tarea tarea = tareaOpt.get();
		
		tareaRepository.delete(tarea);
	}

}
