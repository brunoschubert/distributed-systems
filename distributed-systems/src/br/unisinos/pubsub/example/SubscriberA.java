package br.unisinos.pubsub.example;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class SubscriberA {

    public static void main (String[] args) {

        // Contexto e Subscriber
        Context context = ZMQ.context(1);
        Socket subscriber = context.socket(ZMQ.SUB);

        subscriber.connect("tcp://localhost:5563");
        subscriber.subscribe("Msg1".getBytes());
        while (!Thread.currentThread ().isInterrupted ()) {
            // Lê envelope com o endereço
            String address = subscriber.recvStr ();
            // Lê a mensagem
            String contents = subscriber.recvStr ();
            System.out.println(address + " : " + contents);
        }
        subscriber.close ();
        context.term ();
    }
}