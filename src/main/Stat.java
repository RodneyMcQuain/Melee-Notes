package main;

public class Stat {
	private String playerSql;
	private String myCharacterSql;
	private String opponenentCharacterSql;
	private String formatSql;
	private String typeSql;
	private String startDate;
	private String endDate;

	public Stat(String playerSql, String myCharacterSql, String opponenentCharacterSql, String formatSql, String typeSql, String startDate, String endDate) {
		this.playerSql = playerSql;
		this.myCharacterSql = myCharacterSql;
		this.opponenentCharacterSql = opponenentCharacterSql;
		this.formatSql = formatSql;
		this.typeSql = typeSql;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Stat(String playerSql, String formatSql, String typeSql, String startDate, String endDate) {
		this.playerSql = playerSql;
		this.formatSql = formatSql;
		this.typeSql = typeSql;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getPlayerSql() {
		return playerSql;
	}

	public void setPlayerSql(String playerSql) {
		this.playerSql = playerSql;
	}

	public String getMyCharacterSql() {
		return myCharacterSql;
	}

	public void setMyCharacterSql(String myCharacterSql) {
		this.myCharacterSql = myCharacterSql;
	}

	public String getOpponenentCharacterSql() {
		return opponenentCharacterSql;
	}

	public void setOpponenentCharacterSql(String opponenentCharacterSql) {
		this.opponenentCharacterSql = opponenentCharacterSql;
	}

	public String getFormatSql() {
		return formatSql;
	}

	public void setFormatSql(String formatSql) {
		this.formatSql = formatSql;
	}

	public String getTypeSql() {
		return typeSql;
	}

	public void setTypeSql(String typeSql) {
		this.typeSql = typeSql;
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
