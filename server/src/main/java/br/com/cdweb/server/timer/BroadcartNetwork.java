package br.com.cdweb.server.timer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Base64;

import com.google.gson.Gson;

import br.com.cdweb.conexao.BroadcastMessage;

public class BroadcartNetwork implements Runnable {
	protected DatagramSocket socket = null;
	private Gson gson;

	public BroadcartNetwork() throws IOException {
		gson = new Gson();
	}

	@Override
	public void run() {
		
		try(DatagramSocket socket = new DatagramSocket(4445);) {
			byte[] buf = new byte[256];
			BroadcastMessage message = new BroadcastMessage("rasphome",InetAddress.getLocalHost().getHostAddress());
			buf = Base64.getEncoder().encode((gson.toJson(message)).getBytes());

			InetAddress group = InetAddress.getByName("230.0.0.1");
			DatagramPacket packet;
			packet = new DatagramPacket(buf, buf.length, group, 4446);
			socket.send(packet);

		} catch (IOException e) {
			e.printStackTrace();

		}



	}
}
