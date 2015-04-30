package com.inedo.buildmaster;

public enum ConnectionType {
	NONE ("None"),
	BASIC ("Basic"),
	NTLM ("Ntlm");
	
	//private final String key;
    private final String label; 
    
	ConnectionType(String label) {
        this.label = label;
    }
	
	public String getId() {
		return this.toString();
	}
	
	public String getLabel() {
		return label;
	}
}
