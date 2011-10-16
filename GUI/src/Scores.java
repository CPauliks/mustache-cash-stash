import java.awt.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Scores extends JPanel
{
	final static long serialVersionUID = 0L; // to shut up Eclipse
	public static  JFrame frame = new JFrame ("Scores"); // This is the window that will be shown
    
    public Scores()
    {

        // set the size of the window
        frame.setSize(650, 550);
        
        
        // add a label to the top of the menu
        JLabel title = new JLabel("Scores Board");
        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        
        // create a table that holds the results
        String[] columnNames = {"First Player","Second Player", "Date", "Winner"};
        Object[][] scoresArray = getScores();
        JTable table = new JTable(scoresArray, columnNames);
        
        // add the title label to the upper side of the window
        add(title, BorderLayout.NORTH);
        
        // add the table to the lower side of the window
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane tableHolder = new JScrollPane(table);
        add(tableHolder, BorderLayout.SOUTH);
        
        // Place the window in the middle of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);         
        
        
        // set close operation
        //frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new FrameListener());
        
        // set window not resizable
        frame.setResizable(false);    
        
        // show the window
        frame.setContentPane(this);
        frame.setVisible(true);
        
        setBackground(new Color(0,0,0));
    }
    
    
    /*
     * getScores
     * 
     * returns a 2D array of scores
     */
    public Object[][] getScores()
    {
    	// the 2D array
    	Object[][] thisArray = new Object[4][4];
    	
    	// dummy data
    	thisArray[0][0] = "Player 1";
    	thisArray[0][1] = "Player 2";
    	thisArray[0][2] = "10-04-2011";
    	thisArray[0][3] = "Player 1";
    	thisArray[1][0] = "Player 2";
    	thisArray[1][1] = "Player 3";
    	thisArray[1][2] = "10-05-2011";
    	thisArray[1][3] = "Player 2";
    	thisArray[2][0] = "Player 3";
    	thisArray[2][1] = "Player 4";
    	thisArray[2][2] = "10-06-2011";
    	thisArray[2][3] = "Player 4";
    	thisArray[3][0] = "Player 1";
    	thisArray[3][1] = "Player 2";
    	thisArray[3][2] = "10-07-2011";
    	thisArray[3][3] = "Player 1";    	
    			
    	return thisArray;    	
    }
    
    /*
     * getFrame
     * 
     * returns the main frame
     */
    public static JFrame getFrame()
    {
    	return frame;
    }
    
}



/*
 * FrameListener
 * 
 * listen to the window actions
 */
class FrameListener extends WindowAdapter
{
  /*
   * windowClosing
   * 
   * when the user close the scores menu, notify
   * the mainWindow and close the window
   */
  public void windowClosing(WindowEvent e)
  {
    // get the scores window
	JFrame frame = Scores.getFrame();
	  
	// notify the mainWindow  
    MainWindow.scoresWindowIsClosed();
    
    // close the scores window
	frame.dispose();
  }
}
