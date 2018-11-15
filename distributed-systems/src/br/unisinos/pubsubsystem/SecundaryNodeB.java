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

		//Conecta a quem quer receber e define tópico de interesse
		subscriber.connect("tcp://localhost:5563");
		subscriber.subscribe("Enchente".getBytes());

		// Endereço de comunicação
		publisher.bind("tcp://*:5565");

		//Define valor padrão para as variáveis de Tópico e Msg
		String topic = "";
		String contents = "";

		// Envia msgs até a thread ser parada
		while (!Thread.currentThread ().isInterrupted ()) {
			//Se o tópico for diferente do padrão
			if (topic != (subscriber.recvStr())) {
				// Lê tópico
				topic = subscriber.recvStr ();
				// Lê contéudo
				contents = subscriber.recvStr ();
				System.out.println(topic + " : " + contents);

				//Controla o fluxo fazendo a Publisher dormir
				Thread.sleep(1500);

				//Publica conteúdo
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
