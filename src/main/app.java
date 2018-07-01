package main;
import java.io.File;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.PrinterLocation;

import com.sun.javafx.css.StyleManager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import test.SQLiteTestData;

public class app extends Application {	
	//private Connection c = Utilities.getConnection();
	//private Connection c = null;
	private Stage theStage;
	private Scene userLoginScene, createAccountScene, mainMenuScene, addTournamentScene, specifiedTournamentScene, moneyScene,
				specifiedSetScene, addSetScene, specifiedGameScene, addGameScene, playerScene, addPlayerScene, specifiedPlayerScene, characterScene,
				specifiedCharacterScene, addCharacterNoteScene, specifiedCharacterNoteScene, analysisNoteScene, addAnalysisNoteScene, specifiedAnalysisNoteScene, 
				statsScene, tournamentReportScene, tiltProfileScene, addTiltTypeScene, specifiedTiltTypeScene, specifiedTiltProgressNoteScene, 
				moneyReportScene, errorScene, settingsScene;
	private int tournamentID = 0;
	private int moneyID = 0;
	private int setID = 0;
	private int gameID = 0;
	private int userID = 1; //should be 0 after testing ******************
	private int playerID = 0;
	private int characterID = 0;
	private int characterNoteID = 0;
	private int analysisNoteID = 0;
	private int tiltTypeID = 0;
	private int specifiedTiltID = 0;
	private int tiltProgressNoteID = 0;
	
	private int lastSceneID = 0;
	private final int ADD_PLAYER_SCENE = 1;
	private final int TOURNAMENT_REPORT_SCENE = 2;
	private final int SPECIFIED_SET_SCENE = 3;
	private final int PLAYER_SCENE = 4;
	private final int ADD_SET_SCENE = 5;
	private final int MAIN_MENU_SCENE = 6;
	
	private final int AMOUNT_OF_STAGES = 6;
	private final int BATTLEFIELD_STAGEID = 0;
	private final int DREAMLAND_STAGEID = 1;
	private final int YOSHIS_STORY_STAGEID = 2;
	private final int FOUNTAIN_OF_DREAMS_STAGEID = 3;
	private final int FINAL_DESTINATION_STAGEID = 4;
	private final int POKEMON_STADIUM_STAGEID = 5;
	
	public String[] stages = {"", "Battlefield", "Dreamland", "Yoshis Story", "Fountain of Dreams", "Final Destination", "Pokemon Stadium"};
	public String[] characters = {"", "Fox", "Falco", "Marth", "Sheik", "Jigglypuff", "Peach", "Ice Climbers", "Cptn. Falcon", "Pikachu", 
								   "Samus", "Dr. Mario", "Yoshi", "Luigi", "Gannon", "Mario", "Young Link", "Donkey Kong", "Link", 
								   "Mr. Game & Watch", "Roy", "Mewtwo", "Zelda", "Ness", "Pichu", "Bowser", "Kirby"};
	
	private Settings settings = GeneralUtils.deserialize();
	
	//Main Menu
	private ScrollPane mainMenuScrollPane = new ScrollPane();

	//Add Tournament
	
	//Specified Tournament
	private Label lblSpecifiedTournament = new Label();
	private TextField tfTournamentNameST = new TextField();
	private TextField tfMyPlacingST = new TextField();
	private DatePicker dpDateOfTournamentST = new DatePicker();
	private TextField tfStateST = new TextField();
	private TextField tfCityST = new TextField();
	private TextArea taNotesST = new TextArea();
	private ScrollPane setScrollPane = new ScrollPane();
	
	//Money
	private Label lblSpecifiedMoneyDetails = new Label();
	private TextField tfPrizeMoneyM = new TextField();
	private TextField tfMoneyMatchM = new TextField();
	private TextField tfEntryFeeM = new TextField();
	private TextField tfVenueFeeM = new TextField();
	private TextField tfTravelExpenseM = new TextField();
	
	//Add Set
	ComboBox<String> cbPlayerAS = new ComboBox<String>();
	
	//Specified Set
	private Label lblSpecifiedSet = new Label();
	private ScrollPane gameScrollPane = new ScrollPane();
	private ComboBox<String> cbPlayerSS = new ComboBox<String>();
	private ComboBox<String> cbOutcomeSS = new ComboBox<String>();
	private TextField tfBracketRoundSS = new TextField();
	private ComboBox<String> cbTypeSS = new ComboBox<String>();
	private ComboBox<String> cbFormatSS = new ComboBox<String>();
	private TextArea taNotesSS = new TextArea();
	
	//Player
	private Label lblSpecifiedPlayer = new Label();
	private ScrollPane playerScrollPane = new ScrollPane();
	private TextField tfTagP = new TextField();
	private TextArea taNotesP = new TextArea();
	
	//Add Game
	ComboBox<String> cbMyCharacterAG = new ComboBox<String>();
	
	//Specified Game
	private Label lblSpecifiedGame = new Label();
	private ComboBox<String> cbOutcomeG = new ComboBox<String>();
	private ComboBox<String> cbStageG = new ComboBox<String>();
	private ComboBox<String> cbMyCharacterG = new ComboBox<String>();
	private ComboBox<String> cbOpponentCharacterG = new ComboBox<String>();
	
	//Character
	private Label lblSpecifiedCharacter = new Label();
	private ScrollPane characterScrollPane = new ScrollPane();
	//private ScrollPane specifiedCharacterScrollPane = new ScrollPane();
	
	//Character Notes
	private Label lblSpecifiedCharacterNote = new Label();
	private ScrollPane specifiedCharacterNotesScrollPane = new ScrollPane();
	private TextField tfSubjectCN = new TextField();
	private TextArea taNotesCN = new TextArea();
	
	//Analysis Notes
	private Label lblSpecifiedAnalysisNote = new Label();
	private ScrollPane analysisNotesScrollPane = new ScrollPane();
	private TextField tfSubjectSA = new TextField();
	private TextField tfFocusPointsSA = new TextField();
	private TextArea taNotesSA = new TextArea();
	
	//Settings
//	private ComboBox<String> cbTheme = new ComboBox<String>();
//	private String theme = null;
	private String mainCharacter = settings.getMainCharacter();
	
	//Stats
	ComboBox<String> cbPlayersStats = new ComboBox<String>();
	
	//Tilt Profile 
	private Label lblSpecifiedTiltType = new Label();
	private ScrollPane tiltTypeScrollPane = new ScrollPane();
	private ScrollPane tiltProgressNotesScrollPane = new ScrollPane();
	
	//Tilt Type
	private TextArea taDescribeProblemTT = new TextArea();
	private TextArea taWhyLogicalTT = new TextArea();
	private TextArea taLogicFlawedTT = new TextArea();
	private TextField tfPossibleSolutionsTT = new TextField();
	private TextArea taWhySolutionsTT = new TextArea();

	//Tilt Progress Note
	private Label lblSpecifiedTiltProgressNote = new Label();
	private DatePicker dpDatePN = new DatePicker();
	private TextArea taNotesPN = new TextArea();
	
    @Override
	public void start(Stage primaryStage) {
    	theStage = primaryStage;
    	    	
//    	userLoginSetup();
//    	createAccountSetup();
    	printTournaments();  //remove after testing
    	settingsSetup();
    	errorSetup();
    	mainMenuSetup();
    	addTournamentSetup();
    	specifiedTournamentSetup();
    	moneySetup();
    	addSetSetup();
    	specifiedSetSetup();
    	playerSetup();
    	addPlayerSetup();
    	specifiedPlayerSetup();
    	addGameSetup();
    	specifiedGameSetup();
    	characterSetup();
    	specifiedCharacterSetup();
    	addCharacterNoteSetup();
    	specifiedCharacterNoteSetup();
    	analysisNoteSetup();
    	addAnalysisNoteSetup();
    	specifiedAnalysisNoteSetup();
    	tiltProfileSetup();
    	specifiedTiltType();
    	specifiedTiltProgressNote();
    	statsSetup();
    	tournamentReportSetup();
    	moneyReportSetup();

//    	Application.setUserAgentStylesheet(null);
//    	StyleManager.getInstance().addUserAgentStylesheet("main/assets/stylesheets/stylesheet.css");
        primaryStage.setTitle("Melee Notes");
	    primaryStage.setScene(mainMenuScene); //should be userLoginScene after testing **********************
	    primaryStage.show();
    }
	
	public static void main(String[] args) {
		SQLiteUtils.buildBlankDatabase();
//		SQLiteTestData.buildEntireDatabase();
//		SQLiteUtils.massPopulateDatabase(0);
		Application.launch(args);
	}
	
    public void errorSetup() {
       	GridPane errorPane = new GridPane();
    	errorPane.setHgap(10);
    	errorPane.setVgap(10);
    	errorPane.setPadding(new Insets(10, 10, 10, 10));
    	
		Label lblError = new Label("Sorry, there was an error. Please give information on what you did leading up to this problem at placeholder.com");
		lblError.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		errorPane.add(lblError, 0, 0);
		
		errorScene = new Scene(errorPane, 500, 500);
    }
	
//	public void userLoginSetup() {
//    	GridPane userLoginPane = new GridPane();
//		Button btLogin = new Button("Login");
//		Button btCreateAccount = new Button("Create Account");
//		Label lblEmail = new Label("Email:");
//		TextField tfEmail = new TextField();
//		Label lblPassword = new Label("Password:");
//		PasswordField pfPassword = new PasswordField();
//		
//		userLoginPane.setHgap(10);
//		userLoginPane.setVgap(10);
//		userLoginPane.setPadding(new Insets(10, 10, 10, 10));
//		
//		userLoginPane.add(lblEmail, 0, 0);
//		userLoginPane.add(tfEmail, 1, 0);
//		userLoginPane.add(lblPassword, 0, 1);
//		userLoginPane.add(pfPassword, 1, 1);
//		
//		userLoginPane.add(btLogin, 0, 2);
//		btLogin.setOnAction(e -> {
//			Statement stmt = null;
//			String sql = null;
//			ResultSet rs = null;
//			
//			String email = tfEmail.getText().toLowerCase();
//			String password = pfPassword.getText();
//			if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
//		    	Alert alAccCreateSuccess = new Alert(AlertType.ERROR);
//		    	alAccCreateSuccess.setTitle("Account Login");
//		    	alAccCreateSuccess.setHeaderText(null);
//		    	alAccCreateSuccess.setContentText("Please enter your information in the email and password fields.");
//		    	alAccCreateSuccess.showAndWait();	
//				return;
//			}
//				
//			try {
//		 		stmt = c.createStatement();
//				sql = "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "';";
//				rs = stmt.executeQuery(sql);
//				userID = rs.getInt("userID");
//				
//				stmt.close();
//				rs.close();
//			} catch (Exception ex) {
//				System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
//		    	Alert alAccCreateSuccess = new Alert(AlertType.ERROR);
//		    	alAccCreateSuccess.setTitle("Account Login");
//		    	alAccCreateSuccess.setHeaderText(null);
//		    	alAccCreateSuccess.setContentText("The login information is not valid.");
//		    	alAccCreateSuccess.showAndWait();
//		    	return;
//			}
//			
//			printTournaments();
//			theStage.setScene(mainMenuScene);
//			pfPassword.setText("");
//		});
//		
//		userLoginPane.add(btCreateAccount, 1, 2);
//		btCreateAccount.setOnAction(e -> theStage.setScene(createAccountScene));
//		
//		userLoginScene = new Scene(userLoginPane, 300, 200);
//		userLoginScene.getStylesheets().add("stylesheet.css");
//	}
//	
//	public void createAccountSetup() {
//    	GridPane createAccountPane = new GridPane();
//    	createAccountPane.setHgap(10);
//		createAccountPane.setVgap(10);
//		createAccountPane.setPadding(new Insets(10, 10, 10, 10));
//		
//    	Button btCreateAccount = new Button("Create Account");
//		Label lblEmail = new Label("Username:");
//		TextField tfEmail = new TextField();
//		Text txtEmailAsterisk = new Text("*");
//		txtEmailAsterisk.setFill(Color.RED);
//		Label lblPassword = new Label("Password:");
//		PasswordField pfPassword = new PasswordField();
//		Text txtPasswordAsterisk = new Text("*");
//		txtPasswordAsterisk.setFill(Color.RED);
//		Label lblPasswordCheck = new Label("Re-enter Password:");
//		PasswordField pfPasswordCheck = new PasswordField();
//		Text txtPasswordCheckAsterisk = new Text("*");
//		txtPasswordCheckAsterisk.setFill(Color.RED);
//		Button btBackToLogin = new Button("Back to Login");
//		
//		createAccountPane.add(lblEmail, 0, 0);
//		createAccountPane.add(tfEmail, 1, 0);
//		createAccountPane.add(txtEmailAsterisk, 2, 0);
//		createAccountPane.add(lblPassword, 0, 1);
//		createAccountPane.add(pfPassword, 1, 1);
//		createAccountPane.add(txtPasswordAsterisk, 2, 1);
//		createAccountPane.add(lblPasswordCheck, 0, 2);
//		createAccountPane.add(pfPasswordCheck, 1, 2);
//		createAccountPane.add(txtPasswordCheckAsterisk, 2, 2);
//		createAccountPane.add(btCreateAccount, 0, 3);
//		btCreateAccount.setOnAction(e -> {
//			Statement stmt = null;
//			String email = tfEmail.getText().toLowerCase();
//			String password = pfPassword.getText();
//			String passwordCheck = pfPasswordCheck.getText();
//			
//			//make meth
//			if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
//		    	Alert alAccCreateSuccess = new Alert(AlertType.ERROR);
//		    	alAccCreateSuccess.setTitle("Account Creation");
//		    	alAccCreateSuccess.setHeaderText(null);
//		    	alAccCreateSuccess.setContentText("Please enter information into the email and password fields.");
//		    	alAccCreateSuccess.showAndWait();	
//				return;
//			}
//			
//			if(password.equals(passwordCheck)) {
//				try {
//			 		stmt = c.createStatement();
//			 		String sql = "INSERT INTO users (email, password) " 
//			 					+ "VALUES ('" + email + "', '" + password + "');";
//			 		stmt.executeUpdate(sql);	
//			 		
//			 		stmt.close();
//				} catch (Exception ex) {
//					System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
//				}
//		    	Alert alAccCreateSuccess = new Alert(AlertType.INFORMATION);
//		    	alAccCreateSuccess.setTitle("Account Creation");
//		    	alAccCreateSuccess.setHeaderText(null);
//		    	alAccCreateSuccess.setContentText("Your account has been created");
//		    	alAccCreateSuccess.showAndWait();
//		    	theStage.setScene(userLoginScene);
//			} else {
//		    	Alert alAccCreateSuccess = new Alert(AlertType.ERROR);
//		    	alAccCreateSuccess.setTitle("Account Creation");
//		    	alAccCreateSuccess.setHeaderText(null);
//		    	alAccCreateSuccess.setContentText("Passwords do not match. Try again.");
//		    	alAccCreateSuccess.showAndWait();
//		    }
//		});
//		createAccountPane.add(btBackToLogin, 1, 3);
//		btBackToLogin.setOnAction(e -> theStage.setScene(userLoginScene));
//		
//		createAccountScene = new Scene(createAccountPane, 300, 200);
//		createAccountScene.getStylesheets().add("stylesheet.css");
//	}
//	
    public void settingsSetup() {
       	GridPane settingsPaneTop = new GridPane();
       	settingsPaneTop.setHgap(10);
       	settingsPaneTop.setVgap(10);
    	settingsPaneTop.setPadding(new Insets(10, 10, 10, 10));
    	GridPane settingsPaneCenter = new GridPane();
    	settingsPaneCenter.setHgap(10);
    	settingsPaneCenter.setVgap(10);
    	settingsPaneCenter.setPadding(new Insets(10, 10, 10, 10));
       	BorderPane settingsBorderPane = new BorderPane();
    	
       	Label lblSettings = new Label("Settings");
       	lblSettings.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
       	settingsPaneTop.add(lblSettings, 0, 0);
       	
//    	RadioButton rbDark = new RadioButton("Dark");
//    	RadioButton rbLight = new RadioButton("Light");
//    //	settingsPane.getChildren().addAll(rbDark, rbLight);
//    	ToggleGroup themeGroup = new ToggleGroup();
//    	rbDark.setToggleGroup(themeGroup);
//    	rbLight.setToggleGroup(themeGroup);
     //  	ComboBox<String> cbTheme = new ComboBox<String>();
      // 	cbTheme.getItems().addAll("Dark", "Light");
    	ComboBox<String> cbMainCharacter = new ComboBox<String>();
    	GeneralUtils.populateCharacterComboBox(cbMainCharacter);
    	cbMainCharacter.setValue(mainCharacter);
    	Button btMainCharacter = new Button("Change Main");
    	Label appVersion = new Label("0.5a");
    	Button btMainMenu = new Button("Main Menu");
    	
//    	settingsPane.add(new Label("Theme:"), 0, 0);
//    	settingsPane.add(cbTheme, 1, 0);
//    	cbTheme.setOnAction(e -> {
//    		String theme = cbTheme.getValue();
//    		
//    		//actually change theme
//    		if (theme.equals("Dark")) {
//    	    	Application.setUserAgentStylesheet("stylesheet.css");
//    		} else if (theme.equals("Light")) {
//    	    	Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
//    		}
//    		
//    		settings.setTheme(theme);
//    		settings.serialize();    		
//    	});
    	settingsPaneCenter.add(new Label("Main Character:"), 0, 0);
    	settingsPaneCenter.add(cbMainCharacter, 1, 0);
    	settingsPaneCenter.add(btMainCharacter, 2, 0);
    	btMainCharacter.setOnAction(e -> {
    	//	mainCharacter = cbMainCharacter.getValue();
    		
    		settings.setMainCharacter(cbMainCharacter.getValue());
    		settings.serialize();
    	});
    	settingsPaneCenter.add(new Label("App Version:"), 0, 2);
    	settingsPaneCenter.add(appVersion, 1, 2);
    	settingsPaneCenter.add(btMainMenu, 0, 3);
    	btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
    	
    	settingsBorderPane.setTop(settingsPaneTop);
    	settingsBorderPane.setCenter(settingsPaneCenter);
    	//your tag
    	//new scene with short view of stats
    	
		settingsScene = new Scene(settingsBorderPane, 500, 500);
		settingsScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
    }
	
