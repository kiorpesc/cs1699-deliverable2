package com.kiorpes.fc;

// this class runs the simulated flight controller(s)
public class SimulationRunner {
	
	// TODO: implement main function to tie it all together.
	public static void main(String[] args){
		double fl = 1.0; // initialize with full fuel, for testing purposes only
		
		// initialize "hardware"
		System.out.println("INIT: Hardware");
		FlightController fc1 = new FlightController(1); // single-engine craft
		fc1.setFuelLevel(fl);
		Motor m1 = new Motor();
		Accelerometer fc1_accel = new Accelerometer();
		Gyroscope fc1_gyro = new Gyroscope();
		GPS fc1_gps = new GPS();
		Magnetometer fc1_mag = new Magnetometer();
		fc1.initAccel(fc1_accel);
		//fc1.initGyro(fc1_gyro);
		//fc1.initMag(fc1_mag);
		fc1.initGPS(fc1_gps);
		
		// init checks
		System.out.println("INIT: Verifying Hardware");
		if(!fc1.isAccelGood()){
			// abort, error message about Accel failure
		}
		if(!fc1.isGyroGood()){
			// abort
		}
		if(!fc1.isMagGood()){
			// abort
		}
		if(!fc1.isGPSGood()){
			//abort
		}
		
		System.out.println("INIT: Motors");
		// add motor(s)
		fc1.addMotor(0, m1);
		
		System.out.println("INIT: Verifying motors");
		// motor checks
		if(!fc1.checkMotors()){
			System.out.println("FATAL: Motors failed to initialize");
			//fc1.halt();
			System.exit(1);
		}
		
		System.out.println("TAKEOFF: Arming motors");
		// if all good, arm and takeoff (set motor speed to something)
		if(!fc1.armMotors()){
			System.out.println("FATAL: Motors failed to arm");
			//fc1.halt();
			System.exit(1);
		}
		System.out.println("TAKEOFF: Setting motor speed");
		// takeoff time, motor up
		fc1.setMotorSpeed(0, 500);
		
		// check that we're in the air?
		
		System.out.println("FLIGHT: Begin aerial maneuvers");
		// begin maneuvers, checking fuel level
		while(fc1.fuelOkay()){
			// do some in air maneuvers
			System.out.println(fc1.doABarrelRoll());
			System.out.println(fc1.insideLoop());
			fl -= 0.1;
			fc1.setFuelLevel(fl);
		}
		// LAND
		System.out.println("LANDING: Shutting down motors");
		fc1.setMotorSpeed(0, 0);
		// disarm
		System.out.println("HALT: Disarming motors");
		fc1.disarmMotors();
		// END
		System.out.println("HALT: Shutting down flight controller");
	}
}
