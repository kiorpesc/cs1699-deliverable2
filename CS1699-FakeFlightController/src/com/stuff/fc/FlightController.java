// File: FlightController.java
// Authors: Elizabeth Davis, Charles Kiorpes
// Date: September 27th, 2014
//
// This is a partially implemented flight controller, intended
// mainly as an enumeration of necessary subparts and probably
// best for something like a basic video game or a text-only
// simulation in which the tiny details don't matter as much
//
// TODO: A lot.
// Certain behaviors, like takeoff, landing, maneuvers, etc,
// are only implemented as String returns.  These methods could
// (and should) be altered to also simulate the actual altitude,
// G-force, orientation, throttle, and other changes that would
// occur during the specified behavior.

package com.stuff.fc;

// world's silliest partial simulated flight controller
// This doesn't interface with real hardware, for
//     everyone's safety.
// Sounds GREAT!
public class FlightController {
	
	private Motor[] motors;
	private int numMotors;
	private double fuelLevel;
	private boolean armed;
	private Accelerometer accel;
	private Gyroscope gyro;
	private Magnetometer mag;
	private GPS gps;
	private double targetLatitude;
	private double targetLongitude;
	private boolean accelGood;	// is it okay to use the accel data
	private boolean gyroGood;	// is it okay to use the gyro data 
	private boolean magGood;	// is it okay to use the mag data
	private boolean gpsGood;	// is it okay to use the gps data
	
	// constructor
	public FlightController(int nMotors){
		accelGood = false;
		gyroGood = false;
		magGood = false;
		gpsGood = false;
		numMotors = nMotors;
		motors = new Motor[numMotors];
	}
	
	// addMotor(): int + Motor -> void
	//
	// add a Motor instance at the given index
	// this ONLY exists for the sake of the test
	public void addMotor(int index, Motor m){
		motors[index] = m;
	}
	
	// checkMotors(): void -> boolean
	//
	// check that all motors have been initialized
	// by trying their isArmed() method
	public boolean checkMotors(){
		try {
			for(int i = 0; i < numMotors; i++){
				motors[i].isArmed();
			}
		} catch (NullPointerException e){
			return false;
		}
		return true;
	}

	// initAccel(): Accelerometer -> boolean
	//
	// initialize the accelerometer hardware
	// if the initialization fails, return false
	public boolean initAccel(Accelerometer a){
		boolean retVal = a.init();
		accel = a;
		return retVal;
	}
	
	public boolean initGPS(GPS g){
		boolean retVal = g.init();
		gps = g;
		return retVal;
	}
	
	// armMotors(): void -> boolean
	//
	// iterate over the array of motors
	//    and call each Motor's arm() method
	// return: success or failure (true or false) 
	public boolean armMotors(){
		// attempt to arm all motors
		for(int i = 0; i < numMotors; i++){
			motors[i].arm();
		}
		return true; // makes the test work!
	}
	
	// disarmMotors(): void -> boolean
	//
	// iterate over the array of motors
	//    and call each Motor's disarm() method
	// return: success or failure (true or false)
	public boolean disarmMotors(){
		// attempt to disarm all motors
		for(int i = 0; i < numMotors; i++){
			motors[i].disarm();
		}
		return true; // makes the test work!
	}
	
	// setMotorSpeed(): int int -> int
	//
	// set an individual motor's speed
	// params:  index - integer index of motor in array
	//			speed - integer desired speed
	// return:  the return value from the Motor's setSpeed() method
	public int setMotorSpeed(int index, int speed){
		int retVal;
		retVal = motors[index].setSpeed(speed);
		return retVal;
	}
	
	// isAccelGood(): void -> boolean
	//
	// tells whether the data from the accelerometer is considered good
	// simply return the value of accelGood
	public boolean isAccelGood(){
		return accelGood;
	}
	
	// isGyroGood(): void -> boolean
	//
	// tells whether the data from the gyroscope is considered good
	// simply return the value of gyroGood
	public boolean isGyroGood(){
		return gyroGood;
	}
	
	// isMagGood(): void -> boolean
	//
	// tells whether the data from the magnetometer is considered good
	// simply return the value of magGood
	public boolean isMagGood(){
		return magGood;
	}
	
