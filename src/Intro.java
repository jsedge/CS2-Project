//lmao
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Intro extends Application {
	private TextField input;
	private Text info;
	private int time,pridePC,prideC;
	private Stack<File> storyA,storyB;
	private Stack<String> condA,condB;
	private Stack<Integer> timeA,timeB;
	private TextFlow layout;
	public static void main(String[] args) {
		// TODO Everything
		
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException{
		//Initialise time remaining
		time = 90;
		pridePC = 100;
		prideC = 100;
		
		//make a new stack of story and conditions
		storyA = new Stack<File>();
		storyB = new Stack<File>();
		condA = new Stack<String>();
		condB = new Stack<String>();
		timeA = new Stack<Integer>();
		timeB = new Stack<Integer>();
		
		//push story route a to the story and condition stacks
		storyA.push(new File("act3.txt"));
		storyA.push(new File("act2 a2.txt"));
		storyA.push(new File("act2 a1.txt"));
		storyA.push(new File("act2 intro.txt"));
		storyA.push(new File("act1 a4.txt"));
		storyA.push(new File("act1 a3.txt"));
		storyA.push(new File("act1 a2.txt"));
		storyA.push(new File("act1 a1.txt"));
		condA.push("");
		condA.push("left");
		condA.push("left");
		condA.push("");
		condA.push("flip-flops");
		condA.push("short");
		condA.push("bagel");
		condA.push("breakfast");
		
		//do the same for route b
		storyB.push(new File("act3.txt"));
		storyB.push(new File("act2 b2.txt"));
		storyB.push(new File("act2 b1.txt"));
		storyB.push(new File("act2 intro.txt"));
		storyB.push(new File("act1 b4.txt"));
		storyB.push(new File("act1 b3.txt"));
		storyB.push(new File("act1 b2.txt"));
		storyB.push(new File("act1 b1.txt"));
		condB.push("");
		condB.push("right");
		condB.push("right");
		condB.push("");
		condB.push("sneakers");
		condB.push("long");
		condB.push("bacon and eggs");
		condB.push("run");
		
		//make a new pane to hold all of the wonderful items
		GridPane pane = new GridPane();

		
		//makes a text box and input field
		info = new Text("You woke up and crawl out of bed, it's 7:00, you have 90 minutes to get to class. You have time to either get breakfast or go for a short run before getting ready for school, which do you do?"); 
		info.setFont(Font.font("Times New Roman", 20));//Now with 100% less comic sans
		input = new TextField();
		layout = new TextFlow(info);
		
		
		
		//add both to pane with text on top
		pane.add(layout,0,0);
		pane.add(input, 0, 1);
		

		//make a new scene 
		Scene scene = new Scene(pane,600,600);
		
		//make an event for button presses
		scene.setOnKeyPressed(this::buttonPress);
		
		
		
		//set scene and title of stage
		primaryStage.setTitle("Sweet video game of video gaming ");
		primaryStage.setScene(scene);

		//launch application
		primaryStage.show();
	}

	public void buttonPress(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER)){
			//checks if the given input was the enter key
			//gets text from text box as string
			String s = input.getText();
			
			//clears text box
			input.setText("");
			
			//passes string to another method to handle it
			handleInput(s);
		}
	}

	public void handleInput(String s) {
		/*So to advance the story we'll want to move forward through a 
		 * predetermined route. We can use a tree type data structure for this
		 * if the story will branch. Or we could just use a stack if we want it to be linear
		 * which is probably our best bet. We can have the stack holds references to text files which contain
		 * the story
		 * 
		 * We can look at where we are in the stack and compare it to our given input
		 * to decide what will happen. Someone write a story then I can put this code together.
		 * 
		 */
		
		//inserts the user input in a different font
		Text in = new Text("\n>"+s);
		in.setFont(Font.font("Arial",18));
		layout.getChildren().add(in);
		
		//all this is in a try statement to catch FileNotFound exception so it isnt passed back up
		try {
			if(storyA.isEmpty()){
				layout.getChildren().remove(0, layout.getChildren().size());
				Text header = new Text("Rap Battle!\nYour Pride: " + pridePC + "\n\tCthluhu's Pride: " + prideC);
				layout.getChildren().add(header);
				
				int dealt,taken;
				taken = (int)(Math.random()*15+5);
				dealt = (int)(Math.log(s.length())*((Math.random()*3+4)));
				prideC-=dealt;
				pridePC-=taken;
				
				Text you = new Text("\nYou said: " + s + "\nWhich took away " + dealt + " of Cthulhu's pride\nCthulhu's Pride: " + prideC);
				you.setFont(Font.font(18));
				layout.getChildren().add(you);
				if(prideC<=0){
					Text gg = new Text("You are victorious in the Eldritch Rap Battle");
					gg.setFont(Font.font(18));
					layout.getChildren().add(gg);
				}else{

					Text cthulhu = new Text("\nCthulhu: " + "\nYou lost " + taken + " pride\nYour remaining pride: " + pridePC);
					cthulhu.setFont(Font.font(18));
					layout.getChildren().add(cthulhu);

					if(pridePC<=0){
						Text gg = new Text("You lost.");
						gg.setFont(Font.font(18));
						layout.getChildren().add(gg);
					}
				}
			}else if(s.equals(condB.peek())){
				//assume all the comments here are the exact same as above. its basically the same code
				Text next = new Text("\n");
				next.setFont(Font.font("Times New Roman", 20));
				Scanner read = new Scanner(storyB.pop());
				storyA.pop();
				while (read.hasNext()) {
					next.setText(next.getText() + read.nextLine());
				}
				layout.getChildren().add(next);
				if(condB.size()==8){
					condA.pop();
					condB.pop();
					storyA.pop();
					storyB.pop();
				}
				condA.pop();
				condB.pop();
				read.close();
			}else if (s.equals(condA.peek())) {//checks if the first condition was entered
				
				//creates a new text object and sets the font
				Text next = new Text("\n");
				next.setFont(Font.font("Times New Roman", 20));
				
				//creates a scanner object to read from the next story input
				Scanner read = new Scanner(storyA.pop());
				//pops off other story stack, since it is not needed
				storyB.pop();
				
				//reads all input and adds it to the text object
				while (read.hasNext()) {
					next.setText(next.getText() + read.nextLine());
				}
				
				//adds the text object to the form
				layout.getChildren().add(next);
				if(condA.size()==7){//if next needs to skipped do this
					condA.pop();
					condB.pop();
					storyA.pop();
					storyB.pop();
				}
				
				//pop off the condition for each
				condA.pop();
				condB.pop();
				
				//close the scanner
				read.close();
			
			
			} else {
				//if the input doesnt match a possibility it gives an error 
				Text next = new Text("\nThat's not an option.\nYour options are " + condA.peek() + " or " + condB.peek());
				next.setFont(Font.font("Times New Roman", 20));
				layout.getChildren().add(next);

			}
			while (layout.getHeight() > 391) {
				//if the text is off screen remove stuff until it isnt
				layout.autosize();
				layout.getChildren().remove(0);
				
			}
		} catch (FileNotFoundException e) {
			//this shouldnt happen ever.
			System.out.println("Several things broke. Tell Justin to fix it.");
		}
	}
}
