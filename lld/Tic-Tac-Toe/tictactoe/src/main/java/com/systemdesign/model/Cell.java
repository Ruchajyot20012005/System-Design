package com.systemdesign.model;

public class Cell {
    private int row;
    private int col;
    private PlayingPiece piece;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public void setPiece(PlayingPiece piece) {
        this.piece = piece;
    }

    public PlayingPiece getPiece() {
        return piece;
    }




}
