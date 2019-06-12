package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;
	private String number;
	private float price;
	private String airplane;
	private int firstSize;
	private int businessSize;
	private int economySize;
	private Date date;
	private FlightType type;
	private Destination start;
	private Destination end;
	private List<Reservation> reservations;

	public Flight() {
		super();
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public FlightType getType() {
		return type;
	}

	public void setType(FlightType type) {
		this.type = type;
	}

	public Destination getStart() {
		return start;
	}

	public void setStart(Destination start) {
		this.start = start;
	}

	public Destination getEnd() {
		return end;
	}

	public void setEnd(Destination end) {
		this.end = end;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
