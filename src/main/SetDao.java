package main;
import java.util.List;

public interface SetDao {
	public List<Set> getAllSetsByTournamentId(int tournamentID);
	public Set getSetById(int setID);
	public void updateSet(Set set);
	public void insertSet(Set set);
	public void deleteSetById(int setID);
}
