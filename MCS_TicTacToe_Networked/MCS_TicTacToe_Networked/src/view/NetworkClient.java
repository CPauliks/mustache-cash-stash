//BEGIN FILE NetworkClient.java

package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.GameboardImp;
import model.PlaceValue;
import controller.User;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

/**
 * Client class for networked Tic Tac Toe games
 * @author Benjamin Pellittieri for MustacheCashStash
 * @version 1.0
 */
//BEGIN CLASS NetworkClient
public class NetworkClient implements Client {
	
	private HttpClient httpClient;
	private String serverLocation;
	private int gameNum;
	private GameboardImp currentBoard;
	private User user;
	
	/**
	 * Insanciate a new NetworkClient
	 * @param serverLocation the Location of the server
	 * @param gameNum The game number of the current game in progress.
	 * @param user The user this NetworkClient is representing
	 */
	//BEGIN CONSTRUCTOR public NetworkClient(String serverLocation, int gameNum, User user)
	public NetworkClient(String serverLocation, int gameNum, User user)
	{
		this.httpClient = new DefaultHttpClient();
		this.serverLocation = serverLocation;
		this.gameNum = gameNum;
		this.user = user;	
	}
	//END CONSTRUCTOR public NetworkClient(String serverLocation, int gameNum, User user)

	@Override
	//BEGIN METHOD public void updateGameboard()
	public void updateGameboard() 
	{
		HttpGet getGame = new HttpGet(serverLocation);
		HttpParams parameters = new BasicHttpParams();
		parameters.setParameter("User", this.user.toString());
		parameters.setParameter("GameNumber", Integer.toString(this.gameNum));
		getGame.setParams(parameters);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		
		try 
		{
			responseBody = httpClient.execute(getGame, responseHandler);
		} 
		catch (IOException e) 
		{
			//TODO Placeholder catch. Figure out how to handle gracefully.
			e.printStackTrace();
		}
		setCurrentBoard(XMLDOMReader.convert(responseBody));
	}
	//END METHOD public void updateGameboard()

	@Override
	//BEGIN METHOD public void updateStatistics() 
	public void updateStatistics() 
	{
		//TODO Auto-generated method stub
	}
	//END METHOD public void updateStatistics() 

	@Override
	//BEGIN METHOD public boolean requestMove(int xPosition, int yPosition, PlaceValue side)
	public boolean requestMove(int xPosition, int yPosition, PlaceValue side) 
	{
		HttpPost sendMove = new HttpPost(serverLocation);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("User", this.user.toString()));
		formParams.add(new BasicNameValuePair("GameNumber", Integer.toString(this.gameNum)));
		formParams.add(new BasicNameValuePair("xPosition", Integer.toString(xPosition)));
		formParams.add(new BasicNameValuePair("yPosition", Integer.toString(yPosition)));
		formParams.add(new BasicNameValuePair("Side", side.getRepr()));
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		try
		{
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			sendMove.setEntity(entity);
			responseBody = httpClient.execute(sendMove, responseHandler);
		} 
		catch (HttpResponseException e) 
		{
			return false;
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
			return false;
		}
		
		if(responseBody.equals("Success"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//END METHOD public boolean requestMove(int xPosition, int yPosition, PlaceValue side)

	@Override
	//BEGIN METHOD public boolean resign(PlaceValue side) 
	public boolean resign(PlaceValue side) 
	{
		HttpPost sendResign = new HttpPost(serverLocation);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("User", this.user.toString()));
		formParams.add(new BasicNameValuePair("GameNumber", Integer.toString(this.gameNum)));
		formParams.add(new BasicNameValuePair("Resign", "true"));
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		try 
		{
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			sendResign.setEntity(entity);
			responseBody = httpClient.execute(sendResign, responseHandler);
		} 
		catch (HttpResponseException e) 
		{
			return false;
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
			return false;
		}
		
		if(responseBody.equals("Success"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//END METHOD public boolean resign(PlaceValue side) 
	
	@Override
	//BEGIN METHOD public void endSession(PlaceValue side)
	public void endSession(PlaceValue side) 
	{
		if( gameNum != 0) 
		{
			resign(side);
		}
	}
	//END METHOD public void endSession(PlaceValue side)
	
	/**
	 * @return the currentBoard
	 */
	//BEGIN METHOD public GameboardImp getCurrentBoard()
	public GameboardImp getCurrentBoard() 
	{
		return currentBoard;
	}
	//END METHOD public GameboardImp getCurrentBoard()
	
	/**
	 * @param currentBoard the currentBoard to set
	 */
	//BEGIN METHOD private void setCurrentBoard(GameboardImp currentBoard)
	private void setCurrentBoard(GameboardImp currentBoard) 
	{
		this.currentBoard = currentBoard;
	}
	//END METHOD private void setCurrentBoard(GameboardImp currentBoard)
}
//END CLASS NetworkClient
//END FILE NetworkClient.java