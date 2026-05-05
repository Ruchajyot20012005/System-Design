package com.systemdesign.model;

public class Board {

    private int size;
    private Cell[][] board;

    public Board(int size) {
        this.size = size;
        board = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean placePiece(int row, int col, PlayingPiece piece) {
        if(!board[row][col].isEmpty()){
            return false;
        }
        board[row][col].setPiece(piece);
        return true;
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void printBoard() {
        char label = 'A';

        for(Cell[] row : board) {
            for(Cell cell : row) {
                if(cell.getPiece() == null) {
                    System.out.print("["+label + "] | ");
                }else{
                    System.out.print(cell.getPiece().getSymbol() + " | ");
                }
                label++;
            }
            System.out.println();
        }
    }
}
