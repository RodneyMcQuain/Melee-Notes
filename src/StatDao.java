
public interface StatDao {
	public int calculateSetsWon(String playerSql, String formatSql, String typeSql, String startDate, String endDate);
	public int calculateSetsLost(String playerSql, String formatSql, String typeSql, String startDate, String endDate);
	public int[] calculateGamesWonOnStages(String playerSql, String myCharacterSql, String opponentCharacterSql, String formatSql, String typeSql, String startDate, String endDate);
	public int[] calculateGamesLostOnStages(String playerSql, String myCharacterSql, String opponentCharacterSql, String formatSql, String typeSql, String startDate, String endDate);
}