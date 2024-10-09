package mx.nach.task.manager.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import mx.nach.task.manager.model.Tarea;

@Repository
public interface TareaRepository extends PagingAndSortingRepository<Tarea, Long> {

}
