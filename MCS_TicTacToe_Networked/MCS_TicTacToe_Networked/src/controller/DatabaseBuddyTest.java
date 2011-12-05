package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseBuddyTest {
	
	DatabaseBuddy dbBuddy;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbBuddy = new DatabaseBuddy("jdbc:derby://localhost/TTTDB");
	}

	@After
	public void tearDown() throws Exception {
		dbBuddy.deleteAll();
	}

	@Test
	public void test() {
		User b = new User("Ben",1);
		User c = new User("Chris",1);
		assertTrue(dbBuddy.addUser(b));
		assertTrue(dbBuddy.containsUser(b));
		dbBuddy.keepAlive(b);
		assertTrue(dbBuddy.getLiveUsers(60000).contains(b));
		assertFalse(dbBuddy.getLiveUsers(1).contains(b));
		assertTrue(dbBuddy.addUser(c));
		assertTrue(dbBuddy.containsUser(c));
		dbBuddy.keepAlive(c);
		assertEquals(0, dbBuddy.requestGame(b, c));
		Integer i = dbBuddy.requestGame(c, b);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(i >= 1);
	}

}
