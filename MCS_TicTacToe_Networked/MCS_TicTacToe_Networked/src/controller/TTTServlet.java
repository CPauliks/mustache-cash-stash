package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
	private HashMap<Integer, Game> currentGames;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TTTServlet() {
        super();
    	currentGames = new HashMap<Integer, Game>();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String gameNumString = request.getParameter("GameNumber");
		String userString = request.getParameter("User");
		if(gameNumString != null){
			int gameNum = Integer.parseInt(gameNumString);
			Game game = this.currentGames.get(gameNum);
			if(game != null)
			{
				if(game.hasAPlayer(User.parseUser(userString)))
				{
					StringBuffer sb = new StringBuffer();
					sb.append("<TTTGame num=\""+gameNum+"\">\n");
					sb.append(boardToXML(game.getBoard()));
					sb.append("\n</TTTGame>");
					writer.println(sb.toString());
				}
				else
				{
					response.sendError(403, "You shouldn't be looking at other people's games. Cheater.");
				}
			}
			else
			{
				response.sendError(404, "lol, that's not a game.");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameNumString = request.getParameter("GameNumber");
		String userString = request.getParameter("User");
		String side = request.getParameter("Side");
		String resigning = request.getParameter("Resign");
		if (resigning == null) {
			resigning = "false";
		}
		if(gameNumString != null){
			int gameNum = Integer.parseInt(gameNumString);
			Game game = this.currentGames.get(gameNum);
			if(game != null)
			{
				boolean success = false;
				if(resigning.equals("true")) {
					//TODO: End game, assign loss to player
					success = true;
				}
				else {
					int xPos = Integer.parseInt(request.getParameter("xPosition"));
					int yPos = Integer.parseInt(request.getParameter("yPosition"));
					PlaceValue piece = PlaceValue.parsePlaceValue(side);

					switch(piece){
						case X:
							if(game.hasXPlayer(User.parseUser(userString)))
							{
								success = game.requestMove(xPos, yPos, piece);
							}
							break;
						case O:
							if(game.hasOPlayer(User.parseUser(userString)))
							{
								success = game.requestMove(xPos, yPos, piece);
							}
							break;
						default:
							success = false;
							break;
					}
				}
				if(success)
				{
					response.getWriter().print("Success");
				}
				else
				{
					response.getWriter().print("Failure");
				}
			}
		}
	}
	
	private static String boardToXML(GameboardImp board){
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
		sb.append("  </gameState>");
		return sb.toString();
	}

}
