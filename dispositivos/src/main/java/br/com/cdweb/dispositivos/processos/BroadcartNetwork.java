package br.com.cdweb.dispositivos.processos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Base64;

public class BroadcartNetwork implements Runnable {
	protected DatagramSocket socket = null;

	public BroadcartNetwork() throws IOException {
	}

	@Override
	public void run() {
		
		try(DatagramSocket socket = new DatagramSocket(4445);) {
			byte[] buf = new byte[256];
			
			buf = Base64.getEncoder().encode(("{\"cdweb\":{\"ip\":\"" + InetAddress.getLocalHost().getHostAddress()+"\",\"id\":\"rasphome\"}}").getBytes());

			InetAddress group = InetAddress.getByName("230.0.0.1");
			DatagramPacket packet;
			packet = new DatagramPacket(buf, buf.length, group, 4446);
			socket.send(packet);

		} catch (IOException e) {
			e.printStackTrace();

		}



	}
}
