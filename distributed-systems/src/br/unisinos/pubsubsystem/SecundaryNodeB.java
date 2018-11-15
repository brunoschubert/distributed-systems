package br.unisinos.pubsubsystem;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class SecundaryNodeB {

	public static void main(String[] args) throws InterruptedException {
		// Contexto e Publisher
		Context pubContext = ZMQ.context(1);
		Socket publisher = pubContext.socket(ZMQ.PUB);
		// Contexto e Subscriber
		Context subContext = ZMQ.context(2);
		Socket subscriber = subContext.socket(ZMQ.SUB);

		//Conecta a quem quer receber e define t�pico de interesse
		subscriber.connect("tcp://localhost:5563");
		subscriber.subscribe("Enchente".getBytes());

		// Endere�o de comunica��o
		publisher.bind("tcp://*:5565");

		//Define valor padr�o para as vari�veis de T�pico e Msg
		String topic = "";
		String contents = "";

		// Envia msgs at� a thread ser parada
		while (!Thread.currentThread ().isInterrupted ()) {
			//Se o t�pico for diferente do padr�o
			if (topic != (subscriber.recvStr())) {
				// L� t�pico
				topic = subscriber.recvStr ();
				// L� cont�udo
				contents = subscriber.recvStr ();
				System.out.println(topic + " : " + contents);

				//Controla o fluxo fazendo a Publisher dormir
				Thread.sleep(1500);

				//Publica conte�do
				publisher.sendMore(topic);
				publisher.send(contents);
			}
		}
		publisher.close();
		pubContext.term();
		subscriber.close ();
		subContext.term ();
	}
}
