package br.unisinos.main;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.unisinos.pubsub.*;

public class TertiaryPubSubD {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		//PubNode pub = new PubNode();
		//pub.startPublisher(1, 1, "tcp://127.0.0.1:5563");

		PubSubNode pubSub = new PubSubNode();
		Scanner input = new Scanner(System.in);
		System.out.println("Digite o Tópico para se Inscrever: ");
		String topic = input.nextLine();
						 //id			//pubTo			//listenFrom
		pubSub.startPubSub(UUID.randomUUID(), 2, "tcp://127.0.0.1:5569", 1, "tcp://127.0.0.1:5565", topic);
		
		//receive a msg and saves it locally
		while (!Thread.currentThread ().isInterrupted ()) {
			
			String filePath = pubSub.receiveMessage("D:/");
			Thread.sleep(500);
			pubSub.publishMsg(filePath);

		}
		pubSub.stopPubSub();

		input.close();
	}
}
