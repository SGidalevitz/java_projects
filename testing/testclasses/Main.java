import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //make a simple tictactoe game. have a main method to play the game, have an input method to ask user for a move, have a method to check if the game is over, and have a method to print the game board.
    public static void main(String[] args) {
        //use methods from class tictactoe to play the game
        TicTacToe game = new TicTacToe();
        String player = "X";
        while (game.isGameOver().equals("S")) {
            
            game.askMove(player);
            game.printBoard();
            if (player.equals("X")) {
                player = "O";
            } else {
                player = "X";
            }
        }
        System.out.println(game.isGameOver() + "wins!");
    }
}
//make TicTacToe class
class TicTacToe {
    //create a 3x3 array of strings to represent the game board
    //constructor
    public TicTacToe() {
    }
    //set private String[]][] board to a 3x3 array of blank spaces
    private String[][] board = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    
    //method to ask user for a move
    //public void askMove(String player) { where player is "X" or "O" and that's what it sets the square of the board to
    public void askMove(String player) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your move");
        int row = input.nextInt();
        int col = input.nextInt();
        board[row][col] = player;
    }
    //method to check if the game is over that returns "X" if x won or "O" if o won or "Draw" if it's a draw or "S" if it's still going
    public String isGameOver() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) &&!board[i][0].equals(" ")) {
                return board[i][0];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) &&!board[0][i].equals(" ")) {
                return board[0][i];
            }
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) &&!board[0][0].equals(" ")) {
            return board[0][0];
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) &&!board[0][2].equals(" ")) {
            return board[0][2];
        }
        return "S";
    }
    
    //method to print the game board
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}




