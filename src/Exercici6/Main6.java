public class Main6 {

    public static void main(String[] args) {

        int rowsA = 8;
        int colsA = 8;
        int rowsB = 8;
        int colsB = 8;

        if (colsA != rowsB) {
            System.out.println("No es poden multiplicar, dimensions incompatibles.");
            return;
        }

        int[][] A = MatrixUtils.randomMatrix(rowsA, colsA);
        int[][] B = MatrixUtils.randomMatrix(rowsB, colsB);
        int[][] result = new int[rowsA][colsB];

        int numThreads = 4;
        WorkerThread[] threads = new WorkerThread[numThreads];

        int rowsPerThread = rowsA / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int startRow = i * rowsPerThread;
            int endRow = (i == numThreads - 1)
                    ? rowsA
                    : startRow + rowsPerThread;

            threads[i] = new WorkerThread(A, B, result, startRow, endRow);
            threads[i].start();
        }

        // Esperar a que tots els fils acabin
        for (WorkerThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar el resultat
        System.out.println("Matriu A:");
        MatrixUtils.printMatrix(A);
        System.out.println("\nMatriu B:");
        MatrixUtils.printMatrix(B);
        System.out.println("\nResultat A x B:");
        MatrixUtils.printMatrix(result);

        System.out.println("\nMultiplicació en paral·lel completada!");
    }
}
