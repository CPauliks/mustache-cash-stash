//BEGIN FILE Lobby.java
package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

//BEGIN CLASS Lobby
public class Lobby extends JPanel implements ActionListener, ListSelectionListener 
{
	public static JFrame masterFrame = new JFrame("Lobby"); // the window container
	public static Vector<String> users = new Vector<String>(); // a collection of users
	static final long serialVersionUID = 0L; // just because :]
	public static Color backgroundColor = new Color(0, 0, 0); // color of the background
	protected static boolean statsWindowIsPopulated = false; //monitor the stats window
	//private JTextArea display = new JTextArea();
	private JLabel stats = new JLabel();
	private JList list;
	
	/**
	 * Creates and shows all lobby elements
	 * @param frame The Frame this lobby is contained in.
	 */
	//BEGIN Constructor public Lobby(JFrame frame)
	public Lobby(JFrame frame)
	{
		// create a master panel that holds all the elements
		JPanel masterPanel = new JPanel();
		
		//create a header, body, and footer
		JPanel header = new JPanel();
		JPanel middle = new JPanel();
		JPanel footer = new JPanel();
		
		// set background color to black for all elements
		header.setBackground(backgroundColor);
		middle.setBackground(backgroundColor);
		footer.setBackground(backgroundColor);
		masterPanel.setBackground(backgroundColor);
		this.setBackground(backgroundColor);
		
		//treat master panel as a grid that has 3 rows and one column
		masterPanel.setLayout(new GridLayout(3, 1));
		
		header.setLayout(new GridLayout(1, 1));
		middle.setLayout(new GridLayout(1, 1));
		
		// temporary for testing layout
		users.add("David");
		users.add("Ben");
		users.add("Chris");
		users.add("Dushyant");
		
		//create a list of users for the lobby
		
		JList list = new JList(users);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		
		stats = new JLabel("Ready to play!");
        stats.setForeground(Color.CYAN);
        stats.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		// add the list to the header
		header.add(list);
		
		middle.add(stats);
		//treat footer as a grid with 1 column and 3 rows
		footer.setLayout(new GridLayout(1, 3));
		
		//create 3 buttons
		JButton button1 = new JButton("Start Game");
		JButton button2 = new JButton("Refresh Lobby");
		JButton button3 = new JButton("Quit Game");
		
		// set action command to buttons
		button1.setActionCommand("Start Game");
		button2.setActionCommand("Refresh Lobby");
		button3.setActionCommand("Quit Game");
		
	 	// set action listeners to button
	 	button1.addActionListener(this); 
	 	button2.addActionListener(this);
	 	button3.addActionListener(this);
		
		// add the three buttons to the footer
		footer.add(button1);
		footer.add(button2);
		footer.add(button3);
		
		// add the header, body and footer to the window
		masterPanel.add(header);
		masterPanel.add(middle);
		masterPanel.add(footer);
		
		// add masterPanel to the window
		add(masterPanel);
		
		// set the size of the window
		frame.setSize(400, 460);
		
        // Place the window in the middle of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) 
        {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) 
        {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2); 
		
		
	}
	//END CONSTRUCTOR public Lobby(JFrame frame)
	
	@Override
	//BEGIN METHOD public void actionPerformed(ActionEvent event) 
	public void actionPerformed(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		
		// get the action of the button
		String command = event.getActionCommand();
		
		// if Start Game is clicked
		if(command.equalsIgnoreCase("Start Game")) 
		{
			//startGamePrompts();
			stats.setText("YOU CANNOT QUIT");
		}
		
		// if Refresh Lobby is clicked
		else if (command.equalsIgnoreCase("Refresh Lobby")) 
		{
			//refreshLobby();
			stats.setText("THERE IS NOTHING TO REFRESH");
		}
		
		// if Quit Game is clicked
		else if (command.equalsIgnoreCase("Quit Game")) 
		{
			//quitGame();
			stats.setText("GET OUTTA HERE");
		}
		
		//none of the other buttons have been called
		else 
		{
			//nothing
		}
	}
	//END METHOD public void actionPerformed(ActionEvent event) 
	
	//BEGIN METHOD void displayMessage(String prefix, ListSelectionEvent e) 
	void displayMessage(String prefix, ListSelectionEvent e) 
	{
	}
	//END METHOD void displayMessage(String prefix, ListSelectionEvent e) 

	@Override
	//BEGIN METHOD public void valueChanged(ListSelectionEvent event) 
	public void valueChanged(ListSelectionEvent event) 
	{
		// TODO Auto-generated method stub
		String statistics = event.toString();
		//list.getSelectedIndex();
		stats.setText(event.getLastIndex() + "");
	}
	//END METHOD public void valueChanged(ListSelectionEvent event) 

}
//END CLASS Lobby
//END FILE Lobby.java
