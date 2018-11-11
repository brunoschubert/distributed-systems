package br.unisinos.pubsub;

import br.unisinos.factories.*;
import java.util.Random;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Publisher {

	public static void main (String[] args) throws Exception {
		// Contexto e Publisher
		Context context = ZMQ.context(1);
		Socket publisher = context.socket(ZMQ.PUB);
		//Classes úteis
		Animal tiger = new Animal("Tigre");
		Animal sheep = new Animal("Ovelha");
		Random random = new Random();
//------------------------------------------------------------------------------------
		publisher.bind("tcp://*:5563");
		while (!Thread.currentThread().isInterrupted()) {
			// Cria dois envios com msgs e pacotes diferentes
			//Sorteia qual enviar
			if((random.nextInt(100)%2) == 0){
				publisher.sendMore("Correr");
				publisher.send(tiger.countingAnimals());
			} else {
				publisher.sendMore("Dormir");
				publisher.send(sheep.countingAnimals());
			}
			//Controla o fluxo fazendo a Publisher dormir
			Thread.sleep(100);
		}
		publisher.close();
		context.term();
	}
}