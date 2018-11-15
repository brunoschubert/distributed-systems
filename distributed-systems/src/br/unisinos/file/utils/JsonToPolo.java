package br.unisinos.file.utils;

import br.unisinos.file.message.Message;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToPolo {
	
	public Message convertToMessage(String filePath) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Message msg = objectMapper.readValue(new File(filePath), Message.class);
		return msg;
	}
//-----------------------------TEST CASE-------------------------------------------------	
	/*
	public static void main(String[] args) throws IOException {  
		//Test Case
		JsonToPolo JToString = new JsonToPolo();
		System.out.println(JToString.convertToPoloString("D:/msg.json"));
		
		Message msg = new Message();
		msg = JToString.convertToMessage("D:/msg.json");
		System.out.println(msg.toString());
	}
	*/
}
