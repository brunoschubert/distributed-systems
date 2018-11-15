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
	String pubFilePath;
	Context pubContext;
	Socket publisher;

	Context subContext;
	Socket subscriber;
	//-------------------------------------------------------------------------------
	public String getPubFilePath(){
		return this.pubFilePath;
	}
	//-----------------------------UTILITY METHODS------------------------------------
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
	//-----------------------------PUBLISHER METHODS----------------------------------
	//-----------------------------Message Publishers----------------------------------
	//Creates and Publish a Message 
	public void publishMsg() throws 
	JsonParseException, JsonMappingException, IOException{
		//calls message creation and save filePath
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
	//Publishes an already created message -- For use inside Main loops with getter
	public void publishMsg(String filePath) throws 
	JsonParseException, JsonMappingException, IOException{
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
		System.out.println("Insert Message Topic:");
		String msgTopic = input.nextLine();
		//Receives message Contents
		System.out.println("Insert Message Contents:");
		String msgContents = input.nextLine();
		//Use utility class to create the Message as a JSON String and saves file locally 
		PoloToJson ptj = new PoloToJson();
		//Converts IDs into Strings
		String nId = Integer.toString(nodeId);
		String mId = Integer.toString(msgId);
		//Receives desired file path
		System.out.println("Insert Save Location[ Example: D:/ ]:");
		String saveLocation = input.nextLine();
		String filePath = saveLocation + mId +".json";
		//Creates a msg based on input and saves it on the default location
		ptj.createJsonMessage(nId, mId, msgTopic, msgContents, filePath);
		
		//increments message ID
		this.msgId++;
		
		input.close();
		this.pubFilePath = filePath;
		return pubFilePath;
	}
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
//-----------------------------------Subscriber Methods---------------------------------------------
	
}



