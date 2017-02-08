/**
 * Created by Sydney on 8/3/2016.
 * This program solves a jumbo sudoku puzzles with backtracking
 */

import java.io.*;


public class Sudoku {
    public static void main (String[] args) throws IOException, ClassNotFoundException{
        long start = System.currentTimeMillis();
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("puzzle1.dat"));
        int[][] puzzle = (int[][]) reader.readObject();

        printPuzzle(puzzle);
        solver(puzzle, 0);
        printPuzzle(puzzle);

        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("solution.dat"));
        writer.writeObject(puzzle);
        long finish = System.currentTimeMillis();
        long seconds = (finish-start)/1000;
        long minutes = seconds/60;
        System.out.println( minutes + " minutes and " + seconds + " Seconds.");
    }
    private static boolean isPlaceable(int[][] array, int row, int column, int current) {
        //check current row
        for(int i = 0; i < array.length; i++) {
            if(array[row][i] == current) {
                return false;
            }
        }
        //check current column
        for (int i = 0; i < array.length; i++) {
            if (array[i][column] == current) {
                return false;
            }
        }
        //check box
        int rowStart = (row/4)*4;
        int columnStart = (column/4)*4;
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j <4; j++) {
                if(array[rowStart+i][columnStart+j] == current) {
                    return false;
                }
            }
        }
        //if we find the number in any of those things, return false (divide by 4 int, multiply by 4 to +3
        //check square
        //else...
        return true;
    }
    private static boolean solver(int[][] array, int startRow) {

        for (int i = startRow; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                //don't try and populate it if it's already got something there
                if (array[i][j] != 0) {
                    continue;
                }

                //attempt to populate with numbers 1 through 16(G)
                for (int k = 1; k <= 16; k++) {
                    if (isPlaceable(array, i, j, k)) {
                        array[i][j] = k;

                        //keep populating!
                        if (solver(array, i)) {
                            return true;
                        }
                        array[i][j] = 0;
                    }
                }
                return false;
            }
        }
        return true;
    }

    private static void printPuzzle(int[][] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print("[ ");
            for(int j = 0; j < array[i].length; j++) {
                if(array[i][j] == 0) {
                    System.out.print(". ");
                }
                else {
                    if(array[i][j] < 10) {
                        System.out.print(array[i][j] + " ");
                    }
                    else {
                        switch (array[i][j]) {
                            case 10:  System.out.print("A ");
                                break;
                            case 11:  System.out.print("B ");
                                break;
                            case 12:  System.out.print("C ");
                                break;
                            case 13:  System.out.print("D ");
                                break;
                            case 14:  System.out.print("E ");
                                break;
                            case 15:  System.out.print("F ");
                                break;
                            case 16:  System.out.print("G ");
                                break;
                        }
                    }
                }
                if (j == 3 || j == 7 || j == 11) {
                    System.out.print("| ");
                }
            }
            System.out.print("]");
            if(i == 3 || i == 7 || i == 11) {
                System.out.println();
                System.out.println(" ---------+---------+---------+---------");
            }
            else {
                System.out.println();
            }
        }
        System.out.println("\n");
    }
}
