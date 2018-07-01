package main;
import java.util.List;

public interface TiltTypeDao {
	public List<TiltType> getAllTiltTypes(int userID);
	public TiltType getTiltTypeById(int tiltTypeID);
	public void updateTiltName(TiltType tiltType);
	public void updateTiltProfile(TiltType tiltType);
	public void insertTiltType(TiltType tiltType);
	public void deleteTiltTypeById(int tiltTypeID);
}
