# 🎮 Tic Tac Toe - Low Level Design (LLD)

A scalable console-based Tic-Tac-Toe system supporting:
- NxN board
- Multi-player support
- Dynamic symbol assignment
- O(1) winner detection
- Clean modular design

---

# 🧠 High-Level Idea

The system is designed to separate responsibilities:

- **Main layer (TicTacToe)** → Input handling & validation
- **Game layer** → Game flow management
- **Board layer** → State storage
- **Evaluator layer** → Winner detection logic
- **Player layer** → Player abstraction
- **SymbolProvider** → Symbol management

👉 This ensures the system is:
- Modular
- Extendable
- Easy to test
- Easy to enhance (AI, APIs, GUI)

---

# 🎯 Key Design Goals

✔ Support multiple players (not just X and O)  
✔ NxN board instead of fixed 3x3  
✔ Efficient winner detection (O(1))  
✔ Clean separation of concerns  
✔ Easy future extension (AI / API / GUI)

---

# 🧠 Design Principles

- Separation of Concerns
- Encapsulation
- Open for extension
- Single Responsibility Principle
- Scalable multiplayer design

---

# 🧾 UML Diagram

                    +----------------------+
                    |     TicTacToe        |
                    |----------------------|
                    | + main()             |
                    +----------+-----------+
                               |
                               v
                    +----------------------+
                    |        Game          |
                    |----------------------|
                    | - Board board        |
                    | - List<Player>       |
                    | - GameEvaluator      |
                    |----------------------|
                    | + start()            |
                    | + createPlayers()|
                    +----------+-----------+
                               |
           +-------------------+-----------------------+
           |                   |                       |
           v                   v                       v
    +----------------+    +-----------------+    +-------------------------+
    | Board          |    | Player          |    | GameEvaluator           |
    |----------------|    |-----------------|    |-------------------------|
    | - Cell[][]     |    | - name          |    | - Map<Character,int[]>  |
    | - size         |    | - PlayingPiece  |    | - Map<Character,Integer>|
    |----------------|    |-----------------|    | - diagonal              |
    | + placePiece() |    |                 |    | - antiDiagonal          |
    | + printBoard() |    +-----------------+    |-------------------------|
    +------+---------+                           | + checkWinner()         |
            |                                    +-------------------------+
            v
    +----------------------+
    |   Cell               |
    |----------------------|
    | - int row            |
    | - int col            |
    | - char piece         |
    |----------------------|
    | + isEmpty()          |
    +----------------------+

    +----------------------+
    |   PlayingPiece       |
    |----------------------|
    | - char symbol        |
    +----------------------+

    +----------------------+
    |  SymbolProvider      |
    |----------------------|
    | - char[] SYMBOLS     |
    |----------------------|
    | + getSymbol()        |
    +----------------------+


---

# 🧩 Components

## 1. TicTacToe.java (Main)

- Takes input for board size and players
- Validates input
- Starts game

### Responsibilities:
- Input validation
- Game bootstrapping

---

## 2. Game.java

### Core engine:
- Manages turns
- Controls flow
- Checks win/draw

### Responsibilities:
- Controls game loop
- Manages player turns
- Delegates board updates
- Calls evaluator after each move

---

## 3. Board.java

### Responsibility:
- Maintains NxN grid
- Places moves
- Prints current state

### Internals:
- Uses `Cell[][]`
- Each cell holds a `PlayingPiece`

---

## 4. Cell.java

Represents a board cell:
- row
- col
- PlayingPiece

---

## 5. Player.java

Represents a player:
- name
- PlayingPiece

---

## 6. PlayingPiece.java

Represents player symbol:
- char symbol
- optional if you want to add color

---

## 7. GameEvaluator.java (🔥 Core Logic)

Uses O(1) winner detection:

Tracks:
```java id="eval1"
Map<Character, int[]> rows;
Map<Character, int[]> cols;
Map<Character, Integer> diagonal;
Map<Character, Integer> antiDiagonal;
```

How it works:

When a player places a move:

- Increment row count
- Increment column count
- If row == col → diagonal++
- If row + col == n - 1 → anti-diagonal++

Winning condition:
If any counter == board size → winner


---

## 8. SymbolProvider.java

Central symbol management:

```java
public class SymbolProvider {

    public static final char[] SYMBOLS = {
         'X', 'O', '△', '□', '*', '@', '#', '$', '&', '%'
    };

    public static char getSymbol(int index) {
        return SYMBOLS[index];
    }

    public static int maxSymbols() {
        return SYMBOLS.length;
    }
}
```
# 🔁 Game Flow

1. User enters board size
2. User enters number of players
3. Players are created
4. Symbols assigned from SymbolProvider
5. Game starts
6. Each player:
   - Inputs position (A, B, C...)
   - Move validated
   - Board updated
   - Winner checked (O(1))
7. Game ends:
   - Win OR Draw

---

# ⚙️ Validation Rules

- Board size ≥ 2
- Players ≥ 2
- Players ≤ size × size
- Players must not exceed available symbols or board capacity (Players ≤ SYMBOL_POOL size)
- Player names must be unique (case-insensitive and trimmed)

---

Overall, this design focuses on modularity, scalability, and real-world applicability, making it suitable for both interview scenarios and production-level extension.

# 🖥️ Console Walkthrough (Sample Run)

Below is a sample interaction showing how the system behaves when the program runs.


```text

-----------------------------------------------------------------
Welcome to Tic - Tac - Toe Game!!!! 
-----------------------------------------------------------------

Board size should be at least 2
Enter size of board: 
0
Invalid board size!!! please try again.....
Board size should be at least 2
Enter size of board: 
3
Maximum 10 Players can pla. And Player count should be at least 2
Enter players count: 
1
Invalid count of players!!! please try again.....
Maximum 10 Players can pla. And Player count should be at least 2
Enter players count: 
2
Enter name for Player 1 : 
p1
Enter name for Player 2 : 
p2

-----------------------------------------------------------------

Let's Start The Game !!! Let's Tic - Tac - Toe !!!! 

-----------------------------------------------------------------

Piece Assignments to Player : p1 -> X  p2 -> O  
[A] | [B] | [C] | 
[D] | [E] | [F] | 
[G] | [H] | [I] | 
p1 enter position : 
A
Piece Assignments to Player : p1 -> X  p2 -> O  
X | [B] | [C] | 
[D] | [E] | [F] | 
[G] | [H] | [I] | 
p2 enter position : 
i
Piece Assignments to Player : p1 -> X  p2 -> O  
X | [B] | [C] | 
[D] | [E] | [F] | 
[G] | [H] | O | 
p1 enter position : 
c
Piece Assignments to Player : p1 -> X  p2 -> O  
X | [B] | X | 
[D] | [E] | [F] | 
[G] | [H] | O | 
p2 enter position : 
G
Piece Assignments to Player : p1 -> X  p2 -> O  
X | [B] | X | 
[D] | [E] | [F] | 
O | [H] | O | 
p1 enter position : 
B
X | X | X | 
[D] | [E] | [F] | 
O | [H] | O | 
p1 won!!!
```
---