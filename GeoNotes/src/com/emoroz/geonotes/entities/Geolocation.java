package com.emoroz.geonotes.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Geolocation implements Parcelable {
	private long id_;
	private String name_;
	private double latitude_;
	private double longitude_;

	public Geolocation(String name, double latitude, double longitude) {
		this(0, name, latitude, longitude);
	}

	public Geolocation(long id, String name, double latitude, double longitude) {
		id_ = id;
		name_ = name;
		latitude_ = latitude;
		longitude_ = longitude;
	}

	public Geolocation() {
		this(0, null, 0, 0);
	}

	public Geolocation(Parcel source) {
		id_ = source.readLong();
		name_ = source.readString();
		latitude_ = source.readDouble();
		longitude_ = source.readDouble();
	}

	public long getId() {
		return id_;
	}

	public String getName() {
		return name_;
	}

	public double getLatitude() {
		return latitude_;
	}

	public double getLongitude() {
		return longitude_;
	}

	public void setId(long id) {
		this.id_ = id;
	}

	public void setName(String name) {
		this.name_ = name;
	}

	public void setLatitude(double latitude) {
		this.latitude_ = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude_ = longitude;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id_);
		dest.writeString(name_);
		dest.writeDouble(latitude_);
		dest.writeDouble(longitude_);
	}

	public static final Parcelable.Creator<Geolocation> CREATOR = new Creator<Geolocation>() {
		@Override
		public Geolocation[] newArray(int size) {
			return new Geolocation[size];
		}

		@Override
		public Geolocation createFromParcel(Parcel source) {
			return new Geolocation(source);
		}
	};

}
