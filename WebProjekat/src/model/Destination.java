package model;

import java.io.Serializable;
import java.util.List;

public class Destination implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String state;
	private String airportName;
	private String airportCode;
	private float lat;
	private float log;
	private String picturePath;
	private boolean archived;
	private List<Flight> startFlights;
	private List<Flight> endFlights;

	public Destination() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLog() {
		return log;
	}

	public void setLog(float log) {
		this.log = log;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public List<Flight> getStartFlights() {
		return startFlights;
	}

	public void setStartFlights(List<Flight> startFlights) {
		this.startFlights = startFlights;
	}

	public List<Flight> getEndFlights() {
		return endFlights;
	}

	public void setEndFlights(List<Flight> endFlights) {
		this.endFlights = endFlights;
	}

}
