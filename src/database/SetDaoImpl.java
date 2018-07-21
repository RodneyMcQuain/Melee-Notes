package database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.Set;

public class SetDaoImpl implements SetDao {
	List<Set> sets;
	
	@Override
	public List<Set> getAllSetsByTournamentId(int tournamentID) {
		sets = new ArrayList<Set>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM sets WHERE tournamentID = " + tournamentID + ";"; 
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int setID = rs.getInt("setID");
				int playerID = rs.getInt("playerID");
				String outcome = rs.getString("outcome");
				String bracketRound = rs.getString("bracketRound");
				String type = rs.getString("type");
				String format = rs.getString("format");
				String notes = rs.getString("notes");
				
				Set set = new Set(setID, tournamentID, playerID, outcome, bracketRound, type, format, notes);
				sets.add(set);
			}
			
			return sets;
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
	public Set getSetById(int setID) {
		sets = new ArrayList<Set>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT * FROM sets WHERE setID = " + setID + ";";
	 		rs = stmt.executeQuery(sql);
			
	 		int tournamentID = rs.getInt("tournamentID");
	 		int playerID = rs.getInt("playerID");
	 		String outcome = rs.getString("outcome");
	 		String bracketRound = rs.getString("bracketRound");
	 		String type = rs.getString("type");
	 		String format = rs.getString("format");
	 		String notes = rs.getString("notes");
	 		
			return new Set(setID, tournamentID, playerID, outcome, bracketRound, type, format, notes);
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
	public void updateSet(Set set) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
	 		String sql = "UPDATE sets SET playerID = " + set.getPlayerID() + ", bracketRound = '" + set.getBracketRound() + 
	 			  "', outcome = '" + set.getOutcome() + "', type = '" + set.getType() + "', format = '" + set.getFormat() + 
	 			  "', notes = '" + set.getNotes() + "' WHERE setID = " + set.getSetID() + ";";
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
	public void insertSet(Set set) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
	 		String sql = "INSERT INTO sets (tournamentID, playerID, outcome, bracketRound, format, type, notes)" 
	 					+ "VALUES (" + set.getTournamentID() + ", " + set.getPlayerID() + ", '" + set.getOutcome() 
	 					+ "', '" + set.getBracketRound() + "', '" + set.getFormat() + "', '" + set.getType() + "', '" + set.getNotes() + "');";
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
	public void deleteSetById(int setID) {
		Statement stmtSet = null;
		Statement stmtGames = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmtSet = con.createStatement();
			String sqlSet = "DELETE FROM sets WHERE setID = " + setID + ";";
			stmtSet.executeUpdate(sqlSet);	
			
	 		stmtGames = con.createStatement();
	 		String sqlGames = "DELETE FROM games where setID = " + setID + ";";
	 		stmtGames.executeUpdate(sqlGames);
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		} finally {
			SQLiteUtils.closeQuietly(stmtSet);
			SQLiteUtils.closeQuietly(stmtGames);
			SQLiteUtils.closeQuietly(con);
		}
	}
}
