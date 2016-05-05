package edu.sjsu.rest.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mongodb.ReflectionDBObject;

@JsonIgnoreProperties
public class ClientData {

	String objID;
	String resourceID;
	ResourceVO resourceValue;
	
	public ClientData() {
		super();
	}

	public String getObjID() {
		return objID;
	}

	public void setObjID(String objectID) {
		this.objID = objectID;
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	public ResourceVO getResourceValue() {
		return resourceValue;
	}

	public void setResourceValue(ResourceVO resourceValue) {
		this.resourceValue = resourceValue;
	}
	
	
	
}
