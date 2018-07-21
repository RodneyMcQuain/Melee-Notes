package database;
import java.util.List;

import main.TiltType;

public interface TiltTypeDao {
	public List<TiltType> getAllTiltTypes(int userID);
	public TiltType getTiltTypeById(int tiltTypeID);
	public void updateTiltName(TiltType tiltType);
	public void updateTiltProfile(TiltType tiltType);
	public void insertTiltType(TiltType tiltType);
	public void deleteTiltTypeById(int tiltTypeID);
}
