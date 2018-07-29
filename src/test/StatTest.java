package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.Test;

import database.StatDao;
import database.StatDaoImpl;

import static org.junit.Assert.assertEquals;

import main.Stat;

public class StatTest {

	@Test
	public void specifiedSetsFieldsWonTest() {
		Stat stat = getSpecifiedSets();
		StatDao test = new StatDaoImpl();
		int setsWon = test.calculateSetsWon(stat);

		assertEquals(3, setsWon);
	}
	
	@Test
	public void specifiedSetsFieldsLostTest() {
		Stat stat = getSpecifiedSets();
		StatDao test = new StatDaoImpl();
		int setsLost = test.calculateSetsLost(stat);

		assertEquals(3, setsLost);
	}
	
	private Stat getSpecifiedSets() {
		String playerSql = "= 1";
		String formatSql = "= 'Bo3'";
		String typeSql = "= 'Tournament'";
		String startDate = "2013-01-05";
		String endDate = "2017-01-05";
		Stat stat = new Stat(playerSql, formatSql, typeSql, startDate, endDate);
		
		return stat;
	}
	
	@Test
	public void allSetsFieldsWonTest() {
		Stat stat = getAllSets();
		StatDao test = new StatDaoImpl();
		int setsWon = test.calculateSetsWon(stat);

		assertEquals(7, setsWon);
	}
	
	@Test
	public void allSetsFieldsLostTest() {
		Stat stat = getAllSets();
		StatDao test = new StatDaoImpl();
		int setsLost = test.calculateSetsLost(stat);

		assertEquals(8, setsLost);
	}

	private Stat getAllSets() {
		String playerSql = "LIKE '%' OR tag IS NULL OR tag = ' '";
		String formatSql = "LIKE '%' OR type IS NULL OR type = ' '";
		String typeSql = "LIKE '%' OR format IS NULL OR format = ' '";
		String startDate = "2000-01-01";
		String endDate = LocalDate.now().toString();
		Stat stat = new Stat(playerSql, formatSql, typeSql, startDate, endDate);
		
		return stat;
	}
}
