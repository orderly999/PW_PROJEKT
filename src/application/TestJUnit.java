package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestJUnit {

	@Test
	void testifNotNullConvertFrameToByteArray() {
		Frame frame = new Frame();
		assertNotNull(frame.convertFrameToByteArray(frame));
	}
	
	
	@Test
	void testifNotNullConvertByteArrayToFrame() {
		Frame frame = new Frame();
		byte[] array = new byte[24];
		assertNotNull(frame.convertByteArrayToFrame(array));
	}
	

}
