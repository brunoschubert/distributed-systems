package br.unisinos.file.utils;

import br.unisinos.file.message.Message;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringToPolo {

	public Message convertToJson(String jsonString) 
			throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		Message msg = new Message();
		
		msg = mapper.readValue(jsonString, Message.class);
		return msg;
	}

//-----------------------------TEST CASE-------------------------------------------------	
	/*
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		PoloToString jtp = new PoloToString();
		String str = jtp.convertToPoloString("D:/0.json");
		
		StringToPolo stp = new StringToPolo();
		
		Message msg = new Message();
		msg = stp.convertToJson(str);
		mapper.writeValue(System.out, msg);
	}
	*/

}
