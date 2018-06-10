
public class Player {
	private int playerID;
	private int userID; 
	private String tag; 
	private String notes;
	
	public Player(int playerID, int userID, String tag, String notes) {
		this.playerID = playerID;
		this.userID = userID;
		this.tag = tag;
		this.notes = notes;
	}
	
	public Player(int userID, String tag, String notes) {
		this.userID = userID;
		this.tag = tag;
		this.notes = notes;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
