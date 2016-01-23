package br.com.cdweb.dispositivos.processos;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MulticastServer {
	public static void main(String[] args) throws java.io.IOException, InterruptedException {
		System.out.println("SERVIDOR INICIADO");

		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

		executor.scheduleAtFixedRate(new BroadcartNetwork(), 0, 10, TimeUnit.SECONDS);

	}
}
