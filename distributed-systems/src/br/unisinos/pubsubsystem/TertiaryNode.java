package br.unisinos.pubsubsystem;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class TertiaryNode {

	public static void main(String[] args) throws InterruptedException {
		// Contexto e Subscriber
		Context subContext = ZMQ.context(1);
		Socket subscriber = subContext.socket(ZMQ.SUB);
		
		Context subContext2 = ZMQ.context(1);
		Socket subscriber2 = subContext.socket(ZMQ.SUB);

		//Conecta a quem quer receber e define tópico de interesse
		subscriber.connect("tcp://localhost:5565");
		subscriber.subscribe("Enchente".getBytes());
		//Conecta a quem quer receber e define tópico de interesse
		subscriber2.connect("tcp://localhost:5564");
		subscriber2.subscribe("Terremoto".getBytes());

		// Envia msgs até a thread ser parada
		while (!Thread.currentThread ().isInterrupted ()) {
			// Lê tópico
			String topic = subscriber.recvStr ();
			String topic2 = subscriber.recvStr ();
			// Lê contéudo
			String contents = subscriber2.recvStr ();
			String contents2 = subscriber2.recvStr ();
			System.out.println(topic + " : " + contents);
			System.out.println(topic2 + " : " + contents2);

			//Controla o fluxo fazendo a Publisher dormir
			Thread.sleep(1500);
		}
		subscriber.close ();
		subContext.term ();
		subscriber2.close ();
		subContext2.term ();
	}
}

