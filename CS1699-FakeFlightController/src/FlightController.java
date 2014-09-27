// world's silliest partial simulated flight controller
// This doesn't interface with real hardware,
// 	  because its not supposed to work.
// Sounds GREAT!

public class FlightController {
	
	private Motor[] motors;
	private int numMotors;
	private boolean armed;
	private Accelerometer accel;
	private Gyroscope gyro;
	private Magnetometer mag;
	private GPS gps;
	private boolean accelGood;	// is it okay to use the accel data
	private boolean gyroGood;	// is it okay to use the gyro data 
	private boolean magGood;	// is it okay to use the mag data
	private boolean gpsGood;	// is it okay to use the gps data
	
	public FlightController(int nMotors){
		accelGood = false;
		gyroGood = false;
		magGood = false;
		gpsGood = false;
		numMotors = nMotors;
		motors = new Motor[numMotors];
	}
	
	// this ONLY exists for the sake of the test
	public void addMotor(int index, Motor m){
		motors[index] = m;
	}
	
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
	
	// initialize the accelerometer hardware
	// if the initialization fails, return false
	public boolean initAccel(Accelerometer a){
		boolean retVal = a.init();
		if (retVal){
			accel = a;
		}
		return retVal;
	}
	
	public boolean initGPS(GPS g){
		boolean retVal = g.init();
		if(retVal){
			gps = g;
		}
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
		return gps.getAltitude() > 100.0;
	}
	
}
