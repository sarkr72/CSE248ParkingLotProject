package model;

import java.io.Serializable;

public class SpotNAndDistance implements Comparable<SpotNAndDistance>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int spotNumber;
	private int distance;
	
	public SpotNAndDistance(int spotNumber, int distance) {
		super();
		this.spotNumber = spotNumber;
		this.distance = distance;
	}

	public int getSpotNumber() {
		return spotNumber;
	}

	public void setSpotNumber(int spotNumber) {
		this.spotNumber = spotNumber;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "SpotNAndDistance [spotNumber=" + spotNumber + ", distance=" + distance + "]";
	}

	@Override
	public int compareTo(SpotNAndDistance spot) {
		if(this.distance == spot.distance) {
			return 0;
		}
			else if(this.distance < spot.getDistance()) {
				return -1;
			}else {
				return 1;
			}
	}
}
