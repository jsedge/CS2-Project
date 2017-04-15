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
	private Stack<File> story;
	private Stack<String> cond;
	private TextFlow layout;
	public static void main(String[] args) {
		// TODO Everything
		
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException{
		//make a new stack of story
		story = new Stack<File>();
		story.push(new File("act3.txt"));
		story.push(new File("act2.txt"));
		story.push(new File("act1.txt"));
		
		//stacks of conditions to advance the story
		cond = new Stack<String>();
		cond.push("cthulhu");
		cond.push("test");
		cond.push("cat");
		
		//make a new pane to hold all of the wonderful items
		GridPane pane = new GridPane();

		
		//makes a text box and input field
		info = new Text("Welcome to *please add game name here*\nType cat and press [ENTER] to begin"); //TODO Add name of game
		info.setFont(Font.font("Comic Sans MS", 20));//TODO Maybe dont use comic sans
		input = new TextField();
		layout = new TextFlow(info);
		
		
		
		//add both to pane with text on top
		pane.add(layout,0,0);
		pane.add(input, 0, 1);
		

		//make a new scene 
		Scene scene = new Scene(pane,600,450);
		
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
		
		if(s.equals(cond.peek())){
			//info.setText("");
			try{//i'm using a try statement to actually handle the errors here, because it would just push them up to the start method
				//and i don't want to deal with them there
				Text next = new Text("\n");
				next.setFont(Font.font("Comic Sans MS",20));
				Scanner read = new Scanner(story.pop());
				while(read.hasNext()){
					next.setText(next.getText() + read.nextLine());
				}
				layout.getChildren().add(next);
				cond.pop();
				read.close();
				
			} catch (FileNotFoundException e) {
				System.out.println("Something and/or everything broke. Oops.");
			}
			
			
		}else{
			Text next = new Text("\nThat won't help.");
			next.setFont(Font.font("Comic Sans MS",20));
			layout.getChildren().add(next);
			
		}
		if(layout.getHeight()>391){
			layout.getChildren().remove(0);
		}
	}
}
