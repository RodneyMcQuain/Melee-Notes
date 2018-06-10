
public class AnalysisNote {
	private int analysisNoteID;
	private int userID;
	private String subject;
	private String focusPoints;
	private String notes;
	
	public AnalysisNote (int analysisNoteID, int userID, String subject, String focusPoints, String notes) {
		this.analysisNoteID = analysisNoteID;
		this.userID = userID;
		this.subject = subject;
		this.focusPoints = focusPoints;
		this.notes = notes;
	}
	
	public AnalysisNote (int userID, String subject, String focusPoints, String notes) {
		this.userID = userID;
		this.subject = subject;
		this.focusPoints = focusPoints;
		this.notes = notes;
	}

	public int getAnalysisNoteID() {
		return analysisNoteID;
	}

	public void setAnalysisNoteID(int analysisNoteID) {
		this.analysisNoteID = analysisNoteID;
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

	public String getFocusPoints() {
		return focusPoints;
	}

	public void setFocusPoints(String focusPoints) {
		this.focusPoints = focusPoints;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
