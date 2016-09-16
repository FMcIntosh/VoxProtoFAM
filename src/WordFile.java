/**
 * Created by Fraser McIntosh on 16/09/2016.
 */
public enum WordFile {

    SPELLING_LIST("NZCER-spelling-lists.txt"),
    FAULTED(".faulted_stats.txt"),
    FAILED(".failed_stats.txt"),
    MASTERED(".mastered_stats.txt"),
    REVIEW(".reviewlist.txt"),
    ATTEMPTED(".faultedlist.txt");

    String _filename;
    private WordFile(String filename) {
        _filename = filename;
    }

    @Override
    public String toString() {
        return _filename;
    }
}
