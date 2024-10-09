package mx.nach.task.manager.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageResult<T> {

	private Integer currentPage;
	
    private Integer resultsInPage;

    private Integer totalPages;

    private Long totalResults;

    private List<T> results;
}
