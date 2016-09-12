package raspberryPiFinal;


import java.io.Serializable;

public class Message implements Serializable{
    private String type;
    private String letter;
    private String number;
    private String color;
    
    public Message(String type,String letter,String number, String color){
    	this.letter = letter;
    	this.color = color;
    	this.number = number;
    	this.type = type;

    }
    
    public String getType(){
    	return type;
    }
    
    public String getColor(){
    	return color;
    }
    public String getLetter(){
    	return letter;
    }
    public String getNumber(){
    	return number;
    }
}
