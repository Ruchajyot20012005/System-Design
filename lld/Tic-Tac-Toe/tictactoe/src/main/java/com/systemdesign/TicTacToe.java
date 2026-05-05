package com.systemdesign;


import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int size = 0;
        int count = 0;

        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("Welcome to Tic - Tac - Toe Game!!!! ");
        System.out.println("-----------------------------------------------------------------\n");



        while(true){

            System.out.println("Board size should be at least 2");
            System.out.println("Enter size of board: ");
            size = sc.nextInt();

            if(size < 2){
                System.out.println("Invalid board size!!! please try again.....");
                continue;
            }

            break;

        }

        while(true){

            System.out.println("Maximum " + SymbolProvider.maxSymbols() +" Players can pla. And Player count should be at least 2");
            System.out.println("Enter players count: ");
            count = sc.nextInt();

            if(count < 2 || count > size * size || count > SymbolProvider.maxSymbols()){
                System.out.println("Invalid count of players!!! please try again.....");
                continue;
            }

            break;

        }


        Game game = new Game(size, count);

        System.out.println("\n-----------------------------------------------------------------\n");
        System.out.println("Let's Start The Game !!! Let's Tic - Tac - Toe !!!! ");
        System.out.println("\n-----------------------------------------------------------------\n");

        game.start();

        sc.close();

    }
}