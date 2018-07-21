package database;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.Tournament;

public class TournamentDaoImpl implements TournamentDao {
	List<Tournament> tournaments;
	
	@Override
	public List<Tournament> getAllTournaments(int userID) {
		tournaments = new ArrayList<Tournament>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM tournaments WHERE userID = " + userID + " ORDER BY dateOfTournament DESC;";  
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int tournamentID = rs.getInt("tournamentID");
				String tournamentName = rs.getString("tournamentName");
				String dateOfTournament = rs.getString("dateOfTournament");
				int myPlacing = rs.getInt("myPlacing");
				String state = rs.getString("state");
				String city = rs.getString("city");
				String notes = rs.getString("notes");
				
				Tournament tournament = new Tournament(tournamentID, userID, tournamentName, dateOfTournament, myPlacing, state, city, notes);
				tournaments.add(tournament);
			}
			
			return tournaments;
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
	public List<Tournament> getTournamentsByDate(int userID, String startDate, String endDate) {
		tournaments = new ArrayList<Tournament>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT tournamentID, tournamentName, dateOfTournament FROM tournaments WHERE dateOfTournament BETWEEN '" 
					+ startDate + "' AND '" + endDate + "' WHERE userID = " + userID + " ORDER BY dateOfTournament DESC;";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int tournamentID = rs.getInt("tournamentID");
				String tournamentName = rs.getString("tournamentName");
				String dateOfTournament = rs.getString("dateOfTournament");
				int myPlacing = rs.getInt("myPlacing");
				String state = rs.getString("state");
				String city = rs.getString("city");
				String notes = rs.getString("notes");
				
				Tournament tournament = new Tournament(tournamentID, userID, tournamentName, dateOfTournament, myPlacing, state, city, notes);
				tournaments.add(tournament);
			}
			
			return tournaments;
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
	public Tournament getTournamentById(int tournamentID) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		Tournament tournament = null;
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM tournaments WHERE tournamentID = " + tournamentID + ";";
	 		rs = stmt.executeQuery(sql);
	 		
	 		int userID = rs.getInt("userID");
	 		String tournamentName = rs.getString("tournamentName");
	 		int myPlacing = rs.getInt("myPlacing");
	 		String dateOfTournament = rs.getString("dateOfTournament");
	 		String state = rs.getString("state");
	 		String city = rs.getString("city");
	 		String notes = rs.getString("notes");
	 		
			tournament = new Tournament(tournamentID, userID, tournamentName, dateOfTournament, myPlacing, state, city, notes);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
 			SQLiteUtils.closeQuietly(rs);
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
		}
		
		return tournament;
	}

	@Override
	public void updateTournament(Tournament tournament) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();

		try {
			stmt = con.createStatement();
	 		String sql = "UPDATE tournaments SET tournamentName = '" + tournament.getTournamentName() + "', dateOfTournament = '" + tournament.getDateOfTournament() 
	 			  + "', myPlacing = " + tournament.getMyPlacing() + ", state = '" + tournament.getState() + "', city = '" + tournament.getCity() 
	 			  + "', notes = '" + tournament.getNotes() + "' WHERE tournamentID = " + tournament.getTournamentID() + ";";
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
	public void insertTournament(Tournament tournament) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		 
		try {
			stmt = con.createStatement();
	 		String sql = "INSERT INTO tournaments (userID, tournamentName, dateOfTournament, myPlacing, state, city, notes)" 
	 				   + "VALUES (" + tournament.getUserID() + ", '" + tournament.getTournamentName()  + "', '" + tournament.getDateOfTournament() 
	 				   + "', " + tournament.getMyPlacing() + ", '" + tournament.getState() + "', '" + tournament.getCity()  + "', '" + tournament.getNotes()  + "');";
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
	public void deleteTournamentById(int tournamentID, int setID) {
		Statement stmtTournament = null;
		Statement stmtMoney = null;
		Statement stmtSets = null;
		Statement stmtGames = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmtTournament = con.createStatement();
			String sqlTournament = "DELETE FROM tournaments WHERE tournamentID = " + tournamentID + ";";
			stmtTournament.executeUpdate(sqlTournament);	
			
			stmtMoney = con.createStatement();
			String sqlMoney = "DELETE FROM moneys WHERE tournamentID = " + tournamentID + ";";
			stmtMoney.executeUpdate(sqlMoney);	
			
			stmtSets = con.createStatement();
			String sqlSet = "DELETE FROM sets WHERE tournamentID = " + tournamentID + ";";
			stmtSets.executeUpdate(sqlSet);
			
			stmtGames = con.createStatement();
			String sqlGame = "DELETE FROM games WHERE setID = " + setID + ";";
			stmtGames.executeUpdate(sqlGame);
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		} finally {
			SQLiteUtils.closeQuietly(stmtTournament);
			SQLiteUtils.closeQuietly(stmtMoney);
			SQLiteUtils.closeQuietly(stmtSets);
			SQLiteUtils.closeQuietly(stmtGames);
			SQLiteUtils.closeQuietly(con);
		}
	}

	@Override
	public int getMostRecentTournamentId() {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		 
		try {
			stmt = con.createStatement();
//	 		String sql = "SELECT MAX(tournamentID) AS mostRecentTournamentID FROM moneys;";
	 		String sql = "SELECT tournamentID FROM tournaments ORDER BY tournamentID DESC LIMIT 1;";
			rs = stmt.executeQuery(sql);
	 	
	 		return rs.getInt("tournamentID");
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			SQLiteUtils.closeQuietly(rs);
			SQLiteUtils.closeQuietly(stmt);
			SQLiteUtils.closeQuietly(con);
		}
	}
}
