package enis.backend.model;

public enum StavVozu {
	OPRAVA("V opravě"), PROVOZ("V provozu");
	
	private String name;
	
	StavVozu(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
