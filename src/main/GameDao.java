package main;
import java.util.List;

public interface GameDao {
	public List<Game> getAllGamesBySetId(int setID);
	public Game getGameById(int gameID);
	public void updateGame(Game game);
	public void insertGame(Game game);
	public void deleteGameById(int gameID);
	public int calculateGamesWon(int setID);
	public int calculateGamesLost(int setID);
}
