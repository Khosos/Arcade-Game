import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;

public class BirdGUI extends JFrame implements ActionListener, ListSelectionListener
{
		private BainsyBird flappyGame;  // declaring variables
        private DefaultListModel character;
        private JButton btnStart, btnMainMenu;  
        private JList charlist; 
        private ButtonGroup group; 
      	private ImageIcon Bird;
      	private JLabel title;
      	private JLabel bg;

        public static void main(String[] args) 
        {
                new BirdGUI(); 
        }

        public BirdGUI() 
        {
                super("Bainsy Bird"); // name of window

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int)screenSize.getWidth();
                int height = (int) screenSize.getHeight(); 

                getContentPane().setLayout(null); // set layout to null

                title = new JLabel(new ImageIcon("title.png")); // creates jLabel for title 
                group = new ButtonGroup(); // group buttons 
                
                bg = new JLabel(new ImageIcon("guiBG.jpg"));
                bg.setBounds(0,0,800,720);
                
                btnStart = new JButton("Start"); // creating main menu/start buttons
                btnStart.setBackground(Color.WHITE);
                btnMainMenu = new JButton("Main Menu");
                btnMainMenu.setBackground(Color.WHITE);

                
           
                 
                JLabel pickChar = new JLabel(new ImageIcon("pickChar.png")); // uses a picture to direct user to pick a character
                pickChar.setBounds(0, 110, 400, 200);
                
                JLabel mode = new JLabel(new ImageIcon("mode.png")); // uses picture to direct user to pick a mode
                mode.setBounds(0,400, 200, 200);
             

           
                character = new DefaultListModel();  // adding elements to list so user can select bird
                charlist = new JList(character); 
                character.addElement("Flappy Bird");
                character.addElement("Frozen Bird");
                character.addElement("Angry Bird");
                character.addElement("Happy Bird");
                character.addElement("Green Bird");
                character.addElement("Red Bird");


               
                charlist.setBounds(425, 145, 127, 155); // setting bounds for lists,buttons,title
                title.setBounds(50,0,700,143);
               
                btnStart.setBounds(196, 629, 127, 39);
                btnMainMenu.setBounds(475, 629, 128, 39);

       
                getContentPane().add(charlist);
                getContentPane().add(btnStart);
                getContentPane().add(btnMainMenu);
                getContentPane().add(title);
                getContentPane().add(pickChar);
                getContentPane().add(mode);
                getContentPane().add(bg);

          
                btnStart.setEnabled(true);

                charlist.addListSelectionListener(this);
                btnStart.addActionListener(this);
                btnMainMenu.addActionListener(this);

                setSize(800,720);
                setLocation (width/2 - 400, height/2 - 390);
                setResizable(false); 
                
                setVisible(true);

        }

        public void actionPerformed(ActionEvent e) 
        {
                 if (e.getSource() == btnStart)
                {       
                        flappyGame = new BainsyBird(Bird); 
                }
                else if(e.getSource()==btnMainMenu)
                {
                	this.dispose(); // closes window, goes back to main menu
                }

        }


        public void valueChanged(ListSelectionEvent e)
        { 
                int value = charlist.getSelectedIndex();  // declaring int value to the index selected for the characters list
                										 //  If the user presses the first option, it get the value and set the bird's image to the desired character.
                
              if(value==-0){
            	  this.Bird = new ImageIcon("properbird.png"); 
              }
             
                else if (value == 1)
                { 
                        this.Bird = new ImageIcon("icybird.png"); 
                }
                else  if (value == 2)
                { 
                        this.Bird = new ImageIcon("angrybird.png");  
                }
              
                else  if (value == 3)
                { 
                        this.Bird = new ImageIcon("happybird.png"); 
                }
                else   if (value == 4)
                { 
                        this.Bird = new ImageIcon("greenbird.png"); 
                }
                else if (value == 5)
                { 
                        this.Bird = new ImageIcon("redyellowbird.png"); 
                }
    
        }
        
    	public int getFlappyScore() // getter method for score
    								// returns score from flappy bird
    	{ 
    		if (flappyGame!=null)
    		{ 
    			return this.flappyGame.getScore(); 
    		} 
    		return -1; 
    	}

    	

    
}