class WorkerThread extends Thread {
    private int[][] A;
    private int[][] B;
    private int[][] result;
    private int startRow;
    private int endRow;

    public WorkerThread(int[][] A, int[][] B, int[][] result, int startRow, int endRow) {
        this.A = A;
        this.B = B;
        this.result = result;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    public void run() {
        int nColsB = B[0].length;
        int nColsA = A[0].length;

        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < nColsB; j++) {
                int sum = 0;
                for (int k = 0; k < nColsA; k++) {
                    sum += A[i][k] * B[k][j];
                }
                result[i][j] = sum;
            }
        }
    }
}