	public void mainMenuSetup() {
    	GridPane mainMenuPane = new GridPane();
    	Button btAddTournamentMainMenu = new Button("Add Tournament");
    	Button btPlayer = new Button("Player");
    	Button btCharacter = new Button("Character");
    	Button btAnalysis = new Button("Analysis");
    	Button btTiltProfile = new Button("Tilt Profile");
    	Button btStats = new Button("Stats");
    	Button btTournamentReport = new Button("Tournament Report");
    	Button btMoneyReport = new Button("Money Report");
    	Button btSettings = new Button("Settings"); //change icon
    	Button btLogout = new Button("Logout");
    	
    	mainMenuPane.setPrefSize(30, 300);
    	BorderPane mainMenuBorderPane = new BorderPane();
    	Label lblMainMenu = new Label("Melee Notes");	
	   	mainMenuPane.setHgap(10);
	   	mainMenuPane.setVgap(10);
	   	mainMenuPane.setPadding(new Insets(10, 10, 10, 10));
	   	lblMainMenu.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    	GridPane.setHalignment(lblMainMenu, HPos.CENTER);
//    	GridPane.setHalignment(btAddTournamentMainMenu, HPos.CENTER);
//    	GridPane.setHalignment(btPlayer, HPos.CENTER);
//    	GridPane.setHalignment(btCharacter, HPos.CENTER);
//    	GridPane.setHalignment(btStats, HPos.CENTER);
//    	GridPane.setHalignment(btLogout, HPos.CENTER);
    	
		BorderPane.setAlignment(lblMainMenu, Pos.CENTER);
		BorderPane.setMargin(lblMainMenu, new Insets(10, 10, 10, 10));
		mainMenuBorderPane.setTop(lblMainMenu);
			
		mainMenuPane.add(btAddTournamentMainMenu, 0, 0);
		btAddTournamentMainMenu.setOnAction(e -> theStage.setScene(addTournamentScene));
		mainMenuPane.add(btPlayer, 0, 1);
		btPlayer.setOnAction(e -> {
			printPlayers();
			theStage.setScene(playerScene);
		});
		
		mainMenuPane.add(btCharacter, 0, 2);
		btCharacter.setOnAction(e -> {
			printCharacters();
			theStage.setScene(characterScene);
		});
		
		mainMenuPane.add(btAnalysis, 0, 3);
		btAnalysis.setOnAction(e -> {
			printAnalysisNotes();
			theStage.setScene(analysisNoteScene);
		});
		
		mainMenuPane.add(btTiltProfile, 0, 4);
		btTiltProfile.setOnAction(e -> {
			printTiltTypes();
			theStage.setScene(tiltProfileScene);
		});
		
		mainMenuPane.add(btStats, 0, 5);
		btStats.setOnAction(e -> {
			PlayerDao playerDao = new PlayerDaoImpl();
			playerDao.populatePlayerComboBoxStats(cbPlayersStats, userID);
			cbPlayersStats.setValue("All Players");
			theStage.setScene(statsScene);
		});
		
		mainMenuPane.add(btTournamentReport, 0, 6);
		btTournamentReport.setOnAction(e -> {
			theStage.setScene(tournamentReportScene);
		});
		
		mainMenuPane.add(btMoneyReport, 0, 7);
		btMoneyReport.setOnAction(e -> {
			theStage.setScene(moneyReportScene);
		});
		
		mainMenuPane.add(btSettings, 0, 16);
		btSettings.setOnAction(e -> {
			theStage.setScene(settingsScene);
		});

		mainMenuPane.add(btLogout, 0, 17);
		btLogout.setOnAction(e -> {
			userID = 0;
			theStage.setScene(userLoginScene);
		});
		
		mainMenuScrollPane.setFitToWidth(true);
		mainMenuScrollPane.setPrefSize(300, 500);
			
		mainMenuBorderPane.setRight(mainMenuScrollPane);
    	mainMenuBorderPane.setCenter(mainMenuPane);

		mainMenuScene = new Scene(mainMenuBorderPane, 440, 460);
		mainMenuScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
    }
	
