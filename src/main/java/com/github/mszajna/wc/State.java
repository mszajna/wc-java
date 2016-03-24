package com.github.mszajna.wc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class State {

    private int lines = 0;
    private int words = 0;
    private int letters = 0;
    private boolean inWord = false;
    private Map<Character, Integer> letterOccurrences = new HashMap<>();

    public void consume(char c) {
        if (c == '\n') {
            lines++;
        }
        if (Character.isWhitespace(c)) {
            inWord = false;
        } else {
            if (!inWord) {
                words++;
            }
            inWord = true;

            letters++;
            letterOccurrences.merge(c, 1, (a, b) -> a + b);
        }
    }

    public int getLines() {
        return lines;
    }

    public int getWords() {
        return words;
    }

    public Optional<Float> getAverageLettersPerWord() {
        return words == 0 ? Optional.empty() : Optional.of((float) letters / words);
    }

    public Optional<Character> getMostCommonLetter() {
        return letterOccurrences.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);
    }
}
