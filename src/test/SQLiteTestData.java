package test;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;

import database.SQLiteUtils;

public class SQLiteTestData {
	public static void populateUser() {
		Statement stmt = null;
        Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO users (email, password) " +
	 					 "VALUES ('rodneymcquain95@gmail.com', '1234'), " +
	 					 "('ezoterikSSBM@gmail.com', '123');";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("User table populated.");
	}
	
	public static void populateTournament() {
		Statement stmt = null;
        Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO tournaments (userID, tournamentName, dateOfTournament, myPlacing, state, city, notes) " +
	 					 "VALUES (1, 'IPFW Weekly', '2014-01-01', 1, 'IN', 'Fort Wayne', 'notes n shit'), " +
	 					 "(1, 'Super B-Town', '2013-01-01', 0, '', '', ''), " + 
	 					 "(1, 'OutFoxxed', '2013-05-05', 0, '', '', ''), " + 
	 					 "(1, 'Evo 2013', '2012-01-01', 0, '', '', ''), " + 
	 					 "(1, 'Pound 4', '2015-06-01', 0, '', '', ''), " + 
	 					 "(1, 'ROM 5', '2016-05-26', 0, '', '', ''), " + 
	 					 "(1, 'Here be Mid-Tiers', '2018-01-01', 0, '', '', '');";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Tournament table populated.");
	}
	
	public static void populateMoney() {
		Statement stmt = null;
        Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO moneys (tournamentID, prizeMoney, moneyMatch, entryFee, venueFee, travelExpense) " +
	 					 "VALUES (1, 5, 5, 10, 10, 20), " +
	 					 "(2, 20, 5, 10, 20, 10), " + 
	 					 "(3, 50, 0, 5, 5, 20), " + 
	 					 "(4, 0, 5, 75, 60, 100), " + 
	 					 "(5, 45, 0, 10, 5, 20), " + 
	 					 "(6, 5, 0, 10, 5, 20), " + 
	 					 "(7, 30, 10, 10, 5, 20);"; 
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Tournament table populated.");
	}
	
	public static void populatePlayer() {
		Statement stmt = null;
        Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO players (userID, tag, notes) " +
	 					 "VALUES (1, 'voorhese', 'fuck that dude'), " +
	 					 "(1, 'rik', ''), " + 
	 					 "(2, 'lance', 'agagafgasdfg');";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Player table populated.");
	}
	
	public static void populateSet() {
		Statement stmt = null;
        Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO sets (playerID, tournamentID, outcome, bracketRound, type, format, notes) " +
	 					 "VALUES (1, 1, 'Won', 'Winners Finals', 'Tournament', 'Bo3', 'notes n shit'), " +
	 					 "(1, 1, 'Lost', 'Losers Finals', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 2, 'Lost', 'Loser Semis', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 2, 'Won', 'Grand Finals', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 3, 'Lost', 'Losers', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 4, 'Won', 'Grand Finals', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 1, 'Lost', '', 'Tournament', 'Bo3', ''), " +
	 					 "(1, 1, 'Lost', 'Losers Finals', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 1, 'Lost', 'Losers Finals', 'Tournament', 'Bo3', ''), " +
	 					 "(1, 5, 'Won', 'Losers Finals', 'Tournament', 'Bo3', ''), " + 
	 					 "(1, 3, 'Lost', 'Losers Finals', 'Tournament', 'Bo3', ''), " + 
	 					 "(1, 2, 'Lost', 'Losers Finals', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 4, 'Won', 'Losers Finals', 'Tournament', 'Bo3', ''), " + 
	 					 "(1, 3, 'Won', 'Losers Finals', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 5, 'Won', 'Winners Semis', 'Tournament', 'Bo3', '');";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Set table populated.");
	}

	public static void populateCharacter() {
		Statement stmt = null;
        Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO characters (name) " +
	 					 "VALUES ('Fox'), ('Falco'), ('Marth'), ('Sheik'), ('Jigglypuff'), " +
	 					 "('Peach'), ('Ice Climbers'), ('Cptn. Falcon'), ('Pikachu'), " + 
	 					 "('Samus'), ('Dr. Mario'), ('Yoshi'), ('Luigi'), ('Gannon'), ('Mario'), " +
	 					 "('Young Link'), ('Donkey Kong'), ('Link'), ('Mr. Game & Watch'), " + 
	 					 "('Roy'), ('Mewtwo'), ('Zelda'), ('Ness'), ('Pichu'), ('Bowser'), ('Kirby');";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Character table populated.");
	}
	
	public static void populateStage() {
		Statement stmt = null;
        Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO stages (name) " +
	 					 "VALUES ('Battlefield'), ('Dreamland'), ('Yoshis Story'), " +
	 					 "('Fountain of Dreams'), ('Pokemon Stadium'), ('Final Destination');";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Stage table populated.");
	}
	
	public static void populateGame() {
		Statement stmt = null;
        Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO games (setID, myCharacterID, opponentCharacterID, stageID, outcome) " +
	 					 "VALUES (1, 1, 1, 1, 'Lost'), " +
	 					 "(2, 1, 2, 1, 'Lost'), " +
	 					 "(3, 2, 2, 2, 'Lost'), " +
	 					 "(4, 3, 2, 5, 'Lost'), " +
	 					 "(3, 4, 1, 4, 'Won'), " +
	 					 "(2, 6, 1, 3, 'Won');";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Game table populated.");
	}
	
	public static void populateCharacterNotes() {
		Statement stmt = null;
        Connection con = SQLiteUtils.getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO characterNotes (characterID, userID, subject, notes) " +
	 					 "VALUES (1, 1, 'fuck fox', 'character gets carried by shine spike'), " +
	 					 "(2, 1, 'falco sucks', 'man I wish this fucker was good');";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Character notes table populated.");
	}
	
    public static void buildEntireDatabase() {
		File db = new File("melee.db");
		if (!db.exists()) {
			SQLiteUtils.createNewDatabase("melee.db");
	    	
			SQLiteUtils.createUserTable();
	    	populateUser();
	    	
	    	SQLiteUtils.createTournamentTable();
	    	populateTournament();
	    	
	    	SQLiteUtils.createMoneyTable();
	    	populateMoney();
	    	
	    	SQLiteUtils.createPlayerTable();
	    	populatePlayer();
	    	
	    	SQLiteUtils.createSetTable();
	    	populateSet();
	    	
	    	SQLiteUtils.createCharacterTable();
	    	populateCharacter();
	    	
	    	SQLiteUtils.createCharacterNotesTable();
	    	populateCharacterNotes();
	    	
	    	SQLiteUtils.createAnalysisNotesTable();
	    	
	    	SQLiteUtils.createTiltTypeTable();
	    	
	    	SQLiteUtils.createTiltProgressNotesTable();
	    	
	    	SQLiteUtils.createStageTable();
	    	populateStage();
	    	
	    	SQLiteUtils.createGameTable();
	    	populateGame();    	
		}
    }
    
    public static void deleteDatabaseFile() {
    	File file = new File("melee.db");
    	
    	if (file.delete()) {
    		System.out.println("Database file deleted.");
    	}
    }
    
    public static int massPopulateDatabase(int i) {    	
    	if (i > 100) {
    		return 0;
    	}
    	populateSet();

    	populateGame();
   		populateGame();
    	i++;
    	
    	return massPopulateDatabase(i);
    }
}
