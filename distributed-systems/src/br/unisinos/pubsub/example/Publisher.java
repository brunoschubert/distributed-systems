package br.unisinos.pubsub.example;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Publisher {

    public static void main (String[] args) throws Exception {
        // Contexto e Publisher
        Context context = ZMQ.context(1);
        Socket publisher = context.socket(ZMQ.PUB);

        publisher.bind("tcp://*:5563");
        while (!Thread.currentThread ().isInterrupted ()) {
            // Cria dois envios com msgs e pacotes diferentes
            publisher.sendMore ("Msg1");
            publisher.send ("Esta � a mensagem 1");
            publisher.sendMore ("Msg2");
            publisher.send("Esta � a mensagem 2");
        }
        publisher.close ();
        context.term ();
    }
}