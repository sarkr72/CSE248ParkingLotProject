package model;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class ParkingLot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sizeOfSpot = 10;
	private int numberOfSpots;
	private int totalEmptySpots;
	private int spotsTaken = 0;
	private SpotNAndDistance spot;
	List<Integer> spotList = new LinkedList<Integer>();
	List<Integer> distanceList = new LinkedList<Integer>();
	TreeMap<Integer, SpotNAndDistance> spotMap = new TreeMap<>();
	
	public ParkingLot(int numberOfSpots) {
		super();
		this.numberOfSpots = numberOfSpots;
		this.totalEmptySpots = numberOfSpots;
		makeSpots(numberOfSpots);
		
	}

	public TreeMap<Integer, SpotNAndDistance> getSpotMap() {
		return spotMap;
	}

	public void setSpotMap(TreeMap<Integer, SpotNAndDistance> spotMap) {
		this.spotMap = spotMap;
	}

	public void makeSpots(int numberOfSpots) {
		int n = 0;
		while (n < numberOfSpots) {
			int randSpot = (int) (Math.random() * 500);
			if (!spotList.contains(randSpot)) {
				int randDistance = (int) (Math.random() * (numberOfSpots * 10));
				if (!distanceList.contains(randDistance)) {
					spotList.add(randSpot);
					distanceList.add(randDistance);
					this.spot = new SpotNAndDistance(randSpot, randDistance);
					spotMap.put(randDistance, spot);
				}} else {
					continue;
				}
				n++;
		}
	}
//	public void deleteSpot(SpotNAndDistance spotNumber) {
//		if(sAndDList.contains(spotNumber)) {
//			sAndDList.remove(spotNumber);
//		}else {
//			System.out.println("could not find to delete attendant");
//		}
//	}
//	public HashMap<String, String> getSpotNumbers() {
//		return spotNumbers;
//	}

//	public void setSpotNumbers(HashMap<String, String> spotNumbers) {
//		this.spotNumbers = spotNumbers;
//	}

	public int getTotalEmptySpots() {
		return totalEmptySpots;
	}

	public int getSizeOfSpot() {
		return sizeOfSpot;
	}

	public void setSizeOfSpot(int sizeOfSpot) {
		this.sizeOfSpot = sizeOfSpot;
	}

	public int getNumberOfSpots() {
		return numberOfSpots;
	}

	public void setNumberOfSpots(int numberOfSpots) {
		this.numberOfSpots = numberOfSpots;
	}

	public int getSpotsTaken() {
		return spotsTaken;
	}

	public void setSpotsTaken(int spotsTaken) {
		this.spotsTaken = +spotsTaken;
	}

	public void addSpot(SpotNAndDistance sNd) {
		distanceList.add(sNd.getDistance());
		spotList.add(sNd.getSpotNumber());
		this.spot = new SpotNAndDistance(sNd.getSpotNumber(), sNd.getDistance());
		spotMap.put(sNd.getDistance(), spot);
		spotsTaken++;
	}
	public int gettotalEmptySpots() {
		return totalEmptySpots = numberOfSpots - spotsTaken;
	}

	@Override
	public String toString() {
		return "ParkingLot [sizeOfSpot=" + sizeOfSpot + ", numberOfSpots=" + numberOfSpots + ", totalEmptySpots="
				+ totalEmptySpots + ", spotsTaken=" + spotsTaken + "]";
	}

}
