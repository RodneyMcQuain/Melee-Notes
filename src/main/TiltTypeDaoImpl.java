package main;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TiltTypeDaoImpl implements TiltTypeDao  {
	List<TiltType> tiltTypes;

	@Override
	public List<TiltType> getAllTiltTypes(int userID) {
		tiltTypes = new ArrayList<TiltType>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();

		try {
	 		stmt = con.createStatement();
	 		String sql = "Select * FROM tiltTypes WHERE userID = " + userID + ";";
	 		rs = stmt.executeQuery(sql);
	 		while(rs.next()) {
		 		int tiltTypeID = rs.getInt("tiltTypeID");
		 		String type = rs.getString("type");
		 		String describeProblem = rs.getString("describeProblem");
		 		String whyLogical = rs.getString("whyLogical");
		 		String logicFlawed = rs.getString("logicFlawed");
		 		String possibleSolutions = rs.getString("possibleSolutions");
		 		String whySolutions = rs.getString("whySolutions");		
		 		
		 		TiltType tiltType = new TiltType(tiltTypeID, userID, type, describeProblem, whyLogical, logicFlawed, possibleSolutions, whySolutions);
		 		tiltTypes.add(tiltType);
	 		}
	 		
	 		return tiltTypes;
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
	public TiltType getTiltTypeById(int tiltTypeID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();

		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT * FROM tiltTypes WHERE tiltTypeID = " + tiltTypeID + ";";
	 		rs = stmt.executeQuery(sql);
	 		
	 		int userID = rs.getInt("userID");
	 		String type = rs.getString("type");
	 		String describeProblem = rs.getString("describeProblem");
	 		String whyLogical = rs.getString("whyLogical");
	 		String logicFlawed = rs.getString("logicFlawed");
	 		String possibleSolutions = rs.getString("possibleSolutions");
	 		String whySolutions = rs.getString("whySolutions");		
	 		
	 		return new TiltType(tiltTypeID, userID, type, describeProblem, whyLogical, logicFlawed, possibleSolutions, whySolutions);
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
	public void updateTiltName(TiltType tiltType) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();

		try {
	 		stmt = con.createStatement();
	 		String sql = "UPDATE tiltTypes SET type = '" + tiltType.getType() + "' WHERE tiltTypeID = " + tiltType.getTiltTypeID() + ";";
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
	public void updateTiltProfile(TiltType tiltType) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();

		try {
	 		stmt = con.createStatement();
	 		String sql = "UPDATE tiltTypes SET describeProblem = '" + tiltType.getDescribeProblem() + "', whyLogical = '" + tiltType.getWhyLogical() + 
	 			  "', logicFlawed = '" + tiltType.getLogicFlawed() + "', possibleSolutions = '" + tiltType.getPossibleSolutions() 
	 			  + "', whySolutions = '" + tiltType.getWhySolutions() + "' WHERE tiltTypeID = " + tiltType.getTiltTypeID() + ";";
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
	public void insertTiltType(TiltType tiltType) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
// 		String sql = "INSERT INTO tiltTypes (userID, type, describeProblem, whyLogical, logicFlawed, possibleSolutions, whySolutions)" +

		try {
			stmt = con.createStatement();
	 		String sql = "INSERT INTO tiltTypes (userID, type)" +
	 					  "VALUES (" + tiltType.getUserID() + ", '" + tiltType.getType() + "' );";
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
	public void deleteTiltTypeById(int tiltTypeID) {
		Statement stmtTiltType = null;
		Statement stmtTiltProgressNote = null;
		Connection con = SQLiteUtils.getConnection();

		try {
			stmtTiltType = con.createStatement();
			String sqlTiltType = "DELETE FROM tiltTypes WHERE tiltTypeID = " + tiltTypeID + ";";
			stmtTiltType.executeUpdate(sqlTiltType);	
			
			stmtTiltProgressNote = con.createStatement();
			String sqlTiltProgressNote = "DELETE FROM tiltProgressNotes WHERE tiltTypeID = " + tiltTypeID + ";";
			stmtTiltProgressNote.executeUpdate(sqlTiltProgressNote);	
		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			return;
		} finally {
			SQLiteUtils.closeQuietly(stmtTiltType);
			SQLiteUtils.closeQuietly(stmtTiltProgressNote);
			SQLiteUtils.closeQuietly(con);
		}		
	}
}
