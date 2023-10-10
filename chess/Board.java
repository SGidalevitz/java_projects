import java.util.HashMap;
public class Board {
    private Piece[][] board;
    private int toMove; // 0 for white, 1 for black
    private String castlingRights;
    private Square enPassantTargetSquare;
    private int halfMoveClock;
    private int fullMoveNumber;

    public Board(String fen) {
        this.board = readFen(fen);
        String[] partsOfFen = fen.split(" ");
        if (partsOfFen[1].equals("w")) {
            this.toMove = 0;
        }
        else {
            this.toMove = 1;
        }
        this.castlingRights = partsOfFen[2];
        if (partsOfFen[3].equals("-")) {
            this.enPassantTargetSquare = null;
        }
        else {
            this.enPassantTargetSquare = new Square(partsOfFen[3]);
        }
        this.halfMoveClock = Integer.parseInt(partsOfFen[4]);
        this.fullMoveNumber = Integer.parseInt(partsOfFen[5]);
    }
    public Board(Piece[][] board, int toMove, String castlingRights, Square enPassantTargetSquare, int halfMoveClock, int fullMove) {
        this.board = board;
        this.toMove = toMove;
        this.castlingRights = castlingRights;
        this.enPassantTargetSquare = enPassantTargetSquare;
        this.halfMoveClock = halfMoveClock;
        this.fullMoveNumber = fullMove;
    }
    public Piece[][] readFen(String fen) {
        HashMap<Character, Integer> CharToPieceIDMap = getCharToPieceIDMap();
        String[] partsOfFen = fen.split(" ");
        String[] ranks = partsOfFen[0].split("/");
        Piece[][] board = new Piece[8][8];
        for (int i = 0; i < ranks.length; i++) {
            String rank = ranks[i];
            int fileCount = 0;
            for (int j = 0; j < rank.length(); j++) {
                char c = rank.charAt(j);
                
                if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8') {
                    for (int k = 0; k < Integer.parseInt("" + c); k++) {
                        board[7 - i][fileCount] = new Piece(new Square(7 - i, fileCount), 0);
                        fileCount++;
                    }
                }
                else {
                    board[7 - i][fileCount] = new Piece(new Square(7 - i, fileCount), CharToPieceIDMap.get(c));
                    fileCount++;
                }
                
            }
        }
        return board;
    }
    public HashMap<Character, Integer> getCharToPieceIDMap() {
        HashMap<Character, Integer> charToPieceIDMap = new HashMap<Character, Integer>();
        charToPieceIDMap.put('P', 1);
        charToPieceIDMap.put('N', 2);
        charToPieceIDMap.put('B', 3);
        charToPieceIDMap.put('R', 4);
        charToPieceIDMap.put('Q', 5);
        charToPieceIDMap.put('K', 6);
        charToPieceIDMap.put('p', 7);
        charToPieceIDMap.put('n', 8);
        charToPieceIDMap.put('b', 9);
        charToPieceIDMap.put('r', 10);
        charToPieceIDMap.put('q', 11);
        charToPieceIDMap.put('k', 12);
        return charToPieceIDMap;
    }
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("" + board[7 - i][j].getPieceAsChar() + " ");
            }
            System.out.println();
        }
    }
    public void makeMove(Square origin, Square destination) {
        board[destination.getFile()][destination.getRank()].setType(board[origin.getFile()][origin.getRank()].getType());
        board[origin.getFile()][origin.getRank()].setType(0);
    }
    public ArrayList<Move> getValidMoves() {
        
    }
    public ArrayList<Move> getPawnMoves(ArrayList<Piece> pieces) {
        ArrayList<Move> pawnMoves = new ArrayList<Moves>();
        for (int pieceIndex = 0; pieceIndex < pieces.size(); p++) {
            Piece piece = pieces.get(pieceIndex);
            pieceRank = piece.getRank();
            pieceFile = piece.getFile();
            pieceColor = (piece.getType <= 6) ? 0 : 1;
            if (pieceColor = 0) {
                Move singlePawnPush = new Move(new Square(pieceRank, pieceFile), new Square(pieceRank + 1, pieceFile));
                if (singlePawnPush.isValid(board)) {
                    pawnMoves += singlePawnPush;
                }
                Move doublePawnPush = new Move(new Square(pieceRank, pieceFile), new Square(pieceRank + 2, pieceFile));
                if (doublePawnPush.isValid(board)) {
                    pawnMoves += doublePawnPush;
                }
            }
            else {
                Move singlePawnPush = new Move(new Square(pieceRank, pieceFile), new Square(pieceRank - 1, pieceFile));
                if (singlePawnPush.isValid(board)) {
                    pawnMoves += singlePawnPush;
                }
                Move doublePawnPush = new Move(new Square(pieceRank, pieceFile), new Square(pieceRank - 2, pieceFile));
                if (doublePawnPush.isValid(board)) {
                    pawnMoves += doublePawnPush;
                }
            }
                
    }
}
