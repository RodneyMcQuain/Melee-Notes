
public class Stat {
	private String playerChoice;
	private String myCharacterChoice;
	private String opponenentCharacterChoice;
	private String formatChoice;
	private String typeChoice;
	private String startDate;
	private String endDate;

	public Stat(String playerChoice, String myCharacterChoice, String opponenentCharacterChoice, String formatChoice, String typeChoice, String startDate, String endDate) {
		this.playerChoice = playerChoice;
		this.myCharacterChoice = myCharacterChoice;
		this.opponenentCharacterChoice = opponenentCharacterChoice;
		this.formatChoice = formatChoice;
		this.typeChoice = typeChoice;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getPlayerChoice() {
		return playerChoice;
	}

	public void setPlayerChoice(String playerChoice) {
		this.playerChoice = playerChoice;
	}

	public String getMyCharacterChoice() {
		return myCharacterChoice;
	}

	public void setMyCharacterChoice(String myCharacterChoice) {
		this.myCharacterChoice = myCharacterChoice;
	}

	public String getOpponenentCharacterChoice() {
		return opponenentCharacterChoice;
	}

	public void setOpponenentCharacterChoice(String opponenentCharacterChoice) {
		this.opponenentCharacterChoice = opponenentCharacterChoice;
	}

	public String getFormatChoice() {
		return formatChoice;
	}

	public void setFormatChoice(String formatChoice) {
		this.formatChoice = formatChoice;
	}

	public String getTypeChoice() {
		return typeChoice;
	}

	public void setTypeChoice(String typeChoice) {
		this.typeChoice = typeChoice;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
