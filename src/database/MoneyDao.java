package database;
import java.util.List;

import main.Money;

public interface MoneyDao {
	public List<Money> getAllMoneysByTournamentId(int tournamentID);
	public Money getMoneyById(int moneyID);
	public int getMoneyIdByTournamentId(int tournamentID);
	public Money getMoneyByTournamentId(int tournamentID);
	public void updateMoney(Money money);
	public void insertMoneyByTournamentId(int tournamentID);
//	public void deleteMoneyById(int moneyID);
}
