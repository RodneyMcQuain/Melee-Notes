package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import main.Stat;
import main.StatDao;
import main.StatDaoImpl;

public class StatTest {

	@Test
	public void specifyAllSetsFieldsTest() {
		StatDao test = new StatDaoImpl();
		String playerSql = "= 1";
		String formatSql = "= 'Bo3'";
		String typeSql = "= 'Tournament'";
		String startDate = "2013-01-05";
		String endDate = "2017-01-05";
		Stat stat = new Stat(playerSql, formatSql, typeSql, startDate, endDate);
		
		int setsWon = test.calculateSetsWon(stat);
		int setsLost = test.calculateSetsLost(stat);

		assertEquals(3, setsWon);
		assertEquals(3, setsLost);
	}
	
	@Test
	public void allSetsFieldsTest() {
		StatDao test = new StatDaoImpl();
		String playerSql = "LIKE '%' OR tag IS NULL OR tag = ' '";
		String formatSql = "LIKE '%' OR type IS NULL OR type = ' '";
		String typeSql = "LIKE '%' OR format IS NULL OR format = ' '";
		String startDate = "2000-01-01";
		String endDate = LocalDate.now().toString();
		Stat stat = new Stat(playerSql, formatSql, typeSql, startDate, endDate);

		int setsWon = test.calculateSetsWon(stat);
		int setsLost = test.calculateSetsLost(stat);

		assertEquals(7, setsWon);
		assertEquals(8, setsLost);
	}

}
