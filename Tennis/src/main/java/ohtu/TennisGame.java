package ohtu;

import java.util.HashMap;

public class TennisGame {

    private HashMap<String, Integer> players;
    private HashMap<Integer, String> results;
    private HashMap<Integer, String> advantages;

    public TennisGame(String player1Name, String player2Name) {
        players = new HashMap<>();
        results = new HashMap<>();
        advantages = new HashMap<>();
        players.put(player1Name, 0);
        players.put(player2Name, 0);
        results.put(0, "Love");
        results.put(1, "Fifteen");
        results.put(2, "Thirty");
        results.put(3, "Forty");
        results.put(4, "Deuce");
        advantages.put(1, "Advantage player1");
        advantages.put(-1, "Advantage player2");
        advantages.put(2, "Win for player1");
        advantages.put(-2, "Win for player2");
    }

    public void wonPoint(String playerName) {
        players.replace(playerName, players.get(playerName) + 1);
    }

    public String getScore() {
        StringBuilder str = new StringBuilder();
        int tempScore;
        if (equalPoints() && getPlayer1Points() <= 4) {
            str.append(results.get(getPlayer1Points()));
            if (getPlayer1Points() != 4) str.append("-All");
        } else if (getPlayer1Points() >= 4 || getPlayer2Points() >= 4) {
            str.append(advantages.get(calculatePointDifference()));
        } else {
            for (int i = 1; i < 3; i++) {
                if (i == 1) {
                    tempScore = getPlayer1Points();
                } else {
                    str.append("-");
                    tempScore = getPlayer2Points();
                }
                str.append(results.get(tempScore));
            }
        }
        return str.toString();
    }

    private int calculatePointDifference() {
        int x = limitPointsTo2();
        if(x >= 2) return 2;
        if(x <= -2) return -2;
        return x;
    }

    private int limitPointsTo2() {
        return getPlayer1Points() - getPlayer2Points();
    }

    private int getPlayer1Points() {
        return (int) players.values().toArray()[0];
    }

    private int getPlayer2Points() {
        return (int) players.values().toArray()[1];
    }

    private boolean equalPoints() {
        return getPlayer1Points() == getPlayer2Points();
    }
}