	public void addTournamentSetup() {
    	GridPane addTournamentPaneTop = new GridPane();
    	addTournamentPaneTop.setHgap(10);
    	addTournamentPaneTop.setVgap(10);
    	addTournamentPaneTop.setPadding(new Insets(10, 10, 10, 10));
		GridPane addTournamentPaneCenter = new GridPane();
		addTournamentPaneCenter.setHgap(10);
    	addTournamentPaneCenter.setVgap(10);
    	addTournamentPaneCenter.setPadding(new Insets(10, 10, 10, 10));
    	BorderPane addTournamentBorderPane = new BorderPane();
    	
       	Label lblAddTournament = new Label("Add Tournament");
       	lblAddTournament.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
       	addTournamentPaneTop.add(lblAddTournament, 0, 0);
    	
		TextField tfTournamentName = new TextField();
		tfTournamentName.getStyleClass().add("requiredStyle.css");
//		Text txtTournamentName = new Text("*");
//		txtTournamentName.setFill(Color.RED);
		DatePicker dpDateOfTournament = new DatePicker();
		dpDateOfTournament.setValue(LocalDate.now());
//		Text txtDateOfTournament = new Text("*");
//		txtDateOfTournament.setFill(Color.RED);
		TextField tfMyPlacing = new TextField();
		TextField tfState = new TextField();
		TextField tfCity = new TextField();
		TextArea taNotes = new TextArea();
		Button btAddTournament = new Button("Add Tournament");
		Button btMainMenu = new Button("Main Menu");
    	
		addTournamentPaneCenter.add(new Label("Tournament Name:"), 0, 1);
		addTournamentPaneCenter.add(tfTournamentName, 1, 1);
	//	addTournamentPaneCenter.add(txtTournamentName, 2, 1);
		addTournamentPaneCenter.add(new Label("Date:"), 0, 2);
		addTournamentPaneCenter.add(dpDateOfTournament, 1, 2);
//		addTournamentPaneCenter.add(txtDateOfTournament, 2, 2);
		addTournamentPaneCenter.add(new Label("My Placing:"), 0, 3);
		addTournamentPaneCenter.add(tfMyPlacing, 1, 3);
		addTournamentPaneCenter.add(new Label("State:"), 0, 4);
		addTournamentPaneCenter.add(tfState, 1, 4);
		addTournamentPaneCenter.add(new Label("City:"), 0, 5);
		addTournamentPaneCenter.add(tfCity, 1, 5);
		addTournamentPaneCenter.add(new Label("Notes:"), 0, 6);
		addTournamentPaneCenter.add(taNotes, 1, 6);
		
		addTournamentPaneCenter.add(btAddTournament, 0, 7);
		btAddTournament.setOnAction(e -> {
			String tournamentName = tfTournamentName.getText();
			LocalDate dateOfTournament = dpDateOfTournament.getValue();
			if (dateOfTournament == null) {
		    	Alert alNoDate = new Alert(AlertType.ERROR);
		    	alNoDate.setTitle("Date of Tournament");
		    	alNoDate.setHeaderText(null);
		    	alNoDate.setContentText("Please enter information into the date field.");
		    	alNoDate.showAndWait();	
				return;
			}
			if (tournamentName.isEmpty()) {
		    	Alert alNoTournamentName = new Alert(AlertType.ERROR);
		    	alNoTournamentName.setTitle("Tournament Name");
		    	alNoTournamentName.setHeaderText(null);
		    	alNoTournamentName.setContentText("Please enter information into the tounament name field.");
		    	alNoTournamentName.showAndWait();	
				return;
			}
			String myPlacingString = tfMyPlacing.getText();
			int myPlacing = 0;
			if (!tfMyPlacing.getText().trim().equals("")) {
				try {
					myPlacing = Integer.parseInt(myPlacingString);
				} catch (NumberFormatException nfe) {
			    	Alert alNoMyPlacing = new Alert(AlertType.ERROR);
			    	alNoMyPlacing.setTitle("My Placing");
			    	alNoMyPlacing.setHeaderText(null);
			    	alNoMyPlacing.setContentText("Please enter an integer for the place you recieved.");
			    	alNoMyPlacing.showAndWait();
			    	return;
				}
				if (myPlacing < 0) {
			    	Alert alMyPlacingLessThan = new Alert(AlertType.ERROR);
			    	alMyPlacingLessThan.setTitle("My Placing");
			    	alMyPlacingLessThan.setHeaderText(null);
			    	alMyPlacingLessThan.setContentText("Please enter a non-negative number.");
			    	alMyPlacingLessThan.showAndWait();
			    	return;
			    }
			}
			String state = tfState.getText();
			String city = tfCity.getText();
			String notes = taNotes.getText();
		
			TournamentDao tournamentDao = new TournamentDaoImpl();
			Tournament tournament = new Tournament(userID, tournamentName, dateOfTournament.toString(), myPlacing, state, city, notes);
			tournamentDao.insertTournament(tournament);
			tournamentID = tournamentDao.getMostRecentTournamentId();
			
			MoneyDao moneyDao = new MoneyDaoImpl();
			moneyDao.insertMoneyByTournamentId(tournamentID);
			
	    	Alert alAddTournament = new Alert(AlertType.INFORMATION);
	    	alAddTournament.setTitle("Add Tournament");
	    	alAddTournament.setHeaderText(null);
	    	alAddTournament.setContentText("Tournament has been added.");
	    	alAddTournament.showAndWait();
	    	printTournaments();
	    	theStage.setScene(mainMenuScene);
			tfTournamentName.setText("");
			//dpDateOfTournament.setValue(null);
			tfMyPlacing.setText("");
			tfState.setText("");
			tfCity.setText("");
			taNotes.setText("");
		});
		
		addTournamentPaneCenter.add(btMainMenu, 0, 9);
		btMainMenu.setOnAction(e -> { 
			theStage.setScene(mainMenuScene); 
			printTournaments();
		});
		
		addTournamentBorderPane.setTop(addTournamentPaneTop);
		addTournamentBorderPane.setCenter(addTournamentPaneCenter);
		
		addTournamentScene = new Scene(addTournamentBorderPane, 700, 700);
		addTournamentScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void specifiedTournamentSetup() {
		GridPane specifiedTournamentPaneTop = new GridPane();
		specifiedTournamentPaneTop.setHgap(10);
		specifiedTournamentPaneTop.setVgap(10);
		specifiedTournamentPaneTop.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedTournamentPaneCenter = new GridPane();
		specifiedTournamentPaneCenter.setHgap(10);
		specifiedTournamentPaneCenter.setVgap(10);
    	specifiedTournamentPaneCenter.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedTournamentBottomPane = new GridPane();
		specifiedTournamentBottomPane.setHgap(10);
		specifiedTournamentBottomPane.setVgap(10);
		specifiedTournamentBottomPane.setPadding(new Insets(10, 10, 10, 10));
    	BorderPane specifiedTournamentBorderPane = new BorderPane();
		specifiedTournamentPaneCenter.setPrefSize(30, 300);
		
       	lblSpecifiedTournament.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
       	specifiedTournamentPaneTop.add(lblSpecifiedTournament, 0, 0);
		
		Button btApplyChanges = new Button("Apply Changes");
		Button btRemoveTournament = new Button("Remove Tournament");
		Button btMoney = new Button("Money");
		Button btAddSet = new Button("Add Set");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");
    	
		specifiedTournamentPaneCenter.add(new Label("Tournament Name:"), 0, 1);
		specifiedTournamentPaneCenter.add(tfTournamentNameST, 1, 1);
		specifiedTournamentPaneCenter.add(new Label("Date:"), 0, 2);
		specifiedTournamentPaneCenter.add(dpDateOfTournamentST, 1, 2);
		specifiedTournamentPaneCenter.add(new Label("My Placing:"), 0, 3);
		specifiedTournamentPaneCenter.add(tfMyPlacingST, 1, 3);
		specifiedTournamentPaneCenter.add(new Label("State:"), 0, 4);
		specifiedTournamentPaneCenter.add(tfStateST, 1, 4);
		specifiedTournamentPaneCenter.add(new Label("City:"), 0, 5);
		specifiedTournamentPaneCenter.add(tfCityST, 1, 5);
		specifiedTournamentPaneCenter.add(new Label("Notes:"), 0, 6);
		specifiedTournamentPaneCenter.add(taNotesST, 1, 6);
		
		specifiedTournamentPaneCenter.add(btApplyChanges, 0, 7);
		btApplyChanges.setOnAction(e -> {
			String tournamentName = tfTournamentNameST.getText();
			LocalDate dateOfTournament = dpDateOfTournamentST.getValue();
			if (dateOfTournament == null) {
		    	Alert alNoDate = new Alert(AlertType.ERROR);
		    	alNoDate.setTitle("Date of Tournament");
		    	alNoDate.setHeaderText(null);
		    	alNoDate.setContentText("Please enter information into the date field.");
		    	alNoDate.showAndWait();	
				return;
			}
			if (tournamentName.isEmpty()) {
		    	Alert alNoTournamentName = new Alert(AlertType.ERROR);
		    	alNoTournamentName.setTitle("Tournament Name");
		    	alNoTournamentName.setHeaderText(null);
		    	alNoTournamentName.setContentText("Please enter information into the tounament name field.");
		    	alNoTournamentName.showAndWait();	
				return;
			}
			String myPlacingString = tfMyPlacingST.getText();
			int myPlacing = 0;
			if (!tfMyPlacingST.getText().trim().equals("")) {
				try {
					myPlacing = Integer.parseInt(myPlacingString);
				} catch (NumberFormatException nfe) {
			    	Alert alNotNumberMyPlacing = new Alert(AlertType.ERROR);
			    	alNotNumberMyPlacing.setTitle("My Placing");
			    	alNotNumberMyPlacing.setHeaderText(null);
			    	alNotNumberMyPlacing.setContentText("Please enter an integer for the place you recieved.");
			    	alNotNumberMyPlacing.showAndWait();
			    	return;
				}
				if (myPlacing < 0) {
			    	Alert alMyPlacingLessThan = new Alert(AlertType.ERROR);
			    	alMyPlacingLessThan.setTitle("My Placing");
			    	alMyPlacingLessThan.setHeaderText(null);
			    	alMyPlacingLessThan.setContentText("Please enter a non-negative number.");
			    	alMyPlacingLessThan.showAndWait();
			    	return;
			    }
			}
			String state = tfStateST.getText();
			String city = tfCityST.getText();
			String notes = taNotesST.getText();

			Tournament tournament = new Tournament(tournamentID, userID, tournamentName, dateOfTournament.toString(), myPlacing, state, city, notes);
			TournamentDao tournamentDoa = new TournamentDaoImpl();
			tournamentDoa.updateTournament(tournament);
			
			printTournaments();
	    	Alert alApplyChanges = new Alert(AlertType.INFORMATION);
	    	alApplyChanges.setTitle("Apply Changes");
	    	alApplyChanges.setHeaderText(null);
	    	alApplyChanges.setContentText("The tournament was updated.");
	    	alApplyChanges.showAndWait();
		});
		
		specifiedTournamentPaneCenter.add(btRemoveTournament, 1, 7);
		btRemoveTournament.setOnAction(e -> {
	    	Alert alRemoveTournament = new Alert(AlertType.CONFIRMATION);
	    	alRemoveTournament.setTitle("Remove Tournament");
	    	alRemoveTournament.setHeaderText(null);
	    	alRemoveTournament.setContentText("Are you sure you want to remove the tournament? This action will also delete the sets and games corresponding with the tournament");
			Optional<ButtonType> optionSelected = alRemoveTournament.showAndWait();
			if (optionSelected.get() == ButtonType.OK) {
				TournamentDao tournamentDoa = new TournamentDaoImpl();
				tournamentDoa.deleteTournamentById(tournamentID, setID);
					
				printTournaments();
				theStage.setScene(mainMenuScene);
			} else {
				return;
			}
		});
		
		specifiedTournamentPaneCenter.add(btMoney, 0, 8);
		btMoney.setOnAction(e -> {
			MoneyDao moneyDao = new MoneyDaoImpl();
			moneyID = moneyDao.getMoneyIdByTournamentId(tournamentID);
			
			theStage.setScene(moneyScene);
		});
		
//		specifiedTournamentPaneCenter.add(btBack, 0, 9);
//		btBack.setOnAction(e -> theStage.setScene(main););
		
		specifiedTournamentPaneCenter.add(btBack, 0, 10);
		btBack.setOnAction(e -> { 
			setStage();
		});
		
		specifiedTournamentPaneCenter.add(btMainMenu, 1, 10);
		btMainMenu.setOnAction(e -> { 
			theStage.setScene(mainMenuScene);
		});
		
		specifiedTournamentBottomPane.add(btAddSet, 72, 0);
		btAddSet.setOnAction(e -> {
			PlayerDao playerDao = new PlayerDaoImpl();
			playerDao.populatePlayerComboBox(cbPlayerAS, userID);
			theStage.setScene(addSetScene);
		});
		GridPane.setHalignment(btAddSet, HPos.RIGHT);

		setScrollPane.setFitToWidth(true);
		setScrollPane.setPrefSize(300, 500);
			
		specifiedTournamentBorderPane.setTop(specifiedTournamentPaneTop);
		specifiedTournamentBorderPane.setRight(setScrollPane);
		specifiedTournamentBorderPane.setCenter(specifiedTournamentPaneCenter);
		specifiedTournamentBorderPane.setBottom(specifiedTournamentBottomPane);
		
		specifiedTournamentScene = new Scene(specifiedTournamentBorderPane, 920, 600);
		specifiedTournamentScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	//not in program, need to figure out clean way to implement
	public void moneySetup() {
		GridPane moneyPaneTop = new GridPane();
		moneyPaneTop.setHgap(10);
		moneyPaneTop.setVgap(10);
		moneyPaneTop.setPadding(new Insets(10, 10, 10, 10));
		GridPane moneyPaneCenter = new GridPane();
		moneyPaneCenter.setHgap(10);
		moneyPaneCenter.setVgap(10);
		moneyPaneCenter.setPadding(new Insets(10, 10, 10, 10));
		BorderPane moneyBorderPane = new BorderPane();
		
		tfMoneyMatchM.setPromptText("Enter a positive or negative number.");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");
		Button btApplyChanges = new Button("Apply Changes");
		
		lblSpecifiedMoneyDetails.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
       	moneyPaneTop.add(lblSpecifiedMoneyDetails, 0, 0);
		
		moneyPaneCenter.add(new Label("Prize Money:"), 0, 1);
		moneyPaneCenter.add(tfPrizeMoneyM, 1, 1);
		moneyPaneCenter.add(new Label("Money Match:"), 0, 2);
		moneyPaneCenter.add(tfMoneyMatchM, 1, 2);
		moneyPaneCenter.add(new Label("Entry Fee:"), 0, 3);
		moneyPaneCenter.add(tfEntryFeeM, 1, 3);
		moneyPaneCenter.add(new Label("Venue Fee:"), 0, 4);
		moneyPaneCenter.add(tfVenueFeeM, 1, 4);
		moneyPaneCenter.add(new Label("Travel Expenses:"), 0, 5);
		moneyPaneCenter.add(tfTravelExpenseM, 1, 5);
		moneyPaneCenter.add(btApplyChanges, 0, 6);
		btApplyChanges.setOnAction(e -> {
			int prizeMoney;
			int moneyMatch;
			int entryFee;
			int venueFee;
			int travelExpense;
			try {
				prizeMoney = Integer.parseInt(tfPrizeMoneyM.getText());
				moneyMatch = Integer.parseInt(tfMoneyMatchM.getText());
				entryFee = Integer.parseInt(tfEntryFeeM.getText());
				venueFee = Integer.parseInt(tfVenueFeeM.getText());
				travelExpense = Integer.parseInt(tfTravelExpenseM.getText());
			} catch (NumberFormatException nfe) {
		    	Alert alNotNumberMyPlacing = new Alert(AlertType.ERROR);
		    	alNotNumberMyPlacing.setTitle("My Placing");
		    	alNotNumberMyPlacing.setHeaderText(null);
		    	alNotNumberMyPlacing.setContentText("Please enter an integer for the place you recieved.");
		    	alNotNumberMyPlacing.showAndWait();
		    	return;
			}
			
			if (prizeMoney < 0 || entryFee < 0 || venueFee < 0 || travelExpense < 0) {
		    	Alert alApplyChanges = new Alert(AlertType.ERROR);
		    	alApplyChanges.setTitle("Positive Number");
		    	alApplyChanges.setHeaderText(null);
		    	alApplyChanges.setContentText("Prize money, entry fee, venue fee, and travel expenses must be 0 or a positive number.");
		    	alApplyChanges.showAndWait();
		    	return;
			}
			
			MoneyDao moneyDao = new MoneyDaoImpl();
			//moneyID = moneyDao.getMoneyIdByTournamentId(tournamentID);
			Money money = new Money(moneyID, tournamentID, prizeMoney, moneyMatch, entryFee, venueFee, travelExpense);
			moneyDao.updateMoney(money);
			
	    	Alert alApplyChanges = new Alert(AlertType.INFORMATION);
	    	alApplyChanges.setTitle("Apply Changes");
	    	alApplyChanges.setHeaderText(null);
	    	alApplyChanges.setContentText("The money details were updated.");
	    	alApplyChanges.showAndWait();
		});
		
		moneyPaneCenter.add(btBack, 0, 7);
		btBack.setOnAction(e -> theStage.setScene(specifiedTournamentScene));
		
		moneyPaneCenter.add(btMainMenu, 1, 7);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		moneyBorderPane.setTop(moneyPaneTop);
		moneyBorderPane.setCenter(moneyPaneCenter);
		
		moneyScene = new Scene(moneyBorderPane, 700, 600);
		moneyScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void addSetSetup() {
		GridPane addSetPaneTop = new GridPane();
		addSetPaneTop.setHgap(10);
		addSetPaneTop.setVgap(10);
		addSetPaneTop.setPadding(new Insets(10, 10, 10, 10));
		GridPane addSetPaneCenter = new GridPane();
		addSetPaneCenter.setHgap(10);
		addSetPaneCenter.setVgap(10);
		addSetPaneCenter.setPadding(new Insets(10, 10, 10, 10));
		BorderPane addSetBorderPane = new BorderPane();
		
		Label lblAddSet = new Label("Add Set");
		lblAddSet.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		addSetPaneTop.add(lblAddSet, 0, 0);
		
    	Label lblPlayer = new Label("Player:");
		Button btAddPlayer = new Button("Add Player");
    	//ComboBox<String> cbPlayerAS = new ComboBox<String>();
    	Label lblOutcome = new Label("Outcome:");
    	ComboBox<String> cbOutcome = new ComboBox<String>();
    	cbOutcome.getItems().addAll("Won", "Lost");
    	Label lblBracketRound = new Label("Round:");
    	TextField tfBracketRound = new TextField();
    	Label lblType = new Label("Type:");
    	ComboBox<String> cbType = new ComboBox<String>();
    	cbType.getItems().addAll("Tournament", "Money Match", "Friendly");
    	Label lblFormat = new Label("Format:");
    	ComboBox<String> cbFormat = new ComboBox<String>();
    	cbFormat.getItems().addAll("Bo3", "Bo5");
    	Label lblNotes = new Label("Notes:");
    	TextArea taNotes = new TextArea();
    	Button btAddSet = new Button("Add Set");
    	Button btBack = new Button("Back");
    	Button btMainMenu = new Button("Main Menu");
    	
    	addSetPaneCenter.add(lblPlayer, 0, 0);
    	addSetPaneCenter.add(cbPlayerAS, 1, 0);
    	addSetPaneCenter.add(btAddPlayer, 1, 1);
		btAddPlayer.setOnAction(e -> {
			lastSceneID = ADD_SET_SCENE;
			theStage.setScene(addPlayerScene);
		});
		
    	addSetPaneCenter.add(lblOutcome, 0, 2);
    	addSetPaneCenter.add(cbOutcome, 1, 2);
    	addSetPaneCenter.add(lblType, 0, 3);
    	addSetPaneCenter.add(cbType, 1, 3);
    	cbType.setOnAction(e -> {
    		if (cbType.getValue().equals("Tournament")) {
    	    	addSetPaneCenter.add(lblBracketRound, 0, 6);
    	    	addSetPaneCenter.add(tfBracketRound, 1, 6);
    		} else {
        		addSetPaneCenter.getChildren().remove(lblBracketRound);
        		tfBracketRound.setText("");
        		addSetPaneCenter.getChildren().remove(tfBracketRound);
    		}
    	});
    	
    	addSetPaneCenter.add(lblFormat, 0, 4);
    	addSetPaneCenter.add(cbFormat, 1, 4);
    	btAddSet.setOnAction(e -> {
    		String tag = cbPlayerAS.getValue();
    		if (tag == null || tag.isEmpty()) {
    	    	Alert allNoPlayer = new Alert(AlertType.ERROR);
    	    	allNoPlayer.setTitle("Add Set");
    	    	allNoPlayer.setHeaderText(null);
    	    	allNoPlayer.setContentText("Please select a player.");
    	    	allNoPlayer.showAndWait();	
    	    	return;
    		}
    		
			PlayerDao playerDao = new PlayerDaoImpl();
			playerID = playerDao.getPlayerIdByTag(tag);
			String outcome = cbOutcome.getValue();
    		
    		if (outcome == null || outcome.isEmpty()) {
    	    	Alert alNoOutcome = new Alert(AlertType.ERROR);
    	    	alNoOutcome.setTitle("Add Set");
    	    	alNoOutcome.setHeaderText(null);
    	    	alNoOutcome.setContentText("Please select an outcome.");
    	    	alNoOutcome.showAndWait();	
    	    	return;
    		}
    		String bracketRound = tfBracketRound.getText();
    		String format = cbFormat.getValue();
    		String type = cbType.getValue();
    		String notes = taNotes.getText();
    		
			Set set = new Set(tournamentID, playerID, outcome, bracketRound, type, format, notes);
			SetDao setDao = new SetDaoImpl();
			setDao.insertSet(set);
    		
			printSets();
	    	Alert alAddSet = new Alert(AlertType.INFORMATION);
	    	alAddSet.setTitle("Add Set");
	    	alAddSet.setHeaderText(null);
	    	alAddSet.setContentText("The set was added.");
	    	alAddSet.showAndWait();	
	    	theStage.setScene(specifiedTournamentScene);
	    	cbPlayerAS.setValue("");
	    	tfBracketRound.setText("");
	    	cbOutcome.setValue("");
	    	cbType.setValue("");
	    	cbFormat.setValue("");
	    	taNotes.setText("");
    	});
    	
    	addSetPaneCenter.add(lblNotes, 0, 5);
    	addSetPaneCenter.add(taNotes, 1, 5);
    	addSetPaneCenter.add(btAddSet, 0, 7);	
    	
    	addSetPaneCenter.add(btBack, 0, 8);
    	btBack.setOnAction(e -> theStage.setScene(specifiedTournamentScene));
    	
    	addSetPaneCenter.add(btMainMenu, 1, 8);
    	btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
    	addSetBorderPane.setTop(addSetPaneTop);
    	addSetBorderPane.setCenter(addSetPaneCenter);
    	
		addSetScene = new Scene(addSetBorderPane, 700, 600);
		addSetScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void specifiedSetSetup() {
		GridPane specifiedSetPaneTop = new GridPane();
		specifiedSetPaneTop.setHgap(10);
		specifiedSetPaneTop.setVgap(10);
		specifiedSetPaneTop.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedSetPaneCenter = new GridPane();
		specifiedSetPaneCenter.setHgap(10);
		specifiedSetPaneCenter.setVgap(10);
		specifiedSetPaneCenter.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedSetBottomPane = new GridPane();
		specifiedSetBottomPane.setHgap(10);
		specifiedSetBottomPane.setVgap(10);
		specifiedSetBottomPane.setPadding(new Insets(10, 10, 10, 10));
		BorderPane specifiedSetBorderPane = new BorderPane();
		
       	lblSpecifiedSet.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
       	specifiedSetPaneTop.add(lblSpecifiedSet, 0, 0);
		
		Label lblPlayer = new Label("Player:");
		Button btAddPlayer = new Button("Add Player");
		//TextField tfPlayer = new TextField();
		Label lblOutcome = new Label("Outcome:");
		//ComboBox<String> cbOutcome = new ComboBox<String>();
		cbOutcomeSS.getItems().addAll("Won", "Lost");
		Label lblBracketRound = new Label("Round:");
		//TextField tfBracketRound = new TextField();
		Label lblType = new Label("Type:");
		//ComboBox<String> cbType = new ComboBox<String>();
		cbTypeSS.getItems().addAll("Tournament", "Money Match", "Friendly");
		Label lblFormat = new Label("Format:");
		//ComboBox<String> cbFormat = new ComboBox<String>();
		cbFormatSS.getItems().addAll("Bo3", "Bo5");
		Label lblNotes = new Label("Notes:");
		//TextArea taNotes = new TextArea();
		Button btApplyChanges = new Button("Apply Changes");
		Button btRemove = new Button("Remove");
		Button btAddGame = new Button("Add Game");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");
		
		specifiedSetPaneCenter.add(lblPlayer, 0, 0);
		specifiedSetPaneCenter.add(cbPlayerSS, 1, 0);
		specifiedSetPaneCenter.add(btAddPlayer, 1, 1);
		btAddPlayer.setOnAction(e -> {
			lastSceneID = SPECIFIED_SET_SCENE;
			theStage.setScene(addPlayerScene);
		});
		specifiedSetPaneCenter.add(lblOutcome, 0, 2);
		specifiedSetPaneCenter.add(cbOutcomeSS, 1, 2);
		specifiedSetPaneCenter.add(lblType, 0, 3);
		specifiedSetPaneCenter.add(cbTypeSS, 1, 3);
		cbTypeSS.setOnAction(e -> {
			if (cbTypeSS.getValue().equals("Tournament")) {
				specifiedSetPaneCenter.add(lblBracketRound, 0, 6);
		    	specifiedSetPaneCenter.add(tfBracketRoundSS, 1, 6);
			} else {
	    		specifiedSetPaneCenter.getChildren().remove(lblBracketRound);
	    		specifiedSetPaneCenter.getChildren().remove(tfBracketRoundSS);
			}
		});
		specifiedSetPaneCenter.add(lblFormat, 0, 4);
		specifiedSetPaneCenter.add(cbFormatSS, 1, 4);
		specifiedSetPaneCenter.add(lblNotes, 0, 5);
		specifiedSetPaneCenter.add(taNotesSS, 1, 5);
		specifiedSetPaneCenter.add(btApplyChanges, 0, 7);
		btApplyChanges.setOnAction(e -> {
			String tag = cbPlayerSS.getValue();
			if (tag == null || tag.isEmpty()) {
		    	Alert allNoPlayer = new Alert(AlertType.ERROR);
		    	allNoPlayer.setTitle("Add Set");
		    	allNoPlayer.setHeaderText(null);
		    	allNoPlayer.setContentText("Please select a player.");
		    	allNoPlayer.showAndWait();	
		    	return;
			}
			PlayerDao playerDao = new PlayerDaoImpl();
			playerID = playerDao.getPlayerIdByTag(tag);
			String outcome = cbOutcomeSS.getValue();
			if (outcome == null || outcome.isEmpty()) {
		    	Alert alNoOutcome = new Alert(AlertType.ERROR);
		    	alNoOutcome.setTitle("Add Set");
		    	alNoOutcome.setHeaderText(null);
		    	alNoOutcome.setContentText("Please select an outcome.");
		    	alNoOutcome.showAndWait();	
		    	return;
			}
			String bracketRound = tfBracketRoundSS.getText();
			String type =  cbTypeSS.getValue();
			String format = cbFormatSS.getValue();
			String notes = taNotesSS.getText();
			
			Set set = new Set(setID, tournamentID, playerID, outcome, bracketRound, type, format, notes);
			SetDao setDao = new SetDaoImpl();
			setDao.updateSet(set);
			
			printSets();
	    	Alert alApplyChanges = new Alert(AlertType.INFORMATION);
	    	alApplyChanges.setTitle("Apply Changes");
	    	alApplyChanges.setHeaderText(null);
	    	alApplyChanges.setContentText("The set was updated.");
	    	alApplyChanges.showAndWait();
		});
		
		specifiedSetPaneCenter.add(btRemove, 1, 7);
		btRemove.setOnAction(e -> {
	    	Alert alRemoveSet = new Alert(AlertType.CONFIRMATION);
	    	alRemoveSet.setTitle("Remove Set");
	    	alRemoveSet.setHeaderText(null);
	    	alRemoveSet.setContentText("Are you sure you want to remove the game? This action will also delete the games corresponding with the set.");
			Optional<ButtonType> optionSelected = alRemoveSet.showAndWait();
			if (optionSelected.get() == ButtonType.OK) {
				SetDao setDao = new SetDaoImpl();
				setDao.deleteSetById(setID);
				
				printSets();
				theStage.setScene(specifiedTournamentScene);
			} else {
				return;
			}
		});
		
		specifiedSetPaneCenter.add(btBack, 0, 9);
		btBack.setOnAction(e -> {
			setStage();
		});
		
	   	specifiedSetPaneCenter.add(btMainMenu, 1, 9);
	   	btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		specifiedSetBottomPane.add(btAddGame, 70, 0);
		btAddGame.setOnAction(e -> {
			cbMyCharacterAG.setValue(settings.getMainCharacter());
			theStage.setScene(addGameScene);
		});
		
		gameScrollPane.setFitToWidth(true);
		gameScrollPane.setPrefSize(300, 500);
		
		specifiedSetBorderPane.setTop(specifiedSetPaneTop);
		specifiedSetBorderPane.setRight(gameScrollPane);
		specifiedSetBorderPane.setCenter(specifiedSetPaneCenter);
		specifiedSetBorderPane.setBottom(specifiedSetBottomPane);
		
		specifiedSetScene = new Scene(specifiedSetBorderPane, 950, 600);
		specifiedSetScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void addGameSetup() {
		GridPane addGamePaneTop = new GridPane();
		addGamePaneTop.setHgap(10);
		addGamePaneTop.setVgap(10);
		addGamePaneTop.setPadding(new Insets(10, 10, 10, 10));
		GridPane addGamePaneCenter = new GridPane();
		addGamePaneCenter.setHgap(10);
		addGamePaneCenter.setVgap(10);
		addGamePaneCenter.setPadding(new Insets(10, 10, 10, 10));
		BorderPane addGameBorderPane = new BorderPane();
		
		Label lblAddGame = new Label("Add Game");
		lblAddGame.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		addGamePaneTop.add(lblAddGame, 0, 0);
		
		Label lblOutcome = new Label("Outcome:");
    	ComboBox<String> cbOutcome = new ComboBox<String>();
    	cbOutcome.getItems().addAll("Won", "Lost");
		Label lblMyCharacter = new Label("My Character:");
	//	ComboBox<String> cbMyCharacter = new ComboBox<String>();
		GeneralUtils.populateCharacterComboBox(cbMyCharacterAG);
		Label lblOppenentCharacter = new Label("Opponent Character:");
		ComboBox<String> cbOpponentCharacter = new ComboBox<String>();
		GeneralUtils.populateCharacterComboBox(cbOpponentCharacter);
		Label lblStage = new Label("Stage:");
    	ComboBox<String> cbStage = new ComboBox<String>();
    	cbStage.getItems().addAll("Battlefield", "Dreamland", "Yoshi's Story", "Fountain of Dreams", "Pokemon Stadium", "Final Destination");
    	Button btAddGame = new Button("Add Game");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");

		addGamePaneCenter.add(lblOutcome, 0, 0);
		addGamePaneCenter.add(cbOutcome, 1, 0);
		addGamePaneCenter.add(lblMyCharacter, 0, 1);
		addGamePaneCenter.add(cbMyCharacterAG, 1, 1);
		addGamePaneCenter.add(lblOppenentCharacter, 0, 2);
		addGamePaneCenter.add(cbOpponentCharacter, 1, 2);
		addGamePaneCenter.add(lblStage, 0, 3);
		addGamePaneCenter.add(cbStage, 1, 3);	
		addGamePaneCenter.add(btAddGame, 0, 4);	
		btAddGame.setOnAction(e -> {
    		String gameOutcome = cbOutcome.getValue();
    		if (gameOutcome == null || gameOutcome.isEmpty()) {
    	    	Alert alNoOutcome = new Alert(AlertType.ERROR);
    	    	alNoOutcome.setTitle("No Outcome Selected");
    	    	alNoOutcome.setHeaderText(null);
    	    	alNoOutcome.setContentText("Please select an outcome.");
    	    	alNoOutcome.showAndWait();	
    	    	return;
    		}
    		String myCharacter = cbMyCharacterAG.getValue();
    		if (myCharacter == null || myCharacter.isEmpty()) {
    	    	Alert alNoMyCharacter = new Alert(AlertType.ERROR);
    	    	alNoMyCharacter.setTitle("My Character Not Selected");
    	    	alNoMyCharacter.setHeaderText(null);
    	    	alNoMyCharacter.setContentText("Please select my character.");
    	    	alNoMyCharacter.showAndWait();	
    	    	return;
    		}
    		String opponentCharacter = cbOpponentCharacter.getValue();
    		if (opponentCharacter == null || opponentCharacter.isEmpty()) {
    	    	Alert alNoOpponentCharacter= new Alert(AlertType.ERROR);
    	    	alNoOpponentCharacter.setTitle("Opponent Character Not Selected");
    	    	alNoOpponentCharacter.setHeaderText(null);
    	    	alNoOpponentCharacter.setContentText("Please select opponent character.");
    	    	alNoOpponentCharacter.showAndWait();	
    	    	return;
    		}
    		String stage = cbStage.getValue();
    		if (stage == null || stage.isEmpty()) {
    	    	Alert alNoStage= new Alert(AlertType.ERROR);
    	    	alNoStage.setTitle("No Stage Selected");
    	    	alNoStage.setHeaderText(null);
    	    	alNoStage.setContentText("Please select a stage.");
    	    	alNoStage.showAndWait();	
    	    	return;
    		}
    		
    		final int WIN_CONDITION_BO3 = 2;
    		final int WIN_CONDITION_BO5 = 3;
    		final int INVALID_COUNT_B03 = 1;
    		final int INVALID_COUNT_B05 = 2;
    		String setOutcome = null;
    		String format = null;
    		boolean isSetComplete = false;
    		boolean isInvalidCount = false;

    		GameDao gameDao = new GameDaoImpl();
    		int gameWon = gameDao.calculateGamesWon(setID);
    		int gameLost = gameDao.calculateGamesLost(setID);

    		if (gameOutcome.equals("Won")) {
    			gameWon++;
    		} else {
    			gameLost++;
    		}
    		
			SetDao setDao = new SetDaoImpl();
			Set set = setDao.getSetById(setID);
 	 		
 			format = set.getFormat();
 	 		setOutcome = set.getOutcome();
 	 		if (setOutcome.equals("Won") && format.equals("Bo3")) {
 	 			if (gameLost > INVALID_COUNT_B03) {
 	 				isInvalidCount = true;
 	 			}
 	 			if (gameWon > WIN_CONDITION_BO3) {
 	 				isSetComplete = true;
 	 			}
 	 		} else if (setOutcome.equals("Lost") && format.equals("Bo3")) {
 	 			if (gameWon > INVALID_COUNT_B03) {
 	 				isInvalidCount = true;
 	 			}
 	 			if (gameLost > WIN_CONDITION_BO3) {
 	 				isSetComplete = true;
 	 			}
 	 		} else if (setOutcome.equals("Won") && format.equals("Bo5")) {
 	 			if (gameLost > INVALID_COUNT_B05) {
 	 				isInvalidCount = true;
 	 			}
 	 			if (gameWon > WIN_CONDITION_BO5) {
 	 				isSetComplete = true;
 	 			}
 	 		} else if (setOutcome.equals("Lost") && format.equals("Bo5")) {
 	 			if (gameWon > INVALID_COUNT_B05) {
 	 				isInvalidCount = true;
 	 			}
 	 			if (gameLost > WIN_CONDITION_BO5) {
 	 				isSetComplete = true;
 	 			}
 	 		}
			
 	 		if (isSetComplete) {
 		    	Alert alSetComplete = new Alert(AlertType.ERROR);
 		    	alSetComplete.setTitle("Set Complete");
 		    	alSetComplete.setHeaderText(null);
 		    	alSetComplete.setContentText("The set is complete, remove or edit a game.");
 		    	alSetComplete.showAndWait();	
 		    	theStage.setScene(specifiedSetScene);
 		    	return;
 	 		}
 	 		if (isInvalidCount) {
 		    	Alert alInvalidCount = new Alert(AlertType.ERROR);
 		    	alInvalidCount.setTitle("Invalid Count");
 		    	alInvalidCount.setHeaderText(null);
 		    	alInvalidCount.setContentText("The count of the set is invalid, make sure the set outcome and format are correct.");
 		    	alInvalidCount.showAndWait();	
 		    	theStage.setScene(specifiedSetScene);
 		    	return;
 	 		}
 	 		
 	 		int myCharacterID = GeneralUtils.getCharacterID(myCharacter);
 	 		int opponentCharacterID = GeneralUtils.getCharacterID(opponentCharacter);
 	 		int stageID = GeneralUtils.getStageID(stage);

 	 		Game game = new Game(setID, myCharacterID, opponentCharacterID, stageID, gameOutcome);
 	 		gameDao.insertGame(game);
    		
	    	Alert alAddGame = new Alert(AlertType.INFORMATION);
	    	alAddGame.setTitle("Add Game");
	    	alAddGame.setHeaderText(null);
	    	alAddGame.setContentText("The game was added.");
	    	alAddGame.showAndWait();	
	    	printGames();
	    	theStage.setScene(specifiedSetScene);
			cbOutcome.setValue("");
//			cbMyCharacterAG.setValue("");
			cbOpponentCharacter.setValue("");
			cbStage.setValue("");
		});		
		
		addGamePaneCenter.add(btBack, 0, 6);
		btBack.setOnAction(e -> theStage.setScene(specifiedSetScene));
		
		addGamePaneCenter.add(btMainMenu, 1, 6);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		addGameBorderPane.setTop(addGamePaneTop);
		addGameBorderPane.setCenter(addGamePaneCenter);

		addGameScene = new Scene(addGameBorderPane, 700, 600);
		addGameScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void specifiedGameSetup() {
		GridPane specifiedGamePaneTop = new GridPane();
		specifiedGamePaneTop.setHgap(10);
		specifiedGamePaneTop.setVgap(10);
		specifiedGamePaneTop.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedGamePaneCenter = new GridPane();
		specifiedGamePaneCenter.setHgap(10);
		specifiedGamePaneCenter.setVgap(10);
		specifiedGamePaneCenter.setPadding(new Insets(10, 10, 10, 10));
		BorderPane specifiedGameBorderPane = new BorderPane();
		
		lblSpecifiedGame.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		specifiedGamePaneTop.add(lblSpecifiedGame, 0, 0);
		
		Label lblOutcome = new Label("Outcome:");
//    	ComboBox<String> cbOutcome = new ComboBox<String>();
    	cbOutcomeG.getItems().addAll("Won", "Lost");
		Label lblMyCharacter = new Label("My Character:");
		GeneralUtils.populateCharacterComboBox(cbMyCharacterG);
		//	ComboBox<String> cbMyCharacter = new ComboBox<String>();
		Label lblOppenentCharacter = new Label("Opponent Character:");
		GeneralUtils.populateCharacterComboBox(cbOpponentCharacterG);
		//ComboBox<String> cbOpponentCharacter = new ComboBox<String>();
		Label lblStage = new Label("Stage:");
    	//ComboBox<String> cbStage = new ComboBox<String>();
    	cbStageG.getItems().addAll("Battlefield", "Dreamland", "Yoshi's Story", "Fountain of Dreams", "Pokemon Stadium", "Final Destination");
    	Button btApplyChanges = new Button("Apply Changes");
    	Button btRemove = new Button("Remove");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");

		specifiedGamePaneCenter.add(lblOutcome, 0, 0);
		specifiedGamePaneCenter.add(cbOutcomeG, 1, 0);
		specifiedGamePaneCenter.add(lblMyCharacter, 0, 1);
		specifiedGamePaneCenter.add(cbMyCharacterG, 1, 1);
		specifiedGamePaneCenter.add(lblOppenentCharacter, 0, 2);
		specifiedGamePaneCenter.add(cbOpponentCharacterG, 1, 2);
		specifiedGamePaneCenter.add(lblStage, 0, 3);
		specifiedGamePaneCenter.add(cbStageG, 1, 3);		
		specifiedGamePaneCenter.add(btApplyChanges, 0, 4);
		btApplyChanges.setOnAction(e -> {
			String outcome = cbOutcomeG.getValue();
			String myCharacter = cbMyCharacterG.getValue();
			String opponentCharacter =  cbOpponentCharacterG.getValue();
			String stage =  cbStageG.getValue();
			int myCharacterID = GeneralUtils.getCharacterID(myCharacter);
			int opponentCharacterID = GeneralUtils.getCharacterID(opponentCharacter);
			int stageID = GeneralUtils.getStageID(stage);
			
			GameDao gameDao = new GameDaoImpl();
			Game game = new Game(gameID, setID, myCharacterID, opponentCharacterID, stageID, outcome);
			gameDao.updateGame(game);

	    	printGames();
			Alert alApplyChanges = new Alert(AlertType.INFORMATION);
	    	alApplyChanges.setTitle("Apply Changes");
	    	alApplyChanges.setHeaderText(null);
	    	alApplyChanges.setContentText("The game was updated.");
	    	alApplyChanges.showAndWait();
	    	theStage.setScene(specifiedSetScene);
		});
		
		specifiedGamePaneCenter.add(btRemove, 1, 4);		
		btRemove.setOnAction(e -> {			
	    	Alert alRemoveGame = new Alert(AlertType.CONFIRMATION);
	    	alRemoveGame.setTitle("Remove Game");
	    	alRemoveGame.setHeaderText(null);
	    	alRemoveGame.setContentText("Are you sure you want to remove the game?");
			Optional<ButtonType> optionSelected = alRemoveGame.showAndWait();
			if (optionSelected.get() == ButtonType.OK) {
				GameDao gameDao = new GameDaoImpl();
				gameDao.deleteGameById(gameID);
				
				printGames();
				theStage.setScene(specifiedSetScene);
			} else {
				return;
			}
		});
		
		specifiedGamePaneCenter.add(btBack, 0, 6);
		btBack.setOnAction(e -> {
			theStage.setScene(specifiedSetScene);
		});
		
		specifiedGamePaneCenter.add(btMainMenu, 1, 6);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		specifiedGameBorderPane.setTop(specifiedGamePaneTop);
		specifiedGameBorderPane.setCenter(specifiedGamePaneCenter);
		
		specifiedGameScene = new Scene(specifiedGameBorderPane, 700, 600);
		specifiedGameScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void playerSetup() {
		GridPane playerTopPane = new GridPane();
		playerTopPane.setHgap(10);
		playerTopPane.setVgap(10);
		playerTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane playerBottomPane = new GridPane();
		playerBottomPane.setHgap(10);
		playerBottomPane.setVgap(10);
		playerBottomPane.setPadding(new Insets(10, 10, 10, 10));
    	BorderPane playerBorderPane = new BorderPane();

    	Label lblPlayerNotes = new Label("Player Notes");
    	lblPlayerNotes.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    	playerTopPane.add(lblPlayerNotes, 0, 0);
		
		Button btAddPlayer = new Button("Add Player");
		Button btMainMenu = new Button("Main Menu");
		
		playerBottomPane.add(btAddPlayer, 5, 0);
		btAddPlayer.setOnAction(e -> {
			lastSceneID = PLAYER_SCENE;
			theStage.setScene(addPlayerScene); 
		});
		playerBottomPane.add(btMainMenu, 6, 0);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
    	
		playerScrollPane.setFitToWidth(true);
		playerScrollPane.setPrefSize(300, 500);
		
    	playerBorderPane.setTop(playerTopPane);
    	playerBorderPane.setCenter(playerScrollPane);
    	playerBorderPane.setBottom(playerBottomPane);
    	
		playerScene = new Scene(playerBorderPane, 300, 500);
		playerScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void addPlayerSetup() {
		GridPane addPlayerTopPane = new GridPane();
		addPlayerTopPane.setHgap(10);
		addPlayerTopPane.setVgap(10);
		addPlayerTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane addPlayerCenterPane = new GridPane();
		addPlayerCenterPane.setHgap(10);
		addPlayerCenterPane.setVgap(10);
		addPlayerCenterPane.setPadding(new Insets(10, 10, 10, 10));
		BorderPane addPlayerBorderPane = new BorderPane();
		
    	Label lblAddPlayer = new Label("Add Player");
    	lblAddPlayer.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    	addPlayerTopPane.add(lblAddPlayer, 0, 0);
		
		Label lblTag = new Label("Tag:");
		TextField tfTag = new TextField();
		Label lblNotes = new Label("Notes:");
		TextArea taNotes = new TextArea();
		Button btAddPlayer = new Button("Add Player");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");
		
		addPlayerCenterPane.add(lblTag, 0, 0);
		addPlayerCenterPane.add(tfTag, 1, 0);
		addPlayerCenterPane.add(lblNotes, 0, 1);
		addPlayerCenterPane.add(taNotes, 1, 1);
		addPlayerCenterPane.add(btAddPlayer, 0, 2);
		btAddPlayer.setOnAction(e -> {
			String tag = tfTag.getText();
			String notes = taNotes.getText();
			
			if (tag.isEmpty()) {
		    	Alert alNotNullFields = new Alert(AlertType.ERROR);
		    	alNotNullFields.setTitle("Add Player");
		    	alNotNullFields.setHeaderText(null);
		    	alNotNullFields.setContentText("Please enter information into the tag field.");
		    	alNotNullFields.showAndWait();	
				return;
			}
			
			Player player = new Player(userID, tag, notes);
			PlayerDao playerDao = new PlayerDaoImpl();
			playerDao.insertPlayer(player);
		
	    	Alert alAddTournament = new Alert(AlertType.INFORMATION);
	    	alAddTournament.setTitle("Add Player");
	    	alAddTournament.setHeaderText(null);
	    	alAddTournament.setContentText("Player has been added.");
	    	alAddTournament.showAndWait();
	    	printPlayers();
	    	setStage();
			tfTag.setText("");
			taNotes.setText("");
		});
		
		addPlayerCenterPane.add(btBack, 0, 4);
		btBack.setOnAction(e -> {
			setStage();
		});
		
		addPlayerCenterPane.add(btMainMenu, 1, 4);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		addPlayerBorderPane.setTop(addPlayerTopPane);
		addPlayerBorderPane.setCenter(addPlayerCenterPane);

		addPlayerScene = new Scene(addPlayerBorderPane, 700, 600);
		addPlayerScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void specifiedPlayerSetup() {
		GridPane specifiedPlayerTopPane = new GridPane();
		specifiedPlayerTopPane.setHgap(10);
		specifiedPlayerTopPane.setVgap(10);
		specifiedPlayerTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedPlayerCenterPane = new GridPane();
		specifiedPlayerCenterPane.setHgap(10);
		specifiedPlayerCenterPane.setVgap(10);
		specifiedPlayerCenterPane.setPadding(new Insets(10, 10, 10, 10));
		BorderPane specifiedPlayerBorderPane = new BorderPane();
		
		lblSpecifiedPlayer.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		specifiedPlayerTopPane.add(lblSpecifiedPlayer, 0, 0);
    	
		Label lblTag = new Label("Tag:");
		Label lblNotes = new Label("Notes:");
		Button btApplyChanges = new Button("Apply Changes");
		Button btRemove = new Button("Remove");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");

		specifiedPlayerCenterPane.add(lblTag, 0, 0);
		specifiedPlayerCenterPane.add(tfTagP, 1, 0);
		specifiedPlayerCenterPane.add(lblNotes, 0, 1);
		specifiedPlayerCenterPane.add(taNotesP, 1, 1);
		specifiedPlayerCenterPane.add(btApplyChanges, 0, 2);
		btApplyChanges.setOnAction(e -> {
			String tag = tfTagP.getText();
			String notes = taNotesP.getText();
			
			Player player = new Player(playerID, userID, tag, notes);
			PlayerDao playerDao = new PlayerDaoImpl();
			playerDao.updatePlayer(player);
			
			printPlayers();
	    	Alert alApplyChanges = new Alert(AlertType.INFORMATION);
	    	alApplyChanges.setTitle("Apply Changes");
	    	alApplyChanges.setHeaderText(null);
	    	alApplyChanges.setContentText("The player information was updated.");
	    	alApplyChanges.showAndWait();	
		});
		
		specifiedPlayerCenterPane.add(btRemove, 1, 2);
		btRemove.setOnAction(e -> {
	    	Alert alRemove= new Alert(AlertType.CONFIRMATION);
	    	alRemove.setTitle("Remove Player");
	    	alRemove.setHeaderText(null);
	    	alRemove.setContentText("Are you sure you want to remove this player?");
			Optional<ButtonType> optionSelected = alRemove.showAndWait();
			if (optionSelected.get() == ButtonType.OK) {
				PlayerDao playerDao = new PlayerDaoImpl();
				playerDao.deletePlayerById(playerID);
				
				printPlayers();
				theStage.setScene(playerScene);
			} else {
				return;
			}
		});
		
		specifiedPlayerCenterPane.add(btBack, 0, 4);
		btBack.setOnAction(e -> theStage.setScene(playerScene));
		
		specifiedPlayerCenterPane.add(btMainMenu, 1, 4);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		specifiedPlayerBorderPane.setTop(specifiedPlayerTopPane);
		specifiedPlayerBorderPane.setCenter(specifiedPlayerCenterPane);
		
		specifiedPlayerScene = new Scene(specifiedPlayerBorderPane, 700, 600);
		specifiedPlayerScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void characterSetup() {
		GridPane characterTopPane = new GridPane();
		characterTopPane.setHgap(10);
		characterTopPane.setVgap(10);
		characterTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane characterBottomPane = new GridPane();
		characterBottomPane.setHgap(10);
		characterBottomPane.setVgap(10);
		characterBottomPane.setPadding(new Insets(10, 10, 10, 10));
    	BorderPane characterBorderPane = new BorderPane();

    	Label lblCharacterNotes = new Label("Character Notes");
    	lblCharacterNotes.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    	characterTopPane.add(lblCharacterNotes, 0, 0);
    	
    	Button btMainMenu = new Button("Main Menu");
    	
    	characterBottomPane.add(btMainMenu, 0, 0);
    	btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
    	
		characterScrollPane.setFitToWidth(true);
		characterScrollPane.setPrefSize(300, 500);

		characterBorderPane.setTop(characterTopPane);
		characterBorderPane.setCenter(characterScrollPane);
		characterBorderPane.setBottom(characterBottomPane);
    	
		characterScene = new Scene (characterBorderPane, 300, 500);
		characterScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void specifiedCharacterSetup() {
		GridPane characterTopPane = new GridPane();
		characterTopPane.setHgap(10);
		characterTopPane.setVgap(10);
		characterTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane characterBottomPane = new GridPane();
		characterBottomPane.setHgap(10);
		characterBottomPane.setVgap(10);
		characterBottomPane.setPadding(new Insets(10, 10, 10, 10));
    	BorderPane characterBorderPane = new BorderPane();
		
    	lblSpecifiedCharacter.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    	characterTopPane.add(lblSpecifiedCharacter, 0, 0);
    	
    	Button btAddNote = new Button("Add Note");
    	Button btBack = new Button("Back");
    	Button btMainMenu = new Button("Main Menu");
    	
		characterBottomPane.add(btAddNote, 0, 0);
    	btAddNote.setOnAction(e -> theStage.setScene(addCharacterNoteScene));
    	
    	characterBottomPane.add(btBack, 1, 0);
		btBack.setOnAction(e -> theStage.setScene(characterScene));
		
		characterBottomPane.add(btMainMenu, 2, 0);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		characterScrollPane.setFitToWidth(true);
		characterScrollPane.setPrefSize(300, 500);
		
		characterBorderPane.setTop(characterTopPane);
		characterBorderPane.setCenter(specifiedCharacterNotesScrollPane);
		characterBorderPane.setBottom(characterBottomPane);
    	
		specifiedCharacterScene = new Scene (characterBorderPane, 300, 500);
		specifiedCharacterScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void addCharacterNoteSetup() {
		GridPane addCharacterNoteTopPane = new GridPane();
		addCharacterNoteTopPane.setHgap(10);
		addCharacterNoteTopPane.setVgap(10);
		addCharacterNoteTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane addCharacterNoteCenterPane = new GridPane();
		addCharacterNoteCenterPane.setHgap(10);
		addCharacterNoteCenterPane.setVgap(10);
		addCharacterNoteCenterPane.setPadding(new Insets(10, 10, 10, 10));
		BorderPane addCharacterNoteBorderPane = new BorderPane();
		
		Label lblAddCharacterNote = new Label("Add Character Note");
		lblAddCharacterNote.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		addCharacterNoteTopPane.add(lblAddCharacterNote, 0, 0);
		
		Label lblSubject = new Label("Subject:");
		TextField tfSubject = new TextField();
		Label lblNote = new Label("Note");
		TextArea taNotes = new TextArea();
		Button btAddNote = new Button("Add Note");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");
		
		addCharacterNoteCenterPane.add(lblSubject, 0, 0);
		addCharacterNoteCenterPane.add(tfSubject, 1, 0);
		addCharacterNoteCenterPane.add(lblNote, 0, 1);
		addCharacterNoteCenterPane.add(taNotes, 1, 1);
		addCharacterNoteCenterPane.add(btAddNote, 0, 2);
		btAddNote.setOnAction(e -> {
			String subject = tfSubject.getText();
			String notes = taNotes.getText();
			
			if (subject.isEmpty()) {
		    	Alert alNotNullFields = new Alert(AlertType.ERROR);
		    	alNotNullFields.setTitle("Add Character Note");
		    	alNotNullFields.setHeaderText(null);
		    	alNotNullFields.setContentText("Please enter information into the subject field.");
		    	alNotNullFields.showAndWait();	
				return;
			}
			
			CharacterNoteDao characterNoteDao = new CharacterNoteDaoImpl();
			CharacterNote characterNote = new CharacterNote(characterID, userID, subject, notes);
			characterNoteDao.insertCharacterNote(characterNote);
		
	    	printCharacterNotes();
			Alert alAddCharacterNote = new Alert(AlertType.INFORMATION);
	    	alAddCharacterNote.setTitle("Add Character Note");
	    	alAddCharacterNote.setHeaderText(null);
	    	alAddCharacterNote.setContentText("Character note has been added.");
	    	alAddCharacterNote.showAndWait();
	    	theStage.setScene(specifiedCharacterScene);
			tfSubject.setText("");
			taNotes.setText("");
		});
		
		addCharacterNoteCenterPane.add(btBack, 0, 4);
		btBack.setOnAction(e -> theStage.setScene(specifiedCharacterScene));
		
		addCharacterNoteCenterPane.add(btMainMenu, 1, 4);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		addCharacterNoteBorderPane.setTop(addCharacterNoteTopPane);
		addCharacterNoteBorderPane.setCenter(addCharacterNoteCenterPane);

		addCharacterNoteScene = new Scene(addCharacterNoteBorderPane, 700, 600);
		addCharacterNoteScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void specifiedCharacterNoteSetup() {
		GridPane specifiedCharacterNoteTopPane = new GridPane();
		specifiedCharacterNoteTopPane.setHgap(10);
		specifiedCharacterNoteTopPane.setVgap(10);
		specifiedCharacterNoteTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedCharacterNoteCenterPane = new GridPane();
		specifiedCharacterNoteCenterPane.setHgap(10);
		specifiedCharacterNoteCenterPane.setVgap(10);
		specifiedCharacterNoteCenterPane.setPadding(new Insets(10, 10, 10, 10));
    	BorderPane specifiedCharacterNoteBorderPane = new BorderPane();
    	
    	lblSpecifiedCharacterNote.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    	specifiedCharacterNoteTopPane.add(lblSpecifiedCharacterNote, 0, 0);
    	
		Label lblSubject = new Label("Subject:");
		Label lblNote = new Label("Note:");
		Button btApplyChanges = new Button("Apply Changes");
		Button btRemove = new Button("Remove");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");
		
		specifiedCharacterNoteCenterPane.add(lblSubject, 0, 0);
		specifiedCharacterNoteCenterPane.add(tfSubjectCN, 1, 0);
		specifiedCharacterNoteCenterPane.add(lblNote, 0, 1);
		specifiedCharacterNoteCenterPane.add(taNotesCN, 1, 1);
		specifiedCharacterNoteCenterPane.add(btApplyChanges, 0, 2);
		btApplyChanges.setOnAction(e -> {
			String subject = tfSubjectCN.getText();
			String notes = taNotesCN.getText();

			CharacterNoteDao characterNoteDao = new CharacterNoteDaoImpl();
			CharacterNote characterNote = new CharacterNote(characterNoteID, characterID, userID, subject, notes);
			characterNoteDao.updateCharacterNote(characterNote);
			
			printCharacterNotes();
	    	Alert alApplyChanges = new Alert(AlertType.INFORMATION);
	    	alApplyChanges.setTitle("Apply Changes");
	    	alApplyChanges.setHeaderText(null);
	    	alApplyChanges.setContentText("The character note's information was updated.");
	    	alApplyChanges.showAndWait();	
		});
		
		specifiedCharacterNoteCenterPane.add(btRemove, 1, 2);
		btRemove.setOnAction(e -> {			
	    	Alert alRemove= new Alert(AlertType.CONFIRMATION);
	    	alRemove.setTitle("Remove Character Note");
	    	alRemove.setHeaderText(null);
	    	alRemove.setContentText("Are you sure you want to remove this note?");
			Optional<ButtonType> optionSelected = alRemove.showAndWait();
			if (optionSelected.get() == ButtonType.OK) {
				CharacterNoteDao characterNoteDao = new CharacterNoteDaoImpl();
				characterNoteDao.deleteCharacterNoteById(characterNoteID);
				
				printCharacterNotes();
				theStage.setScene(specifiedCharacterScene);
			} else {
				return;
			}
		});

		specifiedCharacterNoteCenterPane.add(btBack, 0, 4);
		btBack.setOnAction(e -> theStage.setScene(specifiedCharacterScene));
		
		specifiedCharacterNoteCenterPane.add(btMainMenu, 1, 4);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		specifiedCharacterNoteBorderPane.setTop(specifiedCharacterNoteTopPane);
		specifiedCharacterNoteBorderPane.setCenter(specifiedCharacterNoteCenterPane);

		specifiedCharacterNoteScene = new Scene(specifiedCharacterNoteBorderPane, 700, 600);
		specifiedCharacterNoteScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}

	public void analysisNoteSetup() {
		GridPane analysisNoteTopPane = new GridPane();
		analysisNoteTopPane.setHgap(10);
		analysisNoteTopPane.setVgap(10);
		analysisNoteTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane analysisNotesBottomPane = new GridPane();
		analysisNotesBottomPane.setHgap(10);
		analysisNotesBottomPane.setVgap(10);
		analysisNotesBottomPane.setPadding(new Insets(10, 10, 10, 10));
    	BorderPane analysisNotesBorderPane = new BorderPane();

		Label lblAnalysisNotes = new Label("Analysis Notes");
		lblAnalysisNotes.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		analysisNoteTopPane.add(lblAnalysisNotes, 0, 0);
    	
    	Button btAddAnalysisNote = new Button("Add Analysis Note");
    	Button btMainMenu = new Button("Main Menu");
    	
    	analysisNotesBottomPane.add(btAddAnalysisNote, 0, 0);
    	btAddAnalysisNote.setOnAction(e -> theStage.setScene(addAnalysisNoteScene));
    	analysisNotesBottomPane.add(btMainMenu, 1, 0);
    	btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
    	
    	analysisNotesScrollPane.setFitToWidth(true);
		analysisNotesScrollPane.setPrefSize(300, 500);
		
		analysisNotesBorderPane.setTop(analysisNoteTopPane);
		analysisNotesBorderPane.setCenter(analysisNotesScrollPane);
		analysisNotesBorderPane.setBottom(analysisNotesBottomPane);
    	
		analysisNoteScene = new Scene(analysisNotesBorderPane, 300, 500);
		analysisNoteScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void addAnalysisNoteSetup() {
		GridPane addAnalysisNoteTopPane = new GridPane();
		addAnalysisNoteTopPane.setHgap(10);
		addAnalysisNoteTopPane.setVgap(10);
		addAnalysisNoteTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane addAnalysisNoteCenterPane = new GridPane();
		addAnalysisNoteCenterPane.setHgap(10);
		addAnalysisNoteCenterPane.setVgap(10);
		addAnalysisNoteCenterPane.setPadding(new Insets(10, 10, 10, 10));
		BorderPane addAnalysisNoteBorderPane = new BorderPane();
				
		Label lblAddAnalysisNote = new Label("Add Analysis Note");
		lblAddAnalysisNote.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		addAnalysisNoteTopPane.add(lblAddAnalysisNote, 0, 0);
		
		TextField tfSubject = new TextField();
		tfSubject.setPromptText("player 1 (character 1) vs player 2 (character 2)");
		TextField tfFocusPoints = new TextField();
		tfFocusPoints.setPromptText("ex. corner pressure & picking my spots");
		TextArea taNotes = new TextArea();
		Button btAddAnalysisNote = new Button("Add Analysis Note");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");
		
		addAnalysisNoteCenterPane.add(new Label("Subject:"), 0, 0);
		addAnalysisNoteCenterPane.add(tfSubject, 1, 0);
		addAnalysisNoteCenterPane.add(new Label("Focus Points:"), 0, 1);
		addAnalysisNoteCenterPane.add(tfFocusPoints, 1, 1);
		addAnalysisNoteCenterPane.add(new Label("Notes:"), 0, 2);
		addAnalysisNoteCenterPane.add(taNotes, 1, 2);
		addAnalysisNoteCenterPane.add(btAddAnalysisNote, 0, 3);
		btAddAnalysisNote.setOnAction(e -> {
			String subject = tfSubject.getText();
			String focusPoints = tfFocusPoints.getText();
			String notes = taNotes.getText();
			
			if (subject.isEmpty()) {
		    	Alert alNotNullFields = new Alert(AlertType.ERROR);
		    	alNotNullFields.setTitle("Add Analysis Note");
		    	alNotNullFields.setHeaderText(null);
		    	alNotNullFields.setContentText("Please enter information into the subject field.");
		    	alNotNullFields.showAndWait();	
				return;
			}
			
			AnalysisNoteDao analysisNoteDao = new AnalysisNoteDaoImpl();
			AnalysisNote analysisNote = new AnalysisNote(userID, subject, focusPoints, notes);
			analysisNoteDao.insertAnalysisNote(analysisNote);
			
	    	printAnalysisNotes();
			Alert alAddAnalysisNote = new Alert(AlertType.INFORMATION);
	    	alAddAnalysisNote.setTitle("Add Analysis Note");
	    	alAddAnalysisNote.setHeaderText(null);
	    	alAddAnalysisNote.setContentText("Analysis note has been added.");
	    	alAddAnalysisNote.showAndWait();

	    	theStage.setScene(analysisNoteScene);
			tfSubject.setText("");
			tfFocusPoints.setText("");
			taNotes.setText("");
		});
		addAnalysisNoteCenterPane.add(btBack, 0, 5);
		btBack.setOnAction(e -> theStage.setScene(analysisNoteScene));
		
		addAnalysisNoteCenterPane.add(btMainMenu, 1, 5);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		addAnalysisNoteBorderPane.setTop(addAnalysisNoteTopPane);
		addAnalysisNoteBorderPane.setCenter(addAnalysisNoteCenterPane);

		addAnalysisNoteScene = new Scene(addAnalysisNoteBorderPane, 1000, 700);
		addAnalysisNoteScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void specifiedAnalysisNoteSetup() {
		GridPane specifiedAnalysisNoteTopPane = new GridPane();
		specifiedAnalysisNoteTopPane.setHgap(10);
		specifiedAnalysisNoteTopPane.setVgap(10);
		specifiedAnalysisNoteTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedAnalysisNoteCenterPane = new GridPane();
		specifiedAnalysisNoteCenterPane.setHgap(10);
		specifiedAnalysisNoteCenterPane.setVgap(10);
		specifiedAnalysisNoteCenterPane.setPadding(new Insets(10, 10, 10, 10));
		BorderPane specifiedAnalysisNoteBorderPane = new BorderPane();

		lblSpecifiedAnalysisNote.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		specifiedAnalysisNoteTopPane.add(lblSpecifiedAnalysisNote, 0, 0);
		
		tfSubjectSA.setPromptText("player 1 (character 1) vs player 2 (character 2)");
		tfFocusPointsSA.setPromptText("ex. corner pressure & picking my spots");
		Button btApplyChanges = new Button("Apply Changes");
		Button btRemove = new Button("Remove");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");
		
		specifiedAnalysisNoteCenterPane.add(new Label("Subject:"), 0, 0);
		specifiedAnalysisNoteCenterPane.add(tfSubjectSA, 1, 0);
		specifiedAnalysisNoteCenterPane.add(new Label("Focus Points:"), 0, 1);
		specifiedAnalysisNoteCenterPane.add(tfFocusPointsSA, 1, 1);
		specifiedAnalysisNoteCenterPane.add(new Label("Notes:"), 0, 2);
		specifiedAnalysisNoteCenterPane.add(taNotesSA, 1, 2);
		specifiedAnalysisNoteCenterPane.add(btApplyChanges, 0, 3);
		btApplyChanges.setOnAction(e -> {
			String subject = tfSubjectSA.getText();
			String focusPoints = tfFocusPointsSA.getText();
			String notes = taNotesSA.getText();
			
			AnalysisNoteDao analysisNoteDao = new AnalysisNoteDaoImpl();
			AnalysisNote analysisNote = new AnalysisNote(analysisNoteID, userID, subject, focusPoints, notes);
			analysisNoteDao.updateAnalysisNote(analysisNote);
			
			printAnalysisNotes();
	    	Alert alApplyChanges = new Alert(AlertType.INFORMATION);
	    	alApplyChanges.setTitle("Apply Changes");
	    	alApplyChanges.setHeaderText(null);
	    	alApplyChanges.setContentText("The analysis note's information was updated.");
	    	alApplyChanges.showAndWait();	
		});
		specifiedAnalysisNoteCenterPane.add(btRemove, 1, 3);
		btRemove.setOnAction(e -> {
	    	Alert alRemove= new Alert(AlertType.CONFIRMATION);
	    	alRemove.setTitle("Remove Analysis Note");
	    	alRemove.setHeaderText(null);
	    	alRemove.setContentText("Are you sure you want to remove this note?");
			Optional<ButtonType> optionSelected = alRemove.showAndWait();
			if (optionSelected.get() == ButtonType.OK) {
				AnalysisNoteDao analysisNoteDao = new AnalysisNoteDaoImpl();
				analysisNoteDao.deleteAnalysisNoteById(analysisNoteID);
				
				printAnalysisNotes();
				theStage.setScene(analysisNoteScene);
			} else {
				return;
			}
		});
		specifiedAnalysisNoteCenterPane.add(btBack, 0, 5);
		btBack.setOnAction(e -> theStage.setScene(analysisNoteScene));
		
		specifiedAnalysisNoteCenterPane.add(btMainMenu, 1, 5);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		specifiedAnalysisNoteBorderPane.setTop(specifiedAnalysisNoteTopPane);
		specifiedAnalysisNoteBorderPane.setCenter(specifiedAnalysisNoteCenterPane);
		
		specifiedAnalysisNoteScene = new Scene(specifiedAnalysisNoteBorderPane, 1000, 700);
		specifiedAnalysisNoteScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void tiltProfileSetup() {
		GridPane tiltProfileTopPane = new GridPane();
		tiltProfileTopPane.setHgap(10);
		tiltProfileTopPane.setVgap(10);
		tiltProfileTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane tiltProfileBottomPane = new GridPane();
		tiltProfileBottomPane.setHgap(10);
		tiltProfileBottomPane.setVgap(10);
    	tiltProfileBottomPane.setPadding(new Insets(10, 10, 10, 10));
		BorderPane tiltProfileBorderPane = new BorderPane();
		
		Label lblTiltProfile = new Label("Tilt Profile");
		lblTiltProfile.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		tiltProfileTopPane.add(lblTiltProfile, 0, 0);
		
		Button btAddTiltType = new Button("Add Tilt Type");
		Button btAdd = new Button("Add");
		TextField tfType = new TextField();
		tfType.setPromptText("ex. Running-Bad Tilt");
		Button btMainMenu = new Button("Main Menu");
		
		tiltProfileBottomPane.add(btAddTiltType, 0, 0);
		btAddTiltType.setOnAction(e1 -> {
			//tiltProfileBottomPane.add(new Label("Type:"), 0, 1);
			tiltProfileBottomPane.add(tfType, 0, 1, 2, 1);
			tiltProfileBottomPane.add(btAdd, 2, 1);
			btAdd.setOnAction(e2 -> {
	    		String type = tfType.getText();
	    		if (type == null || type.isEmpty()) {
	    	    	Alert alNoType = new Alert(AlertType.ERROR);
	    	    	alNoType.setTitle("Add Tilt Type");
	    	    	alNoType.setHeaderText(null);
	    	    	alNoType.setContentText("Please enter something into the type field.");
	    	    	alNoType.showAndWait();	
	    	    	return;
	    		}
	    		
	    		TiltTypeDao tiltTypeDao = new TiltTypeDaoImpl();
	    		TiltType tiltType = new TiltType(userID, type);
	    		tiltTypeDao.insertTiltType(tiltType);
	    		
	    		printTiltTypes();
	    		tiltProfileBottomPane.getChildren().remove(tfType);
				tiltProfileBottomPane.getChildren().remove(btAdd);
		    	Alert alAddCauseOfTilt = new Alert(AlertType.INFORMATION);
		    	alAddCauseOfTilt.setTitle("Add Tilt Type");
		    	alAddCauseOfTilt.setHeaderText(null);
		    	alAddCauseOfTilt.setContentText("The tilt type was added.");
		    	alAddCauseOfTilt.showAndWait();	
		    	tfType.setText("");
			});
		});
		
		tiltProfileBottomPane.add(btMainMenu, 1, 0);
		btMainMenu.setOnAction(e -> {
    		tiltProfileBottomPane.getChildren().remove(tfType);
			tiltProfileBottomPane.getChildren().remove(btAdd);
			theStage.setScene(mainMenuScene);
		});
		
		tiltProfileBorderPane.setTop(tiltProfileTopPane);
		tiltProfileBorderPane.setCenter(tiltTypeScrollPane);
		tiltProfileBorderPane.setBottom(tiltProfileBottomPane);

		tiltTypeScrollPane.setFitToWidth(true);
    	tiltTypeScrollPane.setPrefSize(300, 500);
		
		tiltProfileScene = new Scene (tiltProfileBorderPane, 300, 500);
		tiltProfileScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void specifiedTiltType() {
		GridPane specifiedTiltTypeTopPane = new GridPane();
		specifiedTiltTypeTopPane.setHgap(10);
		specifiedTiltTypeTopPane.setVgap(10);
		specifiedTiltTypeTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedTiltTypeCenterPane = new GridPane();
		specifiedTiltTypeCenterPane.setHgap(10);
		specifiedTiltTypeCenterPane.setVgap(10);
		specifiedTiltTypeCenterPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedTiltTypeBottomPane = new GridPane();
		specifiedTiltTypeBottomPane.setHgap(10);
		specifiedTiltTypeBottomPane.setVgap(10);
		specifiedTiltTypeBottomPane.setPadding(new Insets(10, 10, 10, 10));
		BorderPane specifiedTiltTypeBorderPane = new BorderPane();
		
		lblSpecifiedTiltType.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Button btRenameTiltType = new Button("Rename Tilt Type");
		TextField tfRenameTiltType = new TextField();
		tfRenameTiltType.setPromptText("Type");
		Button btRename = new Button("Rename");
		Button btCloseRename = new Button("Close");
		Button btRemoveTiltType = new Button("Remove Tilt Type");
		
		specifiedTiltTypeTopPane.add(lblSpecifiedTiltType, 0, 0);
		specifiedTiltTypeTopPane.add(btRenameTiltType, 1, 0);
		btRenameTiltType.setOnAction(e1 -> {
			specifiedTiltTypeTopPane.add(tfRenameTiltType, 0, 1);
			specifiedTiltTypeTopPane.add(btRename, 1, 1);
			btRename.setOnAction(e2 -> {
				String type = tfRenameTiltType.getText();

				TiltTypeDao tiltTypeDao = new TiltTypeDaoImpl();
				TiltType tiltType = new TiltType(tiltTypeID, type);
				tiltTypeDao.updateTiltName(tiltType);
				
		    	Alert alRename = new Alert(AlertType.INFORMATION);
		    	alRename.setTitle("Rename Tilt Type");
		    	alRename.setHeaderText(null);
		    	alRename.setContentText("The tilt type was updated.");
		    	alRename.showAndWait();
		    	lblSpecifiedTiltType.setText(type);
		    	printTiltTypes();
			});
			
			specifiedTiltTypeTopPane.add(btCloseRename, 2, 1);
			btCloseRename.setOnAction(e -> {
				specifiedTiltTypeTopPane.getChildren().remove(tfRenameTiltType);
				specifiedTiltTypeTopPane.getChildren().remove(btRename);
				specifiedTiltTypeTopPane.getChildren().remove(btCloseRename);
			});
		});
		
		specifiedTiltTypeTopPane.add(btRemoveTiltType, 2, 0);
		btRemoveTiltType.setOnAction(e -> {
			Alert alRemoveTiltType = new Alert(AlertType.CONFIRMATION);
			alRemoveTiltType.setTitle("Remove Tilt Type");
			alRemoveTiltType.setHeaderText(null);
			alRemoveTiltType.setContentText("Are you sure you want to remove the tilt type? This action will also delete the notes corresponding with the tilt type");
			Optional<ButtonType> optionSelected = alRemoveTiltType.showAndWait();
			if (optionSelected.get() == ButtonType.OK) {
				TiltTypeDao tiltTypeDao = new TiltTypeDaoImpl();
				tiltTypeDao.deleteTiltTypeById(tiltTypeID);
				
				printTiltTypes();
				theStage.setScene(tiltProfileScene);
			} else {
				return;
			}
		});
		
		//TextArea taDescribeProblem = new TextArea();
		taDescribeProblemTT.setPrefSize(350, 100);
		//TextArea taWhyLogical = new TextArea();
		taWhyLogicalTT.setPrefSize(350, 100);
		//TextArea taLogicFlawed = new TextArea();
		taLogicFlawedTT.setPrefSize(350, 100);
		//TextField tfPossibleSolutions = new TextField();
		//TextArea taWhySolutions = new TextArea();
		taWhySolutionsTT.setPrefSize(350, 100);
		Button btApplyChanges = new Button("Apply Changes");

		specifiedTiltTypeCenterPane.add(new Label("Describe Problem:"), 0, 0);
		specifiedTiltTypeCenterPane.add(taDescribeProblemTT, 1, 0);
		specifiedTiltTypeCenterPane.add(new Label("Why does it \n"
												+ "make logical sense \n"
												+ "to think that way?"), 0, 1);
		specifiedTiltTypeCenterPane.add(taWhyLogicalTT, 1, 1);
		specifiedTiltTypeCenterPane.add(new Label("Why is this \n"
												+ "logic flawed?"), 0, 2);
		specifiedTiltTypeCenterPane.add(taLogicFlawedTT, 1, 2);
		specifiedTiltTypeCenterPane.add(new Label("Possible solutions?"), 0, 3);
		specifiedTiltTypeCenterPane.add(tfPossibleSolutionsTT, 1, 3);
		specifiedTiltTypeCenterPane.add(new Label("Why is this \n"
												+ "a good solution?"), 0, 4);
		specifiedTiltTypeCenterPane.add(taWhySolutionsTT, 1, 4);
		specifiedTiltTypeCenterPane.add(btApplyChanges, 0, 5);
		btApplyChanges.setOnAction(e -> {			
			String describeProblem = taDescribeProblemTT.getText();
			String whyLogical = taWhyLogicalTT.getText();
			String logicFlawed = taLogicFlawedTT.getText();
			String possibleSolutions = tfPossibleSolutionsTT.getText();
			String whySolutions = taWhySolutionsTT.getText();

			TiltTypeDao tiltTypeDao = new TiltTypeDaoImpl();
			TiltType tiltType = new TiltType(describeProblem, whyLogical, logicFlawed, possibleSolutions, whySolutions);
			tiltTypeDao.updateTiltProfile(tiltType);
			
			printTiltTypes();
	    	Alert alApplyChanges = new Alert(AlertType.INFORMATION);
	    	alApplyChanges.setTitle("Apply Changes");
	    	alApplyChanges.setHeaderText(null);
	    	alApplyChanges.setContentText("The tilt type was updated.");
	    	alApplyChanges.showAndWait();
		});
		
		Button btAddProgreessNote = new Button("Add Progress Note");
		Label lblProgressNote = new Label("Add Progress Note");
		lblProgressNote.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Label lblDate = new Label("Date:");
		DatePicker dpDate = new DatePicker();
		Label lblUpdateNotes = new Label("Update \nNotes:");
		TextArea taUpdateNotes = new TextArea();
		taUpdateNotes.setPrefSize(350, 100);
		Button btAdd = new Button("Add");
		Button btCloseAdd = new Button("Close");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");
		
		specifiedTiltTypeBottomPane.add(btAddProgreessNote, 0, 0, 3, 1);
		btAddProgreessNote.setOnAction(e1 -> {
			specifiedTiltTypeBottomPane.add(lblProgressNote, 0, 1, 3, 1);
			specifiedTiltTypeBottomPane.add(lblDate, 0, 2);
			specifiedTiltTypeBottomPane.add(dpDate, 1, 2);
			dpDate.setValue(LocalDate.now());
			specifiedTiltTypeBottomPane.add(lblUpdateNotes, 0, 3);
			specifiedTiltTypeBottomPane.add(taUpdateNotes, 1, 3);
			specifiedTiltTypeBottomPane.add(btAdd, 0, 4);
			btAdd.setOnAction(e2 -> {
				LocalDate date = dpDate.getValue();
				String notes = taUpdateNotes.getText();
				
				if (date == null) {
			    	Alert alNotNullFields = new Alert(AlertType.ERROR);
			    	alNotNullFields.setTitle("Add Progress Note");
			    	alNotNullFields.setHeaderText(null);
			    	alNotNullFields.setContentText("Please enter information into the date field.");
			    	alNotNullFields.showAndWait();	
					return;
				}
				
				TiltProgressNoteDao tiltProgressNoteDao = new TiltProgressNoteDaoImpl();
				TiltProgressNote tiltProgressNote = new TiltProgressNote(tiltTypeID, date.toString(), notes);
				tiltProgressNoteDao.insertTiltProgressNote(tiltProgressNote);
		
		    	Alert alAddTiltProgressNote = new Alert(AlertType.INFORMATION);
		    	alAddTiltProgressNote.setTitle("Add Progress Note");
		    	alAddTiltProgressNote.setHeaderText(null);
		    	alAddTiltProgressNote.setContentText("Progress Note has been added.");
		    	alAddTiltProgressNote.showAndWait();
		    	printTiltProgressNotes();
		    	dpDate.setValue(null);
		    	taUpdateNotes.setText("");
			});
			
			specifiedTiltTypeBottomPane.add(btCloseAdd, 1, 4);
			btCloseAdd.setOnAction(e3 -> {
				specifiedTiltTypeBottomPane.getChildren().remove(lblProgressNote);
	    		specifiedTiltTypeBottomPane.getChildren().remove(lblDate);
				specifiedTiltTypeBottomPane.getChildren().remove(dpDate);
	    		specifiedTiltTypeBottomPane.getChildren().remove(lblUpdateNotes);
	    		specifiedTiltTypeBottomPane.getChildren().remove(taUpdateNotes);
	    		specifiedTiltTypeBottomPane.getChildren().remove(btAdd);
	    		specifiedTiltTypeBottomPane.getChildren().remove(btCloseAdd);
			});
		});
		specifiedTiltTypeBottomPane.add(btBack, 0, 5);
		btBack.setOnAction(e -> {
			theStage.setScene(tiltProfileScene);
			specifiedTiltTypeBottomPane.getChildren().remove(lblProgressNote);
    		specifiedTiltTypeBottomPane.getChildren().remove(lblDate);
			specifiedTiltTypeBottomPane.getChildren().remove(dpDate);
    		specifiedTiltTypeBottomPane.getChildren().remove(lblUpdateNotes);
    		specifiedTiltTypeBottomPane.getChildren().remove(taUpdateNotes);
    		specifiedTiltTypeBottomPane.getChildren().remove(btAdd);
    		specifiedTiltTypeBottomPane.getChildren().remove(btCloseAdd);
			specifiedTiltTypeTopPane.getChildren().remove(tfRenameTiltType);
			specifiedTiltTypeTopPane.getChildren().remove(btRename);
			specifiedTiltTypeTopPane.getChildren().remove(btCloseRename);
		});
		
		specifiedTiltTypeBottomPane.add(btMainMenu, 1, 5);
		btMainMenu.setOnAction(e -> {
			theStage.setScene(mainMenuScene);
			specifiedTiltTypeBottomPane.getChildren().remove(lblProgressNote);
    		specifiedTiltTypeBottomPane.getChildren().remove(lblDate);
			specifiedTiltTypeBottomPane.getChildren().remove(dpDate);
    		specifiedTiltTypeBottomPane.getChildren().remove(lblUpdateNotes);
    		specifiedTiltTypeBottomPane.getChildren().remove(taUpdateNotes);
    		specifiedTiltTypeBottomPane.getChildren().remove(btAdd);
    		specifiedTiltTypeBottomPane.getChildren().remove(btCloseAdd);
			specifiedTiltTypeTopPane.getChildren().remove(tfRenameTiltType);
			specifiedTiltTypeTopPane.getChildren().remove(btRename);
			specifiedTiltTypeTopPane.getChildren().remove(btCloseRename);
		});

		specifiedTiltTypeBorderPane.setTop(specifiedTiltTypeTopPane);
		specifiedTiltTypeBorderPane.setCenter(specifiedTiltTypeCenterPane);
		specifiedTiltTypeBorderPane.setRight(tiltProgressNotesScrollPane);
		specifiedTiltTypeBorderPane.setBottom(specifiedTiltTypeBottomPane);
		
		tiltProgressNotesScrollPane.setFitToWidth(true);
		tiltProgressNotesScrollPane.setPrefSize(250, 250);
		//tiltProgressNotesScrollPane.setMaxHeight(350);
		
		specifiedTiltTypeScene = new Scene(specifiedTiltTypeBorderPane, 750, 800);
		specifiedTiltTypeScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void specifiedTiltProgressNote() {
		GridPane specifiedTiltProgressNoteTopPane = new GridPane();
		specifiedTiltProgressNoteTopPane.setHgap(10);
		specifiedTiltProgressNoteTopPane.setVgap(10);
		specifiedTiltProgressNoteTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane specifiedTiltProgressNoteCenterPane = new GridPane();
		specifiedTiltProgressNoteCenterPane.setHgap(10);
		specifiedTiltProgressNoteCenterPane.setVgap(10);
		specifiedTiltProgressNoteCenterPane.setPadding(new Insets(10, 10, 10, 10));
		BorderPane specifiedTiltProgressNoteBorderPane = new BorderPane();
		
		lblSpecifiedTiltProgressNote.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

		specifiedTiltProgressNoteTopPane.add(lblSpecifiedTiltProgressNote, 0, 0);
		
		Button btApplyChanges = new Button("Apply Changes");
		Button btRemove = new Button("Remove");
		Button btBack = new Button("Back");
		Button btMainMenu = new Button("Main Menu");
		
		specifiedTiltProgressNoteCenterPane.add(new Label("Date:"), 0, 0);
		specifiedTiltProgressNoteCenterPane.add(dpDatePN, 1, 0);
		specifiedTiltProgressNoteCenterPane.add(new Label("Date:"), 0, 1);
		specifiedTiltProgressNoteCenterPane.add(taNotesPN, 1, 1);
		specifiedTiltProgressNoteCenterPane.add(btApplyChanges, 0, 2);
		btApplyChanges.setOnAction(e -> {
			LocalDate date = dpDatePN.getValue();
			String notes = taNotesPN.getText();

			TiltProgressNoteDao tiltProgressNoteDao = new TiltProgressNoteDaoImpl();
			TiltProgressNote tiltProgressNote = new TiltProgressNote(date.toString(), notes);
			tiltProgressNoteDao.updateTiltProgressNote(tiltProgressNote);
			
			printTiltProgressNotes();
	    	Alert alApplyChanges = new Alert(AlertType.INFORMATION);
	    	alApplyChanges.setTitle("Apply Changes");
	    	alApplyChanges.setHeaderText(null);
	    	alApplyChanges.setContentText("The tilt progress note was updated.");
	    	alApplyChanges.showAndWait();
		});
		
		specifiedTiltProgressNoteCenterPane.add(btRemove, 1, 2);
		btRemove.setOnAction(e -> {
			Alert alRemoveTiltType = new Alert(AlertType.CONFIRMATION);
			alRemoveTiltType.setTitle("Remove Tilt Type");
			alRemoveTiltType.setHeaderText(null);
			alRemoveTiltType.setContentText("Are you sure you want to remove the tilt type? This action will also delete the notes corresponding with the tilt type");
			Optional<ButtonType> optionSelected = alRemoveTiltType.showAndWait();
			if (optionSelected.get() == ButtonType.OK) {
				TiltProgressNoteDao tiltProgressNoteDao = new TiltProgressNoteDaoImpl();
				tiltProgressNoteDao.deleteTiltProgressNoteById(tiltProgressNoteID);
				
				printTiltProgressNotes();
				theStage.setScene(specifiedTiltTypeScene);
			} else {
				return;
			}
		});
		
		specifiedTiltProgressNoteCenterPane.add(btBack, 0, 3);
		btBack.setOnAction(e -> theStage.setScene(specifiedTiltTypeScene));

		specifiedTiltProgressNoteCenterPane.add(btMainMenu, 1, 3);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		specifiedTiltProgressNoteBorderPane.setTop(specifiedTiltProgressNoteTopPane);
		specifiedTiltProgressNoteBorderPane.setCenter(specifiedTiltProgressNoteCenterPane);
		
		specifiedTiltProgressNoteScene = new Scene(specifiedTiltProgressNoteBorderPane, 700, 600);
		specifiedTiltProgressNoteScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void statsSetup() {
		GridPane statsTopPane = new GridPane();
		statsTopPane.setHgap(10);
		statsTopPane.setVgap(10);
		statsTopPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane statsCenterPane = new GridPane();
		statsCenterPane.setHgap(10);
		statsCenterPane.setVgap(10);
		statsCenterPane.setPadding(new Insets(10, 10, 10, 10));
		GridPane statsPaneBottom = new GridPane();
		statsPaneBottom.setHgap(10);
		statsPaneBottom.setVgap(10);
		statsPaneBottom.setPadding(new Insets(10, 10, 10, 10));
		BorderPane statsBorderPane = new BorderPane();
		
		Label lblStats = new Label("Stats");
		lblStats.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		statsTopPane.add(lblStats, 0, 0);
		
		Label lblStartDate = new Label("Start Date:");
		DatePicker dpStartDate = new DatePicker();
		Label lblEndDate = new Label("End Date:");
		DatePicker dpEndDate = new DatePicker();
		
		ComboBox<String> cbMyCharacter = new ComboBox<String>();
		GeneralUtils.populateCharacterComboBoxStats(cbMyCharacter);
		cbMyCharacter.setValue("All Characters");
		ComboBox<String> cbOpponentCharacter = new ComboBox<String>();
		GeneralUtils.populateCharacterComboBoxStats(cbOpponentCharacter);
		cbOpponentCharacter.setValue("All Characters");
		ComboBox<String> cbType = new ComboBox<String>();
		cbType.getItems().addAll("All Types", "Tournament", "Money Match", "Friendly");
		cbType.setValue("All Types");
		ComboBox<String> cbFormat = new ComboBox<String>();
		cbFormat.getItems().addAll("All Formats", "Bo3", "Bo5");
		cbFormat.setValue("All Formats");
		ComboBox<String> cbDate = new ComboBox<String>();
		cbDate.getItems().addAll("All Time", "Specify Date");
		cbDate.setValue("All Time");
		Button btGetStats = new Button("Get Stats");
		
		ImageView ivBattlefield = new ImageView();
		ivBattlefield.setFitWidth(250);
		ivBattlefield.setFitHeight(175);
		ivBattlefield.setSmooth(true);
		ivBattlefield.setCache(true);
		ImageView ivDreamland = new ImageView();
		ivDreamland.setFitWidth(250);
		ivDreamland.setFitHeight(175);
		ivDreamland.setSmooth(true);
		ivDreamland.setCache(true);
		ImageView ivFountainOfDreams = new ImageView();
		ivFountainOfDreams.setFitWidth(250);
		ivFountainOfDreams.setFitHeight(175);
		ivFountainOfDreams.setSmooth(true);
		ivFountainOfDreams.setCache(true);
		ImageView ivYoshisStory = new ImageView();
		ivYoshisStory.setFitWidth(250);
		ivYoshisStory.setFitHeight(175);
		ivYoshisStory.setSmooth(true);
		ivYoshisStory.setCache(true);
		ImageView ivFinalDestination = new ImageView();
		ivFinalDestination.setFitWidth(250);
		ivFinalDestination.setFitHeight(175);
		ivFinalDestination.setSmooth(true);
		ivFinalDestination.setCache(true);
		ImageView ivPokemonStadium = new ImageView();
		ivPokemonStadium.setFitWidth(250);
		ivPokemonStadium.setFitHeight(175);
		ivPokemonStadium.setSmooth(true);
		ivPokemonStadium.setCache(true);

		//Label lblQueriedFor = new Label("Queried for:");
		Label lblWhatsQueried = new Label();
		Label lblSetCount = new Label();
	//	Label lblGameCount = new Label();
		Label lblGameCountBattlefield = new Label();
		Label lblWinPercentBattlefield = new Label();
		Label lblGameCountDreamland = new Label();
		Label lblWinPercentDreamland= new Label();
		Label lblGameCountFountainOfDreams = new Label();
		Label lblWinPercentFountainOfDreams = new Label();
		Label lblGameCountYoshisStory = new Label();
		Label lblWinPercentYoshisStory = new Label();
		Label lblGameCountFinalDestination = new Label();
		Label lblWinPercentFinalDestination = new Label();
		Label lblGameCountPokemonStadium = new Label();
		Label lblWinPercentPokemonStadium = new Label();
		Button btMainMenu = new Button("Main Menu");
		GridPane.setHalignment(lblWinPercentBattlefield, HPos.CENTER);
		GridPane.setHalignment(lblGameCountBattlefield, HPos.CENTER);
		GridPane.setHalignment(lblWinPercentDreamland, HPos.CENTER);
		GridPane.setHalignment(lblGameCountDreamland, HPos.CENTER);
		GridPane.setHalignment(lblWinPercentFountainOfDreams, HPos.CENTER);
		GridPane.setHalignment(lblGameCountFountainOfDreams, HPos.CENTER);
		GridPane.setHalignment(lblWinPercentYoshisStory, HPos.CENTER);
		GridPane.setHalignment(lblGameCountYoshisStory, HPos.CENTER);
		GridPane.setHalignment(lblWinPercentFinalDestination, HPos.CENTER);
		GridPane.setHalignment(lblGameCountFinalDestination, HPos.CENTER);
		GridPane.setHalignment(lblWinPercentPokemonStadium, HPos.CENTER);
		GridPane.setHalignment(lblGameCountPokemonStadium, HPos.CENTER);
		
		btGetStats.setOnAction(e -> {
			new Thread(new Runnable() {
				public void run() {
					String playerSql;
					String playerChoice = cbPlayersStats.getValue();
					if (playerChoice.equals("All Players")) {
						playerSql = "LIKE '%' OR tag IS NULL OR tag = ' '";
					} else {
						PlayerDao playerDao = new PlayerDaoImpl();
						int playerID = playerDao.getPlayerIdByTag(playerChoice);
						playerSql = "= " + playerID;
					}
					
					String myCharacterChoice = cbMyCharacter.getValue();
					String myCharacterSql;
					if (myCharacterChoice.equals("All Characters")) {
						myCharacterSql = "LIKE '%' OR name IS NULL OR name = ' '";
					} else {
						int myCharacterID = GeneralUtils.getCharacterID(myCharacterChoice);
						myCharacterSql = "= " + myCharacterID;
					}
					
					String opponentCharacterChoice = cbOpponentCharacter.getValue();
					String opponentCharacterSql;
					if (opponentCharacterChoice.equals("All Characters")) {
						opponentCharacterSql = "LIKE '%' OR name IS NULL OR name = ' '";
					} else {
						int opponentCharacterID = GeneralUtils.getCharacterID(opponentCharacterChoice);
						opponentCharacterSql = "= " + opponentCharacterID;
					}
					
					String formatChoice = cbFormat.getValue();
					String formatSql;
					if (formatChoice.equals("All Formats")) {
						formatSql = "LIKE '%' OR type IS NULL OR type = ' '";
					} else {
						formatSql = "= '" + formatChoice + "'";
					}
					
					String typeChoice = cbType.getValue();
					String typeSql;			
					if (typeChoice.equals("All Types")) {
						typeSql = "LIKE '%' OR format IS NULL OR format = ' '";
					} else {
						typeSql = "= '" + typeChoice + "'";
					}
					
					String dateChoice = cbDate.getValue();
					LocalDate startDate;
					LocalDate endDate;
					if (dateChoice.equals("Specify Date")) {
						startDate = dpStartDate.getValue();
						endDate = dpEndDate.getValue();
					} else {
						startDate = LocalDate.parse("2000-01-01");
						endDate = LocalDate.now();
					}
					String startDateString = startDate.toString();
					String endDateString = endDate.toString();

					StatDao statDao = new StatDaoImpl();
					int wonSetCount = statDao.calculateSetsWon(playerSql, formatSql, typeSql, startDateString, endDateString);
					int lostSetCount = statDao.calculateSetsLost(playerSql, formatSql, typeSql, startDateString, endDateString);
					int[] wonGames = statDao.calculateGamesWonOnStages(playerSql, myCharacterSql, opponentCharacterSql, formatSql, typeSql, startDateString, endDateString);
					int[] lostGames = statDao.calculateGamesLostOnStages(playerSql, myCharacterSql, opponentCharacterSql, formatSql, typeSql, startDateString, endDateString);
					
					String startDateFormatted = GeneralUtils.formatDate(startDateString);
					String endDateFormatted = GeneralUtils.formatDate(endDateString);
					
					Platform.runLater(new Runnable() {
						public void run() {
							if (dateChoice.equals("All Time")) {
								lblWhatsQueried.setText("Queried for: " + playerChoice + ", " + myCharacterChoice + ", " + opponentCharacterChoice 
										+ ", All Time, " + formatChoice + ", " + typeChoice);
							} else {
								lblWhatsQueried.setText("Queried for: " + playerChoice + ", " + myCharacterChoice + ", " + opponentCharacterChoice 
										+ ", " + startDateFormatted + " - " + endDateFormatted + ", " + formatChoice + ", " + typeChoice);				
							}
									
							if (!playerChoice.equals("All Players")) {
								lblSetCount.setText("Set Count Against " + playerChoice + ": " + wonSetCount + " - " + lostSetCount);
							} else {
								lblSetCount.setText("Total Set Count: " + wonSetCount + " - " + lostSetCount);
							}
							
							DecimalFormat df = new DecimalFormat("#.##%");
							ivBattlefield.setImage(new Image("main/assets/images/Battlefield.jpg"));
							lblGameCountBattlefield.setText(wonGames[BATTLEFIELD_STAGEID] + " - " + lostGames[BATTLEFIELD_STAGEID]);
							lblWinPercentBattlefield.setText(df.format((double) wonGames[BATTLEFIELD_STAGEID] / (wonGames[BATTLEFIELD_STAGEID] + lostGames[BATTLEFIELD_STAGEID])));
							
							ivDreamland.setImage(new Image("main/assets/images/Dreamland.png"));
							lblGameCountDreamland.setText(wonGames[DREAMLAND_STAGEID] + " - " + lostGames[DREAMLAND_STAGEID]);
							lblWinPercentDreamland.setText(df.format((double) wonGames[DREAMLAND_STAGEID] / (wonGames[DREAMLAND_STAGEID] + lostGames[DREAMLAND_STAGEID])));
							
							ivYoshisStory.setImage(new Image("main/assets/images/YoshisStory.png"));
							lblGameCountYoshisStory.setText(wonGames[YOSHIS_STORY_STAGEID] + " - " + lostGames[YOSHIS_STORY_STAGEID]);
							lblWinPercentYoshisStory.setText(df.format((double) wonGames[YOSHIS_STORY_STAGEID] / (wonGames[YOSHIS_STORY_STAGEID] + lostGames[YOSHIS_STORY_STAGEID])));
							
							ivFountainOfDreams.setImage(new Image("main/assets/images/FountainOfDreams.jpg"));
							lblGameCountFountainOfDreams.setText(wonGames[FOUNTAIN_OF_DREAMS_STAGEID] + " - " + lostGames[FOUNTAIN_OF_DREAMS_STAGEID]);
							lblWinPercentFountainOfDreams.setText(df.format((double) wonGames[FOUNTAIN_OF_DREAMS_STAGEID] / (wonGames[FOUNTAIN_OF_DREAMS_STAGEID] + lostGames[FOUNTAIN_OF_DREAMS_STAGEID])));
							
							ivFinalDestination.setImage(new Image("main/assets/images/FinalDestination.jpg"));
							lblGameCountFinalDestination.setText(wonGames[FINAL_DESTINATION_STAGEID] + " - " + lostGames[FINAL_DESTINATION_STAGEID]);
							lblWinPercentFinalDestination.setText(df.format((double) wonGames[FINAL_DESTINATION_STAGEID] / (wonGames[FINAL_DESTINATION_STAGEID] + lostGames[FINAL_DESTINATION_STAGEID])));
							
							ivPokemonStadium.setImage(new Image("main/assets/images/PokemonStadium.jpg"));
							lblGameCountPokemonStadium.setText(wonGames[POKEMON_STADIUM_STAGEID] + " - " + lostGames[POKEMON_STADIUM_STAGEID]);
							lblWinPercentPokemonStadium.setText(df.format((double) wonGames[POKEMON_STADIUM_STAGEID] / (wonGames[POKEMON_STADIUM_STAGEID] + lostGames[POKEMON_STADIUM_STAGEID])));
						}
					});
				}
			}).start();		
				
		});
		
		statsCenterPane.add(new Label("Player:"), 0, 0);
		statsCenterPane.add(cbPlayersStats, 1, 0);
		statsCenterPane.add(new Label("My Character:"), 0, 1);
		statsCenterPane.add(cbMyCharacter, 1, 1);
		statsCenterPane.add(new Label("Opponent Character:"), 0, 2);
		statsCenterPane.add(cbOpponentCharacter, 1, 2);
		statsCenterPane.add(new Label("Format:"), 2, 0);
		statsCenterPane.add(cbFormat, 3, 0);
		statsCenterPane.add(new Label("Type:"), 2, 1);
		statsCenterPane.add(cbType, 3, 1);
		statsCenterPane.add(new Label("Date:"), 4, 0);
		statsCenterPane.add(cbDate, 5, 0);
		cbDate.setOnAction(e -> {
			String dateOrAll = cbDate.getValue();
			if (dateOrAll.equals("Specify Date")) {
				statsCenterPane.add(lblStartDate, 4, 1);
				statsCenterPane.add(dpStartDate, 5, 1);
				statsCenterPane.add(lblEndDate, 4, 2);
				statsCenterPane.add(dpEndDate, 5, 2);
			} else {
				statsCenterPane.getChildren().remove(lblStartDate);
				statsCenterPane.getChildren().remove(dpStartDate);
				statsCenterPane.getChildren().remove(lblEndDate);
				statsCenterPane.getChildren().remove(dpEndDate);
			}
		});
		statsCenterPane.add(btGetStats, 0, 3);
		//statsCenterPane.add(lblQueriedFor, 0, 4);
		statsCenterPane.add(lblWhatsQueried, 0, 4, 6, 1);
		
		statsPaneBottom.add(lblSetCount, 0, 0);
		statsPaneBottom.add(ivBattlefield, 0, 1);
		statsPaneBottom.add(lblWinPercentBattlefield, 0, 2);
		statsPaneBottom.add(lblGameCountBattlefield, 0, 3);		
		statsPaneBottom.add(ivDreamland, 1, 1);
		statsPaneBottom.add(lblWinPercentDreamland, 1, 2);
		statsPaneBottom.add(lblGameCountDreamland, 1, 3);
		statsPaneBottom.add(ivFountainOfDreams, 2, 1);
		statsPaneBottom.add(lblWinPercentFountainOfDreams, 2, 2);
		statsPaneBottom.add(lblGameCountFountainOfDreams, 2, 3);

		statsPaneBottom.add(ivYoshisStory, 0, 4);
		statsPaneBottom.add(lblWinPercentYoshisStory, 0, 5);
		statsPaneBottom.add(lblGameCountYoshisStory, 0, 6);
		statsPaneBottom.add(ivFinalDestination, 1, 4);
		statsPaneBottom.add(lblWinPercentFinalDestination, 1, 5);
		statsPaneBottom.add(lblGameCountFinalDestination, 1, 6);
		statsPaneBottom.add(ivPokemonStadium, 2, 4);
		statsPaneBottom.add(lblWinPercentPokemonStadium, 2, 5);
		statsPaneBottom.add(lblGameCountPokemonStadium, 2, 6);
		statsPaneBottom.add(btMainMenu, 0, 8);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		statsBorderPane.setTop(statsTopPane);
		statsBorderPane.setCenter(statsCenterPane);
		statsBorderPane.setBottom(statsPaneBottom);
		
		statsScene = new Scene(statsBorderPane, 790, 800);
		statsScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void tournamentReportSetup() {
		GridPane tournamentReportPaneTop = new GridPane();
		tournamentReportPaneTop.setHgap(10);
		tournamentReportPaneTop.setVgap(10);
		tournamentReportPaneTop.setPadding(new Insets(10, 10, 10, 10));
		GridPane tournamentReportPaneBottom = new GridPane();
		tournamentReportPaneBottom.setHgap(10);
		tournamentReportPaneBottom.setVgap(10);
		tournamentReportPaneBottom.setPadding(new Insets(10, 10, 10, 10));
		BorderPane tournamentReportBorderPane = new BorderPane();
		
		Label lblTournamentReport = new Label("Tournament Report");
		lblTournamentReport.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		
		ComboBox<String> cbDate = new ComboBox<String>();
		cbDate.getItems().addAll("All Time", "Specify Date");
		cbDate.setValue("All Time");
		Label lblStartDate = new Label("Start Date:");
		DatePicker dpStartDate = new DatePicker();
		Label lblEndDate = new Label("End Date:");
		DatePicker dpEndDate = new DatePicker();
		Button btGetReport = new Button("Get Report");
		Button btMainMenu = new Button("Main Menu");
		ScrollPane tournamentReportScrollPane = new ScrollPane();
		
		tournamentReportPaneTop.add(lblTournamentReport, 0, 0);
		tournamentReportPaneTop.add(cbDate, 0, 2);
		cbDate.setOnAction(e -> {
			String date = cbDate.getValue();
			if (date.equals("Specify Date")) {
				tournamentReportPaneTop.add(lblStartDate, 0, 3);
				tournamentReportPaneTop.add(dpStartDate, 1, 3);
				tournamentReportPaneTop.add(lblEndDate, 0, 4);
				tournamentReportPaneTop.add(dpEndDate, 1, 4);
			} else {
				tournamentReportPaneTop.getChildren().remove(lblStartDate);
				tournamentReportPaneTop.getChildren().remove(dpStartDate);
				tournamentReportPaneTop.getChildren().remove(lblEndDate);
				tournamentReportPaneTop.getChildren().remove(dpEndDate);
			}
		});
		tournamentReportPaneTop.add(btGetReport, 0, 5);
		btGetReport.setOnAction(e -> {
			new Thread(new Runnable() {
				public void run() {
					String date = cbDate.getValue();
			    	VBox root = new VBox();
			    	MoneyDao moneyDao = new MoneyDaoImpl();
			    	List<Tournament> tournaments;
					TournamentDao tournamentDao = new TournamentDaoImpl();
			    	
					if (date.equals("Specify Date")) {	
						LocalDate startDate = dpStartDate.getValue();
						LocalDate endDate = dpEndDate.getValue();

						if (startDate == null || startDate.toString().isEmpty() || endDate == null || endDate.toString().isEmpty()) {
					    	Alert alSpecifyDate = new Alert(AlertType.INFORMATION);
					    	alSpecifyDate.setTitle("Specified Date");
					    	alSpecifyDate.setHeaderText(null);
					    	alSpecifyDate.setContentText("Be sure that the start date and end date fields are not empty.");
					    	alSpecifyDate.showAndWait();	
					    	return;
						}
						
						tournaments = tournamentDao.getTournamentsByDate(userID, startDate.toString(), endDate.toString());
					} else {
						tournaments = tournamentDao.getAllTournaments(userID);
					}
					
					int tournamentRowCount = tournaments.size();
					if (tournamentRowCount != 0) {
				    	SetDao setDao = new SetDaoImpl();
				    	PlayerDao playerDao = new PlayerDaoImpl();
				 		Hyperlink[] hlTournaments = new Hyperlink[tournamentRowCount];
				    	for (int i = 0; i < tournamentRowCount; i++) {
				 			if (i != 0) {
			       				root.getChildren().addAll(new Label());
				 			}

				 			tournamentID = tournaments.get(i).getTournamentID();
				 			String nameTournament = tournaments.get(i).getTournamentName();
				 			String dateTournament = tournaments.get(i).getDateOfTournament();
				 			String formattedDate = GeneralUtils.formatDate(dateTournament);
				 			
				 			hlTournaments[i] = new Hyperlink();
					 		hlTournaments[i].setText((i + 1) + ". " + nameTournament + " - " + formattedDate);
					 		hlTournaments[i].setFont(Font.font("Verdana", FontWeight.BOLD, 16));
			   				root.getChildren().addAll(hlTournaments[i]);
			   				
			   				//
					        int tournamentRecordLocation = tournaments.get(i).getTournamentID(); 			        		
				    	 	int myPlacingTournament = tournaments.get(i).getMyPlacing();
				    	 	String stateTournament = tournaments.get(i).getState();
				    	 	String cityTournament = tournaments.get(i).getCity();
				    	 	String notesTournament = tournaments.get(i).getNotes();			 
				    	 	
				        	Money money = moneyDao.getMoneyByTournamentId(tournamentRecordLocation);
				    	 	//int moneyRecordLocation = moneyDao.getMoneyIdByTournamentId(tournamentRecordLocation);
				    	 	int moneyRecordLocation = money.getMoneyID();
				        	String prizeMoneyMoney = Integer.toString(money.getPrizeMoney());
				        	String moneyMatchMoney = Integer.toString(money.getMoneyMatch());
				        	String entryFeeMoney = Integer.toString(money.getEntryFee());
				        	String venueFeeMoney = Integer.toString(money.getVenueFee());
				        	String travelExpenseMoney = Integer.toString(money.getTravelExpense());
					        hlTournaments[i].setOnAction(e -> {
					        	//setSpecifiedTournament()
		    					lastSceneID = TOURNAMENT_REPORT_SCENE;
					        	tournamentID = tournamentRecordLocation;
					        	lblSpecifiedTournament.setText(nameTournament);
					        	tfTournamentNameST.setText(nameTournament);
					    		tfMyPlacingST.setText(String.valueOf(myPlacingTournament));
					    		dpDateOfTournamentST.setValue(LocalDate.parse(dateTournament));
					    		tfStateST.setText(stateTournament);
					    		tfCityST.setText(cityTournament);
					    		taNotesST.setText(notesTournament);
					        	
					    		//setSpecifiedMoney()
					        	moneyID = moneyRecordLocation;
								lblSpecifiedMoneyDetails.setText(nameTournament + " - Money Details");
					    		tfPrizeMoneyM.setText(prizeMoneyMoney);
					        	tfMoneyMatchM.setText(moneyMatchMoney);
					        	tfEntryFeeM.setText(entryFeeMoney);
					        	tfVenueFeeM.setText(venueFeeMoney);
					        	tfTravelExpenseM.setText(travelExpenseMoney);
					    		
					        	printSets();
					        	theStage.setScene(specifiedTournamentScene);
							});
			   				
			   				List<Set> sets = setDao.getAllSetsByTournamentId(tournamentID);
					 		int setRowCount = sets.size();
					 		Hyperlink hlSets[] = new Hyperlink[setRowCount];
				 			for (int j = 0; j < setRowCount; j++) {
				 				hlSets[j] = new Hyperlink();
			    				String bracketRoundSet = sets.get(j).getBracketRound();
			    				String bracketRoundHeader = "";
			    		 		if (!bracketRoundSet.isEmpty() || !bracketRoundSet.trim().equals("")) {
			    		 			bracketRoundHeader = " - " + bracketRoundSet;
			    		 		}
			    				
			    				playerID = sets.get(j).getPlayerID();
			    				String tagSet = playerDao.getPlayerTagById(playerID);
			    				String outcomeSet = sets.get(j).getOutcome();
			    				
			    				hlSets[j].setText("\t• " + tagSet + " - " + bracketRoundHeader + " - " + outcomeSet);
			    				hlSets[j].setFont(Font.font("Verdana", 13));
			    				root.getChildren().addAll(hlSets[j]);
			    				
			    				//print sets code
			    				int setRecordLocation =  sets.get(i).getSetID();
			    		 		String typeSet = sets.get(i).getType();
			    		 		String formatSet = sets.get(i).getFormat();
			    		 		String notesSet = sets.get(i).getNotes();
			    		 		String headerSet = "Set " + (i + 1) + ": " + tagSet + bracketRoundHeader + " - " + outcomeSet;
			    				hlSets[i].setOnAction(e -> {
			    					lastSceneID = TOURNAMENT_REPORT_SCENE;
			    					lblSpecifiedSet.setText(headerSet);
			    			    	setID = setRecordLocation;
			    			    	playerDao.populatePlayerComboBox(cbPlayerSS, userID);
			    			    	printGames();
			    			    	
			    			    	//getSpecifiedSet();
			    					cbPlayerSS.setValue(tagSet);
			    					cbOutcomeSS.setValue(outcomeSet);
			    					cbTypeSS.setValue(typeSet);
			    					cbFormatSS.setValue(formatSet);
			    					tfBracketRoundSS.setText(bracketRoundSet);
			    					taNotesSS.setText(notesSet);
			    			    	
			    			    	theStage.setScene(specifiedSetScene);
			    				});
			    				//
				 			}
				 		}    	
					} else {
		   				root.getChildren().addAll(new Label("No tournaments with those specifications exist."));
					}
					
					Platform.runLater(new Runnable() {
						public void run() {
							tournamentReportScrollPane.setContent(root);
						}
					});
				}
			}).start();	
		});
		tournamentReportPaneBottom.add(btMainMenu, 0, 0);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		tournamentReportBorderPane.setTop(tournamentReportPaneTop);
		tournamentReportBorderPane.setCenter(tournamentReportScrollPane);
		tournamentReportBorderPane.setBottom(tournamentReportPaneBottom);
		
		tournamentReportScene = new Scene(tournamentReportBorderPane, 700, 600);
		tournamentReportScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void moneyReportSetup() {
		GridPane moneyReportPaneTop = new GridPane();
		moneyReportPaneTop.setHgap(10);
		moneyReportPaneTop.setVgap(10);
		moneyReportPaneTop.setPadding(new Insets(10, 10, 10, 10));
		GridPane moneyReportPaneBottom = new GridPane();
		moneyReportPaneBottom.setHgap(10);
		moneyReportPaneBottom.setVgap(10);
		moneyReportPaneBottom.setPadding(new Insets(10, 10, 10, 10));
		BorderPane moneyReportBorderPane = new BorderPane();
		
		Label lblMoneyReport = new Label("Money Report");
		lblMoneyReport.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		ComboBox<String> cbDate = new ComboBox<String>();
		cbDate.getItems().addAll("All Time", "Specify Date");
		cbDate.setValue("All Time");
		Label lblStartDate = new Label("Start Date:");
		DatePicker dpStartDate = new DatePicker();
		Label lblEndDate = new Label("End Date:");
		DatePicker dpEndDate = new DatePicker();
		Button btGetReport = new Button("Get Report");
		Label lblMoneyTotal = new Label("");
		Button btMainMenu = new Button("Main Menu");
		ScrollPane moneyReportScrollPane = new ScrollPane();
		
		moneyReportPaneTop.add(lblMoneyReport, 0, 0);
		moneyReportPaneTop.add(cbDate, 0, 2);
		cbDate.setOnAction(e -> {
			String date = cbDate.getValue();
			if (date.equals("Specify Date")) {
				moneyReportPaneTop.add(lblStartDate, 0, 3);
				moneyReportPaneTop.add(dpStartDate, 1, 3);
				moneyReportPaneTop.add(lblEndDate, 0, 4);
				moneyReportPaneTop.add(dpEndDate, 1, 4);
			} else {
				moneyReportPaneTop.getChildren().remove(lblStartDate);
				moneyReportPaneTop.getChildren().remove(dpStartDate);
				moneyReportPaneTop.getChildren().remove(lblEndDate);
				moneyReportPaneTop.getChildren().remove(dpEndDate);
			}
		});
		
		moneyReportPaneTop.add(btGetReport, 0, 5);
		btGetReport.setOnAction(e -> {
			new Thread(new Runnable() {
				public void run() {
					String date = cbDate.getValue();
			    	VBox root = new VBox();
			    	String totalMoneyString = null;
			    	MoneyDao moneyDao = new MoneyDaoImpl();
			    	List<Tournament> tournaments;
					TournamentDao tournamentDao = new TournamentDaoImpl();
			    	
					if (date.equals("Specify Date")) {	
						LocalDate startDate = dpStartDate.getValue();
						LocalDate endDate = dpEndDate.getValue();

						if (startDate == null || startDate.toString().isEmpty() || endDate == null || endDate.toString().isEmpty()) {
					    	Alert alSpecifyDate = new Alert(AlertType.INFORMATION);
					    	alSpecifyDate.setTitle("Specified Date");
					    	alSpecifyDate.setHeaderText(null);
					    	alSpecifyDate.setContentText("Be sure that the start date and end date fields are not empty.");
					    	alSpecifyDate.showAndWait();	
					    	return;
						}
						
						tournaments = tournamentDao.getTournamentsByDate(userID, startDate.toString(), endDate.toString());
					} else {
						tournaments = tournamentDao.getAllTournaments(userID);
					}
					
					//gotta appease that compiler
					
					int tournamentRowCount = tournaments.size();
					if (tournamentRowCount != 0) {
						DecimalFormat dfMoney = new DecimalFormat("$#.00");
						int prizeMoneyTotal = 0;
				 		int moneyMatchTotal = 0;
				 		int entryFeeTotal = 0;
				 		int venueFeeTotal = 0;
				 		int travelExpenseTotal = 0;
						Label lblMoneyHeader;
				 		Label lblMoney;
				 		Label[] lblTournaments = new Label[tournamentRowCount];
				    	for (int i = 0; i < tournamentRowCount; i++) {
				 			if (i != 0) {
			       				root.getChildren().addAll(new Label());
				 			} else if (i == 0) {
				 				//set headers
				 			}

				 			tournamentID = tournaments.get(i).getTournamentID();
				 			String nameTournament = tournaments.get(i).getTournamentName();
				 			String dateTournament = tournaments.get(i).getDateOfTournament();
				 			String formattedDate = GeneralUtils.formatDate(dateTournament);
				 			
				 			lblTournaments[i] = new Label();
				 			lblTournaments[i].setText((i + 1) + ". " + nameTournament + " - " + formattedDate);
				 			lblTournaments[i].setFont(Font.font("Verdana", FontWeight.BOLD, 16));
			   				root.getChildren().addAll(lblTournaments[i]);
			   				
				 			Money money = moneyDao.getMoneyByTournamentId(tournamentID);
				 			int prizeMoney = money.getPrizeMoney();
				 			int moneyMatch = money.getMoneyMatch();
				 			int entryFee = money.getEntryFee();
				 			int venueFee = money.getVenueFee();
				 			int travelExpense = money.getTravelExpense();
			   				prizeMoneyTotal += prizeMoney;
				 			moneyMatchTotal += moneyMatch;
				 			entryFeeTotal += entryFee;
				 			venueFeeTotal += venueFee;
				 			travelExpenseTotal += travelExpense;

				 			lblMoneyHeader = new Label("\tPrizeMoney\t Money Match\t Entry Fee\t Venue Fee\t Travel Expenses");
				 			lblMoney = new Label("\t" + dfMoney.format(prizeMoney) + "\t\t" + dfMoney.format(moneyMatch) + "\t\t" 
				 						+ dfMoney.format(entryFee) + "\t\t" + dfMoney.format(venueFee) + "\t\t" + dfMoney.format(travelExpense));
				 			root.getChildren().addAll(lblMoneyHeader, lblMoney);
				    	}
				    	
				    	totalMoneyString = "Total: " + dfMoney.format(prizeMoneyTotal) + "\t\t" + dfMoney.format(moneyMatchTotal) + "\t\t" 
								+ dfMoney.format(entryFeeTotal) + "\t\t" + dfMoney.format(venueFeeTotal) + "\t\t" 
								+ dfMoney.format(travelExpenseTotal);
						lblMoneyTotal.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
					} else {
		   				root.getChildren().addAll(new Label("No tournaments with those specifications exist."));
					}
					
					
			    	final String totalMoneyStringFinal = totalMoneyString;
					Platform.runLater(new Runnable() {
						public void run() {
							moneyReportScrollPane.setContent(root);
							lblMoneyTotal.setText(totalMoneyStringFinal);
						}
					});
				}
			}).start();
		});
		
		moneyReportPaneBottom.add(lblMoneyTotal, 0, 0);
		
		moneyReportPaneBottom.add(btMainMenu, 0, 1);
		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
		
		moneyReportBorderPane.setTop(moneyReportPaneTop);
		moneyReportBorderPane.setCenter(moneyReportScrollPane);
		moneyReportBorderPane.setBottom(moneyReportPaneBottom);
		
		moneyReportScene = new Scene(moneyReportBorderPane, 700, 600);
		moneyReportScene.getStylesheets().add("main/assets/stylesheets/stylesheet.css");
	}
	
	public void printTournaments() {
    	VBox root = new VBox();
    	MoneyDao moneyDao = new MoneyDaoImpl();
    	//List<Money> moneys = moneyDao.getAllMoneysByTournamentId(tournamentID);
 		TournamentDao tournamentDao = new TournamentDaoImpl();
 		List<Tournament> tournaments = tournamentDao.getAllTournaments(userID);
    	int rowCount = tournaments.size();
 		
    	if (rowCount != 0) {
			Hyperlink[] hlTournaments = new Hyperlink[tournaments.size()];
	 		for (int i = 0; i < rowCount; i++) {
	 			String tournamentName = tournaments.get(i).getTournamentName();
		 		String dateOfTournament = tournaments.get(i).getDateOfTournament();
		 		String formattedDate = GeneralUtils.formatDate(dateOfTournament);
		 		
		 		hlTournaments[i] = new Hyperlink(); 
		 		hlTournaments[i].setText((i + 1) + ". " + tournamentName + "   -   " + formattedDate);
				root.getChildren().addAll(hlTournaments[i]);
				
		        int tournamentRecordLocation = tournaments.get(i).getTournamentID(); 			        		
	    	 	int myPlacing = tournaments.get(i).getMyPlacing();
	    	 	String state = tournaments.get(i).getState();
	    	 	String city = tournaments.get(i).getCity();
	    	 	String notes = tournaments.get(i).getNotes();			 
	    	 	
	    	 	//getSpecifiedMoney
	        	Money money = moneyDao.getMoneyByTournamentId(tournamentRecordLocation);
	    	 	int moneyRecordLocation = money.getMoneyID();
	        	String prizeMoney = Integer.toString(money.getPrizeMoney());
	        	String moneyMatch = Integer.toString(money.getMoneyMatch());
	        	String entryFee = Integer.toString(money.getEntryFee());
	        	String venueFee = Integer.toString(money.getVenueFee());
	        	String travelExpense = Integer.toString(money.getTravelExpense());
		        hlTournaments[i].setOnAction(e -> {
		        	lastSceneID = MAIN_MENU_SCENE;
		        	
		        	//setSpecifiedTournament()
		        	tournamentID = tournamentRecordLocation;
		        	lblSpecifiedTournament.setText(tournamentName);
		        	tfTournamentNameST.setText(tournamentName);
		    		tfMyPlacingST.setText(String.valueOf(myPlacing));
		    		dpDateOfTournamentST.setValue(LocalDate.parse(dateOfTournament));
		    		tfStateST.setText(state);
		    		tfCityST.setText(city);
		    		taNotesST.setText(notes);
		        	
		    		//setSpecifiedMoney()
		        	moneyID = moneyRecordLocation;
					lblSpecifiedMoneyDetails.setText(tournamentName + " - Money Details");
		    		tfPrizeMoneyM.setText(prizeMoney);
		        	tfMoneyMatchM.setText(moneyMatch);
		        	tfEntryFeeM.setText(entryFee);
		        	tfVenueFeeM.setText(venueFee);
		        	tfTravelExpenseM.setText(travelExpense);
		    		
		        	printSets();
		        	theStage.setScene(specifiedTournamentScene);
				});
	 		}
    	} else if (rowCount == 0) {
    		root.getChildren().addAll(new Label("No tournaments were found"));
    	}
    	
		mainMenuScrollPane.setContent(root);
	}
	
//	public void printMoney() {
//    	MoneyDao moneyDao = new MoneyDaoImpl();
//    	Money money = moneyDao.getMoneyByTournamentId(tournamentID);
//    	
//    	String prizeMoney = Integer.toString(money.getPrizeMoney());
//    	String moneyMatch = Integer.toString(money.getMoneyMatch());
//    	String entryFee = Integer.toString(money.getEntryFee());
//    	String venueFee = Integer.toString(money.getVenueFee());
//    	String travelExpense = Integer.toString(money.getTravelExpense());
//    	
//    	tfPrizeMoneyM.setText(prizeMoney);
//    	tfMoneyMatchM.setText(moneyMatch);
//    	tfEntryFeeM.setText(entryFee);
//    	tfVenueFeeM.setText(venueFee);
//    	tfTravelExpenseM.setText(travelExpense);
//	}
		
	public void printSets() {
    	VBox root = new VBox();
 		PlayerDao playerDao = new PlayerDaoImpl();
    	SetDao setDao = new SetDaoImpl();
    	List<Set> sets = setDao.getAllSetsByTournamentId(tournamentID);
    	int rowCount = sets.size();
    	
    	if (rowCount != 0) {
			Hyperlink[] hlSets = new Hyperlink[rowCount];
	 		for (int i = 0; i < rowCount; i++) {
	 			playerID = sets.get(i).getPlayerID();
	 			String bracketRound = sets.get(i).getBracketRound();
	 			String bracketRoundHeader = "";
		 		if (!bracketRound.isEmpty() || !bracketRound.trim().equals("")) {
		 			bracketRoundHeader = " - " + bracketRound;
		 		}
		 		String outcome = sets.get(i).getOutcome();
		 		String tag = playerDao.getPlayerTagById(playerID);
		 		
		 		String setString = "Set " + (i + 1) + ": " + tag + bracketRoundHeader + " - " + outcome;
	 			hlSets[i] = new Hyperlink(); 
	 			hlSets[i].setText(setString);
				root.getChildren().addAll(hlSets[i]);

				int setRecordLocation =  sets.get(i).getSetID();
		 		String type = sets.get(i).getType();
		 		String format = sets.get(i).getFormat();
		 		String notes = sets.get(i).getNotes();
				hlSets[i].setOnAction(e -> {
					lastSceneID = SPECIFIED_SET_SCENE;
					lblSpecifiedSet.setText(setString);
			    	setID = setRecordLocation;
			    	playerDao.populatePlayerComboBox(cbPlayerSS, userID);
			    	printGames();
			    	
			    	//setSpecifiedSet();
					cbPlayerSS.setValue(tag);
					cbOutcomeSS.setValue(outcome);
					cbTypeSS.setValue(type);
					cbFormatSS.setValue(format);
					tfBracketRoundSS.setText(bracketRound);
					taNotesSS.setText(notes);
			    	
			    	theStage.setScene(specifiedSetScene);
				});
    		} 
    	} else {
			root.getChildren().addAll(new Label("No sets were found"));
		}

    	setScrollPane.setContent(root);
	}
	
	public void printGames() {
    	VBox root = new VBox();
    	GameDao gameDao = new GameDaoImpl();
    	List<Game> games = gameDao.getAllGamesBySetId(setID);
    	int rowCount = games.size();
    		 		
    	if (rowCount != 0) {
			Hyperlink[] hlGames = new Hyperlink[rowCount];
	 		for (int i = 0; i < rowCount; i++) {
	 			String outcome = games.get(i).getOutcome();
	 			
	 			String gameString = "Game " + (i + 1) + " - " + outcome;
		 		hlGames[i] = new Hyperlink(); 
	 			hlGames[i].setText(gameString);
				root.getChildren().addAll(hlGames[i]);

				int gameRecordLocation =  games.get(i).getGameID();
		 		int myCharacterID = games.get(i).getMyCharacterID();
		 		int opponentCharacterID = games.get(i).getOpponentCharacterID();
		 		int stageID = games.get(i).getStageID();
		 		String myCharacter = characters[myCharacterID];
		 		String opponentCharacter = characters[opponentCharacterID];
		 		String stage = stages[stageID];
        		hlGames[i].setOnAction(e -> {
        			lblSpecifiedGame.setText(gameString);
        			gameID = gameRecordLocation;
        			
        			//getSpecifiedGame();
        	 		cbOutcomeG.setValue(outcome);
        			cbMyCharacterG.setValue(myCharacter);
        			cbOpponentCharacterG.setValue(opponentCharacter);
        			cbStageG.setValue(stage);
        			
        			theStage.setScene(specifiedGameScene);
				});
	 		}
    	} else {
			root.getChildren().addAll(new Label("No games were found"));
		}
		
    	gameScrollPane.setContent(root);
	}

	public void printPlayers() {
    	VBox root = new VBox();
		PlayerDao playerDao = new PlayerDaoImpl();
		List<Player> players = playerDao.getAllPlayers(userID);
    	int rowCount = players.size();
 		
    	if (rowCount != 0) {
        	Hyperlink[] hlPlayers = new Hyperlink[rowCount];
    	 	for (int i = 0; i < rowCount; i++) {
    			String tag = players.get(i).getTag();
    			
     			hlPlayers[i] = new Hyperlink(); 
     			hlPlayers[i].setText((i + 1) + ". " + tag);
    			root.getChildren().addAll(hlPlayers[i]);

    			int playerRecordLocation = players.get(i).getPlayerID();
    			String notes = players.get(i).getNotes();
    	        hlPlayers[i].setOnAction(e -> {
    	        	lblSpecifiedPlayer.setText(tag);
    	        	playerID = playerRecordLocation;
    	        	
    	        	//getPlayers();
    	    		tfTagP.setText(tag);
    	    		taNotesP.setText(notes);
    	        	
    	        	theStage.setScene(specifiedPlayerScene);
    			});
     		}
    	} else {
    		root.getChildren().addAll(new Label("No players were found"));
    	}
    	
		playerScrollPane.setContent(root);
	}
	
	public void printCharacters() {
    	VBox root = new VBox();
    	//final int AMOUNT_OF_CHARACTERS = 26;
    	
		Hyperlink[] hlCharacters = new Hyperlink[characters.length];
 		for (int i = 1; i < hlCharacters.length; i++) {
 			String name = characters[i];
	 		
 			hlCharacters[i] = new Hyperlink(); 
 			hlCharacters[i].setText(name);
			root.getChildren().addAll(hlCharacters[i]);
			
			int characterRecordLocation = i; //database starts at 1
	        hlCharacters[i].setOnAction(e -> {
	        	lblSpecifiedCharacter.setText(name);
	        	characterID = characterRecordLocation;
	        	printCharacterNotes();
	        	theStage.setScene(specifiedCharacterScene);
			});
 		}

		characterScrollPane.setContent(root);
	}
	
	public void printCharacterNotes() {
    	VBox root = new VBox();
    	CharacterNoteDao characterNoteDao = new CharacterNoteDaoImpl();
    	List<CharacterNote> characterNotes = characterNoteDao.getAllCharacterNotesByCharacterId(userID, characterID);
    	int rowCount = characterNotes.size();

    	if (rowCount != 0) {
			Hyperlink[] hlCharacterNotes = new Hyperlink[rowCount];
	 		for (int i = 0; i < rowCount; i++) {
	 			String subject = characterNotes.get(i).getSubject();

	 			hlCharacterNotes[i] = new Hyperlink(); 
	 			hlCharacterNotes[i].setText((i + 1) + ". " + subject);
				root.getChildren().addAll(hlCharacterNotes[i]);
				
				int characterNoteRecordLocation = characterNotes.get(i).getCharacterNoteID();
				String notes = characterNotes.get(i).getNotes();
				hlCharacterNotes[i].setOnAction(e -> {
					lblSpecifiedCharacterNote.setText(subject);
					characterNoteID = characterNoteRecordLocation;
		        	
					//getCharacterNotes();
					tfSubjectCN.setText(subject);
					taNotesCN.setText(notes);
					
					theStage.setScene(specifiedCharacterNoteScene);
				});
	 		}
    	} else {
    		root.getChildren().addAll(new Label("No character notes were found"));
    	}
    	
 		specifiedCharacterNotesScrollPane.setContent(root);
	}
	
	public void printAnalysisNotes() {
		VBox root = new VBox();
		AnalysisNoteDao analysisNoteDao = new AnalysisNoteDaoImpl();
		List<AnalysisNote> analysisNotes = analysisNoteDao.getAllAnalysisNotes(userID);
    	int rowCount = analysisNotes.size();
    	
    	if (rowCount != 0) {
			Hyperlink[] hlAnalysisNotes = new Hyperlink[rowCount];
	 		for (int i = 0; i < hlAnalysisNotes.length; i++) {

	 			String subject = analysisNotes.get(i).getSubject();
	 			String focusPoints = analysisNotes.get(i).getFocusPoints();
	 			
	 			hlAnalysisNotes[i] = new Hyperlink(); 
	 			
	 			if (focusPoints.isEmpty()) {
	 				hlAnalysisNotes[i].setText((i + 1) + ". " + subject);
	 			} else {
	 				hlAnalysisNotes[i].setText((i + 1) + ". " + subject + " - " + focusPoints);
	 			}
	 				
	 			root.getChildren().addAll(hlAnalysisNotes[i]);
				
				int analysisNoteRecordLocation = analysisNotes.get(i).getAnalysisNoteID();
			 	String notes = analysisNotes.get(i).getNotes();
				hlAnalysisNotes[i].setOnAction(e -> {
					lblSpecifiedAnalysisNote.setText(subject);
					analysisNoteID = analysisNoteRecordLocation;
		        	
					//getAnalysisNotes();
					tfSubjectSA.setText(subject);
					tfFocusPointsSA.setText(focusPoints);
					taNotesSA.setText(notes);
		        	
		        	theStage.setScene(specifiedAnalysisNoteScene);
				});
	 		}
    	} else {
    		root.getChildren().addAll(new Label("No analysis notes were found"));
    	}
    	
 		analysisNotesScrollPane.setContent(root);
	}
	
	public void printTiltTypes() {
    	VBox root = new VBox();
    	TiltTypeDao tiltTypeDao = new TiltTypeDaoImpl();
    	List<TiltType> tiltTypes = tiltTypeDao.getAllTiltTypes(userID);
    	int rowCount = tiltTypes.size();
    	
    	if (rowCount != 0) {
			Hyperlink[] hlTiltTypes = new Hyperlink[rowCount];
	 		for (int i = 0; i < rowCount; i++) {
	 			String type = tiltTypes.get(i).getType();
		 		
	 			hlTiltTypes[i] = new Hyperlink(); 
	 			hlTiltTypes[i].setText(type);
				root.getChildren().addAll(hlTiltTypes[i]);
				
				int tiltTypeRecordLocation = tiltTypes.get(i).getTiltTypeID();
				String describeProblem = tiltTypes.get(i).getDescribeProblem(); 
				String whyLogical = tiltTypes.get(i).getWhyLogical();
				String logicFlawed = tiltTypes.get(i).getLogicFlawed();
				String possibleSolutions = tiltTypes.get(i).getPossibleSolutions();
				String whySolutions = tiltTypes.get(i).getWhySolutions();
				hlTiltTypes[i].setOnAction(e -> {
					lblSpecifiedTiltType.setText(type);
					tiltTypeID = tiltTypeRecordLocation;
					printTiltProgressNotes();
					
					//getSpecifiedTiltType();
					taDescribeProblemTT.setText(describeProblem);
					taWhyLogicalTT.setText(whyLogical);
					taLogicFlawedTT.setText(logicFlawed);
					tfPossibleSolutionsTT.setText(possibleSolutions);
					taWhySolutionsTT.setText(whySolutions);
					
		        	theStage.setScene(specifiedTiltTypeScene);
				});
	 		}	
    	} else {
    		root.getChildren().addAll(new Label("No tilt types were found"));
    	}

    	tiltTypeScrollPane.setContent(root);
    }
	
	public void printTiltProgressNotes() {
    	VBox root = new VBox();
    	TiltProgressNoteDao tiltProgressNoteDao = new TiltProgressNoteDaoImpl();
    	List<TiltProgressNote> tiltProgressNotes = tiltProgressNoteDao.getAllTiltProgressNotesByTiltTypeId(tiltTypeID);
    	int rowCount = tiltProgressNotes.size();
    	
    	TiltTypeDao tiltTypeDao = new TiltTypeDaoImpl();
    	TiltType tiltType = tiltTypeDao.getTiltTypeById(tiltTypeID);
    	String type = tiltType.getType();
    	
    	if (rowCount != 0) {	
			Hyperlink[] hlTiltProgressNotes = new Hyperlink[rowCount];
	 		for (int i = 0; i < rowCount; i++) {
		 		String date = tiltProgressNotes.get(i).getDate();
		 		
	 			hlTiltProgressNotes[i] = new Hyperlink(); 
	 			hlTiltProgressNotes[i].setText("Progress Note - " + GeneralUtils.formatDate(date));
				root.getChildren().addAll(hlTiltProgressNotes[i]);
				
				int tiltProgressNoteRecordLocation = tiltProgressNotes.get(i).getTiltProgressNoteID();
				String notes = tiltProgressNotes.get(i).getNotes();
				hlTiltProgressNotes[i].setOnAction(e -> {
					lblSpecifiedTiltProgressNote.setText(type + " - " + date);
					tiltProgressNoteID = tiltProgressNoteRecordLocation;
					
					//getSpecifiedTiltProgressNotes();
					dpDatePN.setValue(LocalDate.parse(date));
					taNotesPN.setText(notes);
					
					theStage.setScene(specifiedTiltProgressNoteScene);
				});
	 		}
    	} else {
    		root.getChildren().addAll(new Label("No progress notes were found"));
    	}
    	
 		tiltProgressNotesScrollPane.setContent(root);
	}
	
	public void setStage() {
		if (lastSceneID == SPECIFIED_SET_SCENE) {
			theStage.setScene(specifiedSetScene);
		} else if (lastSceneID == PLAYER_SCENE) {
			theStage.setScene(playerScene);
		} else if (lastSceneID == ADD_SET_SCENE) {
			theStage.setScene(addSetScene);
		} else if (lastSceneID == TOURNAMENT_REPORT_SCENE) {
			theStage.setScene(tournamentReportScene);
		} else if (lastSceneID == MAIN_MENU_SCENE) {
			theStage.setScene(mainMenuScene);
		} else {
			theStage.setScene(errorScene);
		}
	}
//    
//	public Button createMainMenuButton() {
//		Button btMainMenu = new Button("Main Menu");
//		btMainMenu.setOnAction(e -> theStage.setScene(mainMenuScene));
//		
//		return btMainMenu;
//	}
//    
//    
}
