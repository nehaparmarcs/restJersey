package com.sjsu.server;

public class Location {
	
	private String registrationID;
	private double latitude;
	private double longitude;
	private double altitude;
	private String direction;
	private float speed;
	private String serverID;
	
	public Location() {
		super();
	}

	public Location(String registrationID, double latitude, double longitude, double altitude, String direction,
			float speed, String serverID) {
		super();
		this.registrationID = registrationID;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.direction = direction;
		this.speed = speed;
		this.serverID = serverID;
	}

	public String getRegistrationID() {
		return registrationID;
	}

	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public String getServerID() {
		return serverID;
	}

	public void setServerID(String serverID) {
		this.serverID = serverID;
	}

	@Override
	public String toString() {
		return "Location [registrationID=" + registrationID + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", altitude=" + altitude + ", direction=" + direction + ", speed=" + speed + ", server ID =" + serverID + "]";
	}

}
