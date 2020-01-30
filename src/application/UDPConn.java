package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.TextArea;

// klasa komunikacyjna @KamilKruk
//////////////////////////////////////////////////////////////////////////////
public class UDPConn {
	int serverPort = 7777;
	int clientPort = 8888;
	DatagramSocket serverSocket;
	byte[] receiveData = new byte[24];
	DatagramPacket receivePacket;
	byte[] sendData;
	InetAddress myIPAddress;
	InetAddress oponnentIPAddress;
	boolean isServer = false;
	volatile Frame sentFrame = new Frame();
	volatile Frame receivedFrame = new Frame();
	TextArea textArea;
	TimerTask task;
	boolean isClientActive = false;

	/**
	 * inicjalizacja gniazda komunikacyjnego 
	 * @param isServer
	 * @param myIPAddress
	 * @param oponnentIPAddress
	 * @param textArea
	 * @author KamilKruk
	 */
	public UDPConn(boolean isServer, InetAddress myIPAddress, InetAddress oponnentIPAddress, TextArea textArea) {
		this.textArea = textArea;
		this.myIPAddress = myIPAddress;
		this.oponnentIPAddress = oponnentIPAddress;
		this.isServer = isServer;
		try {
			if (this.isServer) {
				serverSocket = new DatagramSocket(serverPort);
			} else {
				serverSocket = new DatagramSocket(clientPort);
			}
			this.textArea.appendText("INFO: Stworzono gniazdo na porcie: " + serverSocket.getLocalPort());
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			receive();
		} catch (SocketException e) {
			this.textArea.appendText("ERROR: Nie udalo sie stworzyc gniazda komunikacyjnego!");
			System.out.println("ERROR: Nie udalo sie stworzyc gniazda komunikacyjnego");
			e.printStackTrace();
		}
		Timer timer = new Timer();
		task = new ConnectionTimer();
		timer.schedule(task, 2000, 10);

	}

	/**
	 * funkcja do odbioru danych w petli 
	 * @author KamilKruk
	 */
	public void receive() {
		Thread t = new Thread(() -> {
			while (true) {
				try {
					serverSocket.receive(receivePacket);
					isClientActive = true;
				} catch (IOException e) {
					System.out.println("ERROR: Blad przy odbiorze datagramu");
					this.textArea.appendText("ERROR: Blad przy odbiorze datagramu!");
					e.printStackTrace();
					isClientActive = false;
					if (serverSocket.isClosed())
						return;
				}
				receivedFrame.convertByteArrayToFrame(receivePacket.getData());

			}
		});
		t.start();
	}

	/**
	 * funkcja do wysylania danych 
	 * @author KamilKruk
	 */
	public void send() {
		DatagramPacket sendPacket;
		if (this.isServer)
			sendPacket = new DatagramPacket(sendData, sendData.length, oponnentIPAddress, clientPort);
		else
			sendPacket = new DatagramPacket(sendData, sendData.length, oponnentIPAddress, serverPort);
		try {
			serverSocket.send(sendPacket);
		} catch (IOException e) {
			System.out.println("ERROR: Nie udalo sie wyslac danych do odbiorcy");
			this.textArea.appendText("ERROR: Nie udalo sie wyslac danych do odbiorcy!");
			e.printStackTrace();
			task.cancel();
			if (serverSocket.isClosed())
				return;
		}
	}

	/**
	 * timer do obslugi wysylanych wiadomosci 
	 * @author KamilKruk
	 *
	 */
	class ConnectionTimer extends TimerTask {
		public void run() {
			sendData = sentFrame.convertFrameToByteArray(sentFrame);
			send();
		}
	}

}
