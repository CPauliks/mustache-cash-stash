package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class TTTServlet
 */
@WebServlet(description = "Servlet for handling tic-tac-toe interactions", urlPatterns = { "/TTTServlet" })
public class TTTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, GameboardImp> currentGames;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TTTServlet() {
        super();
    	currentGames = new HashMap<Integer, GameboardImp>();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String gameNumString = request.getParameter("GameNumber");
		if(gameNumString != null){
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private static String toXML(GameboardImp board){
		StringBuffer sb = new StringBuffer();
		sb.append("  <gameState>\n");
		sb.append("    <gameResult>"+board.getResult().getRepr()+"</gameResult>\n");
		sb.append("    <whoseTurn>");
		if(board.xsTurn()){
			sb.append("X");
		}else if(board.osTurn()){
			sb.append("O");
		}else{
			sb.append("Nobody");
		}
		sb.append("    </whoseTurn>\n");
		sb.append("    <gameBoard>\n");
		for(int i = 0; i < 3; i++){
			sb.append("      <row id=\"row"+i+">");
			for(int j = 0; j < 3; j++){
				sb.append(board.getBoard()[i][j].getRepr());
			}
			sb.append("</row>\n");
		}
		sb.append("    </gameBoard>\n");
		sb.append("  </gameState>\n");
		return sb.toString();
	}

}
