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
	private Story routeA, routeB;
	private Label armWrestle;
	private TextFlow layout;
	private ProgressBar hp;
	private boolean finalFight;
	private Cthulhu highPriest;
	private Player player;
 	public static void main(String[] args) {
		// TODO Everything
		
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException{
		//Initialise time remaining
		player = new Player();
		highPriest = new Cthulhu();
		finalFight = false;
		hp = new ProgressBar();
		routeA = new Story(0);
		routeB = new Story(1);		
		
		
		//make a new pane to hold all of the wonderful items
		GridPane pane = new GridPane();

		
		//makes a text box and input field
		info = new Text("You woke up and crawl out of bed, it's 7:00, you have 90 minutes to get to class. You have time to either get breakfast or go for a short run before getting ready for school, which do you do?"); 
		info.setFont(Font.font("Times New Roman", 20));//Now with 100% less comic sans
		input = new TextField();
		layout = new TextFlow(info);
		
		armWrestle = new Label("Progress on arm wrestle: ");
		armWrestle.setVisible(false);
		
		//add both to pane with text on top
		pane.add(layout,0,0);
		pane.add(input, 0, 1);
		pane.add(armWrestle, 0, 2);
		pane.add(hp, 0, 3);
		hp.setVisible(false);
		

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
		}else if(finalFight){
			Text finalBoss = new Text("Cthulhu does not admit defeat. Quickly tap [LEFT] and [RIGHT] to beat him in an arm wrestle.");
			finalBoss.setFont(Font.font(18));
			layout.getChildren().remove(0, layout.getChildren().size());
			input.setDisable(true);
			layout.getChildren().add(finalBoss);
			if(e.getCode().equals(KeyCode.LEFT)||e.getCode().equals(KeyCode.RIGHT)){
				
				hp.setProgress(highPriest.wrestle(player.isStrong())/100.0);
				if(highPriest.getHP()<=0){
					layout.getChildren().remove(0, layout.getChildren().size());
					finalFight =false;
					Text victory = new Text("At last, the might Cthulhu stands defeated, and you make your way to class.");
					victory.setText(victory.getText() + "\nYou step foot into your classroom but don't see anyone else");
					victory.setText(victory.getText() + "\nYou look down at your phone and notice it's Saturday and you don't have class.");
					victory.setText(victory.getText() + "\nYou suddenly wake up and realize you were dreaming the entire time.");
					victory.setText(victory.getText() + "\nYou look down to your phone and notice it's 9:47 and class already started.");
					victory.setText(victory.getText() + "\nCthulhu laughs at you from across the table as he holds his #1 Rap Battle Master Trophy");
					victory.setFont(Font.font(18));
					layout.getChildren().add(victory);
				}
			}
		}
	}

	public void handleInput(String s) {
				
		//inserts the user input in a different font
		Text in = new Text("\n>"+s);
		in.setFont(Font.font("Arial",18));
		layout.getChildren().add(in);
		
		//all this is in a try statement to catch FileNotFound exception so it isnt passed back up
		try {
			if(routeA.isOver()){
				layout.getChildren().remove(0, layout.getChildren().size());
				Text header = new Text("Rap Battle!\nYour Pride: " + player.getPride() + "\nCthluhu's Pride: " + highPriest.getPride());
				header.setFont(Font.font(18));
				layout.getChildren().add(header);
				
				int dealt,taken;
				taken = (int)(Math.random()*15+5);
				dealt = (int)(Math.log(s.length())*((Math.random()*3+5)));
				highPriest.hurtPride(dealt);
				player.hurtPride(taken);
				if(s.equals("")){
					Text you = new Text("\nYou gave Cthulhu the silent treatment\nWhich completely crushed Cthulhu's pride\nCthulhu's Pride: " + highPriest.getPride());
					you.setFont(Font.font(18));
					layout.getChildren().add(you);
				}else{
					Text you = new Text("\nYou said: " + s + "\nWhich took away " + dealt + " of Cthulhu's pride\nCthulhu's Pride: " + highPriest.getPride());
					you.setFont(Font.font(18));
					layout.getChildren().add(you);
				}
				
				
				if(highPriest.getPride()<=0){
					Text gg = new Text("\nYou are victorious in the Eldritch Rap Battle");
					gg.setFont(Font.font(18));
					layout.getChildren().add(gg); 
					finalFight=true;
					Text finalBoss = new Text("\nCthulhu does not admit defeat. Quickly tap [LEFT] and [RIGHT] to beat him in an arm wrestle.");
					finalBoss.setFont(Font.font(18));
					input.setVisible(false);
					layout.getChildren().add(finalBoss);
					hp.setVisible(true);
					armWrestle.setVisible(true);
				}else{

					Text cthulhu = new Text("\n\nCthulhu: " + "\nYou lost " + taken + " pride\nYour remaining pride: " + player.getPride());
					cthulhu.setFont(Font.font(18));
					layout.getChildren().add(cthulhu);

					if(player.getPride()<=0){
						Text gg = new Text("You lost.");
						gg.setFont(Font.font(18));
						layout.getChildren().add(gg);
						input.setDisable(true);
					}
				}
			}else if(routeB.isCond(s)){
				//assume all the comments here are the exact same as above. its basically the same code
				Text next = new Text("\n");
				next.setFont(Font.font("Times New Roman", 20));
				Scanner read = new Scanner(routeB.getTop());
				routeA.getTop();
				while (read.hasNext()) {
					next.setText(next.getText() + read.nextLine());
				}
				layout.getChildren().add(next);
				if(routeB.checkProg(8)){
					routeA.getTop();
					routeB.getTop();
				}else if(routeB.checkProg(7)){
					player.beStrong();
					routeA.getTop();
					routeB.getTop();
				}
				player.loseTime(routeB.getTime());
				routeB.getTime();
				read.close();
			}else if (routeA.isCond(s)) {//checks if the first condition was entered
				
				//creates a new text object and sets the font
				Text next = new Text("\n");
				next.setFont(Font.font("Times New Roman", 20));
				
				//creates a scanner object to read from the next story input
				Scanner read = new Scanner(routeA.getTop());
				//pops off other story stack, since it is not needed
				routeB.getTop();
				
				//reads all input and adds it to the text object
				while (read.hasNext()) {
					next.setText(next.getText() + read.nextLine());
				}
				
				//adds the text object to the form
				layout.getChildren().add(next);
				if(routeA.checkProg(7)){//if next needs to skipped do this
					routeB.getTop();
					routeA.getTop();
				}
								
				//close the scanner
				read.close();
			
			
			} else {
				//if the input doesnt match a possibility it gives an error 
				Text next = new Text("\nThat's not an option.\nYour options are " + routeA.peek() + " or " + routeB.peek());
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
