package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import main.StatDao;
import main.StatDaoImpl;

class StatTest {

	@Test
	void specifyAllSetsFieldsTest() {
		SQLiteTestData.deleteDatabaseFile();
		SQLiteTestData.buildEntireDatabase();

		StatDao test = new StatDaoImpl();
		String playerSql = "= 1";
		String formatSql = "= 'Bo3'";
		String typeSql = "= 'Tournament'";
		String startDate = "2013-01-05";
		String endDate = "2017-01-05";
		int setsWon = test.calculateSetsWon(playerSql, formatSql, typeSql, startDate, endDate);
		int setsLost = test.calculateSetsLost(playerSql, formatSql, typeSql, startDate, endDate);

		assertEquals(3, setsWon);
		assertEquals(3, setsLost);
	}
	
	@Test
	void allSetsFieldsTest() {
		SQLiteTestData.deleteDatabaseFile();
		SQLiteTestData.buildEntireDatabase();

		StatDao test = new StatDaoImpl();
		String playerSql = "LIKE '%' OR tag IS NULL OR tag = ' '";
		String formatSql = "LIKE '%' OR type IS NULL OR type = ' '";
		String typeSql = "LIKE '%' OR format IS NULL OR format = ' '";
		String startDate = "2000-01-01";
		String endDate = LocalDate.now().toString();

		int setsWon = test.calculateSetsWon(playerSql, formatSql, typeSql, startDate, endDate);
		int setsLost = test.calculateSetsLost(playerSql, formatSql, typeSql, startDate, endDate);

		assertEquals(7, setsWon);
		assertEquals(8, setsLost);
	}

}
