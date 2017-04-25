import java.io.File;
import java.util.Stack;
public class Story {
	private Stack<File> story;
	private Stack<String> cond;
	private Stack<Integer> time;
	int speed;
	
	public Story(int a){
		story = new Stack<File>();
		cond = new Stack<String>();
		time = new Stack<Integer>();
		speed=1;
		if(a==0){
			//push story route a to the story and condition stacks
			story.push(new File("act3.txt"));
			story.push(new File("act2 a2.txt"));
			story.push(new File("act2 a1.txt"));
			story.push(new File("act2 intro.txt"));
			story.push(new File("act1 a4.txt"));
			story.push(new File("act1 a3.txt"));
			story.push(new File("act1 a2.txt"));
			story.push(new File("act1 a1.txt"));
			cond.push("");
			cond.push("left");
			cond.push("left");
			cond.push("");
			cond.push("flip-flops");
			cond.push("short");
			cond.push("bagel");
			cond.push("breakfast");
			time.push(0);
			time.push(5);
			time.push(10);
			time.push(0);
			time.push(0);
			time.push(10);
			time.push(15);
			time.push(0);
		}else{
			//do the same for route b
			story.push(new File("act3.txt"));
			story.push(new File("act2 b2.txt"));
			story.push(new File("act2 b1.txt"));
			story.push(new File("act2 intro.txt"));
			story.push(new File("act1 b4.txt"));
			story.push(new File("act1 b3.txt"));
			story.push(new File("act1 b2.txt"));
			story.push(new File("act1 b1.txt"));
			cond.push("");
			cond.push("right");
			cond.push("right");
			cond.push("");
			cond.push("sneakers");
			cond.push("long");
			cond.push("bacon and eggs");
			cond.push("run");
			time.push(0);
			time.push(15);
			time.push(25);
			time.push(0);
			time.push(0);
			time.push(10);
			time.push(30);
			time.push(0);
		}
	}
	
	public boolean isOver(){
		return story.isEmpty();
	}
	
	public boolean isCond(String s){
		return cond.peek().equals(s);
	}
	
	public File getTop(){
		cond.pop();
		return story.pop();
	}
	
	public boolean checkProg(int x){
		return x==cond.size()+1;
	}
	
	public void beFast(){
		speed=2;
	}
	public String peek(){
		return cond.peek();
	}
	
	public int getTime(){
		return time.pop()/speed;
	}
}
