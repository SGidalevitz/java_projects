import java.util.Scanner;
public class B1846 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        input.nextLine();
        for (int t = 0; t < T; t++) {
            String[] boardS = new String[3];
            boardS[0] = input.nextLine();
            boardS[1] = input.nextLine();
            boardS[2] = input.nextLine();
            int[][] board = parseBoard(boardS);
            int winner = checkWinner(board, 1) + checkWinner(board, 2) + checkWinner(board, 3);
            if (winner == 1) {
                System.out.println("X");
            }
            else if (winner == 2) {
                System.out.println("O");
            }
            else if (winner == 3) {
                System.out.println("+");
            }
            else {
                System.out.println("DRAW");
            }
        }
    }
    public static int[][] parseBoard(String[] boardS) {
        int[][] board = new int[3][3];
        for (int i = 0; i < boardS.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardS[i].charAt(j) == 'X') {
                    board[i][j] = 1;
                }
                else if (boardS[i].charAt(j) == 'O') {
                    board[i][j] = 2;
                }
                else if (boardS[i].charAt(j) == '+') {
                    board[i][j] = 3;
                }
                else {
                    board[i][j] = 0;
                }
            }

        }
        return board;
    }
    public static int checkWinner(int[][] board, int player) {
        //check rows
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] == player) {
                return player;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] == player) {
                return player;
            }
            
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == player) {
            return player;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == player) {
            return player;
        }
        return 0;
    }
}