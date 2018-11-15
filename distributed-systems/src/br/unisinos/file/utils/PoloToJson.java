package br.unisinos.file.utils;

import br.unisinos.file.message.Message;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PoloToJson {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		//Convert Message to JSON
		ObjectMapper mapper = new ObjectMapper();
		//Create new Message
		Message msg = new Message();
		msg.setNodeId("No 1");
		msg.setMsgId("Mensagem 1");
		msg.setTimeUTC();
		msg.setMsgContents("Hello!\nMy name is Inigo Montoya.\n"
				+ "You killed my father...\nPrepare to Die!");

		/*
		//FORMAS DE USO DO JSON
        //Transforma Mensagem em uma string
        String jsonString = mapper.writeValueAsString(msg);
        //Escreve JSON no console
        mapper.writeValue(System.out, msg);
		 */

		//Escreve JSON como arquivo
		mapper.writeValue(new File("D:/msg.json"), msg);
	}
}
