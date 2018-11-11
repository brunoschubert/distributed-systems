package br.unisinos.pubsub;

public class Subscriber {
	//atributo de controle
	boolean executing;
	
	public Subscriber(){
		this.executing = true;
	}
//------------------------------------------------------------------------------------
    //Controle de fluxo do Subscriber
	public boolean isExecuting(){
		return this.executing;
	}
	
	public boolean stopExecuting() {
         return this.executing = false;
    }
//------------------------------------------------------------------------------------
	//Extrai os dígitos da String Enviada pelo Publisher
	public int extractDigits(String str) {
		StringBuilder sbr = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c)) {
				sbr.append(c);
			}
		}
		return Integer.parseInt(sbr.toString());
	}
//------------------------------------------------------------------------------------
	//Recebe e avalia o conteudo da msg e o numero de parada
	public boolean isTimeToStop(String content, int count){
		int stopper;
		stopper = extractDigits(content);
		if(count == stopper){
			return true;
		}
		return false;
	}
}
