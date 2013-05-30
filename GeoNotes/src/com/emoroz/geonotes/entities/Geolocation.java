package com.emoroz.geonotes.entities;

public class Geolocation {
	private long id_;
	private String name_;
	private int drawableId_;
	private double latitude_;
	private double longitude_;
	
	public Geolocation(String name, int drawableId, double latitude, double longitude) {
		this(0, name, drawableId, latitude, longitude);
	}
	
	public Geolocation(long id, String name, int drawableId, double latitude, double longitude) {
		id_ = id;
		name_ = name;
		drawableId_ = drawableId;
		latitude_ = latitude;
		longitude_ = longitude;
	}

	public long getId() {
		return id_;
	}

	public String getName() {
		return name_;
	}

	public int getDrawableId() {
		return drawableId_;
	}

	public double getLatitude() {
		return latitude_;
	}

	public double getLongitude() {
		return longitude_;
	}
	
	
}
