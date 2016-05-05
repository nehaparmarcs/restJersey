package edu.sjsu.rest.pojo;

public class ClientRequestDetails {

	private int clientID;
	private int serverID;
	
	
	public ClientRequestDetails() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "ClientRequestDetails [clientID=" + clientID + ", serverID=" + serverID + "]";
	}
	
	
}
