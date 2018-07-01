package main;
import java.util.List;

public interface CharacterNoteDao {
	public List<CharacterNote> getAllCharacterNotesByCharacterId(int userID, int characterID);
	public CharacterNote getCharacterNoteById(int characterNoteID);
	public void updateCharacterNote(CharacterNote characterNote);
	public void insertCharacterNote(CharacterNote characterNote);
	public void deleteCharacterNoteById(int characterNoteID);
}
