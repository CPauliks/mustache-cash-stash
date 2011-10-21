package model;

public class StatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StatisticsImp stats = new StatisticsImp();
		stats.addPlayer("player1");
		stats.addPlayer("player2");
		System.out.println(stats.getWins("player1"));
	}

}
