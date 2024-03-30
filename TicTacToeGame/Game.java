package TicTacToeGame;

public class Game {

    Piece piece;
    Board board;
    Player[] players;
    Player currentPlayer;
    GameStatus gameStatus;
    int cellCount = 3;
    int noOfPlayers = 2;
    int[] arrRow = new int[cellCount];
    int[] arrCol = new int[cellCount];
    int drl = 0;
    int dlr = 0;

    public Game(Player p1, Player p2) {
        players = new Player[noOfPlayers];
        players[0] = p1;
        players[1] = p2;
        board = new Board(cellCount);
        currentPlayer = players[0];
        gameStatus = GameStatus.PLAYING;
    }

    public GameStatus getGameStatus() {

        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {

        this.gameStatus = gameStatus;
    }

    public void play(int row, int col) {

        if (GameStatus.PLAYING == gameStatus) {

            if (isValidMove(row, col)) {
                board.boxes[row][col].setPiece(currentPlayer.getPiece());

                if (checkIfWon(row, col)) {
                    board.printBoardStatus();
                    System.out.println(currentPlayer.getUserName() + " has won the Game!!");
                    return;
                }

                gameStatus = GameStatus.PLAYING;
                changeTurn();
                board.printBoardStatus();
                return;
            }
            else{
                System.out.println(currentPlayer.getUserName() + " Please put valid move!");
                board.printBoardStatus();
                return;
            }

        } else {
            return;
        }
    }

    private void changeTurn() {
        if (currentPlayer.getUserId().equals(players[0].getUserId())) {
            currentPlayer = players[1];
        } else {
            currentPlayer = players[0];
        }
    }


    private boolean checkIfWon(int row, int col) {

        // for player1
        if (currentPlayer.getUserId().equals(players[0].getUserId())) {
            if (row == col) {
                dlr++;
            } else if (row + col == cellCount - 1) {
                drl++;
            }
            arrRow[row] = arrRow[row] + 1;
            arrCol[col] = arrCol[col] + 1;
            if (arrRow[row] == cellCount || arrCol[col] == cellCount || drl == cellCount || dlr == cellCount) {

                gameStatus = GameStatus.WON;
                return true;
            }
        }

        // for player2
        else {
            if (row == col) {
                dlr--;
            } else if (row + col == -(cellCount - 1)) {
                drl--;
            }
            arrRow[row] = arrRow[row] - 1;
            arrCol[col] = arrCol[col] - 1;
            if (arrRow[row] == -cellCount || arrCol[col] == -cellCount || drl == -cellCount || dlr == -cellCount) {

                gameStatus = GameStatus.WON;
                return true;
            }

        }
        return false;
    }

    private boolean isValidMove(int row, int col) {
        return row < cellCount && col < cellCount && board.boxes[row][col].getPiece() == null;
    }
}
