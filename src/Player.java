
public class Player {
	private int time;
	private int pride;
	private boolean strong;
	
	public Player(){
		time = 90;
		pride = 100;
		strong = false;
	}
	
	public void hurtPride(int d){
		pride-=d;
	}
	
	public void loseTime(int t){
		time-=t;
	}
	
	public void beStrong(){
		strong = true;
	}
	
	public boolean isStrong(){
		return strong;
	}
	
	public void getConfident(){
		pride+=50;
	}
	public int getPride(){
		return pride;
	}
	
	public int getTime(){
		return time;
	}
}
