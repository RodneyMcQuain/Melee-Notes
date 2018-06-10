import java.util.List;

import javafx.scene.control.ComboBox;

public interface PlayerDao {
	public List<Player> getAllPlayers(int userID);
	public Player getPlayerById(int playerID);
	public String getPlayerTagById(int playerID);
	public int getPlayerIdByTag(String tag);
	public void populatePlayerComboBox(ComboBox<String> playerComboBox, int userID);
	public void populatePlayerComboBoxStats(ComboBox<String> playerComboBox, int userID);
	public void updatePlayer(Player player);
	public void insertPlayer(Player player);
	public void deletePlayerById(int playerID);
}
