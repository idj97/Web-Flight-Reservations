package dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import model.Destination;

public class DestinationDTO {
	@NotBlank private String name;
	@NotBlank private String state;
	@NotBlank private String airportName;
	@NotBlank private String airportCode;
	@NotNull private Float lat;
	@NotNull private Float log;
	@NotNull private Boolean archived;
	private String picturePath;
	
	public DestinationDTO() {
		super();
	}
	
	
	
	public DestinationDTO(Destination d) {
		name = d.getName();
		state = d.getState();
		airportName = d.getAirportName();
		airportCode = d.getAirportCode();
		lat = d.getLat();
		log = d.getLog();
		archived = d.isArchived();
		setPicturePath(d.getPicturePath());
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

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLog() {
		return log;
	}

	public void setLog(Float log) {
		this.log = log;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}


	public String getPicturePath() {
		return picturePath;
	}


	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}


}
