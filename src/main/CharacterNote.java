package main;

public class CharacterNote {
	private int characterNoteID;
	private int characterID;
	private int userID;
	private String subject;
	private String notes;
	
	public CharacterNote (int characterNoteID, int characterID, int userID, String subject, String notes) {
		this.characterNoteID = characterNoteID;
		this.characterID = characterID;
		this.userID = userID;
		this.subject = subject;
		this.notes = notes;
	}
	
	public CharacterNote (int characterID, int userID, String subject, String notes) {
		this.characterID = characterID;
		this.userID = userID;
		this.subject = subject;
		this.notes = notes;
	}

	public int getCharacterNoteID() {
		return characterNoteID;
	}

	public void setCharacterNoteID(int characterNoteID) {
		this.characterNoteID = characterNoteID;
	}

	public int getCharacterID() {
		return characterID;
	}

	public void setCharacterID(int characterID) {
		this.characterID = characterID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
