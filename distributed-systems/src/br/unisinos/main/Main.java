package br.unisinos.main;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.unisinos.pubsub.*;

public class Main {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		PubNode pub = new PubNode();
		pub.startPublisher(1, 1, "tcp://*:5563");
		
		SubNode sub = new SubNode();
		Scanner input = new Scanner(System.in);
		System.out.println("Digite o Tópico para se Inscrever: ");
		String topic = input.nextLine();
		
		sub.startSub(2, 2, "tcp://*:5563", topic);
		
		pub.publishMsg();
		sub.receiveMessage();
		
		pub.stopPublisher();
		sub.stopSub();
	}
}
