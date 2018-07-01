package main;

public class Money {
	private int moneyID;
	private int tournamentID;
	private int prizeMoney;
	private int moneyMatch;
	private int entryFee;
	private int venueFee;
	private int travelExpense;
	
	public Money (int moneyID, int tournamentID, int prizeMoney, int moneyMatch, int entryFee, int venueFee, int travelExpense) {
		this.moneyID = moneyID;
		this.tournamentID = tournamentID;
		this.prizeMoney = prizeMoney;
		this.moneyMatch = moneyMatch;
		this.entryFee = entryFee;
		this.venueFee = venueFee;
		this.travelExpense = travelExpense;
	}

	public int getMoneyID() {
		return moneyID;
	}

	public void setMoneyID(int moneyID) {
		this.moneyID = moneyID;
	}

	public int getTournamentID() {
		return tournamentID;
	}

	public void setTournamentID(int tournamentID) {
		this.tournamentID = tournamentID;
	}

	public int getPrizeMoney() {
		return prizeMoney;
	}

	public void setPrizeMoney(int prizeMoney) {
		this.prizeMoney = prizeMoney;
	}

	public int getMoneyMatch() {
		return moneyMatch;
	}

	public void setMoneyMatch(int moneyMatch) {
		this.moneyMatch = moneyMatch;
	}

	public int getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}

	public int getVenueFee() {
		return venueFee;
	}

	public void setVenueFee(int venueFee) {
		this.venueFee = venueFee;
	}

	public int getTravelExpense() {
		return travelExpense;
	}

	public void setTravelExpense(int travelExpense) {
		this.travelExpense = travelExpense;
	}
}
