package edu.sjsu.rest.pojo;

public class BootStrapResponse {

	
	private int clientID;
	private int serverID;
	private String bootStrapID;
	
	
	
	public BootStrapResponse() {
		super();
	}
	
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public int getServerID() {
		return serverID;
	}
	public void setServerID(int serverID) {
		this.serverID = serverID;
	}
	public String getBootStrapID() {
		return bootStrapID;
	}
	public void setBootStrapID(String bootStrapID) {
		this.bootStrapID = bootStrapID;
	}

	@Override
	public String toString() {
		return "BootStrapResponse [clientID=" + clientID + ", serverID=" + serverID + ", bootStrapID=" + bootStrapID
				+ "]";
	}
	
}
