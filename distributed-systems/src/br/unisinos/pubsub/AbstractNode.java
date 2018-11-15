package br.unisinos.pubsub;

import br.unisinos.file.utils.*;
import br.unisinos.file.message.*;

import java.io.IOException;
import java.util.Scanner;

import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public abstract class AbstractNode {
	int nodeId;
	int msgId = 0;
	Context pubContext;
	Socket publisher;
	
	Context subContext;
	Socket subscriber;
	
//-----------------------------UTILITY METHODS------------------------------------
//-----------------------------PUBLISHER METHODS----------------------------------
//-----------------------------Message Publishers----------------------------------
		//Creates and Publish a Message 
		public void publishMsg() throws 
		JsonParseException, JsonMappingException, IOException{
			//calls message creation
			String filePath = createMsg();
			//Use utility class to get MSG attributes
			JsonToPolo jtp = new JsonToPolo();
			//Creates a new message object to extract attributes from
			Message msg = new Message();
			msg = jtp.convertToMessage(filePath);
			//publish message topic
			publisher.sendMore(msg.getMsgTopic());
			//publish entire message as string
			publisher.send(msg.toString());
		}
//-----------------------------------------------------------------------------------------------		
		//Publishes an already created message
		public void publishMsg(String filePath) throws 
		JsonParseException, JsonMappingException, IOException{
			//calls message creation
			createMsg();
			//Use utility class to get MSG attributes
			JsonToPolo jtp = new JsonToPolo();
			//Creates a new message object to extract attributes from
			Message msg = new Message();
			msg = jtp.convertToMessage(filePath);
			//publish message topic
			publisher.sendMore(msg.getMsgTopic());
			//publish entire message as string
			publisher.send(msg.toString());
		}
//---------------------------Message Creator-------------------------------------
	public String createMsg() throws JsonGenerationException, JsonMappingException, IOException{
		//Receives user input
		Scanner input = new Scanner(System.in);
		//Receives message Topic
		System.out.println("Insert Message Topic:\n");
		String msgTopic = input.next();
		//Receives message Contents
		System.out.println("Insert Message Contents:\n");
		String msgContents = input.nextLine();
		//Use utility class to create the Message as a JSON String and saves file locally 
		PoloToJson ptj = new PoloToJson();
		//Converts IDs into Strings
		String nId = Integer.toString(nodeId);
		String mId = Integer.toString(msgId);
		//Receives desired file path
		String saveLocation = input.next();
		System.out.println("Insert Save Location[ Example: C:/ ]:\n");
		String filePath = saveLocation + mId +".json";
		//Creates a msg based on input and saves it on the default location
		ptj.createJsonMessage(nId, mId, msgTopic, msgContents, filePath);
		
		input.close();
		return filePath;
	}
}

	

