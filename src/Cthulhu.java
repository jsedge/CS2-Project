import java.util.ArrayList;
public class Cthulhu {
	private int pride;
	private int hp;
	private ArrayList<String> barz;
	
	public Cthulhu(){
		pride = 100;
		hp = 100;
		barz = new ArrayList<String>();
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
}
