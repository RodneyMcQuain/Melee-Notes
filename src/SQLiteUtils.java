import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteUtils {
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:melee.db");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	
		return c;
	}
	
	public static void closeQuietly(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeQuietly(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeQuietly(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createTournamentTable() {
		Statement stmt = null;
        Connection con = getConnection();

		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE tournaments " +
	 					 "(tournamentID     INTEGER       PRIMARY KEY 		AUTOINCREMENT, " +
	 					 "userID			INTEGER 	  NOT NULL, " +
	 					 "tournamentName    VARCHAR(100)  NOT NULL, " +
	 					 "dateOfTournament  DATE          NOT NULL, " +
	 					 "myPLacing     	INTEGER, " + 
	 					 "state 			VARCHAR(20), " +
	 					 "city              VARCHAR(40), " +
	 					 "notes             LONGTEXT, " + 
	 					 "FOREIGN KEY (userID)		REFERENCES users(userID));";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Tournament table created.");
	}
	
	public static void createMoneyTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE money " +
	 					 "(moneyID		    INTEGER       PRIMARY KEY 		AUTOINCREMENT, " +
	 					 "tournamentID		INTEGER 	  NOT NULL, " +
	 					 "prizeMoney		INTEGER, " +
	 					 "moneyMatch		INTEGER, " +
	 					 "entryFee	     	INTEGER, " + 
	 					 "venueFee 			INTEGER, " +
	 					 "travelExpenses    INTEGER, " + 
	 					 "FOREIGN KEY (tournamentID)		REFERENCES tournaments(tournamentID));";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Money table created.");
	}
	
	public static void createSetTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE sets " +
	 					 "(setID	        INTEGER       PRIMARY KEY 		AUTOINCREMENT," +
	 					 "playerID		    INTEGER	      NOT NULL, " +
	 					 "tournamentID     	INTEGER		  NOT NULL, " + 
	 					 "outcome	  		VARCHAR(5)    NOT NULL, " +
	 					 "bracketRound		VARCHAR(25), " +
	 					 "type	 			VARCHAR(20), " +
	 					 "format            VARCHAR(15), " +
	 					 "notes             LONGTEXT, " + 
	 					 "FOREIGN KEY (playerID)		REFERENCES players(playerID), " + 
	 					 "FOREIGN KEY (tournamentID)	REFERENCES tournaments(tournamentID));";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Set table created.");
	}
	
	public static void createGameTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE games " +
	 					 "(gameID     			INTEGER       PRIMARY KEY 		AUTOINCREMENT, " +
	 					 "setID    	  			INTEGER  	  NOT NULL, " +
	 					 "myCharacterID 		INTEGER 	  NOT NULL, " + 
	 					 "opponentCharacterID 	INTEGER 	  NOT NULL, " + 
	 					 "stageID	  			INTEGER       NOT NULL, " +
	 					 "outcome 				VARCHAR(5), " + 
	 					 "FOREIGN KEY (setID)				REFERENCES sets(setID), " + 
	 					 "FOREIGN KEY (myCharacterID)			REFERENCES characters(characterID), " + 
	 					 "FOREIGN KEY (opponentCharacterID)	REFERENCES characters(characterID), " + 
	 					 "FOREIGN KEY (stageID)				REFERENCES stages(stageID));";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Game table created.");
	}
	
	public static void createUserTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE users " +
	 					 "(userID     		INTEGER		  PRIMARY KEY		AUTOINCREMENT, " + 
	 					 "email 			VARCHAR(100)  NOT NULL			UNIQUE, " + 
	 					 "password 			VARCHAR(20)   NOT NULL);";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("User table created.");
	}
	
	public static void createPlayerTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE players " +
	 					 "(playerID     	INTEGER		  PRIMARY KEY		AUTOINCREMENT, " + 
	 					 "userID			INTEGER		  NOT NULL, " +
	 					 "tag	 			VARCHAR(100)  NOT NULL, " + 
	 					 "notes 			LONG TEXT, " + 
	 					 "FOREIGN KEY (userID)		REFERENCES users(userID));";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Player table created.");
	}
	
	public static void createStageTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE stages " +
	 					 "(stageID     	INTEGER		  PRIMARY KEY		AUTOINCREMENT, " + 
	 					 "name	 		VARCHAR(20)   NOT NULL);";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		
		System.out.println("Stage table created.");
	}
	
	public static void createCharacterTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE characters " +
	 					 "(characterID  INTEGER		 PRIMARY KEY		AUTOINCREMENT, " + 
	 					 "name	 		VARCHAR(20)  NOT NULL);";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Character table created.");
	}
	
	public static void createCharacterNotesTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE characterNotes " +
	 					 "(characterNoteID		INTEGER		 PRIMARY KEY		AUTOINCREMENT, " + 
	 					 "characterID	 		INTEGER		 NOT NULL, " + 
	 					 "userID				INTEGER		 NOT NULL, " +
	 					 "subject 				varchar(50)  NOT NULL, " + 
	 					 "notes					LONG TEXT, " +
	 					 "FOREIGN KEY (characterID)		REFERENCES characters(characterID));";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Character notes table created.");
	}
	
	public static void createAnalysisNotesTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE analysisNotes " +
	 					 "(analysisNoteID	   	INTEGER       PRIMARY KEY 		AUTOINCREMENT, " +
	 					 "userID				INTEGER		  NOT NULL, " +
	 					 "subject				varChar(50)   NOT NULL, " +
	 					 "focusPoints			varChar(50), " +
	 					 "notes					LONG TEXT, " +
	 					 "FOREIGN KEY (userID)	REFERENCES users(userID));";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Analysis Notes table created.");
	}
	
