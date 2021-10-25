package model;

import java.io.Serializable;

public class ReturnTicket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private int ticketNumber;
	private String plateNumber;
	private String arrivalTime;
	private String state;
	private String firstName;
	private String lastName;
	private String id;
	private String departureTime;
	private double fee;
	private String status;

	public ReturnTicket(String plateNumber, String date, String arrivalTime, String state, String firstName, String lastName, String id,
			String departureTime, double fee, String status) {
		super();
		this.plateNumber = plateNumber;
		this.ticketNumber++;
		this.date = date;
		this.arrivalTime = arrivalTime;
		this.state = state;
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.departureTime = departureTime;
		this.fee = fee;
		this.status = status;
	}

	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPlateNumber() {
		return plateNumber;
	}


	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}


	public int getTicketNumber() {
		return ticketNumber;
	}


	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}


	public String getDate() {
		return date;
	}

//	public void setDate(String date) {
//		this.date = date;
//	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ReturnTicket [date=" + date + ", arrivalTime=" + arrivalTime + ", state=" + state + ", firstName="
				+ firstName + ", lastName=" + lastName + ", id=" + id + ", departureTime=" + departureTime + ", fee="
				+ fee + "]";
	}

}
