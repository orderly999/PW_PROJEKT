package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.TextArea;

// klasa komunikacyjna
//////////////////////////////////////////////////////////////////////////////
public class UDPConn {
	int serverPort = 7777;
	//int opponentPort = 7777;
	DatagramSocket serverSocket;
	byte[] receiveData = new byte[8];
	DatagramPacket receivePacket;
	byte[] sendData;
	InetAddress myIPAddress;
	InetAddress oponnentIPAddress;
	boolean isServer = false;
	Frame frame = new Frame();
	TextArea textArea;

	public UDPConn(boolean isServer, InetAddress myIPAddress, InetAddress oponnentIPAddress, TextArea textArea) {
		//this.port = myport;
		this.textArea = textArea;
		this.myIPAddress = myIPAddress;
		this.oponnentIPAddress = oponnentIPAddress;
		
		try {
			if (isServer)
			serverSocket = new DatagramSocket(serverPort);
			else
			serverSocket = new DatagramSocket();	
			this.textArea.appendText("INFO: Stworzono gniazdo na porcie: " + serverSocket.getLocalPort());
			
			receivePacket = new DatagramPacket(receiveData, receiveData.length);

		} catch (SocketException e) {
			System.out.println("ERROR: Nie udalo sie stworzyc gniazda komunikacyjnego");
			e.printStackTrace();
		}
		
		Timer timer = new Timer(); 
        TimerTask task = new ConnectionTimer();         
        timer.schedule(task, 2000, 10); 
			
	}

	// funkcja do odbioru danych w petli
	/////////////////////////////////////////////////////////////////////////////////////////
	public void receive() {
		Thread t = new Thread(() -> {
			while (true) {
				try {
					serverSocket.receive(receivePacket);
					this.textArea.appendText("INFO: Wysłano pakiet: " + Arrays.toString(receivePacket.getData()) + "\n");
				} catch (IOException e) {
					System.out.println("ERROR: Blad przy odbiorze datyagramu");
					e.printStackTrace();
				}
				String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
				receivePacket.getAddress();
				System.out.println("INFO: Odebrano " + sentence);

			}
		});
	}

	// funkcja do wysylania danych
	///////////////////////////////////////////////////////////////////////////////////////////
	public void send() {
		DatagramPacket sendPacket;
		if (isServer)
		 sendPacket = new DatagramPacket(sendData, sendData.length, oponnentIPAddress, receivePacket.getPort());
		else
		 sendPacket = new DatagramPacket(sendData, sendData.length, oponnentIPAddress, serverPort);	
		try {
			serverSocket.send(sendPacket);
			//String s = StandardCharsets.UTF_8.decode(sendPacket.getData()).toString();
			//String s = new String(sendData, "ASCII");
			//System.out.println(Arrays.toString(byteArray));
		//	this.textArea.appendText("INFO: Wysłano pakiet: " + Arrays.toString(sendPacket.getData()) + "\n");
		} catch (IOException e) {
			System.out.println("ERROR: Nie udalo sie wyslac danych do odbiorcy");
			e.printStackTrace();
		}
	}
	
	// timer do obslugi wysylanych wiadomosci
	///////////////////////////////////////////////////////////////////////////////////////////
	class ConnectionTimer extends TimerTask 
	{ 
	    public void run() 
	    { 
	    	sendData = frame.convertFrameToByteArray(frame);
	    	send();
	    	//System.out.println();
	    } 
	} 

}
