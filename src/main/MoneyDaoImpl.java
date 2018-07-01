package main;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MoneyDaoImpl implements MoneyDao {
	List<Money> moneys;

	@Override
	public List<Money> getAllMoneysByTournamentId(int tournamentID) {
		moneys = new ArrayList<Money>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM moneys WHERE tournamentID = " + tournamentID + ";"; 
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int moneyID = rs.getInt("moneyID");
				int prizeMoney = rs.getInt("prizeMoney");
				int moneyMatch = rs.getInt("moneyMatch");
				int entryFee = rs.getInt("entryFee");
				int venueFee = rs.getInt("venueFee");
				int travelExpense = rs.getInt("travelExpense");
				
				Money money = new Money(moneyID, tournamentID, prizeMoney, moneyMatch, entryFee, venueFee, travelExpense);
				moneys.add(money);
			}
			
			return moneys;
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
	public Money getMoneyById(int moneyID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT * FROM moneys WHERE moneyID = " + moneyID + ";";
	 		rs = stmt.executeQuery(sql);
	 		
			int tournamentID = rs.getInt("tournamentID");
			int prizeMoney = rs.getInt("prizeMoney");
			int moneyMatch = rs.getInt("moneyMatch");
			int entryFee = rs.getInt("entryFee");
			int venueFee = rs.getInt("venueFee");
			int travelExpense = rs.getInt("travelExpense");

	 		return new Money(moneyID, tournamentID, prizeMoney, moneyMatch, entryFee, venueFee, travelExpense);
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
	public Money getMoneyByTournamentId(int tournamentID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();

		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT * FROM moneys WHERE tournamentID = " + tournamentID + ";";
	 		rs = stmt.executeQuery(sql);
			int moneyID = rs.getInt("moneyID");
			int prizeMoney = rs.getInt("prizeMoney");
			int moneyMatch = rs.getInt("moneyMatch");
			int entryFee = rs.getInt("entryFee");
			int venueFee = rs.getInt("venueFee");
			int travelExpense = rs.getInt("travelExpense");
	 		
	 		return new Money(moneyID, tournamentID, prizeMoney, moneyMatch, entryFee, venueFee, travelExpense);
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
	public int getMoneyIdByTournamentId(int tournamentID) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "SELECT moneyID FROM moneys WHERE tournamentID = " + tournamentID + ";";
	 		rs = stmt.executeQuery(sql);
	 		
	 		return rs.getInt("moneyID");
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
	public void updateMoney(Money money) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "UPDATE moneys SET prizeMoney = " + money.getPrizeMoney() + ", moneyMatch = " + money.getMoneyMatch() 
	 					+ ", entryFee = " + money.getEntryFee() + ", venueFee = " + money.getVenueFee() + ", travelExpense = " + money.getTravelExpense()
	 					+ " WHERE moneyID = " + money.getMoneyID() + ";";
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
	public void insertMoneyByTournamentId(int tournamentID) {
		Statement stmt = null;
		Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO moneys (tournamentID) " +
	 					 "VALUES (" + tournamentID + ");";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
 			ex.printStackTrace();
			return;
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
	}

//	@Override
//	public void deleteMoneyById(int moneyID) {
//		Statement stmt = null;
//		Connection con = SQLiteUtils.getConnection();
//		
//		try {
//			stmt = con.createStatement();
//			String sql = "DELETE FROM moneys WHERE moneyID = " + moneyID + ";";
//	 		stmt.executeUpdate(sql);
//		} catch (Exception ex) {
// 			ex.printStackTrace();
//			return;
// 		} finally {
// 			SQLiteUtils.closeQuietly(stmt);
// 			SQLiteUtils.closeQuietly(con);
// 		}
//	}
}
