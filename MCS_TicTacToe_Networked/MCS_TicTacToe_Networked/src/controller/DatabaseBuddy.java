package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class DatabaseBuddy {
	
	Connection conn;
	Random rng;
	
	public DatabaseBuddy(String databaseURI)
	{
		try{
			conn = DriverManager.getConnection(databaseURI);
		}catch(Exception e){
			e.printStackTrace();
		}
		rng = new Random();
	}
	
	public boolean addUser(User u)
	{
		String query = "INSERT INTO users(characterName, characterCode, isOnline) VALUES('"+u.getUserName()+"', "+u.getCharacterCode()+", FALSE)";
		try{
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		}catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Requests a game from requester to requestee. If there is a pending reciprical request, a game is created and the game number is returned.
	 * If the request is successful, 0 is returned.
	 * If every thing goes pear-shaped, you get a -1.
	 * @param requestee
	 * @param requester
	 * @return
	 */
	//TODO needs guards
	public int requestGame(User requester, User requestee)
	{
		String queryRecipRequest = "SELECT requesteeName, requesteeCode, requesterName, requesterCode FROM requests WHERE"
				+" requesteeName='"+requester.getUserName()+" AND requesteeCode="+requester.getCharacterCode()+" AND"
				+" requesterName='"+requestee.getUserName()+" AND requesterCode="+requestee.getCharacterCode();
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryRecipRequest);
			if(rs.next())
			{
				int i;
				do{
					i= rng.nextInt(8191)+1;
				}while(conn.createStatement().executeQuery("SELECT gameNum FROM activeGames WHERE gameNum="+i).next());
				if(rng.nextBoolean())
				{
					String newGameQuery = "INSERT INTO activeGames(XName, XCode, OName, OCode) VALUES('"
							+requestee.getUserName()+"', "+requestee.getCharacterCode()+", '"
							+requester.getUserName()+"', "+requester.getCharacterCode()+")";
					conn.createStatement().executeQuery(newGameQuery);
					return i;
				}
			}
			else
			{
				String query = "INSERT INTO requests(requesteeName, requesteeCode, requesterName, requesterCode) VALUES('"
						+requestee.getUserName()+"', "+requestee.getCharacterCode()+", '"
						+requester.getUserName()+"', "+requester.getCharacterCode()+")";
				conn.createStatement().executeQuery(query);
				return 0;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public boolean containsUser(User newUser) {
		// TODO Auto-generated method stub
		return false;
	}
}
