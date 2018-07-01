package main;

public class TiltProgressNote {
	private int tiltProgressNoteID;
	private int tiltTypeID;
	private String date;
	private String notes;
	
	public TiltProgressNote (int tiltProgressNoteID, int tiltTypeID, String date, String notes) {
		this.tiltProgressNoteID = tiltProgressNoteID;
		this.tiltTypeID = tiltTypeID;
		this.date = date;
		this.notes = notes;
	}
	
	public TiltProgressNote (int tiltTypeID, String date, String notes) {
		this.tiltTypeID = tiltTypeID;
		this.date = date;
		this.notes = notes;
	}
	
	public TiltProgressNote (String date, String notes) {
		this.date = date;
		this.notes = notes;
	}

	public int getTiltProgressNoteID() {
		return tiltProgressNoteID;
	}

	public void setTiltProgressNoteID(int tiltProgressNoteID) {
		this.tiltProgressNoteID = tiltProgressNoteID;
	}

	public int getTiltTypeID() {
		return tiltTypeID;
	}

	public void setTiltTypeID(int tiltTypeID) {
		this.tiltTypeID = tiltTypeID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
