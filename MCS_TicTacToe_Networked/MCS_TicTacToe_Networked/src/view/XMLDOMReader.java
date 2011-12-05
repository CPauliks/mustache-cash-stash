//BEGIN FILE XMLDOMReader.java
package view;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import model.GameResult;
import model.GameboardImp;
import model.PlaceValue;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import controller.User;

/**
 * A class of XML Parsers to reconstruct gamedata after being sent of HTTP
 * @author Mustache Cash Stash
 * @version 1.0
 */
//BEGIN CLASS XMLDOMReader
public class XMLDOMReader 
{
	public static List<User> convertUserList(String xml)
	{
		ArrayList<User> returnList = new ArrayList<User>();
		try
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
			NodeList userNodes = doc.getElementsByTagName("User");
			for(int i = 0; i < userNodes.getLength(); i++)
			{
				returnList.add(User.parseUser(userNodes.item(i).getTextContent()));
			}
		}
		catch(Exception e)
		{
			//TODO placehoder exception catch
			e.printStackTrace();
		}
		return returnList;
	}

	/**
	 * A parser for GameBoards represented in XML as constructed by the TTTServlet
	 * @param xml The XML String to be parsed
	 * @return The GameBoard the String represents
	 */
	//BEING METHOD public static GameboardImp convert(String xml)
	public static GameboardImp convert(String xml)
	{
		try
		{
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

			for(int i = 0; i < 3; i++)
			{
				rowValue = doc.getElementById("row"+i).getNodeValue();
				for(int j = 0; j < 3; j++)
				{
					c = rowValue.charAt(j);
					if(c == 'X')
					{
						board[i][j] = PlaceValue.X;
					}
					else if(c == 'O')
					{
						board[i][j] = PlaceValue.O;
					}
					else
					{
						board[i][j] = PlaceValue.BLANK;
					}
				}
			}
			return new GameboardImp(xsTurn, osTurn, result, board);
		}
		catch(Exception e)
		{
			//TODO Figure out a graceful way to handle this error
			e.printStackTrace();
			return null;
		}
	}
	//END METHOD public static GameboardImp convert(String xml)

	/**
	 * Returns the contents of a specific XML tag within the file
	 * @param doc the XML document
	 * @param tag the XML tag to look in
	 * @return the String contained in the specified tag
	 */
	//BEGIN METHOD private static String getContentByTag(Document doc, String tag)
	private static String getContentByTag(Document doc, String tag)
	{
		return doc.getElementsByTagName(tag).item(0).getNodeValue();
	}
	//END METHOD private static String getContentByTag(Document doc, String tag)
}
//END CLASS XMLDOMReader
//END FILE XMLDOMReader.java