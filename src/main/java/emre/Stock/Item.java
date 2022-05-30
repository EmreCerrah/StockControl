package emre.Stock;

import java.io.Serializable;

public class Item implements Serializable {
    private final String name;
    private final String details;
    private int piece;

    public Item(String name, String details, int piece) {
        this.name = name;
        this.details = details;
        this.piece = piece;
    }

    public String getName() {
        return name;
    }



    public String getDetails() {
        return details;
    }



    public int getPieceint() {
        return piece;
    }

    public String getPieceString() {
        return String.valueOf(piece);
    }



    public void addPiece(int i){
        piece +=i;
    }
    public void removePiece(int i){
        piece -=i;
    }

    @Override
    public String toString() {
        return  name;
    }


}
