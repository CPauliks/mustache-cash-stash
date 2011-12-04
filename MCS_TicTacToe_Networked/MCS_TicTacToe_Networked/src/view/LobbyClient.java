package view;

import java.util.List;

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

import controller.User;

public class LobbyClient {
	private boolean hasBeenActivated;
	private String userName;
	private User myUser;
	private HttpClient httpClient;
	private String serverLocation;
	
	public LobbyClient(String serverLocation, String newUserName, long keepAliveTime)
	{
		this.httpClient = new DefaultHttpClient();
		this.serverLocation = serverLocation;
		this.userName = newUserName;
		this.hasBeenActivated = false;
	}
	
	public LobbyClient(String serverLocation, User oldUser, long keepAliveTime)
	{
		this.httpClient = new DefaultHttpClient();
		this.serverLocation = serverLocation;
		this.myUser = oldUser;
		this.hasBeenActivated = false;
	}
	
	public List<User> getUserList()
	{
		HttpGet getRequest = new HttpGet(serverLocation);
		ResponseHandler<String> r = new BasicResponseHandler();
		try{
			return XMLDOMReader.convertUserList(this.httpClient.execute(getRequest, r));
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean registerUser()
	{
		if (!this.hasBeenActivated) {
			HttpPost postRequest = new HttpPost(serverLocation);
			HttpParams params = new BasicHttpParams();
			params.setParameter("RequestedUserName", this.userName);
			postRequest.setParams(params);
			ResponseHandler<String> r = new BasicResponseHandler();
			try {
				this.myUser = User.parseUser(httpClient.execute(postRequest, r));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean keepAlive()
	{
		if (this.hasBeenActivated) {
			HttpPost postRequest = new HttpPost(serverLocation);
			HttpParams params = new BasicHttpParams();
			params.setParameter("UserToKeepAlive", this.myUser.toString());
			postRequest.setParams(params);
			ResponseHandler<String> r = new BasicResponseHandler();
			try {
				return httpClient.execute(postRequest, r).equals("Success");
			} catch (Exception e) {
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public int requestGame(User requestee)
	{
		if(this.hasBeenActivated)
		{
			HttpPost postRequest = new HttpPost(serverLocation);
			HttpParams params = new BasicHttpParams();
			params.setParameter("User", this.myUser.toString());
			params.setParameter("RequestedUserName", requestee.toString());
			postRequest.setParams(params);
			ResponseHandler<String> r = new BasicResponseHandler();
			try {
				String response = httpClient.execute(postRequest, r);
				if (response.equals("Success"))
				{
					return 0;
				}
				else
				{
					return Integer.parseInt(response);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return -1;
	}
	
}
