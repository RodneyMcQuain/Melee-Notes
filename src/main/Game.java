package main;

public class Game {
	private int gameID;
	private int setID;
	private int myCharacterID;
	private int opponentCharacterID;
	private int stageID;
	private String outcome;
	
	public Game(int gameID, int setID, int myCharacterID, int opponentCharacterID, int stageID, String outcome) {
		this.gameID = gameID;
		this.setID = setID;
		this.myCharacterID = myCharacterID;
		this.opponentCharacterID = opponentCharacterID;
		this.stageID = stageID;
		this.outcome = outcome;
	}
	
	public Game(int setID, int myCharacterID, int opponentCharacterID, int stageID, String outcome) {
		this.setID = setID;
		this.myCharacterID = myCharacterID;
		this.opponentCharacterID = opponentCharacterID;
		this.stageID = stageID;
		this.outcome = outcome;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getSetID() {
		return setID;
	}

	public void setSetID(int setID) {
		this.setID = setID;
	}

	public int getMyCharacterID() {
		return myCharacterID;
	}

	public void setMyCharacterID(int myCharacterID) {
		this.myCharacterID = myCharacterID;
	}

	public int getOpponentCharacterID() {
		return opponentCharacterID;
	}

	public void setOpponentCharacterID(int opponentCharacterID) {
		this.opponentCharacterID = opponentCharacterID;
	}

	public int getStageID() {
		return stageID;
	}

	public void setStageID(int stageID) {
		this.stageID = stageID;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
}
