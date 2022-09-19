public class Matrix {
    private int row, col;
    private double[][] contents;
    
    /* *** CONSTRUCTOR *** */
    public Matrix(int m, int n) {
        this.row = m;
        this.col = n;
        this.contents = new double[m][n];
    }

    /* *** SELECTOR *** */
    public double getELMT(int row, int col) {
    /* Mengirimkan element Matrix(row,col) */
        return this.contents[row][col];
    }

    public void setELMT(double x, int row, int col) {
    /* Melakukan assignment Matrix(row,col) <- x */
        this.contents[row][col] = x;
    }

    public int getMatrixRow() {
    /* Mengirimkan jumlah baris pada Matrix */
        return this.row;
    }

    public int getMatrixCol() {
    /* Mengirimkan jumlah kolom pada Matrix */
        return this.col;
    }

    /* *** READ AND DISPLAY *** */

    /* *** TEST *** */
    
    /* *** OPERATIONS *** */


}

