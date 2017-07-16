package android.anagramapplication;

import android.anagramapplication.Model.AnagramDictionary;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * Created by Nathnael on 6/21/2017.
 */
public class AnagramDictionaryTest {
    AnagramDictionary dictionary;
    @Before
    public void setUp() throws Exception {
        dictionary = AnagramDictionary.getDictionary();


    }

    @Test
    public void getWordsToBeDisplayedTest(){
        //the words should be five
        String word = dictionary.getWordToBeDisplayed();
        assert (word.length() > 1);
        assert(dictionary.getAllPossibleAnagrams(word).size() >= 5);
        //check answer takes in a target string and an answer. use the googe examples. including edge case
    }

    @Test
    public void checkAnswerTest(){
        String target = "badge";//make sure that case doesnt matter
        List<String> solutions = new ArrayList<>();
        solutions.add("bagged");
        solutions.add("gabbed");
        solutions.add("banged");
        solutions.add("bodega");
        solutions.add("barged");
        solutions.add("garbed");

        List<String> wrongAnswers = new ArrayList<>();

        wrongAnswers.add("badges");
        wrongAnswers.add("pbadge");
        wrongAnswers.add("gable");
        wrongAnswers.add("dadeg");

        assert(dictionary.checkAnswer(target,solutions.get(0)));
        assert(dictionary.checkAnswer(target,solutions.get(1)));
        assert(dictionary.checkAnswer(target,solutions.get(2)));
        assert(dictionary.checkAnswer(target,solutions.get(3)));
        assert(dictionary.checkAnswer(target,solutions.get(4)));
        assert(dictionary.checkAnswer(target,solutions.get(5)));


        assertFalse(dictionary.checkAnswer(target,wrongAnswers.get(0)));
        assertFalse(dictionary.checkAnswer(target,wrongAnswers.get(1)));
        assertFalse(dictionary.checkAnswer(target,wrongAnswers.get(2)));
        assertFalse(dictionary.checkAnswer(target,wrongAnswers.get(3)));


    }

    @Test
    public void getAllPossibleAnagramsTest(){
        //get a simple word
        //find all possible anagrams with your hand
        //then get all possible anagrams of that word with the method
        //make sure that the two lists are the same
        String targetWord = "ad";
        List<String> allAnagrams = new ArrayList<>();
        assert (dictionary.getAnagramCluster().containsKey("abd"));
        allAnagrams.add("dab");
        assert (dictionary.getAnagramCluster().containsKey("adi"));
        allAnagrams.add("aid");
        assert (dictionary.getAnagramCluster().containsKey("adm"));
        allAnagrams.add("dam");
        assert (dictionary.getAnagramCluster().containsKey("adn"));
        allAnagrams.add("and");
        assert (dictionary.getAnagramCluster().containsKey("ady"));
        allAnagrams.add("day");
        Collections.sort(allAnagrams);
        List<String> anagramsTobeTested = dictionary.getAllPossibleAnagrams("ad");


        assertEquals(anagramsTobeTested.size(),5);
        assert (anagramsTobeTested.contains("dab"));
        assert (anagramsTobeTested.contains("aid"));
        assert (anagramsTobeTested.contains("dam"));
        assert (anagramsTobeTested.contains("and"));
        assert (anagramsTobeTested.contains("day"));
        Collections.sort(anagramsTobeTested);
        assert(allAnagrams.equals(anagramsTobeTested));
    }

    @Test
    public void sortStringTest(){
        final String firstTestingString = "bca";
        final String secondTestingString = "dab";
        final String thirdTestingString = "dad";

        final String sortedFirstString = "abc";
        final String sortedSecondString = "abd";
        final String sortedThirdString = "add";


        assert (dictionary.sortString(firstTestingString).equals(sortedFirstString));
        assert (dictionary.sortString(secondTestingString).equals(sortedSecondString));
        assert (dictionary.sortString(thirdTestingString).equals(sortedThirdString));

    }

    @Test
    public void mapWordTest() throws Exception {
        //dictionary.mapWord("ad");
        assert (dictionary.getWords().contains("ad"));

        //dictionary.mapWord("dad");
        assert (dictionary.getWords().contains("dad"));

        //dictionary.mapWord("add");
        assert (dictionary.getWords().contains("add"));
        assert (dictionary.getAnagramCluster().containsKey("add"));
        assert (dictionary.getAnagramCluster().get("add").contains("dad"));
        //assert (dictionary.getAnagramCluster().get("add").size()==1);





    }

    @Test
    public void binaryInsertCharacterTest() throws Exception {
        List<Character> sortedCharacters = new ArrayList<>();

        sortedCharacters.add('a');

        sortedCharacters.add('e');
        sortedCharacters.add('g');

        sortedCharacters.add('z');

        String newSortedCharacters = dictionary.binaryInsertCharacter(sortedCharacters,'b',0,sortedCharacters.size()-1);

        assertEquals (newSortedCharacters,"abegz");
        newSortedCharacters = dictionary.binaryInsertCharacter(sortedCharacters,'a',0,sortedCharacters.size()-1);
        assertEquals (newSortedCharacters,"aabegz");

        newSortedCharacters = dictionary.binaryInsertCharacter(sortedCharacters,'h',0,sortedCharacters.size()-1);
        assertEquals (newSortedCharacters,"aabeghz");

        newSortedCharacters = dictionary.binaryInsertCharacter(sortedCharacters,'z',0,sortedCharacters.size()-1);
        assertEquals (newSortedCharacters,"aabeghzz");




    }

}