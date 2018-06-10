import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Settings implements Serializable {
    private static final long serialVersionUID = 1L;

	private String mainCharacter;
	private String theme;
	
	public Settings(String mainCharacter, String theme) {
		this.mainCharacter = mainCharacter;
		this.theme = theme;
	}
	
    public void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream("settings.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
    }

	public String getMainCharacter() {
		return mainCharacter;
	}

	public void setMainCharacter(String mainCharacter) {
		this.mainCharacter = mainCharacter;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	
}
