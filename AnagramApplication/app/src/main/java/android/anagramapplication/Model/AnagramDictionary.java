package android.anagramapplication.Model;


import android.anagramapplication.R;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Nathnael on 6/21/2017.
 */

public class AnagramDictionary implements AnagramDictionaryInterface {
    private List<String> words;//list of all words to be displayed within the Game Activity
    private Map<String, Set<String>> anagramCluster;// a set of anagrams will be mapped to a cluster
    private final String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public Map<String, Set<String>> getAnagramCluster() {
        return anagramCluster;
    }

    public void setAnagramCluster(Map<String, Set<String>> anagramCluster) {
        this.anagramCluster = anagramCluster;
    }

    AnagramDictionary() {
        words = new ArrayList<>();
        anagramCluster = new HashMap<>();
        /*try{
            Scanner scan  = new Scanner(new File("C:\\Users\\Nathnael\\Documents\\GoogleAndroid\\anagram-game\\AnagramApplication\\app\\src\\main\\java\\android\\anagramapplication\\Model\\words.txt"));
            String wordToBePassed;
            while(scan.hasNextLine()){
                wordToBePassed = scan.nextLine().trim();
                mapWord(wordToBePassed);
                if(!scan.hasNextLine()){
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }*/

    }

    public static AnagramDictionary getDictionary()
    {
        return new AnagramDictionary();
    }
    public String sortString(String unsorted) {

        char[] charword = unsorted.toCharArray();
        Arrays.sort(charword);
        return String.valueOf(charword);
    }




    @Override
    public String getWordToBeDisplayed() {


        int mapIndex = (int)(Math.random()*words.size());
        String random_word;
        if(words.size() ==0){
            return "Null";
        }else {
            random_word = sortString(words.get(mapIndex));
        }



        int setIndex = (int)(Math.random()*anagramCluster.get(random_word).size());
        String pickedWord = anagramCluster.get(random_word).toArray()[setIndex].toString();
        while(getAllPossibleAnagrams(pickedWord).size() < 5){
            mapIndex = (int)(Math.random()*words.size());
            random_word = sortString(words.get(mapIndex));
            setIndex = (int)(Math.random()*anagramCluster.get(random_word).size());
            pickedWord = anagramCluster.get(random_word).toArray()[setIndex].toString();

        }
        return pickedWord;
    }

    @Override
    public boolean checkAnswer(String targetWord, String answer) {
        String sortedAnswer = sortString(answer);
        if(validInput(targetWord,answer.toLowerCase()) && anagramCluster.containsKey(sortedAnswer)){
            return true;
        }
        return false;
    }

    private boolean validInput(String targetWord, String answer) {

        answer = answer.toLowerCase();
        Map<Character, Integer> mapCharacterCountanswer = new HashMap<>();
        Map<Character, Integer> mapCharTarget = new HashMap<>();


        if(answer.length() - targetWord.length() != 1)
            return false;
        else if(answer.length() - targetWord.length() == 1 && targetWord.equals(answer.substring(0,answer.length()-1))){
            return false;
        }else if(answer.length() - targetWord.length() == 1 && targetWord.equals(answer.substring(1,answer.length()))){
            return false;
        }
        for(char c: answer.toCharArray()){
            if(mapCharacterCountanswer.containsKey(c)){
                mapCharacterCountanswer.put(c,mapCharacterCountanswer.get(c)+1);
            }else{
                mapCharacterCountanswer.put(c,1);
            }
        }

        for(char c: targetWord.toCharArray()){
            if(mapCharTarget.containsKey(c)){
                mapCharTarget.put(c,mapCharTarget.get(c)+1);
            }else{
                mapCharTarget.put(c,1);
            }
        }

        for( Character c : mapCharTarget.keySet()){
            if(!mapCharacterCountanswer.containsKey(c)  || !(mapCharTarget.get(c) <= mapCharacterCountanswer.get(c)) ){
                return false;
            }
        }
        return true;


    }

    @Override
    public List<String> getAllPossibleAnagrams(String targetWord) {
        //Character[] sortedcharTarget = targetWord.toCharArray();
        //Arrays.sort(sortedcharTarget);
        //String sortedTarget = sortedcharTarget.toString();
        Set<String> allAnagrams = new HashSet<>();
        List<Character> sortedTarget = new LinkedList<>();//sort the targetword
        for(int i = 0; i < targetWord.length();i++){
            sortedTarget.add(targetWord.charAt(i));
        }
        Collections.sort(sortedTarget);//form a list of the sorted targetword
        List<Character> copySortedTarget = new LinkedList<>(sortedTarget);
        for (int i = 0; i < ALPHABETS.length(); i++){
            String newSortedTarget = binaryInsertCharacter(copySortedTarget, ALPHABETS.charAt(i), 0, sortedTarget.size()-1);//convert sorted target to linkedList

            copySortedTarget = null;
            copySortedTarget = new LinkedList<>(sortedTarget);
            if(anagramCluster.containsKey(newSortedTarget)){
                allAnagrams.addAll(anagramCluster.get(newSortedTarget));
            }

        }
        List<String> anagramSolutions = new ArrayList<>();
        Iterator<String> iterator = allAnagrams.iterator();
        while(iterator.hasNext()){
            String solution = iterator.next();

            if(validInput(targetWord,solution)) {
                anagramSolutions.add(solution);
            }
        }
        return anagramSolutions;
    }
    private String listToString(List<Character> characterList){
        StringBuilder builder = new StringBuilder();

        for(Character character : characterList){

            builder.append(character);
        }
        return builder.toString();
    }

    public String binaryInsertCharacter(List<Character> sortedTarget, char c, int start, int end) {
        if((end - start) <= 0){
            if(c < sortedTarget.get(end)){
                sortedTarget.add(end,c);
                //return sortedTarget.toString();
            }
            else if(c >= sortedTarget.get(end) && end < (sortedTarget.size() - 1)){
                sortedTarget.add(end+1,c);
            }else if(c >= sortedTarget.get(end) && end >= (sortedTarget.size() - 1)){
                sortedTarget.add(c);
            }
            return listToString(sortedTarget);

        }else if((end - start) == 1){
            if(c < sortedTarget.get(start)){
                sortedTarget.add(start,c);
            }
            else if(c < sortedTarget.get(end)){
                sortedTarget.add(end,c);

            }else if( c > sortedTarget.get(end) && end < sortedTarget.size()-1){
                sortedTarget.add(end+1,c);

            }else{
                sortedTarget.add(c);
            }
            return listToString(sortedTarget);

        }
        else{
            int middle = (end + start)/2;
            if(c > sortedTarget.get(middle)){
                binaryInsertCharacter(sortedTarget,c,middle+1,end);

            }else if(c < sortedTarget.get(middle)){
                binaryInsertCharacter(sortedTarget,c,start,middle-1);
            }else{//we found the spot
                sortedTarget.add(middle,c);
                return sortedTarget.toString();

            }
            return listToString(sortedTarget);
        }


    }

    // this method adds the word into a dictionary. It also adds the word into the right cluster of words that are all
    // anagrams of each other
    @Override
    public void mapWord(String word) {
        words.add(word);

        String sortedWord =  sortString(word);
        if(anagramCluster.containsKey(sortedWord)){
            Set set = anagramCluster.get(sortedWord);
            set.add(word);
            anagramCluster.put(sortedWord,set);
        }else{
            anagramCluster.put(sortedWord,new HashSet<String>());

            Set set = anagramCluster.get(sortedWord);
            set.add(word);
            anagramCluster.put(sortedWord,set);
        }

    }
}
