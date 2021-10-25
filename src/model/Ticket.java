package model;

import java.io.Serializable;

public class Ticket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private String plateNumber;
	private String arrivalTime;
	private String state;
	private String firstName;
	private String lastName;
	private String id;
	private String status;
	private String parkStatus;
	private int spotNumber;
	
	public Ticket(String plateNumber, String date, String arrivalTime, String state, String firstName, String lastName, String id, String status, int spotNumber, String parkStatus) {
		super();
		this.status = status;
		this.plateNumber = plateNumber;
		this.date = date;
		this.arrivalTime = arrivalTime;
		this.state = state;
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.spotNumber = spotNumber;
		this.parkStatus = parkStatus;
	}

	
	public String getParkStatus() {
		return parkStatus;
	}


	public void setParkStatus(String parkStatus) {
		this.parkStatus = parkStatus;
	}


	public int getSpotNumber() {
		return spotNumber;
	}


	public void setSpotNumber(int spotNumber) {
		this.spotNumber = spotNumber;
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


	public String getDate() {
		return date;
	}
	public void setString(String String) {
		this.date = String;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getfirstName() {
		return firstName;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getlastName() {
		return firstName;
	}
	public void setlastName(String firstName) {
		this.firstName = firstName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Ticket [String=" + date + ", plateNumber=" + plateNumber + ", arrivalTime=" + arrivalTime + ", state="
				+ state + ", firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + "]";
	}
}
