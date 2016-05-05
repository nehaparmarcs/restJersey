package edu.sjsu.rest.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mongodb.ReflectionDBObject;

@JsonIgnoreProperties
public class Paging extends ReflectionDBObject {

	Cursors cursors;
	String next;
	public Paging() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cursors getCursors() {
		return cursors;
	}
	public void setCursors(Cursors cursors) {
		this.cursors = cursors;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	
	
	
}
