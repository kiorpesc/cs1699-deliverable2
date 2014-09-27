import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

// Test the FlightController class
public class FlightControllerTest {

	// mock of a disarmed motor
	@Mock
	Motor mockedMotorDisarmed = Mockito.mock(Motor.class);
	
	// mock of an armed motor
	@Mock
	Motor mockedMotorArmed = Mockito.mock(Motor.class);
	
	@Mock
	GPS mockedGPS = Mockito.mock(GPS.class);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(mockedMotorDisarmed);
		MockitoAnnotations.initMocks(mockedMotorArmed);
		
		// stubs!
		Mockito.when(mockedMotorDisarmed.isArmed()).thenReturn(false);
		Mockito.when(mockedMotorDisarmed.setSpeed(Mockito.anyInt())).thenReturn(0);
		
		Mockito.when(mockedMotorArmed.isArmed()).thenReturn(true);
		Mockito.when(mockedMotorArmed.setSpeed(Mockito.anyInt())).thenReturn(1);
	}

	@After
	public void tearDown() throws Exception {
	}

	// this test determines whether all N motors
	// are set up by the constructor using the
	// checkMotors() function - in this case N = 1
	// Expected: checkMotors() should return true
	@Test
	public void testMotorSetup() {
		FlightController fc = new FlightController(1);
		fc.addMotor(0, mockedMotorDisarmed);
		assertTrue(fc.checkMotors());
	}

	// this test checks whether the controller
	// can successfully arm all motors (true return val)
	// Expected: armMotors() returns true
	@Test
	public void testMotorArm() {
		FlightController fc = new FlightController(1);
		fc.addMotor(0, mockedMotorDisarmed);
		assertTrue(fc.armMotors());
	}
	
	// this test checks whether the controller
	// can successfully arm all motors (true return val)
	// Expected: armMotors() returns true
	@Test
	public void testMotorDisarm() {
		FlightController fc = new FlightController(1);
		fc.addMotor(0, mockedMotorArmed);
		assertTrue(fc.disarmMotors());
	}
	
	// this test tries setting the speed of an armed motor
	// and checks to see that the motor's setSpeed() method is called
	// Input: index 0, speed 25
	// Expected: returns 1
	@Test
	public void testSetArmedSpeed(){
		int expected = 1;
		int result;
		FlightController fc = new FlightController(1);
		fc.addMotor(0, mockedMotorArmed);
		result = fc.setMotorSpeed(0, 25);
		assertEquals(expected, result);
	}
	
	// this test tries setting the speed of a DISARMED motor
	// and checks to see that the motor's setSpeed() method is called
	// input: index 0, speed 25
	// Expected: returns 0
	@Test
	public void testSetDisarmedSpeed(){
		int expected = 0;
		int result;
		FlightController fc = new FlightController(1);
		fc.addMotor(0, mockedMotorDisarmed);
		result = fc.setMotorSpeed(0, 25);
		assertEquals(expected, result);
	}
	
	// testPreInitAccelGood
	// This test ensures that, before the accelerometer is
	// initialized, the FlightController assumes that the
	// data will be bad and does not try to use it
	// Expected: isAccelGood will return false
	@Test
	public void testPreInitAccelGood(){
		FlightController fc = new FlightController(1);
		assertTrue(fc.isAccelGood() == false);
	}
	
	// testPreInitGyroGood
	// This test ensures that, before the gyroscope is
	// initialized, the FlightController assumes that the
	// data will be bad and does not try to use it
	// Expected: isGyroGood will return false
	@Test
	public void testPreInitGyroGood(){
		FlightController fc = new FlightController(1);
		assertTrue(fc.isGyroGood() == false);
	}	
	
	// testPreInitMagGood
	// This test ensures that, before the magnetometer is
	// initialized, the FlightController assumes that the
	// data will be bad and does not try to use it
	// Expected: isMagGood will return false
	@Test
	public void testPreInitMagGood(){
		FlightController fc = new FlightController(1);
		assertTrue(fc.isMagGood() == false);
	}
	
	// testPreInitGPSGood
	// This test ensures that, before the GPS is
	// initialized, the FlightController assumes that the
	// data will be bad and does not try to use it
	// Expected: isGPSGood will return false
	@Test
	public void testPreInitGPSGood(){
		FlightController fc = new FlightController(1);
		assertTrue(fc.isGPSGood() == false);
	}
	
	// testAltitudeTooHigh
	// This test ensures the aircraft will 
	// send error if over an altitude of 
	// 100 meters.
	// Expected: altTooHigh() will return true
	@Test
	public void testAltitudeTooHigh(){
		double alt = 175.3;
		FlightController fc = new FlightController(1);
		fc.initGPS(mockedGPS);
		Mockito.when(mockedGPS.getAltitude()).thenReturn(alt);
		assertTrue(fc.altTooHigh());
	}
	
	// testAltitudeTooLow
	// This test ensures the aircraft will
	// send error if under an altitude of 
	// 1 meter.
	@Test
	public void testAltitudeTooLow(){
		FlightController fc = new FlightController(1);
		assertTrue(fc.altTooLow() == true);
	}
	
	// testGoingLightSpeed
	// This test ensures the aircraft will
	// send error if over a velocity of 
	// 3 x 10^8 meters/second.
	@Test
	public void testGoingLightSpeed(){
		
	}
	
	// testSpeedWayTooFast
	// This test ensures the aircraft will
	// send error if over a velocity of 
	// 40 meters/second.
	@Test
	public void testSpeedWayTooFast(){
		
	}
		
	// testSpeedTooFast
	// This test ensures the aircraft will
	// send error if over a velocity of 
	// 15 meters/second.
	@Test
	public void testSpeedTooFast(){
		
	}
	
	// testSpeedAverage
	// This test ensures the aircraft will
	// report a velocity of 
	// 10 meters/second. (idk...)
	@Test
	public void testSpeedAverage(){
		
	}
		
	// testSpeedTooSlow
	// This test ensures the aircraft will
	// send error if under a velocity of 
	// 5 meters/second.
	@Test
	public void testSpeedTooSlow(){
		
	}
}
