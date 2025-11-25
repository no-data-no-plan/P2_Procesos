class MatrixUtils {

    public static int[][] randomMatrix(int rows, int cols) {
        int[][] m = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = (int)(Math.random() * 10);
            }
        }
        return m;
    }

    public static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int v : row) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}