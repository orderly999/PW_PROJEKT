package application;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;


// klasa reprezentuje ramke wymieniana pomiedzy programami
//////////////////////////////////////////////////////////////////////////////////////
public class Frame {

	ByteBuffer buf = ByteBuffer.allocate(24);

	int myXPosition;
	int myYPosition;
	int opponentXposition;
	int opponentYpostion;
	int ballXposition;
	int ballYposition;	
	
	public Frame()
	{
		// buf.putInt(value);
	}
	
	// konwersja integerow do buforu bajtow
	///////////////////////////////////////////////////////////////////////////////////
	public byte[] convertFrameToByteArray(Frame frame)
	{
		 buf.putInt(myXPosition);
		 buf.putInt(myYPosition);
		 buf.putInt(opponentXposition);
		 buf.putInt(opponentYpostion);
		 buf.putInt(ballXposition);
		 buf.putInt(ballYposition);	
		 return buf.array();
	}

}
