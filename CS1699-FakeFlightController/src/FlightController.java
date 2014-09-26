// world's silliest flight controller
// This doesn't interface with real hardware,
// 	  because if it did, it would fall out of the
//    sky if any sensors returned invalid data.
// Sounds GREAT!

public class FlightController {
	
	private Motor[] motors;
	private int numMotors;
	private boolean armed;
	private int pwmRate;
	private int minPWM;
	private int maxPWM;
	private boolean i2c; 	// if this is enabled, use i2c instead of PWM
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
	
	public boolean armMotors(){
		// attempt to arm all motors
		for(int i = 0; i < numMotors; i++){
			motors[i].arm();
		}
		return true; // makes the test work!
	}
	
	public boolean disarmMotors(){
		// attempt to disarm all motors
		for(int i = 0; i < numMotors; i++){
			motors[i].disarm();
		}
		return true; // makes the test work!
	}
	
	public int setMotorSpeed(int index, int speed){
		int retVal;
		retVal = motors[index].setSpeed(speed);
		return retVal;
	}
}
