package mx.nach.task.manager.errormagament;

public class NotFoundException extends IllegalArgumentException {

	private static final long serialVersionUID = 3411628371234237644L;

	public NotFoundException(String message) { // message to show in service
		super(message);
	}
}
