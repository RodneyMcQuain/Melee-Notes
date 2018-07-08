package main;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatDaoImpl implements StatDao {

	@Override
	public int calculateSetsWon(Stat stat) {
		ResultSet rs = null;
		Statement stmt = null;
		
		String playerSql = stat.getPlayerSql();
		String formatSql = stat.getFormatSql();
		String typeSql = stat.getTypeSql();
		String startDate = stat.getStartDate();
		String endDate = stat.getEndDate();
		
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT COUNT(*) AS setWonCount FROM sets "
					+ "INNER JOIN tournaments ON sets.tournamentID = tournaments.tournamentID "
					+ "INNER JOIN players ON players.playerID = sets.playerID "
					+ "WHERE (sets.format " + formatSql + ") "
					+ "AND (sets.type " + typeSql + ") "
					+ "AND (sets.playerID " + playerSql + ") "
					+ "AND sets.outcome = 'Won' "
					+ "AND tournaments.dateOfTournament BETWEEN '" + startDate + "' AND '" + endDate + "';";
			rs = stmt.executeQuery(sql);
		
			return rs.getInt("setWonCount");
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			SQLiteUtils.closeQuietly(rs);
			SQLiteUtils.closeQuietly(stmt);
			SQLiteUtils.closeQuietly(con);
		}
	}

	@Override
	public int calculateSetsLost(Stat stat) {
		ResultSet rs = null;
		Statement stmt = null;
		
		String playerSql = stat.getPlayerSql();
		String formatSql = stat.getFormatSql();
		String typeSql = stat.getTypeSql();
		String startDate = stat.getStartDate();
		String endDate = stat.getEndDate();
		
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT COUNT(*) AS setLostCount FROM sets "
					+ "INNER JOIN tournaments ON sets.tournamentID = tournaments.tournamentID "
					+ "INNER JOIN players ON players.playerID = sets.playerID "
					+ "WHERE (sets.format " + formatSql + ") "
					+ "AND (sets.type " + typeSql + ") "
					+ "AND (sets.playerID " + playerSql + ") "
					+ "AND sets.outcome = 'Lost' "
					+ "AND tournaments.dateOfTournament BETWEEN '" + startDate + "' AND '" + endDate + "';";
			rs = stmt.executeQuery(sql);
		
			return rs.getInt("setLostCount");
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			SQLiteUtils.closeQuietly(rs);
			SQLiteUtils.closeQuietly(stmt);
			SQLiteUtils.closeQuietly(con);
		}
	}

	@Override
	public int[] calculateGamesWonOnStages(Stat stat) {
		final int AMOUNT_OF_STAGES = 6;
		ResultSet rs = null;
		Statement stmt = null;
		
		String playerSql = stat.getPlayerSql();
		String myCharacterSql = stat.getMyCharacterSql();
		String opponentCharacterSql = stat.getOpponenentCharacterSql();
		String formatSql = stat.getFormatSql();
		String typeSql = stat.getTypeSql();
		String startDate = stat.getStartDate();
		String endDate = stat.getEndDate();
		
		Connection con = SQLiteUtils.getConnection();
		
		try {
			int[] wonGames = new int[AMOUNT_OF_STAGES];
			for (int i = 0; i < AMOUNT_OF_STAGES; i++) {
				stmt = con.createStatement();
				String sql = "SELECT COUNT(*) AS gameWonCount FROM games "
						   + "INNER JOIN sets ON games.setID = sets.setID "
						   + "INNER JOIN players ON players.playerID = sets.playerID "
						   + "INNER JOIN characters ON characters.characterID = games.myCharacterID OR characters.characterID = games.opponentCharacterID "
						   + "INNER JOIN tournaments ON sets.tournamentID = tournaments.tournamentID "
						   + "WHERE tournaments.dateOfTournament BETWEEN '" + startDate + "' AND '" + endDate + "' "
						   + "AND (sets.format " + formatSql + ") "
						   + "AND (sets.type " + typeSql + ") "
						   + "AND games.outcome = 'Won' "
						   + "AND (games.myCharacterID " + myCharacterSql + ") "
						   + "AND (games.opponentCharacterID " + opponentCharacterSql + ") "
						   + "AND games.stageID = " + (i + 1) + " " 
						   + "AND (players.tag " + playerSql + ");";
				rs = stmt.executeQuery(sql);
				wonGames[i] = rs.getInt("gameWonCount");
			}
			
			return wonGames;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			SQLiteUtils.closeQuietly(rs);
			SQLiteUtils.closeQuietly(stmt);
			SQLiteUtils.closeQuietly(con);
		}
	}

	@Override
	public int[] calculateGamesLostOnStages(Stat stat) {
		final int AMOUNT_OF_STAGES = 6;
		ResultSet rs = null;
		Statement stmt = null;
		
		String playerSql = stat.getPlayerSql();
		String myCharacterSql = stat.getMyCharacterSql();
		String opponentCharacterSql = stat.getOpponenentCharacterSql();
		String formatSql = stat.getFormatSql();
		String typeSql = stat.getTypeSql();
		String startDate = stat.getStartDate();
		String endDate = stat.getEndDate();
		
		Connection con = SQLiteUtils.getConnection();
		
		try {
			int[] wonGames = new int[AMOUNT_OF_STAGES];
			for (int i = 0; i < AMOUNT_OF_STAGES; i++) {
				stmt = con.createStatement();
				String sql = "SELECT COUNT(*) AS gameLostCount FROM games "
						   + "INNER JOIN sets ON games.setID = sets.setID "
						   + "INNER JOIN players ON players.playerID = sets.playerID "
						   + "INNER JOIN characters ON characters.characterID = games.myCharacterID OR characters.characterID = games.opponentCharacterID "
						   + "INNER JOIN tournaments ON sets.tournamentID = tournaments.tournamentID "
						   + "WHERE tournaments.dateOfTournament BETWEEN '" + startDate + "' AND '" + endDate + "' "
						   + "AND (sets.format " + formatSql + ") "
						   + "AND (sets.type " + typeSql + ") "
						   + "AND games.outcome = 'Lost' "
						   + "AND (games.myCharacterID " + myCharacterSql + ") "
						   + "AND (games.opponentCharacterID " + opponentCharacterSql + ") "
						   + "AND games.stageID = " + (i + 1) + " " 
						   + "AND (players.tag " + playerSql + ");";
				rs = stmt.executeQuery(sql);
				wonGames[i] = rs.getInt("gameLostCount");
			}
			
			return wonGames;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			SQLiteUtils.closeQuietly(rs);
			SQLiteUtils.closeQuietly(stmt);
			SQLiteUtils.closeQuietly(con);
		}
	}
}