//	public static void createMentalityTable() {
//		Connection c = null;
//		Statement stmt = null;
//		
//		try {
//			Class.forName("org.sqlite.JDBC");
//	        c = DriverManager.getConnection("jdbc:sqlite:melee.db");
//	 		
//	 		stmt = c.createStatement();
//	 		String sql = "CREATE TABLE mentalityNotes " +
//	 					 "(mentalityNoteID	INTEGER       PRIMARY KEY 		AUTOINCREMENT, " +
//	 					 "date				DATE   		  NOT NULL, " +
//	 					 "subject			varChar(50)	  NOT NULL, " +
//	 					 "notes				LONG TEXT, " +
//	 					 "FOREIGN KEY (tournamentID)		REFERENCES tournaments(tournamentID));";
//	 		stmt.executeUpdate(sql);
//	 		stmt.close();
//	 		c.close();
//		} catch (Exception e) {
//			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//			System.exit(0);
//		}
//		System.out.println("Mentality Notes table created.");
//	}
	
	public static void createTiltTypeTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE tiltTypes " +
	 					 "(tiltTypeID		INTEGER       PRIMARY KEY 		AUTOINCREMENT, " +
	 					 "userID			INTEGER		  NOT NULL, " +
	 					 "type				varChar(50)	  NOT NULL, " +
	 					 "describeProblem	LONG TEXT, " +
	 					 "whyLogical		LONG TEXT, " +
	 					 "logicFlawed		LONG TEXT, " +
	 					 "possibleSolutions	varChar(50), " +
	 					 "whySolutions		LONG TEXT, " +
	 					 "FOREIGN KEY (userID)		REFERENCES users(userID));";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Tilt Type table created.");
	}
	
