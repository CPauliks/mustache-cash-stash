//BEGIN FILE OnlineUserTracker.java
package controller;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class that tracks which users are online by clearing all users who are not kept alive
 * over a certain period of time.
 * @author Benjamin Pellittieri for Mustache Cash Stash
 * @version 1.0
 */
//BEGIN CLASS OnlineUserTracker
public class OnlineUserTracker 
{
	private long keepAliveTime;
	private TreeSet<User> currentUsers;
	private HashSet<User> usersKeptAlive;
	private Timer t;

	/**
	 * Constructor which sets how long to keep the users alive for.
	 */
	//BEGIN CONSTRUCTOR OnlineUserTracker(long keepAliveTime)
	public OnlineUserTracker(long keepAliveTime)
	{
		this.keepAliveTime = keepAliveTime;
		currentUsers = new TreeSet<User>();
		usersKeptAlive = new HashSet<User>();
		t = new Timer(true); //is a daemon. This is why it gets a "true"
		t.schedule(new RefreshTask(), keepAliveTime, keepAliveTime);
	}
	//END CONSTRUCTOR OnlineUserTracker(long keepAliveTime)

	/**
	 * Simply adds a user to the set of users who have been kept alive this period.
	 * @param userToKeepAlive who has requested to be kept alive
	 */
	//BEGIN METHOD public void keepUserAlive(User userToKeepAlive)
	public void keepUserAlive(User userToKeepAlive)
	{
		usersKeptAlive.add(userToKeepAlive);
	}
	//END METHOD public void keepUserAlive(User userToKeepAlive)

	/**
	 * Removes everyone from the structure except those who have requested to be kept alive this period.
	 */
	//BEGIN METHOD public void dumpInactive()
	public void dumpInactive()
	{
		currentUsers.clear();
		currentUsers.addAll(usersKeptAlive);
		usersKeptAlive.clear();
	}
	//END METHOD public void dumpInactive()

	/**
	 * Checks if the given user is active. If she is, return true. Else false.
	 * @param test Who you want to know about.
	 * @return true if she is online, else false.
	 */
	//BEGIN METHOD public boolean isUserOnline(User test)
	public boolean isUserOnline(User test)
	{
		return currentUsers.contains(test) || usersKeptAlive.contains(test);
	}
	//END METHOD public boolean isUserOnline(User test)

	/**
	 * Returns a set containing all of the users currently online.
	 * @return a Set of Users currently online
	 */
	//BEGIN METHOD public Set<User> getOnlineUsers()
	public Set<User> getOnlineUsers()
	{
		currentUsers.addAll(usersKeptAlive);
		return currentUsers;
	}
	//END METHOD public Set<User> getOnlineUsers()

	/**
	 * Returns an XML representation of the users currently online.
	 * Root node is <UsersOnline>.
	 * Leaves are all <User>s.
	 * @return The same as getOnlineUsers, but as a String of XML.
	 */
	//BEGIN public String getOnlineUsersInXML
	public String getOnlineUsersInXML()
	{
		StringBuffer returnSB = new StringBuffer();
		returnSB.append("<UsersOnline>\n");
		currentUsers.addAll(usersKeptAlive);
		for(User u:currentUsers)
		{
			returnSB.append("  <User>"+u+"</User>\n");
		}
		returnSB.append("</UsersOnline>");
		return returnSB.toString();
	}
	//END public String getOnlineUsersInXML

	//BEGIN CLASS RefreshTask
	private class RefreshTask extends TimerTask 
	{
		@Override
		//BEGIN METHOD public void run()
		public void run()
		{
			dumpInactive();
		}
		//END METHOD public void run()
	}
	//END CLASS RefreshTask
}
//END CLASS OnlineUserTracker
//END FILE OnlineUserTracker.java
