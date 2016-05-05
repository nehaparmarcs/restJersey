package edu.sjsu.rest.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientResponseDetails {

	
	private int clientID;
	private int serverID;
	private String bootStrapId;
	private String manufactureNo;
	private String versionNo;
	
	
	
	public ClientResponseDetails() {
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
	public String getBootStrapId() {
		return bootStrapId;
	}
	public void setBootStrapId(String bootStrapId) {
		this.bootStrapId = bootStrapId;
	}
	public String getManufactureNo() {
		return manufactureNo;
	}
	public void setManufactureNo(String manufactureNo) {
		this.manufactureNo = manufactureNo;
	}
	
	
	
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public String toString() {
		return "ClientResponseDetails [clientID=" + clientID + ", serverID=" + serverID + ", bootStrapId=" + bootStrapId
				+ ", manufactureNo=" + manufactureNo + ", versionNo=" + versionNo + "]";
	}
	
	
}
