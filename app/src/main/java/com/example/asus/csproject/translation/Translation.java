package com.example.asus.csproject.translation;

import java.util.HashMap;
import java.util.Map;

public class Translation {

    private static final String [] englishArray = {"apple", "avocado", "banana", "grape pink", "grape white", "grapefruit pink",
                            "grapefruit white", "kiwi", "lemon", "limes", "mandarine", "mango", "orange",
                            "peach", "pear", "pomegranate", "raspberry", "strawberry"};

    private static final String[] germanArray = {"Apfel", "Avocado" ,"Banane" ,"Trauben rosa" , "Trauben weiß" , "Grapefruit Pink",
                            "Grapefruit weiß", "Kiwi", "Zitrone", "Zitronen","Mandarine" ,"Mango" ,"Orange",
                            "Pfirsich","Birne" ,"Granatapfel" ,"Himbeere" ,"Erdbeere"};

    private static final String[] polishArray = {"jabłko", "awokado", "banan", "różowe winogrono", "białe winogrono", "różowy grejpfrut",
                            "biały grejpfrut", "kiwi", "cytrynya", "limonka", "mandarynka", "pomarańcz", "brzoskwinia",
                            "gruszka", "granat", "malina", "truskawka"};

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

        return englishToGerman.get(word);
    }

    public String  getPolish(String word){
        return englishToPolish.get(word);
    }
}
