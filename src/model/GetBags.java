package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class GetBags implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Bags bags =  new Bags();
	
	
	public static Bags getBags() {
		return bags;
	}
	
	public void start1() throws ClassNotFoundException, IOException {
		if (new File("Bags.dat").exists()) {
		FileInputStream fis = new FileInputStream("Bags.dat");
		ObjectInputStream ois =  new ObjectInputStream(fis);
		bags = (Bags)(ois.readObject());
		ois.close();
		fis.close();
		if(bags == null) {
			System.out.println("bag is null");
		}
		}
	}
	
	
	
}
