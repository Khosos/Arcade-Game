
public class BirdXL {
	
	Bird bird;

	public BirdXL() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	  public void fall()
	  {
		  elapsedTime = endTime - startTime; 
		  update(); 
		  
		  int speed = (int)(((int)(G*-1)*elapsedTime)/1000 + velocity); 
		  setY(loc.y - speed); 
	  }
	  
	  public void jump()
	  {
		  startTime = System.currentTimeMillis(); 
		  elapsedTime = endTime - startTime; 
		  velocity = -3; 
	  }
	  
	  

}
