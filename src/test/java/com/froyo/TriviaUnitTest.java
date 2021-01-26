package com.froyo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriviaUnitTest {

    private Trivia trivia;

    @Before
    public void init() {
        trivia = new Trivia();
    }

    @Test
    public void createRockQuestion() {
        int index = 2;
        String result = trivia.createRockQuestion(index);
        assertEquals("Rock Question " + index, result);
    }

    @Test
    public void isPlayable() {
        boolean notPlayable = trivia.isPlayable();
        trivia.add("toto");
        trivia.add("toto2");
        boolean playable = trivia.isPlayable();
        assertFalse(notPlayable);
        assertTrue(playable);
    }

    @Test
    public void add() {
        int playersBefore = trivia.howManyPlayers();
        trivia.add("toto");
        int playersAfter = trivia.howManyPlayers();
        assertEquals(0, playersBefore);
        assertEquals(1, playersAfter);
    }

    @Test
    public void howManyPlayers() {
        int playersBefore = trivia.howManyPlayers();
        trivia.add("toto");
        int playersAfter = trivia.howManyPlayers();
        assertEquals(0, playersBefore);
        assertEquals(1, playersAfter);
    }

    @Test
    public void roll() {
        trivia.add("toto");
        trivia.add("toto2");
        trivia.roll(2);
        trivia.wrongAnswer();
        trivia.wrongAnswer();
        trivia.roll(3);
        trivia.roll(2);
        assertTrue(true);
    }

    @Test
    public void wasCorrectlyAnswered() {
        trivia.add("toto");
        trivia.add("toto2");
        trivia.wasCorrectlyAnswered();
        trivia.wasCorrectlyAnswered();
        trivia.wrongAnswer();
        trivia.wrongAnswer();
        trivia.roll(3);
        assertTrue(trivia.wasCorrectlyAnswered());
    }

    @Test
    public void wasCorrectlyAnsweredWin() {
        trivia.add("toto");
        trivia.add("toto2");
        trivia.wasCorrectlyAnswered();
        trivia.wasCorrectlyAnswered();
        trivia.wrongAnswer();
        trivia.wrongAnswer();
        assertTrue(trivia.wasCorrectlyAnswered());
    }

    @Test
    public void askQuestion() {
        trivia.add("toto");
        trivia.add("toto2");
        trivia.roll(0);
        assertEquals(2, trivia.howManyPlayers());
    }
}