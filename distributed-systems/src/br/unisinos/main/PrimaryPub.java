package br.unisinos.main;

import java.io.IOException;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.unisinos.pubsub.*;

public class PrimaryPub {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		PubNode pub = new PubNode();
		Random rand = new Random();
		pub.startPublisher(1, 1, "tcp://127.0.0.1:5563");

		pub.publishMsg();
		
		int i = 0;
		int counter = 0;
		//Sends 20 msg
		while (counter < 200) {
			
			pub.publishMsg(pub.getPubFilePath());

			//Randomizes publishing time
			i = rand.nextInt(10 + 1);
			System.out.println(i);
			Thread.sleep(i * 100);
			
			counter++;
		}
		pub.stopPublisher();

	}
}