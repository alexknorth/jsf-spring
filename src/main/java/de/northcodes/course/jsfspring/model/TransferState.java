package de.northcodes.course.jsfspring.model;

public enum TransferState {
	
	READY("Transaction is ready for processing"),
	TRANSMITTED("Transmitted successfully"),
	CANCELLED("Your balance is not allowing this Transmission"),
	FAILED("Transaction failed");

	private final String description;
	
	private TransferState(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
