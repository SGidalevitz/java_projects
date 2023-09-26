import java.util.Scanner;

public class tictactoe{
    enum State {
        empty,
        player1,
        player2,
        draw
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("vs friend or vs computer? (1/2)");
        int choice = input.nextInt();
        while (!(choice == 1 || choice == 2)) {
            System.out.println("invalid choice, choose again (1/2)");
            choice = input.nextInt();
        }
        if (choice == 1) {
            gameCycleVsFriend();
        }
        else {
            gameCycleVsComputer();
        }
        
    }
    public static State checkForWin(int[][] board) {
        if (board[0][0] == board[1][0] && board[1][0] == board[2][0]) { //left vertical
            if (board[0][0] == 1) {
                return State.player1;
            }
            else if (board[0][0] == 2) {
                return State.player2;
            }
        }
        else if (board[0][1] == board[1][1] && board[1][1] == board[2][1]) { //middle vertical
            if (board[0][1] == 1) {
                return State.player1;
            }
            else if (board[0][1] == 2) {
                return State.player2;
            }
        }
        else if (board[0][2] == board[1][2] && board[1][2] == board[2][2]) { //right vertical
            if (board[0][2] == 1) {
                return State.player1;
            }
            else if (board[0][2] == 2) {
                return State.player2;
            }
        }
        else if (board[0][0] == board[0][1] && board[0][1] == board[0][2]) { //top horizontal
            if (board[0][0] == 1) {
                return State.player1;
            }
            else if (board[0][0] == 2) {
                return State.player2;
            }
        }
        else if (board[1][0] == board[1][1] && board[1][1] == board[1][2]) { //middle horizontal
            if (board[1][0] == 1) {
                return State.player1;
            }
            else if (board[1][0] == 2) {
                return State.player2;
            }
        }
        else if (board[2][0] == board[2][1] && board[2][1] == board[2][2]) { //bottom horizontal
            if (board[2][0] == 1) {
                return State.player1;
            }
            else if (board[2][0] == 2) {
                return State.player2;
            }
        }
        else if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) { //top left diagonal
            if (board[0][0] == 1) {
                return State.player1;
            }
            else if (board[0][0] == 2) {
                return State.player2;
            }
        }
        else if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) { //top left diagonal
            if (board[0][2] == 1) {
                return State.player1;
            }
            else if (board[0][2] == 2) {
                return State.player2;
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    return State.empty; // if nothing is valid
                }
            }
        }
        return State.draw; // if nothing is empty, hence game is over
    }
    public static int[] getInput(int turn) {
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();
        int[] cell = new int[3];
        while (data.length() != 3) {
            System.out.println("invalid format, remember \"a,b\" format");
            data = input.nextLine();
        }
        cell[1] = Integer.parseInt(data.substring(0, 1)) - 1;
        cell[0] = 3 - Integer.parseInt(data.substring(2));
        cell[2] = turn;
        
        return cell;
    }
    public static int[][] modifyBoard(int[][] board, int[] cell, int turn) {
        board[cell[0]][cell[1]] = turn;
        return board;

    }
    public static void printBoard (int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    System.out.print("X");
                }
                else if (board[i][j] == 2) {
                    System.out.print("O");
                }
                else if (board[i][j] == 0) {
                    System.out.print(".");
                }
                else { //if bug
                    System.out.print("!");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public static boolean isOccupied (int[][] board, int[] cell) {
        return (board[cell[0]][cell[1]] == 1 || board[cell[0]][cell[1]] == 2);
    }
    public static void gameEndOutput(int[][] board) {
        if (checkForWin(board) == State.player1) {
            System.out.println("X WINS");
        }
        else if (checkForWin(board) ==State.player2) {
            System.out.println("O WINS");
        }
        else if (checkForWin(board) == State.draw) {
            System.out.println("IT'S A DRAW");
        }
        else {
            System.out.println("error 01");
        }
    }
    public static void gameCycleVsFriend() {
        int[][] board = {{0,0,0},
                         {0,0,0},
                         {0,0,0}};
        int turn = 1;
        while (checkForWin(board) ==State.empty) {
            if (turn == 1) {
                System.out.println("X TO MOVE");
            }
            else if (turn == 2) {
                System.out.println("O TO MOVE");
            }

            int[] cell = getInput(turn);

            if (isOccupied(board, cell)) {
                System.out.println("CELL TAKEN");
            }
            else {
                modifyBoard(board, cell, turn);
                printBoard(board);
                turn = 3 - turn;
            }             
        }
        gameEndOutput(board);
    }
    public static void gameCycleVsComputer() {
        int[][] board = {{0,0,0},
                         {0,0,0},
                         {0,0,0}};
        int turn = 1;
        while (checkForWin(board) ==State.empty) {
            if (turn == 1) {
                int[] cell = getInput(1);
                if (isOccupied(board, cell)) {
                    System.out.println("CELL TAKEN");
                }
                else {
                    modifyBoard(board, cell, turn);
                    printBoard(board);
                    turn = 3 - turn;
                }             
            }
            else if (turn == 2) {
                //System.out.println("Board after first move again: ");
                //printBoard(board);
                board = playBestComputerMove(board, turn);
                printBoard(board);
                //System.out.println("board after best computer move is played: ");
                //printBoard(board);
                turn = 3 - turn;
                System.out.println("turn: " + turn);
            }
            System.out.println(checkForWin(board));
        }
        gameEndOutput(board);
    }
    public static int[][] getEmptySquares(int[][] board) {
        int numEmptySquares = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    numEmptySquares += 1;
                }
            }
        }
        int[][] emptySquares = new int[numEmptySquares][2];
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                
                if (board[i][j] == 0) {
                    emptySquares[count][0] = i;
                    emptySquares[count][1] = j;
                    count += 1;
                }
            }
        }
        return emptySquares;
    }
    public static int[][] playBestComputerMove(int[][] board, int turn) {
        
        int[][] emptySquares = getEmptySquares(board);
        
        int[] bestMove = search(board, turn, 0, 4);
        //System.out.println("board before best computer move is played");
        //printBoard(board);
        int[] cell = new int[3];
        cell[0] = bestMove[1];
        cell[1] = bestMove[2];
        modifyBoard(board, cell, turn);
        //printBoard(board);
        return board;
        
    }
    public static int evaluatePosition(int[][] board, int turn, int depth, int maxDepth) {
        int winning;
        int depthProgress = (maxDepth / depth);
        if (checkForWin(board) == State.player1) {
            if (turn == 1) {
                winning = 1;
            }
            else {
                winning = -1;
            }
        }
        else if (checkForWin(board) == State.player2) {
            if (turn == 1) {
                winning = -1;
            }
            else {
                winning = 1;
            }
        }
        else if (checkForWin(board) == State.draw) {
            winning = 0;
        }
        else {
            winning = 0;
        }
        return winning * depthProgress;

    }
    public static int[] search(int[][] board, int turn, int depth, int maxDepth) {
    int[][] emptySquares = getEmptySquares(board);
    //System.out.println("Current depth: " + depth + "\nCurrent board: ");
    //printBoard(board);
    if (depth == maxDepth || checkForWin(board) != State.empty) {
        int value = evaluatePosition(board, turn, depth, maxDepth);
        return new int[] { value, -1, -1 };
    }
    int[] bestMove = new int[] { (turn == 1) ? Integer.MIN_VALUE : Integer.MAX_VALUE, -1, -1 };

    for (int i = 0; i < emptySquares.length; i++) {
        int[][] theoryBoard = new int[board.length][board[0].length];
        for (int j = 0; i < board.length; i++) {
            for (int k = 0; j < board.length; j++) {
                theoryBoard[j][k] = board[j][k];
            }
        } 
        theoryBoard[emptySquares[i][0]][emptySquares[i][1]] = turn;
        
        int[] result = search(theoryBoard, 3 - turn, depth + 1, maxDepth);

        int value = result[0];

        if ((turn == 1 && value > bestMove[0]) || (turn == 2 && value < bestMove[0])) {
            bestMove[0] = value;
            bestMove[1] = emptySquares[i][0];
            bestMove[2] = emptySquares[i][1];
        }
    }
    //int[] bestMove = {0, 2, 2};
    return bestMove;
}


}