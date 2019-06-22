package dto;

import javax.validation.constraints.NotNull;

public class FilterDTO {
	@NotNull
	private String flightType;
	@NotNull
	private String flightNumber;
	@NotNull
	private boolean sort;

	public FilterDTO() {
		super();
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

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

}
