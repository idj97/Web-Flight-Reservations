package dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CombinedSearchDTO {
	@Valid
	private BasicSearchDTO basicSearch;
	@NotNull
	private String flightType;
	@NotNull
	private String flightNumber;
	
	public CombinedSearchDTO() {
		super();
	}
	
	public BasicSearchDTO getBasicSearch() {
		return basicSearch;
	}
	public void setBasicSearch(BasicSearchDTO basicSearch) {
		this.basicSearch = basicSearch;
	}
	public String getFlightType() {
		return flightType;
	}
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	
		
}
