package main;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TiltProgressNoteDaoImpl implements TiltProgressNoteDao {
	List<TiltProgressNote> tiltProgressNotes;
	
	@Override
	public List<TiltProgressNote> getAllTiltProgressNotesByTiltTypeId(int tiltTypeID) {
		tiltProgressNotes = new ArrayList<TiltProgressNote>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
	 		String sql= "SELECT * FROM tiltProgressNotes WHERE tiltTypeID = " + tiltTypeID + ";";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int tiltProgressNoteID = rs.getInt("tiltProgressNoteID");
				String date = rs.getString("date");
				String notes = rs.getString("notes");
				
				TiltProgressNote tiltProgressNote = new TiltProgressNote(tiltProgressNoteID, tiltTypeID, date, notes);
				tiltProgressNotes.add(tiltProgressNote);
			}
			
			return tiltProgressNotes;
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
	public TiltProgressNote getTiltProgressNoteById(int tiltProgressNoteID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		 
		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT * FROM tiltProgressNotes WHERE tiltProgressNoteID = " + tiltProgressNoteID + ";";
	 		rs = stmt.executeQuery(sql);
	 		
	 		int tiltTypeID = rs.getInt("tiltTypeID");
	 		String date = rs.getString("date");
	 		String notes = rs.getString("notes");
	 		
	 		return new TiltProgressNote(tiltProgressNoteID, tiltTypeID, date, notes);
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
	public void updateTiltProgressNote(TiltProgressNote tiltProgressNote) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		 
		try {
	 		stmt = con.createStatement();
	 		String sql = "UPDATE tiltProgressNotes SET date = '" + tiltProgressNote.getDate() + "', notes = '" + tiltProgressNote.getNotes() + 
	 			  "' WHERE tiltProgressNoteID = " + tiltProgressNote.getTiltProgressNoteID() + ";";
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
	public void insertTiltProgressNote(TiltProgressNote tiltProgressNote) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		 
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO tiltProgressNotes (tiltTypeID, date, notes)" +
	 					 "VALUES (" + tiltProgressNote.getTiltTypeID() + ", '" + tiltProgressNote.getDate() + "', '" + tiltProgressNote.getNotes() + "')";
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
	public void deleteTiltProgressNoteById(int tiltProgressNoteID) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		 
		try {
			stmt = con.createStatement();
			String sql = "DELETE FROM tiltProgressNotes WHERE tiltProgressNoteID = " + tiltProgressNoteID + ";";
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
