import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Fraser McIntosh on 16/09/2016.
 */
public class FileModel {

    //Stores Words
    static HashMap<WordFile, HashMap<Integer, ArrayList<String>>> _fileMap = new HashMap<>();

    static private ArrayList<String> getLevelWords(WordFile file, int level) {
        HashMap<Integer, ArrayList<String>> fileWords =_fileMap.get(file);
        // Add logic to check that the file has been made
        if(fileWords.containsKey(level)) {
            return fileWords.get(level);
        } else {
            ArrayList<String> emptyList = new ArrayList<>();
            fileWords.put(level, emptyList);
            return getLevelWords(file, level);
        }
    }

    // Creates files that don't already exist and also parses files into _fileMape for
    // easy access during application
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
     * read format. Need to parse every time application is started
     * Coupled to format of text file
     */
    private static void parseFiles() {
        //Loop through every file
        for (WordFile filename : WordFile.values()) {
            File file = new File(filename + "");
            BufferedReader in = null;
            // file de-constructed into lists of levels
            HashMap<Integer, ArrayList<String>> fileWords = new HashMap<>();
            try {
                in = new BufferedReader(new FileReader(file + ""));

                String currentLine = in.readLine();

                // loop through till end of file

                // to keep track of the right level
                int level = 1;
                while (currentLine != null) {
                    currentLine = in.readLine();



                    // construct levels between each $Level, relying on indexing to store lists in right location
                    ArrayList<String> levelWords = new ArrayList<>();
                    while (currentLine != null && currentLine.charAt(0) != '%') {
                        System.out.println(currentLine);
                        levelWords.add(currentLine);
                        currentLine = in.readLine();
                    }


                    // add levels to construct file
                    fileWords.put(level, levelWords);
                    // next level's words
                    level++;


                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            // put all files into a map
            _fileMap.put(filename, fileWords);
        }
    }


    // Sync files with file map incase words have been added
    // that are not on file
    public static void SyncFile(WordFile filename) {
       //clear every file
        clearFiles();
        //Loop through every file
        File file = new File(filename + "");
        PrintWriter output;

        try {
            // make writer
            output = new PrintWriter(new FileWriter(file, true));
            HashMap<Integer, ArrayList<String>> fileWords =  _fileMap.get(filename);

            //loop through every file
            for(int level = 1; level <= fileWords.size(); level++){
                ArrayList<String> levelWords = fileWords.get(level);

                //write out level header
                output.println("%Level " + (level));

                //write each word
                for(String word : levelWords) {
                    output.println(word);
                }

            }
            output.close();

        } catch (IOException e){
            e.printStackTrace();
        }


    }
    /*
     * Clear all data from files
     */
    public static void clearFiles() {
        // Loop through each file and if it exists clear it
        for (WordFile filename : WordFile.values()) {
            // Don't want to clear the spelling list
            if(filename.equals(WordFile.SPELLING_LIST)) {
                continue;
            }
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


    /*
     * Get all the words in a level
     */


    /*
     * Helper method that returns all words from a level in a file selected
     */
    public static ArrayList<String> getWordsFromLevel(WordFile file, int level) {

//        BufferedReader in = null;
//        ArrayList<String> words = new ArrayList<>();
//
//        try {
//            in = new BufferedReader(new FileReader(file + ""));
//
//            String currentLine = in.readLine();
//            String toFind = "%Level " + level;
//            /*
//             * Find level indicator in file
//             */
//            while (currentLine != toFind && currentLine != null) {
//                currentLine = in.readLine();
//            }
//
//            toFind = "%Level " + (level + 1);
//            while (currentLine != toFind && currentLine != null) {
//                words.add(currentLine);
//                currentLine = in.readLine();
//            }
//            // add all words after this
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return words;

        return _fileMap.get(file).get(level);
    }



    public static void addUniqueWordToLevel(WordFile file, String word, int level) {
        if (!containsWordInLevel(file, word, level)) {
            addWordToLevel(file, word, level);
        }
        SyncFile(file);
    }

    public static void addWordToLevel(WordFile file, String word, int level) {
        getLevelWords(file, level).add(word);
        SyncFile(file);
    }


    /*
     * Returns a random word from a level in a given file
     */
    public static String randWordFromLevel(WordFile file, int level) {
        ArrayList<String> levelWords = getLevelWords(file, level);
        //check to make sure there are words
        if(levelWords.size() >0) {
            int index = new Random().nextInt((levelWords.size()));
            return levelWords.get(index);
        } else {
            // Shouldn't get to this point as should already have picked up if file is empty
            return "";
        }
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
        if(containsWordInLevel(file, word, level)) {
            getLevelWords(file, level).remove(word);
        }
        // Synce file with array
        SyncFile(file);
    }

    /*
     * returns how many matches for a word there are in a level of a file
     */
    public static int countOccurencesInLevel(WordFile file, String word, int level) {
        ArrayList<String> levelWords = getLevelWords(file, level);
        if(levelWords == null) {
            return 0;
        } else{
                int count = 0;
                for (String s : levelWords) {
                    if (s.equals(word)) {
                        count++;
                    }
                }
                return count;
            }
        }
}