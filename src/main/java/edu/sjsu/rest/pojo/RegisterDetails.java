package edu.sjsu.rest.pojo;

public class RegisterDetails {
	private int bootStrapID;
	private String manufactureNo;
	private String versionNo;
	private String modelNo;
	private int clientID;
	
	public RegisterDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RegisterDetails(int bootStrapID, String manufactureNo, String versionNo, String modelNo, int clientID) {
		super();
		this.bootStrapID = bootStrapID;
		this.manufactureNo = manufactureNo;
		this.versionNo = versionNo;
		this.modelNo = modelNo;
		this.clientID = clientID;
	}


	@Override
	public String toString() {
		return "RegisterDetails [bootStrapID=" + bootStrapID + ", manufactureNo=" + manufactureNo + ", versionNo="
				+ versionNo + ", modelNo=" + modelNo + ", clientID=" + clientID + "]";
	}


	public int getClientID() {
		return clientID;
	}




	public void setClientID(int clientID) {
		this.clientID = clientID;
	}




	public int getBootStrapID() {
		return bootStrapID;
	}
	public void setBootStrapID(int bootStrapID) {
		this.bootStrapID = bootStrapID;
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
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	
	
	
	
	
}
