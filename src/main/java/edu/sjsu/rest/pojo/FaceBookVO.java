package edu.sjsu.rest.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mongodb.ReflectionDBObject;

@JsonIgnoreProperties
public class FaceBookVO extends ReflectionDBObject {

	private Data[] data;
	
	private Paging paging;

	public FaceBookVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Data[] getData() {
		return data;
	}




	public void setData(Data[] data) {
		this.data = data;
	}




	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	
	
}
