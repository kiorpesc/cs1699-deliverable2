package com.stuff.fc;

public class GPS {

	private int numSats;
	private int fixType;
	private double latitude;
	private double longitude;
	private double altitude;
	private double velocity;
	private double heading;
	
	public GPS(){
		// construct it here
	}
	
	public boolean init(){
		return true;
	}
	
	// return the number of currently "visible" satellites
	public int getNumSats(){
		return numSats;
	}
	
	// return the fix type (0-1 is no fix, 2 is 2d fix, 3 is 3d fix)
	public int getFixType(){
		return fixType;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public double getAltitude(){
		return altitude;
	}
	
	public double getHeading(){
		return heading;
	}
	
	public double getVelocity(){
		return velocity;
	}
}
