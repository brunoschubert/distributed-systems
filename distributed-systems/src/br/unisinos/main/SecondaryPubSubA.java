package br.unisinos.main;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.unisinos.pubsub.*;

public class SecondaryPubSubA {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		//PubNode pub = new PubNode();
		//pub.startPublisher(1, 1, "tcp://127.0.0.1:5563");

		PubSubNode pubSub = new PubSubNode();
		Scanner input = new Scanner(System.in);
		System.out.println("Digite o Tópico para se Inscrever: ");
		String topic = input.nextLine();
		
		pubSub.startPubSub(UUID.randomUUID(), 2, "tcp://127.0.0.1:5564", 1, "tcp://127.0.0.1:5563", topic);
		
		//receive a msg and saves it locally
		while (!Thread.currentThread ().isInterrupted ()) {
			
			String filePath = pubSub.receiveMessage("D:/");
			Thread.sleep(1000);
			pubSub.publishMsg(filePath);

		}
		//pub.stopPublisher();
		pubSub.stopPubSub();

		input.close();
	}
}
