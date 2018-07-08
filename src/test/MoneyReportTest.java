package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import main.Money;
import main.MoneyDao;
import main.MoneyDaoImpl;
import main.Tournament;
import main.TournamentDao;
import main.TournamentDaoImpl;

class MoneyReportTest {
	@Test
	void moneyTotalsTest() {
		SQLiteTestData.deleteDatabaseFile();
		SQLiteTestData.buildEntireDatabase();
				
    	MoneyDao moneyDao = new MoneyDaoImpl();
		TournamentDao tournamentDao = new TournamentDaoImpl();
		final int USER_ID = 1;
		List<Tournament> tournaments = tournamentDao.getAllTournaments(USER_ID);

		int prizeMoneyTotal = 0;
 		int moneyMatchTotal = 0;
 		int entryFeeTotal = 0;
 		int venueFeeTotal = 0;
 		int travelExpenseTotal = 0;
 		int tournamentID = 0;
		int tournamentRowCount = tournaments.size();
		
		for (int i = 0; i < tournamentRowCount; i++) {
 			tournamentID = tournaments.get(i).getTournamentID();
			
 			Money money = moneyDao.getMoneyByTournamentId(tournamentID);
 			int prizeMoney = money.getPrizeMoney();
 			int moneyMatch = money.getMoneyMatch();
 			int entryFee = money.getEntryFee();
 			int venueFee = money.getVenueFee();
 			int travelExpense = money.getTravelExpense();
			
			prizeMoneyTotal += prizeMoney;
 			moneyMatchTotal += moneyMatch;
 			entryFeeTotal += entryFee;
 			venueFeeTotal += venueFee;
 			travelExpenseTotal += travelExpense;
		}
		
		assertEquals(155, prizeMoneyTotal);
		assertEquals(25, moneyMatchTotal);
		assertEquals(130, entryFeeTotal);
		assertEquals(110, venueFeeTotal);
		assertEquals(210, travelExpenseTotal);
	}
}
