public class Move {
    private Square originSquare;
    private Square destinationSquare;
    public Move(Square origin, Square destination) {
        this.originSquare = origin;
        this.destinationSquare = destination;
    }
    private Square getOriginSquare() {
        return originSquare;
    }
    private Square getDestinationSquare() {
        return destinationSquare;
    }
    
}
