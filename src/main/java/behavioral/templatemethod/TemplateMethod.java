package behavioral.templatemethod;

abstract class Game {

    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void run() {
        start();
        while (!haveWinner()) {
            takeTurn();
        }
        System.out.println("Player " + gewtWinningPlayer() + " wins!");
    }

    protected abstract void takeTurn();

    protected abstract boolean haveWinner();

    protected abstract int gewtWinningPlayer();

    protected abstract void start();

    protected int currentPlayer;
    protected final int numberOfPlayers;
}

class Chess extends Game {

    private int maxTurns = 10;
    private int turn = 1;


    public Chess() {
        super(2);
    }

    @Override
    protected void takeTurn() {
        System.out.println("Turn " + (turn++) + " taken by player " + currentPlayer);
        currentPlayer = (currentPlayer + 1) % numberOfPlayers;
    }

    @Override
    protected boolean haveWinner() {
        return turn == maxTurns;
    }

    @Override
    protected int gewtWinningPlayer() {
        return 0;
    }

    @Override
    protected void start() {
        System.out.println("Starting a game of chess");
    }
}

public class TemplateMethod {

    public static void main(String[] args) {
        new Chess().run();
    }
}
