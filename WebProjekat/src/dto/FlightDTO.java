package dto;

import java.text.SimpleDateFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import model.Flight;
import model.FlightType;

public class FlightDTO {
	@NotBlank private String number;
	@Positive private float price;
	@NotBlank private String airplane;
	@Positive private int firstSize;
	@Positive private int businessSize;
	@Positive private int economySize;
	@NotBlank private String date;
	@NotNull private FlightType type;
	@NotNull private String start;
	@NotNull private String end;

	public FlightDTO() {
		super();
	}
	
	public FlightDTO(Flight f) {
		number = f.getNumber();
		price = f.getPrice();
		airplane = f.getAirplane();
		firstSize = f.getFirstSize();
		businessSize = f.getBusinessSize();
		economySize = f.getEconomySize();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		date = sdf.format(f.getDate());
		type = f.getType();
		start = f.getStart().getName();
		end = f.getEnd().getName();
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getAirplane() {
		return airplane;
	}

	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}

	public int getFirstSize() {
		return firstSize;
	}

	public void setFirstSize(int firstSize) {
		this.firstSize = firstSize;
	}

	public int getBusinessSize() {
		return businessSize;
	}

	public void setBusinessSize(int businessSize) {
		this.businessSize = businessSize;
	}

	public int getEconomySize() {
		return economySize;
	}

	public void setEconomySize(int economySize) {
		this.economySize = economySize;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public FlightType getType() {
		return type;
	}

	public void setType(FlightType type) {
		this.type = type;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
