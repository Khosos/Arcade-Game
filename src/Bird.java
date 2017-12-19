import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.ImageIcon;

public class Bird
{
  private static final int G = -9;
  int velocity = 0; 
  private ImageIcon bird;
  public Point loc;
  private boolean ball;
  
  public Bird()
  
  {  
	  if(ball=true){
		  this.bird=new ImageIcon("8ball.png");
		  
	  }
	  
	  //loc = new Point(3,100);
  } 
  
  public void jump(int elapsedTime, int velocity)
  {
	  int speed = (int)(((int)(G*-1)*elapsedTime)/1000) + velocity; 
	  setY(loc.y - speed); 
  }
  
  


  public void setX(int x)
  {
    this.loc.x = x;
  }
  
  public int getX()
  {
    return this.loc.x;
  }
  
  public void setY(int y)
  {
    this.loc.y = y;
  }
  
  public int getY()
  {
    return this.loc.y;
  }
  
  
	public static void main(String[] args) 
	{
		new BainsyBird(null); 
		
	}
	
	

 
}