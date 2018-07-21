package database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.CharacterNote;

public class CharacterNoteDaoImpl implements CharacterNoteDao {
	List<CharacterNote> characterNotes;
	
	@Override
	public List<CharacterNote> getAllCharacterNotesByCharacterId(int userID, int characterID) {
		characterNotes = new ArrayList<CharacterNote>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT * FROM characterNotes WHERE userID = " + userID + " AND characterID = " + characterID + ";"; 
	 		rs = stmt.executeQuery(sql);

	 		while(rs.next()) {
				int characterNoteID = rs.getInt("characterNoteID");
				String subject = rs.getString("subject");
				String notes = rs.getString("notes");

				CharacterNote characterNote = new CharacterNote(characterNoteID, characterID, userID, subject, notes);
				characterNotes.add(characterNote);
			}

			return characterNotes;
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
	public CharacterNote getCharacterNoteById(int characterNoteID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT * FROM characterNotes WHERE characterNoteID = " + characterNoteID + ";";
	 		rs = stmt.executeQuery(sql);
	 		int characterID = rs.getInt("characterID");
	 		int userID = rs.getInt("userID");
	 		String subject = rs.getString("subject");
	 		String notes = rs.getString("notes");
	 		
	 		return new CharacterNote(characterNoteID, characterID, userID, subject, notes);
		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			return null;
		} finally {
			SQLiteUtils.closeQuietly(rs);
			SQLiteUtils.closeQuietly(stmt);
			SQLiteUtils.closeQuietly(con);
		}	
	}

	@Override
	public void updateCharacterNote(CharacterNote characterNote) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		String sql = "UPDATE characterNotes SET subject = '" + characterNote.getSubject() 
	 					+ "', notes = '" + characterNote.getNotes() + "' WHERE characterNoteID = " + characterNote.getCharacterNoteID() + ";";
			stmt = con.prepareStatement(sql);
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			return;
		} finally {
			SQLiteUtils.closeQuietly(stmt);
			SQLiteUtils.closeQuietly(con);
		}
	}

	@Override
	public void insertCharacterNote(CharacterNote characterNote) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "INSERT INTO characterNotes (characterID, userID, subject, notes)" +
	 					 "VALUES (" + characterNote.getCharacterID() + ", " + characterNote.getUserID() 
	 					 + ", '" + characterNote.getSubject() + "', '" + characterNote.getNotes() + "')";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			return;
		} finally {
			SQLiteUtils.closeQuietly(stmt);
			SQLiteUtils.closeQuietly(con);
		}			
	}

	@Override
	public void deleteCharacterNoteById(int characterNoteID) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "DELETE FROM characterNotes WHERE characterNoteID = " + characterNoteID + ";";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			return;
		} finally {
			SQLiteUtils.closeQuietly(stmt);
			SQLiteUtils.closeQuietly(con);
		}	
	}
}
