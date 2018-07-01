package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.StatDao;
import main.StatDaoImpl;

class StatTest {

	@Test
	void specifyAllSetsFieldsTest() {
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

}
