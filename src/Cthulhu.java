import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
public class Cthulhu {
	private int pride;
	private int hp;
	private ArrayList<String> word;
	
	public Cthulhu(){
		pride = 100;
		hp = 100;
		
		//intialize arraylist and populate it with words from a text file
		word = new ArrayList<String>();
		File words = new File("words.txt");
		try{
			//try to read from the file
			Scanner in = new Scanner(words);
			while(in.hasNext()){
				//add each word in the file to the arraylist
				String s = in.next();
				word.add(s);
			}
			
		}catch(FileNotFoundException e){
			System.out.println("several things broke, commence tears");
		}
	}
	
	public void hurtPride(int d){
		pride-=d;
	}
	
	public int wrestle(boolean s){
		hp--;
		if(s){
			hp--;
		}
		
		return (100-hp);
	}
	
	public int getHP(){
		return hp;
	}
	
	public int getPride(){
		return pride;
	}
	
	public String generateBar(){
		String bar = "";		
		
		//picks a number of words from the sentence
		int num =(int)(Math.random()*5+1);

		for(int i = 0;i<num;i++){
			//picks that many words and puts them in a sentence
			int rng = (int)(Math.random()*word.size());
			bar+=" " + word.get(rng);
		}
		
		//returns the generated sentence
		return bar;
	}
}
