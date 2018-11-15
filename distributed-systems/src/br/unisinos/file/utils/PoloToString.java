package br.unisinos.file.utils;

import br.unisinos.file.message.Message;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PoloToString {
	//Receives a JSON file Path and convert its contents to a matching string
	public String convertToPoloString(String filePath) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Message msg = objectMapper.readValue(new File(filePath), Message.class);
		return objectMapper.writeValueAsString(msg);
	}
	
}
