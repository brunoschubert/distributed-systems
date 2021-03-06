package br.unisinos.main;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.unisinos.pubsub.*;

public class SimpleMain {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		PubNode pub = new PubNode();
		pub.startPublisher(UUID.randomUUID(), 1, "tcp://127.0.0.1:5563");

		SubNode sub = new SubNode();
		Scanner input = new Scanner(System.in);
		System.out.println("Digite o T�pico para se Inscrever: ");
		String topic = input.nextLine();
		
		sub.startSub(UUID.randomUUID(), 2, "tcp://127.0.0.1:5563", topic);
		
		pub.publishMsg();
		sub.receiveMessage();
		
		pub.stopPublisher();
		sub.stopSub();
		
		input.close();
	}
}
