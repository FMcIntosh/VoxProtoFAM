package app.process;

/**
 * Created by Fraser McIntosh on 21/09/2016.
 */
/**
 * Created by Fraser McIntosh on 17/09/2016.
 */
public class FestivalStub {

    //Not sure what the difference in these two methods is?

    //Says aloud the given word
    public static void sayWord(String word){
        System.out.println(word);
    }

    //Says aloud the given word
    public static void spellWord(String word){
        word = word.replaceAll(".(?!$)", "$0 ");
        System.out.println(word);
    }

}
