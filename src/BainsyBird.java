import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BainsyBird extends JPanel implements ActionListener, KeyListener
{
	
	private Timer timer; //declaring variables 
	private int highscore; 
	private Pipe pipe, pipe2; 
	public Point box; 
	int counter = 0, afterCounter = 150; 
	private ImageIcon background, bird; 
	private ImageIcon tp,bp;
	private boolean dead;
	private boolean start=false;
	private int score=0;
	private JFrame frame;
	private long endTime, elapsedTime, startTime; 
	double G = -9, A; 
	int velocity, var; 
	int speed = 0; 
	private int tries;
	private JButton play;
	Bird Bird = new Bird();
	private JButton exit;
	private boolean pressed=false;
	private ImageIcon hsMenu;
	


	public BainsyBird(ImageIcon Bird)
	{
		frame = new JFrame("Bainsy Bird"); // creating new JFrame Bainsy Bird
		//For getting the screen dimensions
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // getting size of user's screen
		int width = (int)screenSize.getWidth(); 
		int height = (int) screenSize.getHeight();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // create default exit option

		tp = new ImageIcon("tp.png"); // setting bottom/top pipe pictures
		bp = new ImageIcon("bp.png");
		this.bird=Bird;
		background = new ImageIcon("background.png"); // setting background/highscore menu pictures
		hsMenu = new ImageIcon("hsMenu.png");
	
		play = new JButton("Play"); // creating jbuttons
		exit = new JButton("Exit");

		play.setBounds(200,350,125,78); // setting bounds and declaring them to be invisible
		play.setBackground(Color.WHITE);
		play.setVisible(true);
		play.setFocusable(false);

		exit.setBounds(400,350,125,78);
		exit.setVisible(false);
		exit.setFocusable(false);
		exit.setBackground(Color.WHITE);
		
		frame.setSize(800,720); // set size of frame
		frame.setLocation (width/2 - 400, height/2 - 390);  // location of frame
		frame.add(play); // adding listeners to buttons and adding buttons to frame
		frame.add(exit);
		exit.addActionListener(this);
		play.addActionListener(this);
		frame.addKeyListener(this) ;
		frame.add(this);

		box = new Point (3,100);  // creating box to detect collisions
		pipe = new Pipe();  // creating 2 sets of pipes
		pipe2 = new Pipe(); 
		this.tries=0;
		frame.setResizable(false); // doesnt allow user to increase/decrease size of frame

		frame.setVisible(true);
	}

	protected void paintComponent (Graphics g)
	{       
		super.paintComponent(g);
		
	
		
		this.background.paintIcon(this,g,0,0); // painting background image

		bird.paintIcon(this, g, box.x*30, box.y); // painting bird icon 

		tp.paintIcon(this, g, pipe.getX(),-1*tp.getIconHeight() + pipe.getLength()); // painting pipes on 

		bp.paintIcon(this,g,pipe.getX(),500 - pipe.getLength2());

		tp.paintIcon(this, g, pipe2.getX(),-1*tp.getIconHeight() + pipe2.getLength());

		bp.paintIcon(this, g, pipe2.getX(), 500 - pipe2.getLength2());

		g.setColor(Color.WHITE);
		g.fillRect(0, 500, 800, 220);  // creating box to detect collisions

		g.setFont(new Font("Aharoni", Font.PLAIN, 120));              

		if(this.dead==true){
			
			this.hsMenu.paintIcon(this,g,200,150);
			g.drawString(""+this.score/22, 320, 300); // displays score when bird is dead
		
			
		}
	 if(this.pressed==false && this.tries<=1){
             
             g.setFont(new Font("Aharoni", Font.PLAIN, 40));              
             g.drawString("Use UP arrow to Jump", 400 - 100, 200); 
		 }
		

	}

	public static void main(String[] args) 
	{


	} 

	public void actionPerformed(ActionEvent e) 
	{

		if(e.getSource()==play){ // when play button is pressed, starts a new game and hides buttons
			this.pressed=true;
			newGame();
			play.setVisible(false);
			exit.setVisible(false);

		}
		else if(e.getSource()==exit)
		{
			frame.dispose();  // disposes frame when exit button is pressed
		}


		endTime = System.currentTimeMillis(); // get current time 
		elapsedTime = endTime - startTime;  // calculating elapsed time 

		int x = pipe.getX();  // getting x-coordinates for pipes
		int x2 = pipe2.getX();

		if (x >-50)  // if the pipes location passes -50, sets to another location to bring them back on screen
		{ 
			pipe.setX(x-4);
			if (x < 450)
			{ 
				pipe2.setX(x2-4);
			}
		}
		else
		{ 
			pipe = pipe2; 
			counter++; 
			pipe2 = new Pipe();  
		}
		repaint();
		checkCollision(); //checks for collision, stops timer if collided

		speed = (int)(((int)(G)*elapsedTime)/1000) + velocity;  // calculating speed for bird

		box.y = box.y - speed; 




	}

	public void keyReleased(KeyEvent e) 
	{
		int i = e.getKeyCode(); // get keypressed

		if (i == KeyEvent.VK_UP&& this.dead==false)  // if up arrow is pressed and bird is alive   
		{ 
			startTime = System.currentTimeMillis();  // gets start time for jump and sets velocity to 3
			velocity = 3;  
		}



	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	} 

	public void newGame() 
	{
		tries++;
		play.setVisible(true); // setting buttons visible
		exit.setVisible(true);
		play.setFocusable(false);
		exit.setFocusable(false);

		this.dead = false; 
		this.score=0;
		this.start=true; // bird is alive again,start is true, and score reset
		box.setLocation(3,100); 

		pipe = new Pipe();
		pipe2 = new Pipe(); // creating pipes

		velocity = 0; 	//resetting velocity to stop it from using previous movements


		timer = new Timer(10, this); // creating new timer

		if(this.pressed==true){ // starts timer when play is pressed
			
		
		timer.start(); 
		}
		startTime = System.currentTimeMillis(); // gets new starttime



	}


	public void checkCollision()
	{ 
		int x = pipe.getX();  // gets pipe's X coordinate

		if (x < 125 && x > 38) // adds to score while bird is alive

		{ 
			this.score++;
			if (box.y < pipe.getLength() || box.y + 30 > pipe.getLength() + 100) // if bird hits pipes, timer is stopped,buttons are visible 
			{ 
				timer.stop(); 
				this.dead=true;
				this.start=false;
				play.setVisible(true);
				exit.setVisible(true);
				this.tries++;
			
			}
		}
		
		if(box.getY()>=500 || box.getY()<-30) // if bird has exceeded frame limits, stops timer,buttons are visible
		{
			timer.stop();
			this.dead=true;
			play.setVisible(true);
			exit.setVisible(true);
			this.start=false;
		}

		if (this.dead == true) // if bird is dead and high score is greater than the score achieved, new highscore is set
		{ 
			if (this.highscore < this.score/22)
			{
				this.highscore = this.score/22; 
			}
		}


	}


	public int getScore() 
	{
		return this.highscore; 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
