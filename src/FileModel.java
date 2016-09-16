import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Fraser McIntosh on 16/09/2016.
 */
public class FileModel {

    static HashMap<WordFile, ArrayList<ArrayList<String>>> _fileMap = new HashMap<>();

    static private ArrayList<String> getLevelWords(WordFile file, int level) {
        return _fileMap.get(file).get(level);
    }
    public static void initialise() {
        createFiles();
        parseFiles();
    }

    // Helper method to create files that don't already exist
    private static void createFiles() {
        for (WordFile filename : WordFile.values()) {
            File f = new File(filename + "");
            if (!f.isFile()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * Helper method that parses the files and converts them into a more easily
     * read format. Need to parse everytime application is started
     * Coupled to format of text file
     */
    private static void parseFiles() {

        //Loop through every file
        for (WordFile filename : WordFile.values()) {
            File file = new File(filename + "");
            BufferedReader in = null;
            // file de-constructed into lists of levels
            ArrayList<ArrayList<String>> fileWords = new ArrayList<>();
            try {
                in = new BufferedReader(new FileReader(file + ""));

                String currentLine = in.readLine();

                // loop through till end of file
                while (currentLine != null) {
                    currentLine = in.readLine();

                    // construct levels between each $Level, relying on indexing to store lists in right location
                    ArrayList<String> level = new ArrayList<>();
                    while (currentLine.charAt(0) != '%') {
                        level.add(currentLine);
                    }
                    // add levels to construct file
                    fileWords.add(level);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            // put all files into a map
            _fileMap.put(filename, fileWords);
        }
    }

    /*
     * Clear all data from files
     */
    public static void clearFiles() {
        // Loop through each file and if it exists clear it
        for (WordFile filename : WordFile.values()) {
            File f = new File(filename + "");
            if (f.isFile()) {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(f);
                    // writer.print("");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    writer.close();
                }
            }
        }
    }


    public static void getWordsFromLevel(WordFile file, int level) {
    }

    /*
     * Helper method that returns all words from a level in a file selected
     */
    private static ArrayList<String> wordsFromLevel(WordFile file, int level) {
        BufferedReader in = null;
        ArrayList<String> words = new ArrayList<>();
        try {
            in = new BufferedReader(new FileReader(file + ""));

            String currentLine = in.readLine();
            String toFind = "%Level " + level;
            /*
             * Find level indicator in file
             */
            while (currentLine != toFind && currentLine != null) {
                currentLine = in.readLine();
            }

            toFind = "%Level " + (level + 1);
            while (currentLine != toFind && currentLine != null) {
                words.add(currentLine);
                currentLine = in.readLine();
            }
            // add all words after this

        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }


    public static void addUniqueWordToLevel(WordFile file, String word, int level) {
        if (!containsWordInLevel(file, word, level)) {
            addWordToLevel(file, word, level);
        }
    }

    public static void addWordToLevel(WordFile file, String word, int level) {
        getLevelWords(file, level).add(word);
    }


    /*
     * Returns a random word from a level in a given file
     */
    public static String randWordFromLevel(WordFile file, int level) {
        ArrayList<String> levelWords = getLevelWords(file, level);
        int index =  new Random().nextInt((levelWords.size())) + 1;
        return levelWords.get(index);
    }
    /*
     * returns whether a word is in a level
     */
    public static boolean containsWordInLevel(WordFile file, String word, int level) {
        return getLevelWords(file, level).contains(word);
    }


    /*
     * Removes word from list // need to make sure it keeps files in sync
     */
    public static void removeWordFromLevel(WordFile file, String word, int level) {
        getLevelWords(file, level).remove(word);
    }

    /*
     * returns how many matches for a word there are in a level of a file
     */
    public static int countOccurencesInLevel(WordFile file, String word, int level) {
        ArrayList<String> levelWords = getLevelWords(file, level);
        int count = 0;
        for (String s : levelWords) {
            if (s.equals(word)) {
                count++;
            }
        }

        return count;
    }
}