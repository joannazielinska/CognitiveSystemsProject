package com.example.asus.csproject.translation;


public class Translator {

    private Language language = Language.ENGLISH;
    private Translation translation;

    public Translator(){
        translation = new Translation();
    }
    public void SetLanguage(String lang){
        switch (lang.toUpperCase()){
            case "GERMAN":
                language = Language.GERMAN;
                break;
            case "POLISH":
               language = Language.POLISH;
                break;
            default:
                language = Language.ENGLISH;
        }
    }

    public String translate(String word){
        String result = getTranslation(word);
        return result;
    }

    private String getTranslation(String word){

        String result = "";

        switch (language){
            case GERMAN:
                result = translation.getGerman(word);
                break;
            case POLISH:
                result = translation.getPolish(word);
                break;
            default:
                result = word;
                break;
        }

        return result;

    }
}
