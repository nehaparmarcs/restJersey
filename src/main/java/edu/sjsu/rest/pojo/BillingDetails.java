package edu.sjsu.rest.pojo;

public class BillingDetails {

	String start_time;
	String end_time;
	String plan_type;
	
	
	public BillingDetails() {
		super();
	}

	public BillingDetails(String start_time, String end_time, String plan_type) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		this.plan_type = plan_type;
	}



	public String getPlan_type() {
		return plan_type;
	}



	public void setPlan_type(String plan_type) {
		this.plan_type = plan_type;
	}



	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {
		return "Payment Information for the client --> [start_time=" + start_time + ", end_time=" + end_time + ", plan_type=" + plan_type + "]";
	}
	
	
	
}
