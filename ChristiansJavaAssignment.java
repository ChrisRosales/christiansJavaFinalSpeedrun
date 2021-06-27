import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

public class ChristiansJavaAssignment {
  public static String[][] board = new String[3][3];

  public static void loadRecord() {
    Scanner input = new Scanner(System.in);
    System.out.println("Paste the path in here for which file you would like to read");
    String path = input.nextLine();
    String content = Files.readString(path, StandardCharsets.US_ASCII);

  }

  public static void editRecord() {
    // here we edit a record to check
  }

  public static void saveRecord() {
    // here we save a record to disk
  }

  public static void checkQuitStr(String val) {
    if (val.equals("3")) {
      System.exit(0);
    }
  }

  public static void checkQuitInt(int val) {
    if (val == 3) {
      System.exit(0);
    }
  }

  public static void resetBoard(String[][] board) {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        board[row][col] = "-";
      }
    }
  }

  public static void printBoard(String[][] board) {
    int i = 0;
    System.out.println("\nColumn 012");
    for (int row = 0; row < board.length; row++) {
      System.out.print("Row " + i + "  ");
      for (int col = 0; col < board[row].length; col++) {
        System.out.print(board[row][col]);
      }
      i += 1;
      System.out.println();
    }
    System.out.println();
  }

  public static void letterInput(String letter, int row, int column) {
    Scanner input = new Scanner(System.in);
    while (true) {
      if (board[row][column] == "-") {
        board[row][column] = letter;
        break;
      } else {
        System.out.print("A Letter is already there! Please enter a new value");
        letter = input.next();
        checkQuitStr(letter);
      }
    }
  }

  public static boolean checkBoard(String[][] board) {// didn't wanna hardcode this method lol
    // x,x,x 0,0 ; 0,1 ; 0,2
    // x,o,o
    // o,o,x
    int row = 0;
    int column = 0;
    while (row < 2) {// this checks the row if any are equal
      if (board[row][column] != "-" && board[row][column + 1] != "-" && board[row][column + 2] != "-") {
        if (board[row][column] == board[row][column + 1] && board[row][column] == board[row][column + 2]) {
          return true;
        }
      }
      row += 1;
    }
    row = 0;
    while (column < 2) {// this checkthe column if any are equal
      if (board[row][column] != "-" && board[row + 1][column] != "-" && board[row + 2][column] != "-") {
        if (board[row][column] == board[row + 1][column] && board[row][column] == board[row + 2][column]) {
          return true;
        }
      }
      column += 1;
    }
    column = 0;
    // these check if any of the diagonals are equal
    if (board[row][column] != "-" && board[row + 1][column + 1] != "-" && board[row + 2][column + 2] != "-") {
      if (board[row][column] == board[row + 1][column + 1] && board[row][column] == board[row + 2][column + 2]) {
        return true;
      }
    }
    if (board[row][column + 2] != "-" && board[row + 1][column + 1] != "-" && board[row + 2][column + 2] != "-") {
      if (board[row][column + 2] == board[row + 1][column + 1] && board[row][column + 2] == board[row + 2][column]) {
        return true;
      }
    }
    return false;

  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    boolean firstGame = true; // don't forget to set this to false at the end of the game
    String player1 = "";
    String player2 = "";
    String letter1 = "";
    String letter2 = "";
    while (true) {
      if (firstGame == false) {
        System.out.println("Would you like to play again? 1 for yes, 0 for no");
        int option = input.nextInt();
        checkQuitInt(option);
      }
      System.out
          .println("Welcome to Christians tic tac toe game, you can break out of the game anytime by typing in 3");
      resetBoard(board);
      printBoard(board); // temp checker
      System.out.println("Type in your names");
      System.out.println("Player 1: ");
      player1 = input.next();
      checkQuitStr(player1);
      System.out.println("Would you like 'X' or 'O'");
      letter1 = input.next();
      checkQuitStr(letter1);
      System.out.println("Player 2: ");
      player2 = input.next();
      checkQuitStr(player2);
      if (letter1.equals("X")) {
        letter2 = "O";
      } else {
        letter2 = "X";
      }
      System.out.println("Player 1 is: " + player1 + " with " + letter1 + "'s" + " and Player 2 is: " + player2
          + " with " + letter2 + "'s");
      System.out.println();
      int turn = 0;
      int row = 0;
      int column = 0;
      while (true) {
        // check conditions for winning here
        row = 0;
        column = 0;
        if (turn == 0) {
          System.out.println(
              "Player 1, your move.. type in the row followed by the column seperated by enter, type 3 to quit out");
          row = input.nextInt();
          checkQuitInt(row);
          column = input.nextInt();
          checkQuitInt(column);
          letterInput(letter1, row, column);
        }
        if (turn == 1) {
          System.out.println(
              "Player 2, your move.. type in the row followed by the column seperated by enter, type 3 to quit out");
          row = input.nextInt();
          checkQuitInt(row);
          column = input.nextInt();
          checkQuitInt(column);
          letterInput(letter2, row, column);
        }
        printBoard(board);
        if (checkBoard(board)) {
          if (turn == 0) {
            System.out.println(player1 + " Wins!");
          }
          if (turn == 1) {
            System.out.println(player2 + " Wins!");
          }
          firstGame = false;
          break;
        }
        if (turn == 0) {
          turn = 1;
        } else {
          turn = 0;
        }
      }

    }

  }
}
