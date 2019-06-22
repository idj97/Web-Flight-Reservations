package model;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long number; 
	private Date dateCreated;
	private ReservationType type;
	private Flight flight;
	private User owner;
	private int passengerNumber;

	public Reservation() {
		super();
	}
	
	public Reservation(Long number, ReservationType type) {
		this.dateCreated = new Date();
		this.type = type;
		this.number = number;
	}
	
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public ReservationType getType() {
		return type;
	}

	public void setType(ReservationType type) {
		this.type = type;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public int getPassengerNumber() {
		return passengerNumber;
	}

	public void setPassengerNumber(int passengerNumber) {
		this.passengerNumber = passengerNumber;
	}

}
