package main;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ComboBox;

public class PlayerDaoImpl implements PlayerDao {
	List<Player> players;
	
	@Override
	public List<Player> getAllPlayers(int userID) {
		players = new ArrayList<Player>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM players WHERE userID = " + userID + ";"; 
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int playerID = rs.getInt("playerID");
				String tag = rs.getString("tag");
				String notes = rs.getString("notes");
				
				Player player = new Player(playerID, userID, tag, notes);
				players.add(player);
			}
		} catch (Exception ex) {
 			ex.printStackTrace();
 			return null;
 		} finally {
 			SQLiteUtils.closeQuietly(rs);
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		
		return players;
	}

	@Override
	public Player getPlayerById(int playerID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT * FROM players WHERE playerID = " + playerID + ";";
	 		rs = stmt.executeQuery(sql);
	 		
	 		int userID = rs.getInt("userID");
	 		String tag = rs.getString("tag");
	 		String notes = rs.getString("notes");
			
	 		return new Player(playerID, userID, tag, notes);
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
	public String getPlayerTagById(int playerID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT tag FROM players WHERE playerID = " + playerID + ";";
	 		rs = stmt.executeQuery(sql);
	 		String tag = rs.getString("tag");
			
	 		return tag;	 		
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
	public int getPlayerIdByTag(String tag) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT playerID FROM players WHERE tag = '" + tag + "';";
			rs = stmt.executeQuery(sql);
			
			return rs.getInt("playerID");
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
	public void updatePlayer(Player player) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "UPDATE players SET tag = '" + player.getTag() + "', notes = '" + player.getNotes() + "' WHERE playerID = " + player.getPlayerID() + ";";
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
	public void insertPlayer(Player player) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO players (userID, tag, notes)" +
	 					 "VALUES (" + player.getUserID() + ", '" + player.getTag() + "', '" + player.getNotes() + "')";
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
	public void deletePlayerById(int playerID) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "DELETE FROM players WHERE playerID = " + playerID + ";";
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
	public void populatePlayerComboBox(ComboBox<String> playerComboBox, int userID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
 		playerComboBox.getItems().clear();
		try {
	 		stmt = con.createStatement();
	 		String sql = "Select tag FROM players WHERE userID = " + userID + ";"; 
	 		rs = stmt.executeQuery(sql);
	 		
	 		while (rs.next()) {
	 			String tag = rs.getString("tag");
	 			playerComboBox.getItems().add(tag);	 		
	 		}
		} catch (Exception ex) {
 			ex.printStackTrace();
			return;
 		} finally {
 			SQLiteUtils.closeQuietly(rs);
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
	}

	@Override
	public void populatePlayerComboBoxStats(ComboBox<String> playerComboBox, int userID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
 		playerComboBox.getItems().clear();
 		playerComboBox.getItems().add("All Players");
		try {
	 		stmt = con.createStatement();
	 		String sql = "Select tag FROM players WHERE userID = " + userID + ";"; 
	 		rs = stmt.executeQuery(sql);
	 		
	 		while (rs.next()) {
	 			String tag = rs.getString("tag");
	 			playerComboBox.getItems().add(tag);	 		
	 		}
		} catch (Exception ex) {
 			ex.printStackTrace();
			return;
 		} finally {
 			SQLiteUtils.closeQuietly(rs);
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}		
	}
}
