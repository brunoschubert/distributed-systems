package br.unisinos.file.utils;

import br.unisinos.file.message.Message;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PoloToJson {
	//Creates and convert a Message Object to a matching JSON
	public void createJsonMessage(String nodeId, String msgId, String msgTopic,String msgContents,
			String filePath) throws JsonGenerationException, JsonMappingException, IOException{
		//Convert Message to JSON
		ObjectMapper mapper = new ObjectMapper();
		//Create new Message
		Message msg = new Message();
		msg.setNodeId(nodeId);
		msg.setMsgId(msgId);
		msg.setTimeUTC();
		msg.setMsgTopic(msgTopic);
		msg.setMsgContents(msgContents);
		//Save file to specified location
		mapper.writeValue(new File(filePath), msg);

		/*
		//JSON OTHER POSSIBLE USES
        //Transform Message into a String
        String jsonString = mapper.writeValueAsString(msg);
        //Writes JSON to the console
        mapper.writeValue(System.out, msg);
		 */
	}
//-----------------------------TEST CASE-------------------------------------------------	
	/* CREATES A HARDCODED JSON
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		//Convert Message to JSON
		ObjectMapper mapper = new ObjectMapper();
		//Create new Message
		Message msg = new Message();
		msg.setNodeId("No 1");
		msg.setMsgId("Mensagem 1");
		msg.setTimeUTC();
		msg.setMsgTopic("Introduction");
		msg.setMsgContents("Hello!\nMy name is Inigo Montoya.\n"
				+ "You killed my father...\nPrepare to Die!");

		//Escreve JSON como arquivo
		mapper.writeValue(new File("D:/msg.json"), msg);
	}
	*/
}
