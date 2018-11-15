package br.unisinos.pubsubsystem;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class PrimaryNode {

	public static void main(String[] args) throws InterruptedException {
		// Contexto e Publisher
		Context pubContext = ZMQ.context(1);
		Socket publisher = pubContext.socket(ZMQ.PUB);
		// Endere�o de comunica��o
		publisher.bind("tcp://*:5563");

		// Envia msgs at� a thread ser parada
		while (!Thread.currentThread().isInterrupted()) {
			
			//Define o t�pico da msg e seu conte�do
			publisher.sendMore("Terremoto");
			publisher.send("Cuidado!");
			
			publisher.sendMore("Enchente");
			publisher.send("Evacuar!");
			
			//Controla o fluxo fazendo a Publisher dormir
			Thread.sleep(1000);
		}
		publisher.close();
		pubContext.term();
	}
}
