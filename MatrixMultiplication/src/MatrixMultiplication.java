//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MatrixMultiplication {
    public static void main(String[] args) {
        // --- Example 1: Compatible Matrices ---
        int[][] matrixA1 = {
                {1, 2, 3},
                {4, 5, 6}
        }; // 2x3 matrix

        int[][] matrixB1 = {
                {7, 8},
                {9, 1},
                {2, 3}
        }; // 3x2 matrix

        System.out.println("--- Example 1: Compatible Matrices ---");
        System.out.println("Matrix A1:");
        printMatrix(matrixA1);
        System.out.println("Matrix B1:");
        printMatrix(matrixB1);

        int[][] result1 = multiplyMatrices(matrixA1, matrixB1);
        if (result1 != null) {
            System.out.println("Result of A1 * B1:");
            printMatrix(result1);
        }
        System.out.println("\n");

        // --- Example 2: Incompatible Matrices ---
        int[][] matrixA2 = {
                {1, 2},
                {3, 4}
        }; // 2x2 matrix

        int[][] matrixB2 = {
                {5, 6, 7},
                {8, 9, 0},
                {1, 2, 3}
        }; // 3x3 matrix

        System.out.println("--- Example 2: Incompatible Matrices ---");
        System.out.println("Matrix A2:");
        printMatrix(matrixA2);
        System.out.println("Matrix B2:");
        printMatrix(matrixB2);

        int[][] result2 = multiplyMatrices(matrixA2, matrixB2); // This will print an error message
        if (result2 == null) {
            System.out.println("Matrix multiplication failed as expected due to incompatible dimensions.");
        }
        System.out.println("\n");

        // --- Example 3: Another Compatible Example ---
        int[][] matrixA3 = {
                {1, 0},
                {0, 1}
        }; // 2x2 identity matrix

        int[][] matrixB3 = {
                {5, 2},
                {3, 4}
        }; // 2x2 matrix

        System.out.println("--- Example 3: Another Compatible Example ---");
        System.out.println("Matrix A3:");
        printMatrix(matrixA3);
        System.out.println("Matrix B3:");
        printMatrix(matrixB3);

        int[][] result3 = multiplyMatrices(matrixA3, matrixB3);
        if (result3 != null) {
            System.out.println("Result of A3 * B3:");
            printMatrix(result3);
        }
    }

    public static int[][] convertStringToMatrix(String matrixString) {
        if (matrixString == null || matrixString.trim().isEmpty()) {
            System.err.println("Error: Input matrix string is empty or null.");
            return null;
        }

        // Split the string into rows based on the semicolon delimiter
        String[] rowStrings = matrixString.split(";");

        if (rowStrings.length == 0) {
            System.err.println("Error: No rows found in the matrix string.");
            return null;
        }

        // Determine the number of columns from the first row
        String[] firstRowElements = rowStrings[0].split(",");
        int numCols = firstRowElements.length;

        // Initialize the 2D array with the determined dimensions
        int[][] matrix = new int[rowStrings.length][numCols];

        // Iterate through each row string
        for (int i = 0; i < rowStrings.length; i++) {
            String rowStr = rowStrings[i];
            String[] elements = rowStr.split(",");

            // Check for consistent number of columns in each row
            if (elements.length != numCols) {
                System.err.println("Error: Inconsistent number of columns in row " + i + ".");
                System.err.println("Expected " + numCols + " columns, but found " + elements.length + ".");
                return null; // Return null if dimensions are inconsistent
            }

            // Parse each element in the row
            for (int j = 0; j < elements.length; j++) {
                try {
                    matrix[i][j] = Integer.parseInt(elements[j].trim()); // Parse and trim whitespace
                } catch (NumberFormatException e) {
                    System.err.println("Error: Invalid number format in matrix string at row " + i + ", column " + j + ": " + elements[j]);
                    return null; // Return null if a non-integer is found
                }
            }
        }
        return matrix;
    }

    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        // Get dimensions of matrix A
        int rowsA = A.length;
        int colsA = A[0].length; // Assumes A is not empty and has at least one row

        // Get dimensions of matrix B
        int rowsB = B.length;
        int colsB = B[0].length; // Assumes B is not empty and has at least one row

        // Check for compatibility: Number of columns in A must equal number of rows in B
        if (colsA != rowsB) {
            System.err.println("Error: Matrix dimensions are incompatible for multiplication.");
            System.err.println("Number of columns in first matrix (" + colsA + ") must equal ");
            System.err.println("number of rows in second matrix (" + rowsB + ").");
            return null; // Return null to indicate an error
        }

        // The resulting matrix C will have dimensions rowsA x colsB
        int[][] C = new int[rowsA][colsB];

        // Perform matrix multiplication
        // C[i][j] = sum(A[i][k] * B[k][j]) for k from 0 to colsA-1 (or rowsB-1)
        for (int i = 0; i < rowsA; i++) { // Iterate over rows of C (and A)
            for (int j = 0; j < colsB; j++) { // Iterate over columns of C (and B)
                for (int k = 0; k < colsA; k++) { // Iterate over columns of A (and rows of B)
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C; // Return the resulting product matrix
    }

    /**
     * Helper method to print a matrix for demonstration purposes.
     * @param matrix The matrix to print.
     */
    public static void printMatrix(int[][] matrix) {
        if (matrix == null) {
            System.out.println("Matrix is null.");
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t"); // Print element followed by a tab
            }
            System.out.println(); // New line after each row
        }
    }
}