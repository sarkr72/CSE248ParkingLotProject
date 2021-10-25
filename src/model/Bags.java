package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeMap;

public class Bags implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberOfSpots = 50;
	private HashMap<String, Attendant> attendants;
	private int n1;
	private int n2;
	private int n3;
	private int n4;
	private int n5 = 0;
	private TreeMap<String, Attendant> attendantsHistory;
	private TreeMap<String, Ticket> tickets;
	private TreeMap<Integer, ReturnTicket> returnTickets;
	private TreeMap<String, Admin> admins;
	private ParkingLot parkingLot;
	
	public Bags() {
		admins = new TreeMap<>();
		attendants = new HashMap<>(numberOfSpots);
		tickets = new TreeMap<>();
		returnTickets = new TreeMap<>();
		parkingLot = new ParkingLot(numberOfSpots);
		attendantsHistory = new TreeMap<>();
		n1 = 0;
		n2 = 0;
		n3 = 0;
		n4 = 0;
//		createSpotsAndDistance();
	}
//	
//	public void createSpotsAndDistance() {
//		while(n5 < numberOfSpots) {
//			 spotN = (int)(Math.random()*1000);
//			 distance = (int)(Math.random() *(spotN * 10));
//			parkingLot.getSpotNumbers().put(spotN+"", distance+"");
//			n5++;
//		}
//	}
	
	public void addAttendantHistory(Attendant attendant) {
		attendantsHistory.put(attendant.getUserName(), attendant);
	}
	
	public TreeMap<String, Attendant> getAttendantsHistory() {
		return attendantsHistory;
	}

	public void setAttendantsHistory(TreeMap<String, Attendant> attendantsHistory) {
		this.attendantsHistory = attendantsHistory;
	}

	public TreeMap<String, Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(TreeMap<String, Admin> admins) {
		this.admins = admins;
	}

	public void setTickets(TreeMap<String, Ticket> tickets) {
		this.tickets = tickets;
	}

	public void setReturnTickets(TreeMap<Integer, ReturnTicket> returnTickets) {
		this.returnTickets = returnTickets;
	}

	public Ticket deleteTicket(String id) {
		Ticket t = null;
		if(tickets.containsKey(id)){
			 t = tickets.remove(id);
		}
		return t;
	}
	
	public ReturnTicket deleteReturnTicket(String id) {
		ReturnTicket rt = null;
		if(tickets.containsKey(id)){
			rt = returnTickets.remove(id);
		}
		return rt;
	}
	
	public void addAdmin(Admin admin) {
		admins.put(admin.getUserName(), admin);
		n4++;
	}
	
	public void addAttendant(Attendant attendant) {
		attendants.put(attendant.getUserName(), attendant);
		n1++;
	}

	public void addTicket(Ticket ticket) {
		tickets.put(ticket.getId(), ticket);
		n2++;
	}
	
	public void addReturnTicket(ReturnTicket returnTicket) {
		returnTickets.put(returnTicket.getTicketNumber(), returnTicket);
		n3++;
	}
	
	public Admin searchAdmin(String username) {
		Admin admin = null;
		if (admins.containsKey(username)) {
			admin = admins.get(username);
		}
		return admin;
	}

	public Attendant searchAttendant(String id) {
		Attendant attendant = null;
		if (attendantsHistory.containsKey(id)) {
			attendant = attendantsHistory.get(id);
		}
		return attendant;
	}

	public Ticket searchTicket(String number) {
		Ticket ticket = null;
		if (tickets.containsKey(number)) {
			ticket = tickets.get(number);
		}
		return ticket;
	}

	public ReturnTicket searchReturnTicket(String number) {
		ReturnTicket returnTicket = null;
		if (returnTickets.containsKey(number)) {
			returnTicket = returnTickets.get(number);
		}
		return returnTicket;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public HashMap<String, Attendant> getAttendants() {
		return attendants;
	}

	public void setAttendants(HashMap<String, Attendant> attendants) {
		this.attendants = attendants;
	}

	
	public TreeMap<String, Ticket> getTickets() {
		return tickets;
	}

	public TreeMap<Integer, ReturnTicket> getReturnTickets() {
		return returnTickets;
	}

	public int getNumberOfSpots() {
		return numberOfSpots;
	}

	public void setNumberOfSpots(int numberOfSpots) {
		this.numberOfSpots = numberOfSpots;
	}

	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public int getN3() {
		return n3;
	}

	public void setN3(int n3) {
		this.n3 = n3;
	}

	@Override
	public String toString() {
		return "Bag [numberOfSpots=" + numberOfSpots + ", parkingLot=" + parkingLot + ", attendants=" + attendants
				+ ", n1=" + n1 + ", n2=" + n2 + ", n3=" + n3 + ", tickets=" + tickets + ", returnTickets="
				+ returnTickets + "]";
	}
}
