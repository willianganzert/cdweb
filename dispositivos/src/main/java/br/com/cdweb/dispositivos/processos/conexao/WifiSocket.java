package br.com.cdweb.dispositivos.processos.conexao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Base64;

import com.google.gson.Gson;

import br.com.cdweb.conexao.BroadcastMessage;
import br.com.cdweb.conexao.MensagemConexao;
import br.com.cdweb.dispositivos.configuracoes.Configuracoes;
import br.com.cdweb.dispositivos.configuracoes.conexao.ControleLogConexoes;

public class WifiSocket implements Runnable{
	private Gson gson;
	public WifiSocket() {
		gson = new Gson();
	}
	@Override
	public void run() {
		while(true){
			
		
		try {
			MulticastSocket socket;
			socket = new MulticastSocket(4446);
		
			InetAddress address = InetAddress.getByName("230.0.0.1");
			socket.joinGroup(address);
	
			DatagramPacket packet;
	
			byte[] buf = new byte[256];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			String received = new String(packet.getData(), 0, packet.getLength());
			received = new String(Base64.getDecoder().decode(received));
//			System.out.println(String.format("%s",received));
			if(received != null){
				MensagemConexao mensagemConexao = gson.fromJson(received, MensagemConexao.class);
				if(mensagemConexao.getTipoMensagem().equals(MensagemConexao.TipoMensagem.BROADCAST)){
					BroadcastMessage broadcastMessage = gson.fromJson(received, BroadcastMessage.class);  
					ControleLogConexoes.INSTANCE.setHoraUltimaConexaoServerWIFI(System.currentTimeMillis());
					Configuracoes.INSTANCE.setUrlServicosServidorInterno(broadcastMessage.getIp());
				}
			}
			
				
			socket.leaveGroup(address);
			socket.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		}
	}
}
