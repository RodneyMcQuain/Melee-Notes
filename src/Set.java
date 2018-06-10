
public class Set {
	private int setID;
	private int tournamentID;
	private int playerID;
	private String outcome;
	private String bracketRound;
	private String type;
	private String format;
	private String notes;

	public Set(int setID, int tournamentID, int playerID, String outcome, String bracketRound, String type, String format, String notes) {
		this.setID = setID;
		this.tournamentID = tournamentID;
		this.playerID = playerID;
		this.outcome = outcome;
		this.bracketRound = bracketRound;
		this.type = type;
		this.format = format;
		this.notes = notes;
	}
	
	public Set(int tournamentID, int playerID, String outcome, String bracketRound, String type, String format, String notes) {
		this.tournamentID = tournamentID;
		this.playerID = playerID;
		this.outcome = outcome;
		this.bracketRound = bracketRound;
		this.type = type;
		this.format = format;
		this.notes = notes;
	}

	public int getSetID() {
		return setID;
	}

	public void setSetID(int setID) {
		this.setID = setID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getTournamentID() {
		return tournamentID;
	}

	public void setTournamentID(int tournamentID) {
		this.tournamentID = tournamentID;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getBracketRound() {
		return bracketRound;
	}

	public void setBracketRound(String bracketRound) {
		this.bracketRound = bracketRound;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
