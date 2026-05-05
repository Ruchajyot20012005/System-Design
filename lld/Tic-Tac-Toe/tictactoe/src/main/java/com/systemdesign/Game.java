package com.systemdesign;

import com.systemdesign.evaluator.EvaluateGame;
import com.systemdesign.model.Board;
import com.systemdesign.model.Cell;
import com.systemdesign.model.Player;
import com.systemdesign.model.PlayingPiece;

import java.util.*;

public class Game {

    private Board board;
    private Deque<Player> players;
    private EvaluateGame evaluateGame;
    Set<String> usedNames = new HashSet<>();

    private String symbolAssignmentMsg = "Piece Assignments to Player : ";

    public Game(int size, int playerCount) {
        this.board = new Board(size);
        this.players = new ArrayDeque<>();
        evaluateGame = new EvaluateGame(size);

        createPlayers(playerCount);
    }

    private void createPlayers(int playerCount) {

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < playerCount; i++) {

            String name;

            while(true){
                System.out.println("Enter name for Player " + (i+1) + " : " );
                name = sc.nextLine().toLowerCase().trim();

                if (usedNames.contains(name)) {
                    System.out.println("Name already exists! Choose another.");
                } else if (name.isEmpty()) {
                    System.out.println("Name cannot be empty!");
                } else {
                    break;
                }
            }

            usedNames.add(name);

            char piece = SymbolProvider.getSymbol(i);
            players.add(new Player(name, new PlayingPiece(piece)));

        }

        for(Player player : players) {
            symbolAssignmentMsg += player.getName() + " -> " + player.getPiece().getSymbol() + "  ";
        }
    }

    public void start(){
        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println(symbolAssignmentMsg);
            board.printBoard();

            Player currentPlayer = players.pollFirst();

            System.out.println(currentPlayer.getName() + " enter position : ");
            char input = sc.next().toUpperCase().charAt(0);

            int idx = input - 'A';

            int row = idx / board.getSize();
            int col = idx % board.getSize();

            if(idx < 0 || idx > board.getSize() * board.getSize()) {
                System.out.println("Invalid position!!  Please try again....");
                players.addFirst(currentPlayer);
                continue;
            }

            if (!board.placePiece(row, col, currentPlayer.getPiece())) {
                System.out.println("Cell already occupied!");
                players.addFirst(currentPlayer);
                continue;
            }

            if(evaluateGame.isWinner(row, col, currentPlayer)) {
                board.printBoard();
                System.out.println(currentPlayer.getName() + " won!!!");
                break;
            }

            players.addLast(currentPlayer);

            if(isDrow()){
                board.printBoard();
                System.out.println("Game Drow!!!");
                break;
            }

        }
    }

    private boolean isDrow(){
        for(Cell[] row : board.getBoard()){
            for(Cell cell : row){
                if(cell.isEmpty()) return false;
            }
        }
        return true;
    }
}
