package ch.zli.nbastats;

public class Game {
    private String homeTeamName;
    private String awayTeamName;
    private String homeTeamScore = "";
    private String awayTeamScore = "";
    private String homeTeamWins = "0";
    private String awayTeamWins = "0";
    private int homeTeamImage;
    private int awayTeamImage;
    private String nugget;
    private String gameTime = ":";
    private String gameDate;
    private String gameId;
    private boolean isGameActive;
    private boolean isGameOver;

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getHomeTeamScore() {
        return String.valueOf(homeTeamScore);
    }

    public void setHomeTeamScore(String homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public String getAwayTeamScore() {
        return String.valueOf(awayTeamScore);
    }

    public void setAwayTeamScore(String awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public boolean isGameActive() {
        return isGameActive;
    }

    public void setGameActive(boolean gameActive) {
        isGameActive = gameActive;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public String getHomeTeamWins() {
        return String.valueOf(homeTeamWins);
    }

    public void setHomeTeamWins(String homeTeamWins) {
        this.homeTeamWins = homeTeamWins;
    }

    public String getAwayTeamWins() {
        return String.valueOf(awayTeamWins);
    }

    public void setAwayTeamWins(String awayTeamWins) {
        this.awayTeamWins = awayTeamWins;
    }

    public int getHomeTeamImage() {
        return homeTeamImage;
    }

    public void setHomeTeamImage(int homeTeamImage) {
        this.homeTeamImage = homeTeamImage;
    }

    public int getAwayTeamImage() {
        return awayTeamImage;
    }

    public void setAwayTeamImage(int awayTeamImage) {
        this.awayTeamImage = awayTeamImage;
    }

    public String getNugget() {
        return nugget;
    }

    public void setNugget(String nugget) {
        this.nugget = nugget;
    }


}
