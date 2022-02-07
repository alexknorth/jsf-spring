package de.northcodes.course.jsfspring.model;

public enum TransferState {
	
	READY("Transfer is ready for processing"),
	TRANSMITTED("Transmitted successfully"),
	CANCELLED("Your balance is not allowing this Transfer"),
	FAILED("Transfer failed");

	private final String description;
	
	TransferState(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
