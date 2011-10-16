package UI;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import model.*;


public class GameBoardDisplay extends JPanel
{
	static final long serialVersionUID = 0L; // to shut up Eclipse
	private GameboardImp boardModel;
	
	public boolean attemptMove(int xPosition, int yPosition){
		if(boardModel.getResult() == GameResult.PENDING){
    		if(boardModel.xsTurn()){
    			return boardModel.requestMove(xPosition, yPosition, PlaceValue.X);
    		}else if(boardModel.osTurn()){
    			return boardModel.requestMove(xPosition, yPosition, PlaceValue.O);
    		}
    	}
		return false;
	}
    
    public GameBoardDisplay(String username, String modeName)
    {
    	boardModel = new GameboardImp();
    	
       // This is the window that will be shown
    	JFrame frame = new JFrame ("Tic Tac Toe - " + modeName + " Mode");
        
        // set the size of the window
        frame.setSize(450, 400);
        
        // get players
        String[] players = Game.getPlayers(username);
        String player1 = players[0];
        String player2 = players[1];
        
        // add a label to the top of the window
        JLabel title = new JLabel(player1 + " vs " + player2);
        title.setFont(new Font("Serif", Font.BOLD, 36));  
        title.setForeground(Color.WHITE);
        
        // add the title label to the upper side of the window
        add(title, BorderLayout.NORTH); 
        
        // create the board size 3 by 3
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3,3));

        
        // show the board's borders
        Border whiteLine = BorderFactory.createLineBorder(Color.white);
        board.setBackground(new Color(0,0,0));
        board.setPreferredSize(new Dimension(400, 200));
        
        // create cells
        JLabel cell_00 = new JLabel ("0,0");
        cell_00.setBorder(whiteLine);
        cell_00.setForeground(Color.WHITE);
        cell_00.setBackground(new Color(0,0,0));        
        JLabel cell_01 = new JLabel ("0,1");
        cell_01.setBorder(whiteLine);
        cell_01.setForeground(Color.WHITE);
        cell_01.setBackground(new Color(0,0,0));        
        JLabel cell_02 = new JLabel ("0,2");
        cell_02.setBorder(whiteLine);
        cell_02.setForeground(Color.WHITE);
        cell_02.setBackground(new Color(0,0,0));        
        JLabel cell_10 = new JLabel ("1,0");
        cell_10.setBorder(whiteLine);
        cell_10.setForeground(Color.WHITE);
        cell_10.setBackground(new Color(0,0,0));        
        JLabel cell_11 = new JLabel ("1,1");
        cell_11.setBorder(whiteLine);
        cell_11.setForeground(Color.WHITE);
        cell_11.setBackground(new Color(0,0,0));        
        JLabel cell_12 = new JLabel ("1,2");
        cell_12.setBorder(whiteLine);
        cell_12.setForeground(Color.WHITE);
        cell_12.setBackground(new Color(0,0,0));        
        JLabel cell_20 = new JLabel ("2,0");
        cell_20.setBorder(whiteLine);
        cell_20.setForeground(Color.WHITE);
        cell_20.setBackground(new Color(0,0,0));        
        JLabel cell_21 = new JLabel ("2,1");
        cell_21.setBorder(whiteLine);
        cell_21.setForeground(Color.WHITE);
        cell_21.setBackground(new Color(0,0,0));        
        JLabel cell_22 = new JLabel ("2,2");
        cell_22.setBorder(whiteLine);
        cell_22.setForeground(Color.WHITE);
        cell_22.setBackground(new Color(0,0,0));        
        
        
        // create a listener for all cells
        MouseListener listener00 = new MouseAdapter(){public void mouseClicked(MouseEvent event){attemptMove(0,0);}};
        MouseListener listener01 = new MouseAdapter(){public void mouseClicked(MouseEvent event){attemptMove(0,1);}};
        MouseListener listener02 = new MouseAdapter(){public void mouseClicked(MouseEvent event){attemptMove(0,2);}};
        MouseListener listener10 = new MouseAdapter(){public void mouseClicked(MouseEvent event){attemptMove(1,0);}};
        MouseListener listener11 = new MouseAdapter(){public void mouseClicked(MouseEvent event){attemptMove(1,1);}};
        MouseListener listener12 = new MouseAdapter(){public void mouseClicked(MouseEvent event){attemptMove(1,2);}};
        MouseListener listener20 = new MouseAdapter(){public void mouseClicked(MouseEvent event){attemptMove(2,0);}};
        MouseListener listener21 = new MouseAdapter(){public void mouseClicked(MouseEvent event){attemptMove(2,1);}};
        MouseListener listener22 = new MouseAdapter(){public void mouseClicked(MouseEvent event){attemptMove(2,2);}};
         
        
        
        // link listeners to cells
        cell_00.addMouseListener(listener00);
        cell_01.addMouseListener(listener01);
        cell_02.addMouseListener(listener02);
        cell_10.addMouseListener(listener10);
        cell_11.addMouseListener(listener11);
        cell_12.addMouseListener(listener12);
        cell_20.addMouseListener(listener20);
        cell_21.addMouseListener(listener21);
        cell_22.addMouseListener(listener22);
        
        
        
        // add cells to the board
        board.add(cell_00);
        board.add(cell_01);
        board.add(cell_02);
        board.add(cell_10);
        board.add(cell_11);
        board.add(cell_12);
        board.add(cell_20);
        board.add(cell_21);
        board.add(cell_22);
        
        
        // add the board label to the lower side of the window
        add(board, BorderLayout.SOUTH);         
        
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // set window not resizable
        frame.setResizable(false);    
        
        // show the window
        frame.setContentPane(this);
        frame.setVisible(true);
        setBackground(new Color(0,0,0));
    }
    
}
