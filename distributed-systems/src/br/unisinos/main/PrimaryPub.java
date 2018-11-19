package br.unisinos.main;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.unisinos.pubsub.*;

public class PrimaryPub {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		PubNode pub = new PubNode();
		pub.startPublisher(1, 1, "tcp://*:5563");

		pub.publishMsg();
		
		int i = 0;
		int counter = 0;
		//Sends 20 msg
		while (counter < 20) {
			
			pub.publishMsg(pub.getPubFilePath());

			//Randomizes publishing time
			i = ((int)Math.random()*10);
			Thread.sleep(i * 1000);
			
			counter++;
		}
		pub.stopPublisher();

	}
}