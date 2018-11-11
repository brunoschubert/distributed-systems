package br.unisinos.factories;

public class Animal {
	private String name;
	private int count;
	
	public Animal(String name){
		this.name = name;
		this.count = 1;
	}
	
	public String getName() { return name; }
	public int getCount() { return count; }
	
	public String countingAnimals(){
		String str = name + " : " + count;
		this.count++;		
		return str;
	}
}
