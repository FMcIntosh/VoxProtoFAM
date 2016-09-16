import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Fraser McIntosh on 16/09/2016.
 */
public class FileModel {

    static HashMap<WordFile, ArrayList<ArrayList<String>>> _fileMap = new HashMap<>();

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


    public static void addUniqueWord(WordFile file, String word) {
        if (!containsWord(file, word)) {
            addWord(file, word);
        }
    }

    public static void addWord(WordFile file, String word) {
        PrintWriter output;
        try {
            output = new PrintWriter(new FileWriter(file + "", true));
            output.println(word);
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean containsWord(WordFile file, String word) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file + ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String currentWord = in.readLine();
            while (currentWord != null) {
                if (word.equals(currentWord)) {
                    return true;
                }
                currentWord = in.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removeWord(WordFile file, String word) {
        // I got this method from http://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(file + "")));
            ArrayList<String> lines = new ArrayList<>();

            String lineToRemove = word;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) continue;
                lines.add(currentLine);
            }
            PrintWriter output = new PrintWriter(new FileWriter(file + ""));
            for (String s : lines) {
                output.println(s);
            }
            reader.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /*
     * returns how many matches for a word there are in a level of a file
     */
    public static int countOccurencesInLevel(WordFile file, String word, int level) {
        ArrayList<String> levelWords = _fileMap.get(file).get(level - 1);
        int count = 0;
        for (String s : levelWords) {
            if (s.equals(word)) {
                count++;
            }
        }

        return count;
    }
}