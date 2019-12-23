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

	int myXPosition = 0;
	int myYPosition = 0;
	int opponentXposition = 0;
	int opponentYpostion = 0;
	int ballXposition = 0;
	int ballYposition = 0;
	
	public Frame()
	{
		
	}
	
	// konwersja integerow do buforu bajtow
	///////////////////////////////////////////////////////////////////////////////////
	public byte[] convertFrameToByteArray(Frame frame)
	{
		 buf.clear();
		 buf.putInt(myXPosition);
		 buf.putInt(myYPosition);
		 buf.putInt(opponentXposition);
		 buf.putInt(opponentYpostion);
		 buf.putInt(ballXposition);
		 buf.putInt(ballYposition);	
		 return buf.array();
	}

}
