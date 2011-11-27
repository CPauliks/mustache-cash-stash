package view;
import java.io.StringReader;

import model.GameResult;
import model.GameboardImp;
import model.PlaceValue;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLDOMReader {
	public static GameboardImp convert(String xml)
	{
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
			String turn = getContentByTag(doc, "whoseTurn");
			boolean xsTurn = (turn.equals("X"));
			boolean osTurn = (turn.equals("O"));
			String resultString = getContentByTag(doc, "gameResult");
			GameResult result;
			if(resultString.equals("XWIN"))
			{
				result = GameResult.XWIN;
			}else if(resultString.equals("OWIN"))
			{
				result = GameResult.OWIN;
			}else if(resultString.equals("CAT"))
			{
				result = GameResult.CAT;
			}else
			{
				result = GameResult.PENDING;
			}
			String rowValue;
			char c;
			PlaceValue[][] board = new PlaceValue[3][3];
			for(int i = 0; i < 3; i++){
				rowValue = doc.getElementById("row"+i).getNodeValue();
				for(int j = 0; j < 3; j++){
					c = rowValue.charAt(j);
					if(c == 'X'){
						board[i][j] = PlaceValue.X;
					}else if(c == 'O'){
						board[i][j] = PlaceValue.O;
					}else{
						board[i][j] = PlaceValue.BLANK;
					}
				}
			}
			return new GameboardImp(xsTurn, osTurn, result, board);
		}catch(Exception e){
			//TODO Figure out a graceful way to handle this error
			e.printStackTrace();
			return null;
		}
	}
	
	private static String getContentByTag(Document doc, String tag)
	{
		return doc.getElementsByTagName(tag).item(0).getNodeValue();
	}
}
