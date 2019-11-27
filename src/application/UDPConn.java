package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

// klasa komunikacyjna
//////////////////////////////////////////////////////////////////////////////
public class UDPConn {
	int port;
	DatagramSocket serverSocket;
	byte[] receiveData = new byte[8];
	DatagramPacket receivePacket;
	byte[] sendData;
	InetAddress IPAddress;
	Frame frame = new Frame();

	public UDPConn(int port) {
		this.port = port;
		try {
			serverSocket = new DatagramSocket(this.port);
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
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, receivePacket.getPort());
		try {
			serverSocket.send(sendPacket);
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
	    	System.out.println();
	    } 
	} 

}
