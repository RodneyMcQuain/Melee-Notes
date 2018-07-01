package main;
import java.util.List;

public interface AnalysisNoteDao {
	public List<AnalysisNote> getAllAnalysisNotes(int userID);
	public AnalysisNote getAnalysisNoteById(int analysisNoteID);
	public void updateAnalysisNote(AnalysisNote analysisNote);
	public void insertAnalysisNote(AnalysisNote analysisNote);
	public void deleteAnalysisNoteById(int analysisNoteID);
}
