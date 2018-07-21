package database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.AnalysisNote;

public class AnalysisNoteDaoImpl implements AnalysisNoteDao {
	List<AnalysisNote> analysisNotes;
	
	@Override
	public List<AnalysisNote> getAllAnalysisNotes(int userID) {
		analysisNotes = new ArrayList<AnalysisNote>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM analysisNotes WHERE userID = " + userID + ";"; 
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int analysisNoteID = rs.getInt("analysisNoteID");
				String subject = rs.getString("subject");
				String focusPoints = rs.getString("focusPoints");
				String notes = rs.getString("notes");
				
				AnalysisNote analysisNote = new AnalysisNote(analysisNoteID, userID, subject, focusPoints, notes);
				analysisNotes.add(analysisNote);
			}
			
			return analysisNotes;
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
	public AnalysisNote getAnalysisNoteById(int analysisNoteID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT * FROM analysisNotes WHERE analysisNoteID = " + analysisNoteID + ";";
	 		rs = stmt.executeQuery(sql);
	 		
	 		int userID = rs.getInt("userID");
	 		String subject = rs.getString("subject");
	 		String focusPoints = rs.getString("focusPoints");
	 		String notes = rs.getString("notes");

	 		
	 		return new AnalysisNote(analysisNoteID, userID, subject, focusPoints, notes);
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
	public void updateAnalysisNote(AnalysisNote analysisNote) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "UPDATE analysisNotes SET subject = '" + analysisNote.getSubject() + "', focusPoints = " + analysisNote.getFocusPoints() 
	 			+ ", notes = '" + analysisNote.getNotes() + "' WHERE analysisNoteID = " + analysisNote.getAnalysisNoteID() + ";";
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
	public void insertAnalysisNote(AnalysisNote analysisNote) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO analysisNotes (userID, subject, focusPoints, notes)" +
	 					 "VALUES (" + analysisNote.getUserID() + ", '" + analysisNote.getSubject() 
	 					 		+ "', '" + analysisNote.getFocusPoints() + "', '" + analysisNote.getNotes() + "')";
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
	public void deleteAnalysisNoteById(int analysisNoteID) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "DELETE FROM analysisNotes WHERE analysisNoteID = " + analysisNoteID + ";";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
 			ex.printStackTrace();
			return;
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}		
	}
}
