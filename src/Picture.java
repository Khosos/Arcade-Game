import javax.swing.*;
import java.awt.*;
/**
 * @(#)Picture.java
 *
 *	
 * @Campos
 * @version 1.00 2014/4/6
 * @Description: Draws a picture on a window.
 */
public class Picture extends JComponent 
{
	private int x;
	private int y; 
	private Color c;  // private variable for colour 
	private boolean drawString; 
	private String text; 
	private boolean drawImage;
	private ImageIcon pic; 
	
    public Picture(int x, int y) 
    {
    	this.c = Color.RED;
    	this.x = x; 
    	this.y = y; 
    	this.drawImage = false; 
    	this.drawString = false; 
    	repaint();
    }
    
    public Picture() 
    {
    	this.c = Color.RED;
    	this.x = 0; 
    	this.y = 0; 
    	this.drawImage = false;
    	this.drawString = false; 
    	repaint();
    }
    
    public Picture (ImageIcon img, int x, int y)
    { 
    	this.pic = img; 
    	this.x = x; 
    	this.y = y; 
    	this.drawImage = true; 
    	this.drawString = false; 
    	repaint(); 
    }
    
    public Picture (ImageIcon img)
    { 
    	this.pic = img;
    	this.x = 0; 
    	this.y = 0; 
    	this.drawImage = true; 
    	this.drawString = false; 
    	repaint(); 
    }
    
    public Picture (String title, int x, int y)
    { 
    	this.x = x; 
    	this.y = y; 
    	this.drawImage = false; 
    	this.drawString = true; 
    	this.text = title; 
    	repaint(); 
    }
    
    public void resetColor (Color col)
    {
    	this.c = col;
    	repaint();
    }
    
    public void paint (Graphics g)
    {		
    	if (drawImage == true)
    	{ 
    		this.pic.paintIcon(this, g, x, y);
    		repaint(); 
    	}
    	if (drawString == true)
    	{ 
    		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
    		g.setColor(Color.BLUE);
    		g.drawString(text, x, y);
    		repaint(); 
    	}
    	
    }
    
    public static void main (String args[])
    {
    	// self testing main method;    	
    	JFrame f = new JFrame("Testing");
    	
    	Picture p = new Picture();
    	f.setSize(400,150);
    	f.add(p);
    	f.setVisible(true);
    	
    	JOptionPane.showMessageDialog(null,"Wait");
    	p.resetColor(Color.BLUE); 
    	JOptionPane.showMessageDialog(null,"Wait");
    	f.remove(p);
  
    	Picture p2 = new Picture(100, 15);
    	f.add(p2);
    	f.setVisible(true);
    	JOptionPane.showMessageDialog(null,"Wait");
    	//f.remove(p2);
    	
    	Picture p3 = new Picture (new ImageIcon ("swag.png"), 50, 50);
    	f.add(p3); 
    	f.setVisible(true);
    	JOptionPane.showMessageDialog(null,"Wait");
    	//f.remove(p3);
    	
    	Picture p4 = new Picture (new ImageIcon ("swag.png"), 100, 50);
    	f.add(p4); 
    	f.setVisible(true);
    	JOptionPane.showMessageDialog(null,"Wait");
    	
    	String name = "Shahzada"; 
    	Picture p5 = new Picture (name,150, 30);
    	f.add(p5);
    	f.setVisible(true);
    	
    	
    	
    	
    	
    }   
}