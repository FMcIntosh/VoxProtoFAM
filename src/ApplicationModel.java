

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ApplicationModel {

	private static Boolean _isFirstTime;
	private static int _levelsUnlocked;
	private static String _voice;
	
	private static QuizModel _quizModel;

	public ApplicationModel(){
		setup();
	}

	/*
	 * Reads in the 3 settings values from .settings.txt file. 
	 * These values will persist even if the application is closed.
	 */
	private void setup(){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(".settings.txt"));
			_isFirstTime = Boolean.parseBoolean(reader.readLine());
			_levelsUnlocked = Integer.parseInt(reader.readLine());
			_voice = reader.readLine();
			reader.close();
		}catch(FileNotFoundException e){
			//worth creating an alert box to inform user that .setting.txt file is missing?
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Getter methods
	public static int getLevelsUnlocked(){
		return _levelsUnlocked;
	}
	public static Boolean isItFirstTime(){
		return _isFirstTime;
	}
	public static String getVoice(){
		return _voice;
	}
	public static QuizModel getQuizModel(){
		return _quizModel;
	}

	//Setter methods
	public static void setLevelsUnlocked(int value) throws FileNotFoundException{
		_levelsUnlocked = value;
		updateTxtFile();
	}
	public static void setNotFirstTime() throws FileNotFoundException{
		_isFirstTime = false;
		updateTxtFile();
	}
	public static void setVoice(String voice) throws FileNotFoundException{
		_voice = voice;
		updateTxtFile();
	}
	public static void setQuizModel(boolean isReview, int levelSelected){
		QuizModel _quizModel = new QuizModel(isReview, levelSelected);
	}
	public static void setToDefault() throws FileNotFoundException{
		_isFirstTime = true;
		_levelsUnlocked = 0;
		_voice = "default";
		updateTxtFile();
	}
	
	//Overwrites .settings.txt file with updated field values 
	public static void updateTxtFile() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(".settings.txt");
		writer.println(_isFirstTime.toString());
		writer.println(_levelsUnlocked);
		writer.println(_voice);
		writer.close();
	}

}
