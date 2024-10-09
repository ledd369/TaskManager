package mx.nach.task.manager.errormagament;

public class BadRequestException extends IllegalArgumentException {

	private static final long serialVersionUID = 4561628371234007391L;

	public BadRequestException(String message) { // message to show in service
		super(message);
	}
}
