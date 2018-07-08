package main;

public interface StatDao {
	public int calculateSetsWon(Stat stat);
	public int calculateSetsLost(Stat stat);
	public int[] calculateGamesWonOnStages(Stat stat);
	public int[] calculateGamesLostOnStages(Stat stat);
}