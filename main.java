import java.util.Scanner;

public class main {

    public static final String BLACK = "█";
    public static final String WHITE = " ";
    public static final String EMPTY = "#";

    public static void main(String[] args) {
        String[][] board = new String[8][8];

        String turn = BLACK;
        String opponent = WHITE;

        int Y = 0;
        int X = 0;

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
            if (count(board, EMPTY) == 0) {
                System.out.println("black team score: " + String.valueOf(count(board, BLACK)));
                System.out.println("black team score: " + String.valueOf(count(board, WHITE)));
                break;
            }
            while (true) {
                display(board);
                Scanner scanner = new Scanner(System.in);

                System.out.println("it is " + turn + "'s turn");
                System.out.print("enter the Y cord: ");
                while (true) {
                    if (scanner.hasNextInt()) {
                        Y = scanner.nextInt();
                        if (Y > 7) {
                            System.out.println("Please neter a number from 0-7");
                            scanner.next();
                            continue;
                        }
                        break;
                    } else {
                        System.out.println("Please neter a NUMBER from 0-7");
                        scanner.next();
                    }
                }

                System.out.print("enter the X cord: ");
                while (true) {
                    if (scanner.hasNextInt()) {
                        X = scanner.nextInt();
                        if (X > 7) {
                            System.out.println("Please neter a number from 0-7");
                            scanner.next();
                            continue;
                        }
                        break;
                    } else {
                        System.out.println("please neter a NUMBER from 0-7");
                        scanner.next();
                    }
                }

                if (is_possible(board, turn, X, Y)) {
                    board = (String[][]) place(board, turn, X, Y, opponent);
                    break;
                } else {
                    System.out.println("Illegal move, please try again");
                }
            }
        if (turn.equals(BLACK)) {
            turn = WHITE;
            opponent = BLACK;
        } else {
            turn = BLACK;
            opponent = WHITE;
        }
        for (int i = 0; i < 50; ++i) System.out.println();
        }
    }

    public static Object place(String[][] board, String turn, int x_in, int y_in, String opponent) {
        int y_num = 0;
        int x_num = 0;

        String[][] temp_board = deep_copy(board);

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (y == 0 && x == 0) {
                    continue;
                }
                y_num = y_in + y;
                x_num = x_in + x;
                while (true) {
                    try {
                        if (board[y_num][x_num].equals(turn)) {
                            board = deep_copy(temp_board);
                        } if (board[y_num][x_num].equals(EMPTY)) {
                            temp_board = deep_copy(board);
                            break;
                        } else {
                            temp_board[y_num][x_num] = turn;
                            y_num += y;
                            x_num += x;
                        }
                    } catch (Exception e) {
                        temp_board = deep_copy(board);
                        break;
                    }
                }
            }
        }
        board[y_in][x_in] = turn;
        return board;
    }

    public static boolean is_possible(String[][] board, String turn, int x_in, int y_in) {
        int y_num = 0;
        int x_num = 0;
        boolean enemy_hit = false;

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (y == 0 && x == 0) {
                    continue;
                }
                y_num = y_in + y;
                x_num = x_in + x;
                while (true) {
                    try {
                        if (board[y_num][x_num].equals(turn)) {
                            if (enemy_hit) {
                                return true;
                            }
                        } if (board[y_num][x_num].equals(EMPTY)) {
                            enemy_hit = false;
                            break;
                        } else {
                            enemy_hit = true;
                            y_num += y;
                            x_num += x;
                        }
                    } catch (Exception e) {
                        enemy_hit = false;
                        break;
                    }
                }
            }
        }
    return false;
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
    public static String[][] deep_copy(String[][] old_array) {
        int num1 = old_array.length;
        String[][] new_array = new String[8][8];

        for (int y = 0; y < num1; y++) {
            for (int x = 0; x < old_array[y].length; x++) {
                new_array[y][x] = old_array[y][x];
            }
        }
        return new_array;
    }
}
