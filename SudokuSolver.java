import java.util.Scanner;

public class SudokuSolver {

    // Method to print the Sudoku grid
    public static void printGrid(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Method to check if placing a number is safe according to Sudoku rules
    public static boolean isSafe(int[][] grid, int row, int col, int num) {
        // Check if the number is already present in the row
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num)
                return false;
        }

        // Check if the number is already present in the column
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == num)
                return false;
        }

        // Check if the number is already present in the 3x3 grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num)
                    return false;
            }
        }

        return true;
    }

    // Method to solve the Sudoku puzzle using recursion and backtracking
    public static boolean solveSudoku(int[][] grid) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // No empty space left
        if (isEmpty) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid)) {
                    return true;
                }
                grid[row][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] grid = new int[9][9];

        System.out.println("Enter the Sudoku puzzle (use 0 for empty cells):");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        if (solveSudoku(grid)) {
            printGrid(grid);
        } else {
            System.out.println("No solution exists!");
        }

        scanner.close();
    }
}
