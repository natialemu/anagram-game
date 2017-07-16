package android.anagramapplication.Model;

import java.util.List;

/**
 * Created by Nathnael on 6/21/2017.
 */

public interface AnagramDictionaryInterface {
    //gets the right words to start off with
    String getWordToBeDisplayed();

    boolean checkAnswer(String targetWord, String answer);

    List<String> getAllPossibleAnagrams(String targetWord);
    void mapWord(String word);

    //checks if the user got the right answer

    // a method that returns a list of all possible solutions
}
