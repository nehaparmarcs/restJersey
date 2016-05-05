package edu.sjsu.rest.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mongodb.ReflectionDBObject;

@JsonIgnoreProperties
public class ResourceVO  {

	String type1;
	String value1;
	String type2;
	String value2;
	
	public ResourceVO() {
		super();
	}

	public ResourceVO(String type1, String value1, String type2, String value2) {
		super();
		this.type1 = type1;
		this.value1 = value1;
		this.type2 = type2;
		this.value2 = value2;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	
	
}
