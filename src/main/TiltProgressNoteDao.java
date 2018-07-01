package main;
import java.util.List;

public interface TiltProgressNoteDao {
	public List<TiltProgressNote> getAllTiltProgressNotesByTiltTypeId(int tiltTypeID);
	public TiltProgressNote getTiltProgressNoteById(int tiltProgressNoteID);
	public void updateTiltProgressNote(TiltProgressNote tiltProgressNote);
	public void insertTiltProgressNote(TiltProgressNote tiltProgressNote);
	public void deleteTiltProgressNoteById(int tiltProgressNoteID);
}
