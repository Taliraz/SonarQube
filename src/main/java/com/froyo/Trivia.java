package com.froyo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Trivia {

    private Logger logger = LoggerFactory.getLogger(Trivia.class);

    private static final String SCIENCE = "Science";
    private static final String SPORTS = "Sports";
    private static final String POP = "Pop";

    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Trivia() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {

        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;


        logger.info("{} was added", playerName);
        logger.info("They are player number {}", players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        logger.info("{} is the current player", players.get(currentPlayer));
        logger.info("They have rolled a {}", roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                logger.info("{} is getting out of the penalty box", players.get(currentPlayer));
                places[currentPlayer] = places[currentPlayer] + roll;
                if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

                logger.info("{}'s new location is {}", players.get(currentPlayer), places[currentPlayer]);
                String currentCategory = currentCategory();
                logger.info("The category is {}", currentCategory);
                askQuestion();
            } else {
                logger.info("{} is not getting out of the penalty box", players.get(currentPlayer));
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            logger.info("{}'s new location is {}", players.get(currentPlayer), places[currentPlayer]);
            String currentCategory = currentCategory();
            logger.info("The category is {}", currentCategory);
            askQuestion();
        }

    }

    private void askQuestion() {
        String removed = null;
        if (currentCategory().equals(POP))
            removed = popQuestions.removeFirst();
        if (currentCategory().equals(SCIENCE))
            removed = scienceQuestions.removeFirst();
        if (currentCategory().equals(SPORTS))
            removed = sportsQuestions.removeFirst();
        if (currentCategory().equals("Rock"))
            removed = rockQuestions.removeFirst();
        if (removed != null)
            logger.info(removed);
    }


    private String currentCategory() {
        if (places[currentPlayer] == 0) return POP;
        if (places[currentPlayer] == 4) return POP;
        if (places[currentPlayer] == 8) return POP;
        if (places[currentPlayer] == 1) return SCIENCE;
        if (places[currentPlayer] == 5) return SCIENCE;
        if (places[currentPlayer] == 9) return SCIENCE;
        if (places[currentPlayer] == 2) return SPORTS;
        if (places[currentPlayer] == 6) return SPORTS;
        if (places[currentPlayer] == 10) return SPORTS;
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                logger.info("Answer was correct!!!!");
                purses[currentPlayer]++;
                logger.info("{} now has {} Gold Coins.", players.get(currentPlayer), purses[currentPlayer]);

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            logger.info("Answer was corrent!!!!");
            purses[currentPlayer]++;
            logger.info("{} now has {} Gold Coins.", players.get(currentPlayer), purses[currentPlayer]);

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        logger.info("Question was incorrectly answered");
        logger.info("{} was sent to the penalty box", players.get(currentPlayer));
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return (purses[currentPlayer] != 6);
    }
}
