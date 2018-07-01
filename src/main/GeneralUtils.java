package main;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.control.ComboBox;

public class GeneralUtils {	
	private static String[] characters = {"", "Fox", "Falco", "Marth", "Sheik", "Jigglypuff", "Peach", "Ice Climbers", "Cptn. Falcon", "Pikachu", 
			   "Samus", "Dr. Mario", "Yoshi", "Luigi", "Gannon", "Mario", "Young Link", "Donkey Kong", "Link", 
			   "Mr. Game & Watch", "Roy", "Mewtwo", "Zelda", "Ness", "Pichu", "Bowser", "Kirby"};
	
	private static String[] stages = {"", "Battlefield", "Dreamland", "Yoshis Story", "Fountain of Dreams", "Final Destination", "Pokemon Stadium"};
	
//	public static ArrayList<Tournament> getTournaments() {
//		ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
//		Statement stmt = null;
//		String tournamentName = null;
//		String myPlacing = null;
//		String dateOfTournament = null;
//		String state = null;
//		String city = null;
//		String notes = null;
//		
//		try {
//	 		stmt = c.createStatement();
//	 		String sql = "SELECT * FROM tournaments";
//	 		ResultSet rs = stmt.executeQuery(sql);
//			
//	 		while(rs.next()) {
//		 		tournamentName = rs.getString("tournamentName");
//		 		myPlacing = rs.getString("myPlacing");
//		 		dateOfTournament = rs.getString("dateOfTournament");
//		 		state = rs.getString("state");
//		 		city = rs.getString("city");
//		 		notes = rs.getString("notes");
//		 		Tournament tournament = new Tournament(tournamentName, dateOfTournament, Integer.parseInt(myPlacing), state, city, notes);
//		 		tournaments.add(tournament);
//	 		}
//	 		
//	 		stmt.close();
//	 		rs.close();
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return tournaments;
//	}
	
//	public static void setSpecifiedTournament(TextField tfTournamentName, TextField tfMyPlacing, TextField tfDateOfTournament, TextField tfState, TextField tfCity, TextArea taNotes, int tournamentRecordLocation) {
//		Statement stmt = null;
//		String sql = null;
//		ResultSet rs = null;
//		
//		String tournamentName = "'" + tfTournamentName.getText() + "'";
//		String myPlacing = tfMyPlacing.getText();
//		String dateOfTournament = tfDateOfTournament.getText();
//		String state = "'" + tfState.getText() + "'";
//		String city = "'" + tfCity.getText() + "'";
//		String notes = "'" + taNotes.getText() + "'";
//		System.out.println(tournamentName);
//		try {
//	 		stmt = c.createStatement();
//	 		
////	 		sql = "SELECT * FROM tournaments WHERE tournamentID = " + tournamentRecordLocation + ";";
////	 		rs = stmt.executeQuery(sql);
////	 		tournamentName = rs.getString("tournamentName");
////	 		myPlacing = rs.getString("myPlacing");
////	 		dateOfTournament = rs.getString("dateOfTournament");
////	 		state = rs.getString("state");
////	 		city = rs.getString("city");
////	 		notes = rs.getString("notes");
//	 		
////	 		rs.close();
//	 		
//	 		sql = "UPDATE tournaments SET tournamentName = " + tournamentName + ", myPlacing = " + myPlacing
//	 					+ ", dateOfTournament = " + dateOfTournament + ", state = " + state + ", city = " + city
//	 					+ ", notes = " + notes + " WHERE tournamentID = " + tournamentRecordLocation + ";";
//	 		stmt.executeUpdate(sql);
//	 		
//	 		stmt.close();
//	// 		rs.close();
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		tfTournamentName.setText(tournamentName);
//		tfMyPlacing.setText(myPlacing);
//		tfDateOfTournament.setText(dateOfTournament);
//		tfState.setText(state);
//		tfCity.setText(city);
//		taNotes.setText(notes);
//	}
	
//	public static void applyChangesToTournament(int tournamentRecordLocation) {
//		Statement stmt = null;
//		
//		try {
//			stmt = c.createStatement();
//			String sql = "UPDATE FROM tournaments WHERE tournamentID = " + tournamentRecordLocation + ";";
//	 		stmt.executeUpdate(sql);
//	 		stmt.close();
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			//System.exit(0);
//		}
//	}
	
	
//	public static ArrayList<Tournament> getSets() {
//		ArrayList<Tournament> sets = null;
//		
//		return sets;
//	}
    
