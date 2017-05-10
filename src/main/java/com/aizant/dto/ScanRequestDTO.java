package com.aizant.dto;

public class ScanRequestDTO {

	private String scannedId;
	private String username;
	private String password;
	private String token;
	
	public String getScannedId() {
		return this.scannedId;
	}
	
	public void setScannedId(String scannedId) {
		this.scannedId = scannedId;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}	
	
	public String getToken() {
		return this.token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}		
}