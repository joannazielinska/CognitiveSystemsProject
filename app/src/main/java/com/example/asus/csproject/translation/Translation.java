package com.example.asus.csproject.translation;

import java.util.HashMap;
import java.util.Map;

public class Translation {

    private static final String [] englishArray = {"apple",  "banana"};

    private static final String[] germanArray = {"Apfel", "Banane"};

    private static final String[] polishArray = {"jabłko", "banan"};

    private static final int wordCount = englishArray.length;

    private Map<String, String> englishToGerman;
    private Map<String, String> englishToPolish;


    public Translation(){
        initDics();
    }

    private void initDics() {

        englishToGerman = new HashMap<>();
        englishToPolish = new HashMap<>();
        for (int i = 0; i < wordCount; i++) {
            englishToGerman.put(englishArray[i], germanArray[i]);
            englishToPolish.put(englishArray[i], polishArray[i]);
        }
    }

    public String  getGerman(String word){

        return englishToGerman.get(word.toLowerCase());
    }

    public String getPolish(String word){
        return englishToPolish.get(word.toLowerCase());
    }
}
