package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.TextArea;

// klasa komunikacyjna
//////////////////////////////////////////////////////////////////////////////
public class UDPConn {
	int serverPort = 7777;
	int clientPort = 8888;
	// int opponentPort = 7777;
	DatagramSocket serverSocket;
	byte[] receiveData = new byte[24];
	DatagramPacket receivePacket;
	byte[] sendData;
	InetAddress myIPAddress;
	InetAddress oponnentIPAddress;
	boolean isServer = false;
	Frame sentFrame = new Frame();
	Frame receivedFrame = new Frame();
	TextArea textArea;
	TimerTask task;

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
			System.out.println("ERROR: Nie udalo sie stworzyc gniazda komunikacyjnego");
			e.printStackTrace();
		}
		Timer timer = new Timer();
		task = new ConnectionTimer();
		timer.schedule(task, 2000, 10);

	}

	// funkcja do odbioru danych w petli
	/////////////////////////////////////////////////////////////////////////////////////////
	public void receive() {
		Thread t = new Thread(() -> {
			while (true) {
				// System.out.println("Wejscie do receive ");
				try {
					serverSocket.receive(receivePacket);
				} catch (IOException e) {
					System.out.println("ERROR: Blad przy odbiorze datagramu");
					e.printStackTrace();
					if (serverSocket.isClosed())
						return;
				}
				receivedFrame.convertByteArrayToFrame(receivePacket.getData());

			}
		});
		t.start();
	}

	// funkcja do wysylania danych
	///////////////////////////////////////////////////////////////////////////////////////////
	public void send() {
		DatagramPacket sendPacket;
		if (this.isServer)
			sendPacket = new DatagramPacket(sendData, sendData.length, oponnentIPAddress, clientPort);
		else
			sendPacket = new DatagramPacket(sendData, sendData.length, oponnentIPAddress, serverPort);
		try {
			serverSocket.send(sendPacket);
			// String s = StandardCharsets.UTF_8.decode(sendPacket.getData()).toString();
			// String s = new String(sendData, "ASCII");
			// System.out.println("SEND: " + this.isServer);
			// this.textArea.appendText("INFO: Wysłano pakiet: " +
			// Arrays.toString(sendPacket.getData()) + "\n");

		} catch (IOException e) {
			System.out.println("ERROR: Nie udalo sie wyslac danych do odbiorcy");
			e.printStackTrace();
			task.cancel();
			if (serverSocket.isClosed())
				return;
		}
	}

	// timer do obslugi wysylanych wiadomosci
	///////////////////////////////////////////////////////////////////////////////////////////
	class ConnectionTimer extends TimerTask {
		public void run() {
			sendData = sentFrame.convertFrameToByteArray(sentFrame);
			send();
			// System.out.println();
		}
	}

}
