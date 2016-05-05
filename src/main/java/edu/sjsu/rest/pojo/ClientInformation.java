package edu.sjsu.rest.pojo;

public class ClientInformation {
	private int bootID;
	private String mNo;
	private String verNo;
	
	public int getBootID() {
		return bootID;
	}
	public void setBootID(int id) {
		this.bootID = id;
	}
	public String getmNo() {
		return mNo;
	}
	public void setmNo(String name) {
		this.mNo = name;
	}
	public String getVerNo() {
		return verNo;
	}
	public void setVerNo(String location) {
		this.verNo = location;
	}
}
