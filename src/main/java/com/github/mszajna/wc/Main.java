package com.github.mszajna.wc;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage:\nword-count filename");
        } else {
            String filename = args[0];
            State state = new State();
            try (Reader reader = Files.newBufferedReader(Paths.get(filename))) {
                char[] buffer = new char[BUFFER_SIZE];
                int numOfCharsRead = reader.read(buffer);
                for (int i = 0; i < numOfCharsRead; i++) {
                    state.consume(buffer[i]);
                }
            }

            System.out.println(Formatter.formatWords(state.getWords()));
            System.out.println(Formatter.formatLines(state.getLines()));
            state.getAverageLettersPerWord().ifPresent(a -> System.out.println(Formatter.formatAverageLettersPerWords(a)));
            state.getMostCommonLetter().ifPresent(c -> System.out.println(Formatter.formatMostCommonLetter(c)));
        }
    }

}