	// isGPSGood(): void -> boolean
	//
	// tells whether the data from the GPS is considered good
	// simply return the value of gpsGood
	public boolean isGPSGood(){
		return gpsGood;
	}
	
	// altTooHigh(): void -> boolean
	//
	// checks whether the craft has exceeded the
	// model aircraft ceiling
	public boolean altTooHigh() {
		double alt = gps.getAltitude();
		return alt > 100.0;
	}
	
	// altTooLow(): void -> boolean
	//
	// checks whether the craft is below
	// one meter
	public boolean altTooLow() {
		double alt = gps.getAltitude();
		return alt < 1.0;
	}
	
	// speedCheck(): void -> String
	//
	// check the current velocity
	// and return a message indicating
	// the status
	public String speedCheck(){
		double vel = gps.getVelocity();
		
		if (vel >= 300000000.0){
			return "LUDICROUS SPEED!";
		} else if (vel > 40.0){
			return "WAY TOO FAST!";
		} else if (vel > 15.0){
			return "TOO FAST!";
		} else if (vel < 5.0){
			return "TOO SLOW!";
		} else {
			return "SPEED OKAY";
		}
	}
	
	// doABarrelRoll(): void -> String
	//
	// have the craft perform
	// a barrel roll (in spirit only)
	public String doABarrelRoll() {
		return "DO A BARREL ROLL!";
	}

	// insideLoop(): void -> String
	//
	// have the craft perform an
	// inside loop (by returning a
	// String claiming that it did so)
	public String insideLoop() {
		return "Inside Loop Successful!";
	}

	// outsideLoop(): void -> String
	//
	// have the craft perform an
	// outside loop (by returning a
	// String claiming that it did so)
	public String outsideLoop() {
		return "Outside Loop Successful!";
	}

	// immelmannTurn(): void -> String
	//
	// have the craft perform an
	// Immelmann turn (by returning a
	// String claiming that it did so)
	public String immelmannTurn(){
		return "Successful Immelmann Turn!";
	}
	
	// splitS(): void -> String
	//
	// have the craft perform a
	// split S (by returning a
	// String claiming that it did so)
	public String splitS(){
		return "Successful Split S!";
	}
	
	// setGPSTarget(): void -> void
	//
	// set the target latitude and longitude
	public void setGPSTarget(double lat, double longi){
		targetLatitude = lat;
		targetLongitude = longi;
	}
	
	// getLatError(): void -> double
	//
	// return the difference between the current
	// latitude and the target latitude
	public double getLatError() {
		return targetLatitude - gps.getLatitude();
	}
	
	// getLongError(): void -> double
	//
	// return the difference between the current
	// longitude and the target longitude
	public double getLongError() {
		return targetLongitude - gps.getLongitude();
	}
	
	// checkGForces(): void -> String
	//
	// check that g forces in all axes are within safe limits,
	// returning a String stating the status
	public String checkGForces(){
		double gx = accel.getX();
		double gy = accel.getY();
		double gz = accel.getZ();
		if(Math.abs(gx) > 15.0 || Math.abs(gy) > 15.0 || Math.abs(gz) > 15.0){
			return "Unsafe maneuver! G forces too high!";
		} else {
			return "G's within tolerance.";
		}
	}
	
	// testMotor(): int -> boolean
	//
	// check that the motor at the given index
	// has not failed by ensuring that, if the speed
	// is greater than zero, the motor's RPS is also
	// greater than zero
	public boolean testMotor(int index){
		if(motors[index].isArmed() && motors[index].getSpeed() > 0 && motors[index].getRPS() == 0){
			return true;
		}
		return false;
	}
	
	// setFuelLevel(): double -> void
	//
	// a utility function to manually
	// set the fuel level
	public void setFuelLevel(double fl){
		fuelLevel = fl;
	}
	
	// fuelOkay(): void -> boolean
	//
	// returns whether the fuel level is
	// at 0.25 or higher
	public boolean fuelOkay(){
		return fuelLevel >= 0.25;
	}
	
	// isLanded(): void -> boolean
	// 
	// return whether or not the craft
	// is on the ground (relative altitude,
	// so ground is ALWAYS 0.0m)
	public boolean isLanded(){
		boolean result = true;
		double alt = gps.getAltitude();
		if(alt > 0.0) {
			result = false;
		}
		return result;
	}
}
