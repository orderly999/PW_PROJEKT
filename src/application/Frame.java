package application;

import java.nio.ByteBuffer;

// klasa reprezentuje ramke wymieniana pomiedzy programami
//////////////////////////////////////////////////////////////////////////////////////
public class Frame {

	ByteBuffer buf = ByteBuffer.allocate(24);

	int myXPosition = 0;
	int myYPosition = 0;
	int opponentXposition = 0;
	int opponentYposition = 0;
	int ballXposition = 0;
	int ballYposition = 0;

	public Frame() {

	}

	// konwersja integerow do buforu bajtow
	///////////////////////////////////////////////////////////////////////////////////
	public byte[] convertFrameToByteArray(Frame frame) {
		buf.clear();
		buf.putInt(myXPosition);
		buf.putInt(myYPosition);
		buf.putInt(opponentXposition);
		buf.putInt(opponentYposition);
		buf.putInt(ballXposition);
		buf.putInt(ballYposition);
		return buf.array();
	}

	/// konwersja tablicy bajtowej do integerow
	///////////////////////////////////////////////////////////////////////////////////
	public void convertByteArrayToFrame(byte[] array) {
		this.myXPosition = (int) ((array[0] & 0xFF) << 24 | (array[1] & 0xFF) << 16 | (array[2] & 0xFF) << 8
				| (array[3] & 0xFF));
		this.myYPosition = (int) ((array[4] & 0xFF) << 24 | (array[5] & 0xFF) << 16 | (array[6] & 0xFF) << 8
				| (array[7] & 0xFF));
		this.opponentXposition = (int) ((array[8] & 0xFF) << 24 | (array[9] & 0xFF) << 16 | (array[10] & 0xFF) << 8
				| (array[11] & 0xFF));
		this.opponentYposition = (int) ((array[12] & 0xFF) << 24 | (array[13] & 0xFF) << 16 | (array[14] & 0xFF) << 8
				| (array[15] & 0xFF));
		this.ballXposition = (int) ((array[16] & 0xFF) << 24 | (array[17] & 0xFF) << 16 | (array[18] & 0xFF) << 8
				| (array[19] & 0xFF));
		this.ballYposition = (int) ((array[20] & 0xFF) << 24 | (array[21] & 0xFF) << 16 | (array[22] & 0xFF) << 8
				| (array[23] & 0xFF));
	}

}
