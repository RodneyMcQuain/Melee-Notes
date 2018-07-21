package database;
import java.util.List;

import main.CharacterNote;

public interface CharacterNoteDao {
	public List<CharacterNote> getAllCharacterNotesByCharacterId(int userID, int characterID);
	public CharacterNote getCharacterNoteById(int characterNoteID);
	public void updateCharacterNote(CharacterNote characterNote);
	public void insertCharacterNote(CharacterNote characterNote);
	public void deleteCharacterNoteById(int characterNoteID);
}
