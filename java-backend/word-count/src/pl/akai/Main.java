package pl.akai;

import java.util.*;


public class Main {

    private static String[] sentences = {
            "Taki mamy klimat",
            "Wszędzie dobrze ale w domu najlepiej",
            "Wyskoczył jak Filip z konopii",
            "Gdzie kucharek sześć tam nie ma co jeść",
            "Nie ma to jak w domu",
            "Konduktorze łaskawy zabierz nas do Warszawy",
            "Jeżeli nie zjesz obiadu to nie dostaniesz deseru",
            "Bez pracy nie ma kołaczy",
            "Kto sieje wiatr ten zbiera burzę",
            "Być szybkim jak wiatr",
            "Kopać pod kimś dołki",
            "Gdzie raki zimują",
            "Gdzie pieprz rośnie",
            "Swoją drogą to gdzie rośnie pieprz?",
            "Mam nadzieję, że poradzisz sobie z tym zadaniem bez problemu",
            "Nie powinno sprawić żadnego problemu, bo Google jest dozwolony"
    };

    public static void main(String[] args) {
        /* TODO Twoim zadaniem jest wypisanie na konsoli trzech najczęściej występujących słów
                w tablicy 'sentences' wraz z ilością ich wystąpień..

                Przykładowy wynik:
                1. "mam" - 12
                2. "tak" - 5
                3. "z" - 2
        */

        Integer showAmountWords = 3;
        HashMap<String, Integer> counter = getMyMap(sentences);
        printMostCommonWords(counter, showAmountWords);
    }

    private static HashMap<String, Integer> getMyMap(String[] sentences) {
        HashMap<String, Integer> counter = new HashMap<>();

        for (String x: sentences) {
            for (String y : x.split(" ")) {
                String key = y.toLowerCase(Locale.ROOT);
                if (counter.containsKey(key)) {
                    counter.put(key, counter.get(key) + 1);
                } else {
                    counter.put(key, 1);
                }
            }
        }
        return counter;
    }


    private static void printMostCommonWords(HashMap<String, Integer> counter, Integer showAmountWords) {

        Set<String> words = counter.keySet();

        String maxWord = "";
        Integer maxCount = 0;

        for(int i = 0; i < showAmountWords; i++){
            for(String word:words){
                if(counter.get(word) > maxCount){
                    maxWord = word;
                    maxCount = counter.get(word);
                }
            }

            System.out.println( i+1 + ". '" + maxWord + "' wystepuje " + maxCount);
            counter.remove(maxWord);
            words.remove(maxWord);
            maxCount = 0;
            maxWord = "";

        }
    }

}