package pl.akai;

import java.util.*;
import java.util.stream.Collectors;


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

    private static Map<String, Integer> counter = new HashMap<>();

    public static void main(String[] args) {
        /* TODO Twoim zadaniem jest wypisanie na konsoli trzech najczęściej występujących słów
                w tablicy 'sentences' wraz z ilością ich wystąpień..

                Przykładowy wynik:
                1. "mam" - 12
                2. "tak" - 5
                3. "z" - 2
        */

        Integer showAmountWords = 3;
        getMyMap();
        printMostCommonWords(showAmountWords);
    }


    private static void printMostCommonWords(Integer showAmountWords) {

        counter = counter.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        Iterator<String> keys = counter.keySet().iterator();
        for (int i = 0; i < showAmountWords; i++) {
            String key = keys.next();
            System.out.println(key + " wystepuje : " + counter.get(key));
        }
    }

    private static void getMyMap() {
        String[] words = Arrays.toString(sentences).replaceAll("[^A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ ]", "").toLowerCase().split(" ");
        for (String x: words) {
            counter.merge(x, 1, (oldValue, newValue) -> oldValue + 1);
        }
    }
}