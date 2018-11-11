package br.unisinos.pubsub;

import java.util.Random;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class SubscriberB extends Subscriber{

	public static void main(String[] args) {
		// Contexto e Subscriber
		Context context = ZMQ.context(1);
		Socket subscriber = context.socket(ZMQ.SUB);
		//Classes Úteis
		SubscriberB subs = new SubscriberB();
		//Sorteia Variável na qual a Thread será interrompida
		Random random = new Random();
		int stopper = random.nextInt(100);
//------------------------------------------------------------------------------------
		subscriber.connect("tcp://localhost:5563");
		subscriber.subscribe("Correr".getBytes());
		while (subs.isExecuting()) {
			// Lê envelope com o endereço
			String address = subscriber.recvStr();
			// Lê a mensagem
			String contents = subscriber.recvStr();
			//passa o conteudo e o numero de parada
			if(subs.isTimeToStop(contents, stopper)){
				System.out.println("Dormiu na " + contents);
				subs.stopExecuting();
			} else {
				System.out.println(address + " : " + contents);
			}
		}
		subscriber.close();
		context.term();
	}
}