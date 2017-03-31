package fr.afcepf.atod.exception;

public class WineServerUtilException extends Exception {
	/**
	 * generated Unique Identifier
	 */
	private static final long serialVersionUID = 458591L;
	private WineServerCodeError errorServerUtilWine;
	
	public WineServerUtilException(String message) {
		super(message);
	}

	public WineServerUtilException(WineServerCodeError errorServerUtilWine,
			String message) {
		super(message);
		this.errorServerUtilWine = errorServerUtilWine;
	}

	public WineServerCodeError getErrorServerUtilWine() {
		return errorServerUtilWine;
	}

	public void setErrorServerUtilWine(WineServerCodeError errorServerUtilWine) {
		this.errorServerUtilWine = errorServerUtilWine;
	}
}
