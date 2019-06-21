package model;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	private Map<String, Flight> startFlights;
	private Map<String, Flight> endFlights;

	public Destination() {
		super();
	}

	public Destination(String name, String state, String airportName, String airportCode, float lat, float log,
			String picturePath) {
		super();
		this.name = name;
		this.state = state;
		this.airportName = airportName;
		this.airportCode = airportCode;
		this.lat = lat;
		this.log = log;
		this.picturePath = picturePath;
		this.archived = false;
		this.startFlights = new ConcurrentHashMap<>();
		this.endFlights = new ConcurrentHashMap<>();
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

	public Map<String, Flight> getStartFlights() {
		return startFlights;
	}

	public void setStartFlights(Map<String, Flight> startFlights) {
		this.startFlights = startFlights;
	}

	public Map<String, Flight> getEndFlights() {
		return endFlights;
	}

	public void setEndFlights(Map<String, Flight> endFlights) {
		this.endFlights = endFlights;
	}

	@Override
	public String toString() {
		return "Destination [name=" + name + ", state=" + state + ", airportName=" + airportName + ", airportCode="
				+ airportCode + ", lat=" + lat + ", log=" + log + ", picturePath=" + picturePath + ", archived="
				+ archived + "]";
	}
	
}
