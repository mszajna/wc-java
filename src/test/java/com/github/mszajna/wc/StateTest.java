package com.github.mszajna.wc;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class StateTest {

    private static float DELTA = 0.01f;

    @Test
    public void testLines_emptyStringHasZero() {
        assertEquals(0, run("").getLines());
    }

    @Test
    public void testLines_countsLinesByNumberOfNewlineCharacters() {
        assertEquals(2, run("a\nb\n").getLines());
    }

    @Test
    public void testLines_charactersAfterLastNewlineDontCount() {
        assertEquals(1, run("abc\nabc").getLines());
    }

    @Test
    public void testLines_countsEmptyLines() {
        assertEquals(2, run("\n\n").getLines());
    }

    @Test
    public void testWords_emptyStringHasZero() {
        assertEquals(0, run("").getWords());
    }

    @Test
    public void testWords_countsWordsSeparatedByWhitespace() {
        assertEquals(2, run("it works").getWords());
    }

    @Test
    public void testWords_ignoresMultipleWhitespaces() {
        assertEquals(2, run("word  \n  word").getWords());
    }

    @Test
    public void testWords_countsAnyNonWhitespaceCharacter() {
        assertEquals(1, run("!@#$%^&*()_+|?><~`").getWords());
    }

    @Test
    public void testAverageLettersPerWord_emptyStringHasNone() {
        assertEquals(Optional.empty(), run("").getAverageLettersPerWord());
    }

    @Test
    public void testAverageLettersPerWord_whitespaceStringHasNone() {
        assertEquals(Optional.empty(), run("  \n\n  ").getAverageLettersPerWord());
    }

    @Test
    public void testAverageLettersPerWord_countsAverage() {
        assertEquals(2f, run("a bcd").getAverageLettersPerWord().get(), DELTA);
    }

    @Test
    public void testMostCommonLetter_emptyStringHasNone() {
        assertEquals(Optional.empty(), run("").getMostCommonLetter());
    }

    @Test
    public void testMostCommonLetter_whitespaceStringHasNone() {
        assertEquals(Optional.empty(), run("  \n\n  ").getMostCommonLetter());
    }

    @Test
    public void testMostCommonLetter_findsMostCommon() {
        assertEquals('b', (char) run("abc ab bc").getMostCommonLetter().get());
    }

    @Test
    public void testMostCommonLetter_ignoresWhitespace() {
        assertEquals('a', (char) run("a  \n\n").getMostCommonLetter().get());
    }

    @Test
    public void testMostCommonLetter_countsAnyNonWhitespaceCharacter() {
        assertEquals('`', (char) run("!@#$%^&*()_+|?><~``").getMostCommonLetter().get());
    }

    private State run(String s) {
        State state = new State();
        s.chars().forEach(c -> state.consume((char) c));
        return state;
    }

}
