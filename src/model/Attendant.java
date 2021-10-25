package model;

import java.io.Serializable;

public class Attendant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String id;
	private String plateNumber;
	private String state;
	private Ticket ticket;
	private int n = 0;
	private String arrivalTime;
	private String departureTime;
	private double fee;
	private String status;
	private ReturnTicket returnTicket;

	
	public Attendant(String firstName, String lastName, String id, String plateNumber, String state, Ticket ticket,
			String userName, String password, String arrivalTime, String departureTime, double fee, String status,
			ReturnTicket returnTicket) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.plateNumber = plateNumber;
		this.state = state;
		this.ticket = ticket;
		this.userName = userName;
		this.password = password;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.fee = fee;
		this.status = status;
		this.returnTicket = returnTicket;
	}


	public String getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String generateId() {
		while(n < 9) {
			getNewId();
			n++;
		}
		return id;
	}
	
	public void getNewId() {
		this.id = ((int)(Math.random() * 10 )+ "");
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
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public String getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Attendant [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", plateNumber="
				+ plateNumber + ", state=" + state + ", ticket=" + ticket + "]";
	}


	public ReturnTicket getReturnTicket() {
		return returnTicket;
	}
	
	public void setReturnTicket(ReturnTicket returnTicket) {
		this.returnTicket = returnTicket;
	}
	
}
