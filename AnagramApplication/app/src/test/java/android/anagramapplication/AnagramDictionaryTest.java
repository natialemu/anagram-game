package android.anagramapplication;

import android.anagramapplication.Model.AnagramDictionary;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Nathnael on 6/21/2017.
 */
public class AnagramDictionaryTest {
    AnagramDictionary dictionary;
    @Before
    public void setUp() throws Exception {
        dictionary = new AnagramDictionary();


    }

    @Test
    public void getWordsToBeDisplayedTest(){
        //the words should be five
        String word = dictionary.getWordToBeDisplayed();
        assert (word.length() > 1);
        assert(dictionary.getAllPossibleAnagrams(word).size() >= 5);
        //check answer takes in a target string and an answer. use the googe examples. including edge cases






    }

    @Test
    public void checkAnswerTest(){
        String target = "Badge";//make sure that case doesnt matter
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

        for(String solution: solutions){
            assertTrue (dictionary.checkAnswer(target, solution));
        }

        for(String wrongAnswer: wrongAnswers){
            assertFalse(dictionary.checkAnswer(target,wrongAnswer));
        }

    }

    @Test
    public void getAllPossibleAnagramsTest(){
        //get a simple word
        //find all possible anagrams with your hand
        //then get all possible anagrams of that word with the method
        //make sure that the two lists are the same
        String targetWord = "ad";
        List<String> allAnagrams = new ArrayList<>();
        allAnagrams.add("dab");
        allAnagrams.add("add");
        allAnagrams.add("dad");
        allAnagrams.add("aid");
        allAnagrams.add("dam");
        allAnagrams.add("and");
        allAnagrams.add("day");
        Collections.sort(allAnagrams);
        List<String> anagramsTobeTested = dictionary.getAllPossibleAnagrams("ad");
        Collections.sort(anagramsTobeTested);
        assert(allAnagrams.equals(anagramsTobeTested));
    }

}