package com.simon.backstage.domain.model;

public class ManagerWithToken {
	
	private Manager manager;
	
	private String token;

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
