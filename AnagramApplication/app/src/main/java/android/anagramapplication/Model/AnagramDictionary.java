package android.anagramapplication.Model;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nathnael on 6/21/2017.
 */

public class AnagramDictionary implements AnagramDictionaryInterface {
    private List<String> words;//questions to be displayed within the Game Activity
    private Map<String, Set<String>> anagramCluster;// a set of anagrams will be mapped to a cluster



    @Override
    public String getWordToBeDisplayed() {
        return null;
    }

    @Override
    public boolean checkAnswer(String targetWord, String answer) {
        return false;
    }

    @Override
    public List<String> getAllPossibleAnagrams(String targetWord) {
        return null;
    }
}
