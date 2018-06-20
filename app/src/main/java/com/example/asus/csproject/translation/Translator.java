package com.example.asus.csproject.translation;


public class Translator {

    private Language language = Language.ENGLISH;
    private Translation translation;

    public Translator(){
        translation = new Translation();
    }
    public void SetLanguage(Language language){
        this.language = language;
    }

    public String translate(String word){
        String result = "";
        return result;
    }

    private String getTranslation(String word){

        String result;

        switch (language){
            case GERMAN:
                result = translation.getGerman(word);
                break;
            case POLISH:
                result = translation.getPolish(word);
                break;
            default:
                result = word;
        }

        return result;

    }
}
