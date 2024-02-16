import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void printGame(char[][] game) {
        for (char[] mas : game) {
            for (char el : mas) {
                System.out.print(el);
            }
            System.out.println();
        }
    }

    public static void makeMove(char move, char[][] game, char currentPlayer) {
        int[] indices = getIndices(Character.toString(move));
        game[indices[0]][indices[1]] = currentPlayer;
    }

    private static int[] getIndices(String move) {
        switch (move) {
            case "1":
                return new int[]{4, 0};
            case "2":
                return new int[]{4, 2};
            case "3":
                return new int[]{4, 4};
            case "4":
                return new int[]{2, 0};
            case "5":
                return new int[]{2, 2};
            case "6":
                return new int[]{2, 4};
            case "7":
                return new int[]{0, 0};
            case "8":
                return new int[]{0, 2};
            case "9":
                return new int[]{0, 4};
            default:
                throw new IllegalArgumentException("Invalid move: " + move);
        }
    }

    public static boolean check(char[][] game) {
        boolean win = false;
        boolean lose = false;
        for (int i = 0; i < 5; i += 2) {
            int x1 = 0, x2 = 0, o1 = 0, o2 = 0;
            for (int j = 0; j < 5; j += 2) {
                if (game[i][j] == 'X') {
                    x1++;
                } else if (game[j][i] == 'X') {
                    x2++;
                } else if (game[i][j] == 'O') {
                    o1++;
                } else if (game[j][i] == 'O') {
                    o2++;
                }
            }
            if (x1 == 3 || x2 == 3) {
                System.out.println("You won!!!");
                win = true;
                break;
            } else if (o1 == 3 || o2 == 3) {
                System.out.println("You lost.");
                lose = true;
                break;
            }
        }
        if (game[4][0] == 'X' && game[2][2] == 'X' && game[0][4] == 'X') {
            System.out.println("You won!!!");
            win = true;
        } else if (game[4][4] == 'O' && game[2][2] == 'O' && game[0][0] == 'O') {
            System.out.println("You lost");
            lose = true;
        }
        return win || lose;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        char[][] game = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        List<Character> moves = new LinkedList<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));

        int count = 0;

        while (true) {
            char currentPlayer = (count % 2 == 0) ? 'X' : 'O';

            if (currentPlayer == 'X') {
                System.out.print("What's your move: ");
                char move = sc.nextLine().charAt(0);
                if (moves.contains(move)) {
                    makeMove(move, game, currentPlayer);
                    moves.remove(Character.valueOf(move));
                } else {
                    System.out.print("You can't make that move.\nYour options are: " + moves + "\n");
                    continue;
                }
            } else {
                System.out.println("Computer's move.");
                int ind = rnd.nextInt(moves.size());
                makeMove(moves.get(ind), game, currentPlayer);
                moves.remove(ind);
            }
            printGame(game);
            if (check(game)) {
                break;
            }
            count++;
        }
        sc.close();
    }
}
