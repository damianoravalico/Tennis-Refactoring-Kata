
public class TennisGame1 implements TennisGame {
    private int player1Score;
    private int player2Score;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        player1Score = 0;
        player2Score = 0;
    }

    public void wonPoint(String playerName) {
        player1Score = player1Name.equals(playerName) ? player1Score + 1 : player1Score;
        player2Score = player2Name.equals(playerName) ? player2Score + 1 : player2Score;
    }

    public String buildCurrentGameResult() {
        if (player1Score == player2Score) {
            return generateNamedScoreGivenTie();
        }
        if (player1Score >= 4 || player2Score >= 4) {
            return generateNamedScoreGivenAdvantage();
        }
        return castIntegerScoreToTennisScore(player1Score) + "-" + castIntegerScoreToTennisScore(player2Score);
    }

    private String generateNamedScoreGivenTie() {
        return switch (player1Score) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
    }

    private String generateNamedScoreGivenAdvantage() {
        int scoreDifference = player1Score - player2Score;
        if (Math.abs(scoreDifference) == 1) {
            return "Advantage " + (scoreDifference == 1 ? "player1" : "player2");
        }
        return scoreDifference >= 2 ? "Win for player1" : "Win for player2";
    }

    private static String castIntegerScoreToTennisScore(int score) {
        return switch (score) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            case 3 -> "Forty";
            default -> throw new IllegalStateException("Unexpected value: " + score);
        };
    }

}