    public static Settings deserialize() {
    	ObjectInputStream in = null;
    	Settings settings = null;
  
		try {
			File file = new File("settings.ser");
			file.createNewFile();
			FileInputStream fileIn = new FileInputStream("settings.ser");
			in = new ObjectInputStream(fileIn);
			settings = (Settings) in.readObject();
			in.close();
			fileIn.close();
		} catch (EOFException eof) {
			defaultSettings();
			deserialize();
    	} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return settings;
    }
	
    public static void defaultSettings() {
    	Settings settings = new Settings("Fox", "Dark");
    	
    	try {
			FileOutputStream fileOut = new FileOutputStream("settings.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
		
			out.writeObject(settings);
			out.close();
			fileOut.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
    }
    
//	public static int calculateTournamentRows(int userID) {
//		Statement stmt = null;
//		String sql = null;
//    	ResultSet rs = null;
//    	int rowCount = 0;
//    	
//		try {		
//	 		stmt = c.createStatement();
//	 		sql = "SELECT COUNT(*) AS rowcount FROM tournaments WHERE userID = " + userID + ";";
//	 		rs = stmt.executeQuery(sql);
//	 		rs.next();
//	 		rowCount = rs.getInt("rowcount");
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return rowCount;	
//	}
//	
//	public static int calculateSetRows(int tournamentID) {
//		Statement stmt = null;
//		String sql = null;
//    	ResultSet rs = null;
//    	int rowCount = 0;
//    	
//		try {		
//	 		stmt = c.createStatement();
//	 		sql = "SELECT COUNT(*) AS rowcount FROM sets WHERE tournamentID = " + tournamentID + ";";
//	 		rs = stmt.executeQuery(sql);
//	 		rs.next();
//	 		rowCount = rs.getInt("rowcount");
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return rowCount;	
//	}
//	
//	public static int calculatePlayerRows(int userID) {
//		Statement stmt = null;
//		String sql = null;
//    	ResultSet rs = null;
//    	int rowCount = 0;
//    	
//		try {		
//	 		stmt = c.createStatement();
//	 		sql = "SELECT COUNT(*) AS rowcount FROM players WHERE userID = " + userID + ";";
//	 		rs = stmt.executeQuery(sql);
//	 		rs.next();
//	 		rowCount = rs.getInt("rowcount");
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return rowCount;	
//	}
//	
//	public static int calculateCharacterNotesRows(int characterID, int userID) {
//		Statement stmt = null;
//		String sql = null;
//    	ResultSet rs = null;
//    	int rowCount = 0;
//    	
//		try {		
//	 		stmt = c.createStatement();
//	 		sql = "SELECT COUNT(*) AS rowcount FROM characterNotes WHERE characterID = " + characterID + " AND userID = " + userID + ";";
//	 		rs = stmt.executeQuery(sql);
//	 		rs.next();
//	 		rowCount = rs.getInt("rowcount");
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return rowCount;	
//	}
//	
//	public static int calculateAnalysisNotesRows(int userID) {
//		Statement stmt = null;
//		String sql = null;
//    	ResultSet rs = null;
//    	int rowCount = 0;
//    	
//		try {		
//	 		stmt = c.createStatement();
//	 		sql = "SELECT COUNT(*) AS rowcount FROM analysisNotes WHERE userID = " + userID + ";";
//	 		rs = stmt.executeQuery(sql);
//	 		rs.next();
//	 		rowCount = rs.getInt("rowcount");
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return rowCount;	
//	}
//	
//	public static int calculateTiltTypesRows(int userID) {
//		Statement stmt = null;
//		String sql = null;
//    	ResultSet rs = null;
//    	int rowCount = 0;
//    	
//		try {		
//	 		stmt = c.createStatement();
//	 		sql = "SELECT COUNT(*) AS rowcount FROM tiltTypes WHERE userID = " + userID + ";";
//	 		rs = stmt.executeQuery(sql);
//	 		rs.next();
//	 		rowCount = rs.getInt("rowcount");
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return rowCount;	
//	}
//	
//	public static int calculateTiltProgressNotesRows(int tiltTypeID) {
//		Statement stmt = null;
//		String sql = null;
//    	ResultSet rs = null;
//    	int rowCount = 0;
//    	
//		try {		
//	 		stmt = c.createStatement();
//	 		sql = "SELECT COUNT(*) AS rowcount FROM tiltProgressNotes WHERE tiltTypeID = " + tiltTypeID + ";";
//	 		rs = stmt.executeQuery(sql);
//	 		rs.next();
//	 		rowCount = rs.getInt("rowcount");
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return rowCount;	
//	}
//	
//	public static int calculateGameRows(int setID) {
//		Statement stmt = null;
//		String sql = null;
//    	ResultSet rs = null;
//    	int rowCount = 0;
//    	
//		try {		
//	 		stmt = c.createStatement();
//	 		sql = "SELECT COUNT(*) AS rowcount FROM games WHERE setID = " + setID + ";";
//	 		rs = stmt.executeQuery(sql);
//	 		rs.next();
//	 		rowCount = rs.getInt("rowcount");
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return rowCount;	
//	}
//	
//	public static int calculateGamesWon(int setID) {
//		int gameWon = 0;
//		
//		try {
//			Statement stmt = c.createStatement();
//			String sql = "SELECT outcome FROM games WHERE setID = " + setID + ";";
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				if (rs.getString("outcome").equals("Won")) {
//					gameWon++;
//				}
//			}
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return gameWon;
//	}
//	
//	public static int calculateGamesLost(int setID) {
//		int gameLost = 0;
//		
//		try {
//			Statement stmt = c.createStatement();
//			String sql = "SELECT outcome FROM games WHERE setID = " + setID + ";";
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				if (rs.getString("outcome").equals("Lost")) {
//					gameLost++;
//				}
//			}
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		
//		return gameLost;
//	}
//	
//	public static void populatePlayerComboBox(ComboBox<String> playerComboBox, int userID) {
//		Statement stmt = null;
//    	int rowCount = Utilities.calculatePlayerRows(userID);
//    	String sql = null;
//    	ResultSet rs = null;
//    	String tag = null;
//    	
//    	try { 		
//	 		stmt = c.createStatement();
//	 		sql = "Select tag FROM players WHERE userID = " + userID + ";"; 
//	 		rs = stmt.executeQuery(sql);
//	 		
//	 		playerComboBox.getItems().clear();
//	 		for (int i = 0; i < rowCount; i++) {
//	 			rs.next();
//
//	 			tag = rs.getString("tag");
//	 			playerComboBox.getItems().add(tag);
//	 		}
//    	} catch (Exception ex) {
//			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
//    	}
//	}
//	
//	public static void populatePlayerComboBoxStats(ComboBox<String> playerComboBox, int userID) {
//		Statement stmt = null;
//    	int rowCount = Utilities.calculatePlayerRows(userID);
//    	String sql = null;
//    	ResultSet rs = null;
//    	String tag = null;
//    	
//    	try { 		
//	 		stmt = c.createStatement();
//	 		sql = "Select tag FROM players WHERE userID = " + userID + ";"; 
//	 		rs = stmt.executeQuery(sql);
//	 		
//	 		playerComboBox.getItems().clear();
//	 		playerComboBox.getItems().add("All Players");
//	 		for (int i = 0; i < rowCount; i++) {
//	 			rs.next();
//
//	 			tag = rs.getString("tag");
//	 			playerComboBox.getItems().add(tag);
//	 		}
//    	} catch (Exception ex) {
//			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
//    	}
//	}
//	
	public static void populateCharacterComboBox(ComboBox<String> characterComboBox) {
		characterComboBox.getItems().clear();
		characterComboBox.getItems().addAll("Fox", "Falco", "Marth", "Sheik", "Jigglypuff", "Peach", "Ice Climbers", "Cptn. Falcon", "Pikachu", 
											"Samus", "Dr. Mario", "Yoshi", "Luigi", "Gannon", "Mario", "Young Link", "Donkey Kong", "Link", 
											"Mr. Game & Watch", "Roy", "Mewtwo", "Zelda", "Ness", "Pichu", "Bowser", "Kirby");
	}
	
	public static void populateCharacterComboBoxStats(ComboBox<String> characterComboBox) {
		characterComboBox.getItems().clear();
		characterComboBox.getItems().addAll("All Characters", "Fox", "Falco", "Marth", "Sheik", "Jigglypuff", "Peach", "Ice Climbers", "Cptn. Falcon", "Pikachu", 
											"Samus", "Dr. Mario", "Yoshi", "Luigi", "Gannon", "Mario", "Young Link", "Donkey Kong", "Link", 
											"Mr. Game & Watch", "Roy", "Mewtwo", "Zelda", "Ness", "Pichu", "Bowser", "Kirby");
	}
    
	public static String formatDate(String date) {
 		String[] splitDate = date.split("-");
 		String formattedDate = splitDate[1] + "/" + splitDate[2] + "/" + splitDate[0];
 		
		return formattedDate;
	}
	
	public static int getCharacterID(String character) {
		for (int i = 1; i < characters.length; i++) {
			if (character.equals(characters[i])) {
				int characterID = i;
				
				return characterID;
			}
		}
		
		return 0;
	}
	
	public static int getStageID(String stage) {
		for (int i = 1; i < stages.length; i++) {
			if (stage.equals(stages[i])) {
				int stageID = i;
				
				return stageID;
			}
		}
		
		return 0;
	}
}
