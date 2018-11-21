package br.unisinos.main;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

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
			
			sub.startSub(UUID.randomUUID(), 1, "tcp://127.0.0.1:5566", topic);
			sub.receiveMessage();
			
		}
		sub.stopSub();

		input.close();
	}
}
