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
import mx.nach.task.manager.dto.ProyectoRequest;
import mx.nach.task.manager.dto.ProyectoResponse;
import mx.nach.task.manager.errormagament.BadRequestException;
import mx.nach.task.manager.errormagament.NotFoundException;
import mx.nach.task.manager.model.Proyecto;
import mx.nach.task.manager.persistence.ProyectoRepository;
import mx.nach.task.manager.utils.Utils;

@Service
public class ProyectoService {
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	public ProyectoResponse getProyectoById(Long id) {
		Optional<Proyecto> tareaOpt = proyectoRepository.findById(id); 
		
		if(tareaOpt.isEmpty()) {
			throw new NotFoundException("No se encontró el proyecto con el id solicitado");
		}
		
		Proyecto proyecto = tareaOpt.get();
		
		return ProyectoResponse.fromEntity(proyecto);
	}
	
	public PageResult<ProyectoResponse> getProyectos(Integer pageNumber, Integer pageSize) {
		
		Utils.validatePageSize(pageNumber, pageSize);
		
		PageResult<ProyectoResponse> pageResult = new PageResult<>();
		
		List<ProyectoResponse> proyectos = new LinkedList<>();
		
		if( pageNumber != null && pageSize != null) {
			
			Pageable paging = PageRequest.of(pageNumber, pageSize);
			
			Page<Proyecto> page = proyectoRepository.findAll(paging);
			
			page.get().forEach(proyecto -> {
				proyectos.add(ProyectoResponse.fromEntity(proyecto));
			});
			
			pageResult.setCurrentPage(page.getNumber());
			pageResult.setResultsInPage(page.getNumberOfElements());
			pageResult.setTotalPages(page.getTotalPages());
			pageResult.setTotalResults(page.getTotalElements());
			pageResult.setResults(proyectos);
		} else {
			Iterable<Proyecto> proyectosEntity = proyectoRepository.findAll();
			
			proyectosEntity.forEach(proyecto -> {
				proyectos.add(ProyectoResponse.fromEntity(proyecto));
			});
			
			pageResult.setCurrentPage(0);
			pageResult.setResultsInPage(proyectos.size());
			pageResult.setTotalPages(1);
			pageResult.setTotalResults(Long.valueOf(proyectos.size()));
			pageResult.setResults(proyectos);
		}
		
		return pageResult;
	}
	
	public ProyectoResponse createProyecto(ProyectoRequest request) {
		
		Utils.validateMandatoryFieldsProyecto(request);
		
		Proyecto proyecto = Proyecto.builder()
				.nombre(request.getNombre())
				.descripcion(request.getDescripcion())
				.build();
		
		proyecto =  proyectoRepository.save(proyecto);
		
		return ProyectoResponse.fromEntity(proyecto);
	}
	
	public ProyectoResponse updateProyecto(Long proyectoId, ProyectoRequest request) {
		
		Utils.validateMandatoryFieldsProyecto(request);
		
		if(null == proyectoId) {
			throw new BadRequestException("El id del producto es requerido");
		}
		
		Optional<Proyecto> proyectoOpt = proyectoRepository.findById(proyectoId);
		
		if(proyectoOpt.isEmpty()) {
			throw new BadRequestException("El proyecto no existe");
		}
		
		Proyecto proyecto =  proyectoOpt.get();
		proyecto.setNombre(request.getNombre());
		proyecto.setDescripcion(request.getDescripcion());
		
		proyecto =  proyectoRepository.save(proyecto);
		
		return ProyectoResponse.fromEntity(proyecto);
	}
	
	public void deleteProyectoById(Long id) {
		Optional<Proyecto> proyectoOpt = proyectoRepository.findById(id); 
		
		if(proyectoOpt.isEmpty()) {
			throw new NotFoundException("No se encontró el proyecto con el id solicitado");
		}
		
		Proyecto proyecto = proyectoOpt.get();
		
		proyectoRepository.delete(proyecto);
	}

}
