package mx.nach.task.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "mx.nach.task.manager.persistence", "mx.nach.task.manager.controller", "mx.nach.task.manager.business", "mx.nach.task.manager.errormagament"  })
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

}
