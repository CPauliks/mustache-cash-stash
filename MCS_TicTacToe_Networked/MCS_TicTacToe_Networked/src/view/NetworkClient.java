package view;
import java.io.IOException;

import model.GameboardImp;
import model.PlaceValue;
import controller.User;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class NetworkClient implements Client {
	
	public NetworkClient(String serverLocation, int gameNum, User user)
	{
		httpClient = new DefaultHttpClient();
		this.serverLocation = serverLocation;
		this.gameNum = gameNum;
		this.user = user;
		
	}

	@Override
	public void updateGameboard() {
		HttpGet getGame = new HttpGet(serverLocation);
		HttpParams parameters = new BasicHttpParams();
		parameters.setParameter("User", this.user.toString());
		parameters.setParameter("GameNumber", Integer.toString(this.gameNum));
		getGame.setParams(parameters);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		try {
			responseBody = httpClient.execute(getGame, responseHandler);
		} catch (IOException e) {
			//TODO Placeholder catch. Figure out how to handle gracefully.
			e.printStackTrace();
		}
		currentBoard = XMLDOMReader.convert(responseBody);
	}

	@Override
	public void updateStatistics() {
		//TODO Auto-generated method stub
	}

	@Override
	public boolean requestMove(int xPosition, int yPosition, PlaceValue side) {
		HttpPost sendMove = new HttpPost(serverLocation);
		HttpParams parameters = new BasicHttpParams();
		parameters.setParameter("User", this.user.toString());
		parameters.setParameter("GameNumber", Integer.toString(this.gameNum));
		parameters.setParameter("xPosition", Integer.toString(xPosition));
		parameters.setParameter("yPosition", Integer.toString(yPosition));
		parameters.setParameter("Side", side.getRepr());
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		try{
			responseBody = httpClient.execute(sendMove, responseHandler);
		} catch (HttpResponseException e) {
			return false;
		} catch (IOException ioe) {
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

	@Override
	public boolean resign() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void endSession() {
		// TODO Auto-generated method stub

	}
	
	private HttpClient httpClient;
	private String serverLocation;
	private int gameNum;
	private GameboardImp currentBoard;
	private User user;

}
