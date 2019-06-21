package dto;

import java.text.SimpleDateFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import model.Reservation;
import model.ReservationType;

public class ReservationDTO {
	private long number;
	private String dateCreated;
	@NotNull private ReservationType type;
	@Positive private int passengerNum;
	@NotBlank private String flightNum;

	public ReservationDTO() {
		super();
	}

	public ReservationDTO(long number, String dateCreated, ReservationType type, int passengerNum, String flightNum) {
		super();
		this.number = number;
		this.dateCreated = dateCreated;
		this.type = type;
		this.passengerNum = passengerNum;
		this.flightNum = flightNum;
	}

	public ReservationDTO(Reservation r) {
		super();
		this.number = r.getNumber();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.dateCreated = sdf.format(r.getDateCreated());
		this.type = r.getType();
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public ReservationType getType() {
		return type;
	}

	public void setType(ReservationType type) {
		this.type = type;
	}

	public int getPassengerNum() {
		return passengerNum;
	}

	public void setPassengerNum(int passengerNum) {
		this.passengerNum = passengerNum;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

}
