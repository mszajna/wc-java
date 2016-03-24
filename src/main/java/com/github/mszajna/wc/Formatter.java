package com.github.mszajna.wc;

class Formatter {

    public static String formatWords(int words) {
        return String.format("words:%d", words);
    }

    public static String formatLines(int lines) {
        return String.format("lines:%d", lines);
    }

    public static String formatAverageLettersPerWords(float averageLettersPerWord) {
        return String.format("average letters per word:%.1f", averageLettersPerWord);
    }

    public static String formatMostCommonLetter(char c) {
        return String.format("most common letter:%c", c);
    }
}
