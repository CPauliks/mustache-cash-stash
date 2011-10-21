package model;

public class StatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Statistics stats = new StatisticsImp();
		stats.addPlayer("plaer");
		System.out.println(stats.getWins("plaer"));
		stats.addWin("plaer");
		System.out.println(stats.getWins("plaer"));
		System.out.println(stats.getLosses("plaer"));
		stats.addLoss("plaer");
		System.out.println(stats.getLosses("plaer"));
		System.out.println(stats.getWins("plaer"));
		stats.addWin("plaer");
		System.out.println(stats.getWins("plaer"));
		System.out.println(stats.getLosses("plaer"));
		stats.addLoss("plaer");
		System.out.println(stats.getLosses("plaer"));
		stats.addPlayer("butt");
		System.out.println(stats.getWins("butt"));
		stats.addWin("butt");
		System.out.println(stats.getWins("butt"));
		System.out.println(stats.getLosses("plaer"));
		stats.addLoss("plaer");
		System.out.println(stats.getLosses("plaer"));
		System.out.println(stats.toString());
		
	}

}
