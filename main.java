import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String BLACK = "█";
        String WHITE = " ";
        String EMPTY = "#";
        String[][] board = new String[8][8];

        int place_output = 0;
        int num = 0;
        String turn = BLACK;
        String opponent = WHITE;

        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                board[row][col] = EMPTY;
            }
        }

        board[3][3] = BLACK;
        board[4][4] = BLACK;
        board[3][4] = WHITE;
        board[4][3] = WHITE;

        while (true) {
            display(board);
            if (count(board, EMPTY) == 0) {
                System.out.println("black team score: " + String.valueOf(count(board, BLACK)));
                System.out.println("black team score: " + String.valueOf(count(board, WHITE)));
                break;
            }
            while (true) {
                Scanner scanner = new Scanner(System.in);

                System.out.print("enter the Y cord: ");
                int Y = scanner.nextInt();

                System.out.print("enter the X cord: ");
                int X = scanner.nextInt();

                String[][] old_board = new String[8][8];
                old_board = deep_copy(board, old_board);

                place_output = place(EMPTY, BLACK, WHITE, board, turn, X, Y, opponent, old_board);
                if (place_output == 1) {
                    break;
                } else {
                    System.out.println("invalid input, please try again");
                    display(board);
                }
            }
            if (turn.equals(BLACK)) {
                opponent = BLACK;
                turn = WHITE;
            } else if (turn.equals(WHITE)) {
                opponent = WHITE;
                turn = BLACK;
            }
        }
    }

    public static int place(String EMPTY, String BLACK, String WHITE, String[][] board, String turn, int x_in, int y_in, String opponent, String[][] old_board) {

        String[][] new_board = new String[8][8];
        new_board = deep_copy(board, new_board);

        new_board[y_in][x_in] = turn;

        int x_num;
        int y_num;

        boolean is_possible = false;
        boolean final_is_possible = false;

        String[][] temp_board = new String[8][8];
        temp_board = deep_copy(board, temp_board);

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                System.out.println(y);
                System.out.println(x);
                y_num = y_in + y;
                x_num = x_in + x;

                try {
                    new_board[y_num][x_num].equals(opponent);
                } catch(Exception e) {
                    continue;
                }
                //TODO: REWRITE FUNCTION
                // COPY CORDS TO A LIST AND THEN CHECK CORDS TO LIST TO SEE IF ITS
                while (true) {
                    System.out.println("----------------------------------------------------------------------------------");
                    System.out.println(y_num);
                    System.out.println(x_num);
                    display(new_board);
                    display(temp_board);
                    if (new_board[y_num][x_num].equals(opponent)) {
                        temp_board[y_num][x_num] = turn;
                        y_num += y;
                        x_num += x;
                        is_possible = true;
                    } else if (new_board[y_num][x_num].equals(turn)) {
                        if (is_possible) {
                            final_is_possible = true;
                            new_board = temp_board;
                            System.out.println("VICTORY!");
                            break;
                        } else {
                            is_possible = false;
                            temp_board = new_board;
                            break;
                        }
                    } else {
                        is_possible = false;
                        temp_board = new_board;
                        break;
                    }
                }
            }
        }
        if (final_is_possible) {
            board = deep_copy(temp_board, board);
            return 1;
        } else {
            return 0;
        }
    }

    public static void display(String[][] board) {
        System.out.println("    0   1   2   3   4   5   6   7   X");
        System.out.println("  ┌───┬───┬───┬───┬───┬───┬───┬───┐");
        for (int row = 0; row <= 7; row++) {
            System.out.print(String.valueOf(row) + " │ ");
            for (int col = 0; col <= 7; col++) {
                System.out.print(board[row][col]);
                System.out.print(" │ ");
            }
            if (row != 7) {
                System.out.println("\n  ├───┼───┼───┼───┼───┼───┼───┼───┤");
            } else {
                System.out.println("\n  └───┴───┴───┴───┴───┴───┴───┴───┘");
                System.out.println("Y\n");
            }
        }
    }
    public static int count(String[][] array, String item) {
        int count = 0;

        for (int X = 0; X < array.length; X++) {
            for (int Y = 0; Y < array[X].length; Y++) {
                if (array[X][Y].equals(item)) {
                    count++;
                }
            }
        }
        return count;
    }
    public static String[][] deep_copy(String[][] old_array, String[][] new_array) {
        int num1 = old_array.length;
        int num2 = new_array.length;

        for (int y = 0; y < num1; y++) {
            for (int x = 0; x < num2; x++) {
                new_array[y][x] = old_array[y][x];
            }
        }
        return new_array;
    }
}
