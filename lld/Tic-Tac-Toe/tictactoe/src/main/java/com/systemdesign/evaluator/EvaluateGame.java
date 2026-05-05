package com.systemdesign.evaluator;

import com.systemdesign.model.Player;
import com.systemdesign.model.PlayingPiece;

import java.util.HashMap;
import java.util.Map;

public class EvaluateGame {

    private int size;
    private Map<Character, int[]> rows;
    private Map<Character, int[]> cols;
    private Map<Character, Integer> diagonal;
    private Map<Character, Integer> antiDiagonal;

    public EvaluateGame(int size){
        this.size = size;
        rows = new HashMap<Character, int[]>();
        cols = new HashMap<Character, int[]>();
        diagonal = new HashMap<>();
        antiDiagonal = new HashMap<>();
    }

    public boolean isWinner(int row, int col, Player player){

        char piece = player.getPiece().getSymbol();

        rows.putIfAbsent(piece, new int[size]);
        cols.putIfAbsent(piece, new int[size]);
        diagonal.putIfAbsent(piece, 0);
        antiDiagonal.putIfAbsent(piece, 0);

        rows.get(piece)[row]++;
        cols.get(piece)[col]++;

        if(row == col){
            diagonal.put(piece, diagonal.get(piece) + 1);
        }

        if(row + col == size - 1){
            antiDiagonal.put(piece, antiDiagonal.get(piece) + 1);
        }

        return rows.get(piece)[row] == size ||
                cols.get(piece)[col] == size ||
                diagonal.get(piece) == size ||
                antiDiagonal.get(piece) == size;
    }
}
