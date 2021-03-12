package com.blockhead7360.dms.launcher.internet;

public class InternetServerData {
	
	private String script;
	private String id;
	private boolean accounts;
	
	public InternetServerData(String script, String id, String accounts) {
		this.script = script;
		this.id = id;
		
		this.accounts = accounts.equals("yes");
		
	}
	
	public String getScript() {
		return script;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean usesAccounts() {
		return accounts;
	}
	
}
