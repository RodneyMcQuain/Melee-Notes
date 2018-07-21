package database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.Game;

public class GameDaoImpl implements GameDao {
	List<Game> games;
	
	@Override
	public List<Game> getAllGamesBySetId(int setID) {
		games = new ArrayList<Game>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM games WHERE setID = " + setID + ";"; 
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int gameID = rs.getInt("gameID");
				int myCharacterID = rs.getInt("myCharacterID");
				int opponentCharacterID = rs.getInt("opponentCharacterID");
				int stageID = rs.getInt("stageID");
				String outcome = rs.getString("outcome");
				
				Game game = new Game(gameID, setID, myCharacterID, opponentCharacterID, stageID, outcome);
				games.add(game);
			}
			
			return games;
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
	public Game getGameById(int gameID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM games WHERE gameID = " + gameID + ";";
	 		rs = stmt.executeQuery(sql);
	 		
	 		int setID = rs.getInt("setID");
	 		int myCharacterID = rs.getInt("myCharacterID");
	 		int opponentCharacterID = rs.getInt("opponentCharacterID");
	 		int stageID = rs.getInt("stageID");
	 		String outcome = rs.getString("outcome");

	 		return new Game(gameID, setID, myCharacterID, opponentCharacterID, stageID, outcome);
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
	public void updateGame(Game game) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
	 		String sql = "UPDATE games SET myCharacterID = " + game.getMyCharacterID() + ", opponentCharacterID = " + game.getOpponentCharacterID() + 
	 					 ", stageID = " + game.getStageID() + ", outcome = '" + game.getOutcome() + "' WHERE gameID = " + game.getGameID() + ";";
	 		stmt.executeUpdate(sql);
		System.out.println(game.getOpponentCharacterID());
		} catch (Exception ex) {
 			ex.printStackTrace();
			return;
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
	}

	@Override
	public void insertGame(Game game) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "INSERT INTO games (setID, outcome, myCharacterID, opponentCharacterID, stageID) " +
						  "VALUES (" + game.getSetID() + ", '" + game.getOutcome() + "', " + game.getMyCharacterID()
						  + ", " + game.getOpponentCharacterID() + ", " + game.getStageID() + ");";
			stmt.executeUpdate(sql);
		} catch (Exception ex) {
 			ex.printStackTrace();
			return;
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
	}

	@Override
	public void deleteGameById(int gameID) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "DELETE FROM games WHERE gameID = " + gameID + ";";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
 			ex.printStackTrace();
			return;
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
	}

	@Override
	public int calculateGamesWon(int setID) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT COUNT(*) AS gameWonCount FROM games WHERE outcome = 'Won' AND setID = " + setID + ";";
			ResultSet rs = stmt.executeQuery(sql);		
			
			return rs.getInt("gameWonCount");
		} catch (Exception ex) {
 			ex.printStackTrace();
			return 0;
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
	}

	@Override
	public int calculateGamesLost(int setID) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT COUNT(*) AS gameWonCount FROM games WHERE outcome = 'Lost' AND setID = " + setID + ";";
			ResultSet rs = stmt.executeQuery(sql);		
			
			return rs.getInt("gameWonCount");
		} catch (Exception ex) {
 			ex.printStackTrace();
			return 0;
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}		
	}

}
