package de.northcodes.course.jsfspring.model;

public enum Muskelgruppe {

	BIZEPS("Bizeps"),
	TRIZEPS("Trizeps"),
	BRUST("Brust"),
	UNTERERRUECKEN("Unterer Rücken"),
	OBERERRUECKEN("Oberer Rücken"),
	SCHULTERN("Schultern"),
	GERADEBAUCH("Gerade Bauchmuskeln"),
	SEITLICHERBAUCH("Seitliche Baumuskeln"),
	GESAESS("Gesäß"),
	OBERSCHENKEL("Oberschenkel"),
	WADEN("Waden");

	private final String name;
	
	Muskelgruppe(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public static Muskelgruppe byName(String name) {
		for (Muskelgruppe m : Muskelgruppe.values()) {
			if (m.name.equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	
}
