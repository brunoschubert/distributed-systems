package br.unisinos.file.utils;

import br.unisinos.file.message.Message;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToPolo {

	public static void main(String[] args) throws IOException {  
		//
		ObjectMapper objectMapper = new ObjectMapper();
		Message msg = objectMapper.readValue(new File("D:/msg.json"), Message.class);
		System.out.println(msg.toString());
	}
}
