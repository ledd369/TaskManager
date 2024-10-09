package mx.nach.task.manager.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

import mx.nach.task.manager.model.Proyecto;

public interface ProyectoRepository extends PagingAndSortingRepository<Proyecto, Long> {

}
