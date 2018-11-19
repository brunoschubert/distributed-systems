package br.unisinos.main;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.unisinos.pubsub.*;

public class FinalSub {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		SubNode sub = new SubNode();
		Scanner input = new Scanner(System.in);
		System.out.println("Digite o Tópico para se Inscrever: ");
		String topic = input.nextLine();
		
		while (!Thread.currentThread ().isInterrupted ()) {
			
			sub.startSub(10, 1, "tcp://*:5566", topic);
			sub.receiveMessage();
			
			sub.startSub(10, 1, "tcp://*:5567", topic);
			sub.receiveMessage();
			
			sub.startSub(10, 1, "tcp://*:5568", topic);
			sub.receiveMessage();
			
			sub.startSub(10, 1, "tcp://*:5569", topic);
			sub.receiveMessage();
			
			sub.startSub(10, 1, "tcp://*:5570", topic);
			sub.receiveMessage();
			
			sub.startSub(10, 1, "tcp://*:5571", topic);
			sub.receiveMessage();

		}
		sub.stopSub();

		input.close();
	}
}
