
public class Motor {
	private int speed;  // 0 - 1023?
	private boolean armed;  // is the motor output active?
	private int rps;
	
	public Motor() {
		armed = false;
		speed = 0;
		rps = 0;
	}
	
	// return whether or not the motor is armed
	public boolean isArmed(){
		return armed;
	}
	
	// activate the motor
	public void arm(){
		setSpeed(0);	// when arming motors, speed is set to zero for safety
		armed = true;	// in a real system, this would require hardware interaction
	}
	
	// deactivate the motor
	public void disarm(){
		setSpeed(0);
		armed = false;
	}
	
	// return the current speed setting of the motor
	public int getSpeed(){
		return speed;
	}
	
	// set the speed of the motor
	public int setSpeed(int spd){
		speed = spd;
		return speed;
	}
	
	public int getRPS(){
		return rps;
	}
}
