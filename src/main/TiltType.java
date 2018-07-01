package main;

public class TiltType {
	private int tiltTypeID;
	private int userID;
	private String type;
	private String describeProblem;
	private String whyLogical;
	private String logicFlawed;
	private String possibleSolutions;
	private String whySolutions;
	
	public TiltType (int tiltTypeID, int userID, String type, String describeProblem, String whyLogical, String logicFlawed, String possibleSolutions, String whySolutions) {
		this.tiltTypeID = tiltTypeID;
		this.userID = userID;
		this.type = type;
		this.describeProblem = describeProblem;
		this.whyLogical = whyLogical;
		this.logicFlawed = logicFlawed;
		this.logicFlawed = possibleSolutions;
		this.logicFlawed = whySolutions;
	}
	
	public TiltType (int userID, String type, String describeProblem, String whyLogical, String logicFlawed, String possibleSolutions, String whySolutions) {
		this.userID = userID;
		this.type = type;
		this.describeProblem = describeProblem;
		this.whyLogical = whyLogical;
		this.logicFlawed = logicFlawed;
		this.possibleSolutions = possibleSolutions;
		this.whySolutions = whySolutions;
	}
	
	public TiltType (int userID, String type) {
		this.userID = userID;
		this.type = type;
	}

	public TiltType (String describeProblem, String whyLogical, String logicFlawed, String possibleSolutions, String whySolutions) {
		this.describeProblem = describeProblem;
		this.whyLogical = whyLogical;
		this.logicFlawed = logicFlawed;
		this.possibleSolutions = possibleSolutions;
		this.whySolutions = whySolutions;
	}

	
	public int getTiltTypeID() {
		return tiltTypeID;
	}

	public void setTiltTypeID(int tiltTypeID) {
		this.tiltTypeID = tiltTypeID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescribeProblem() {
		return describeProblem;
	}

	public void setDescribeProblem(String describeProblem) {
		this.describeProblem = describeProblem;
	}

	public String getWhyLogical() {
		return whyLogical;
	}

	public void setWhyLogical(String whyLogical) {
		this.whyLogical = whyLogical;
	}

	public String getLogicFlawed() {
		return logicFlawed;
	}

	public void setLogicFlawed(String logicFlawed) {
		this.logicFlawed = logicFlawed;
	}

	public String getPossibleSolutions() {
		return possibleSolutions;
	}

	public void setPossibleSolutions(String possibleSolutions) {
		this.possibleSolutions = possibleSolutions;
	}

	public String getWhySolutions() {
		return whySolutions;
	}

	public void setWhySolutions(String whySolutions) {
		this.whySolutions = whySolutions;
	}
}