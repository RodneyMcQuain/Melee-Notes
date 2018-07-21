package database;
import java.util.List;

import main.TiltProgressNote;

public interface TiltProgressNoteDao {
	public List<TiltProgressNote> getAllTiltProgressNotesByTiltTypeId(int tiltTypeID);
	public TiltProgressNote getTiltProgressNoteById(int tiltProgressNoteID);
	public void updateTiltProgressNote(TiltProgressNote tiltProgressNote);
	public void insertTiltProgressNote(TiltProgressNote tiltProgressNote);
	public void deleteTiltProgressNoteById(int tiltProgressNoteID);
}
