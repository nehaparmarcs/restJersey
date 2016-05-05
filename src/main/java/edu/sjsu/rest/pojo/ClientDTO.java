package edu.sjsu.rest.pojo;

public class ClientDTO {

	int id; 
	String data;
	String data2;
	
	@Override
	public String toString() {
		return "ClientDTO [id=" + id + ", data=" + data + ", data2=" + data2 + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	
}