//	public static void createSpecifiedTiltTable() {
//		Connection c = null;
//		Statement stmt = null;
//		
//		try {
//			Class.forName("org.sqlite.JDBC");
//	        c = DriverManager.getConnection("jdbc:sqlite:melee.db");
//	 		
//	 		stmt = c.createStatement();
//	 		String sql = "CREATE TABLE specifiedTilt " +
//	 					 "(specifiedTiltID	INTEGER       PRIMARY KEY 		AUTOINCREMENT, " +
//	 					 "tiltTypeID		INTEGER   	  NOT NULL, " +
//	 					 "describeProblem	LONG TEXT, " +
//	 					 "whyLogical		LONG TEXT, " +
//	 					 "logicFlawed		LONG TEXT, " +
//	 					 "possibleSolutions	LONG TEXT, " +
//	 					 "whySolutions		LONG TEXT, " +
//	 					 "FOREIGN KEY (tiltTypeID)		REFERENCES tiltTypes(tiltTypeID));";
//	 		stmt.executeUpdate(sql);
//	 		stmt.close();
//	 		c.close();
//		} catch (Exception e) {
//			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//			System.exit(0);
//		}
//		System.out.println("Specified Tilt table created.");
//	}

	public static void createTiltProgressNotesTable() {
		Statement stmt = null;
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "CREATE TABLE tiltProgressNotes " +
	 					 "(tiltProgressNoteID	INTEGER       PRIMARY KEY 		AUTOINCREMENT, " +
	 					 "tiltTypeID			INTEGER   	  NOT NULL, " +
	 					 "date					DATE   		  NOT NULL, " +
	 					 "notes					LONG TEXT, " +
	 					 "FOREIGN KEY (tiltTypeID)		REFERENCES tiltTypes(tiltTypeID));";
	 		stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(stmt);
 			SQLiteUtils.closeQuietly(con);
 		}
		System.out.println("Tilt Progress notes table created.");
	}
	
	public static void populateUser() {
		Statement stmt = null;
        Connection con = getConnection();
		
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
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO tournaments (userID, tournamentName, dateOfTournament, myPlacing, state, city, notes) " +
	 					 "VALUES (1, 'IPFW Weekly', '2014-01-01', 1, 'IN', 'Fort Wayne', 'notes n shit'), " +
	 					 "(1, 'Super B-Town', '2013-01-01', 0, '', '', ''), " + 
	 					 "(1, 'OutFoxxed', '2013-01-01', 0, '', '', ''), " + 
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
	
	public static void populatePlayer() {
		Statement stmt = null;
        Connection con = getConnection();
		
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
        Connection con = getConnection();
		
		try {
	 		stmt = con.createStatement();
	 		String sql = "INSERT INTO sets (playerID, tournamentID, outcome, bracketRound, type, format, notes) " +
	 					 "VALUES (1, 1, 'Won', 'Winners Finals', 'Tournament', 'Bo3', 'notes n shit'), " +
	 					 "(1, 1, 'Lost', 'Losers Finals', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 2, 'Lost', 'Loser Semis', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 2, 'Won', 'Grand Finals', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 3, 'Lost', 'Losers', 'Tournament', 'Bo5', ''), " + 
	 					 "(1, 4, 'Won', 'Grand Finals', 'Tournament', 'Bo5', ''), " + 
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
        Connection con = getConnection();
		
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
        Connection con = getConnection();
		
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
        Connection con = getConnection();
		
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
        Connection con = getConnection();
		
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
		
    public static void createNewDatabase(String filename) {
        String url = "jdbc:sqlite:F:/users/Rodney/Documents/JavaWorkSpace/MeleeNotes" + filename;
        Connection con = null;
        
        try {
            con = DriverManager.getConnection(url);
        	if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
		} catch (Exception ex) {
			ex.printStackTrace();
 		} finally {
 			SQLiteUtils.closeQuietly(con);
 		}
    }
    
    public static void buildEntireDatabase() {
    	createNewDatabase("melee.db");
    	
    	createUserTable();
    	populateUser();
    	
    	createTournamentTable();
    	populateTournament();
    	
    	createPlayerTable();
    	populatePlayer();
    	
    	createSetTable();
    	populateSet();
    	
    	createCharacterTable();
    	populateCharacter();
    	
    	createCharacterNotesTable();
    	populateCharacterNotes();
    	
    	createAnalysisNotesTable();
    	
    	createTiltTypeTable();
    	
    	//createSpecifiedTiltTable();
    	createTiltProgressNotesTable();
    	
    	createStageTable();
    	populateStage();
    	
    	createGameTable();
    	populateGame();    	
    }
    
    public static void buildBlankDatabase() {
    	createNewDatabase("melee.db");
    	createUserTable();
    	createTournamentTable();
    	createPlayerTable();
    	createSetTable();
    	createCharacterTable();
    	createCharacterNotesTable();
    	createAnalysisNotesTable();
    	createStageTable();
    	createGameTable();
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
