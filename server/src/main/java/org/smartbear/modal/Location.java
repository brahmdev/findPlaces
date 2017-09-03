package org.smartbear.modal;

/**
 * 
 * @author KalpDev
 * @version 1.0
 * @since 02-09-2017
 *
 */
public class Location {
	private String lng;

	private String lat;

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "ClassPojo [lng = " + lng + ", lat = " + lat + "]";
	}
}
