package es.arf.mca.tfm.apimanager.exception;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 9066938572558338768L;
	
	public TaskNotFoundException(String symbol, long id) {
		super("No task for symbol: " + symbol + " and id: " + id + " found");
	}

}
