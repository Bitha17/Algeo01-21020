import java.io.*;
import java.util.*;
// import java.lang.Math;

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

    /* *** READ, DISPLAY, SAVE TO FILE *** */
    void readMatrix() {
    /* Membaca matrix dari input keyboard */
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.col; j++){
                double x = in.nextDouble();
                setELMT(x, row, col);
            }
        }
        in.close();
    }

    void readMatrix2(File text) {
    /* Membaca matrix dari file */
        try { 
            Scanner in = new Scanner(text);
            for (int i = 0; i < this.row; i++){
                for (int j = 0; j < this.col; j++){
                    double x = in.nextDouble();
                    setELMT(x, row, col);
                }
            }
            in.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void displayMatrix() {
    /* Menampilkan matrix ke layar */
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.col; j++){
                System.out.print(this.contents[i][j] + " ");
            }
            System.out.println();
        }
    }

    void displaySPL() {
    /* Menampilkan matrix penyelesaian SPL  */
        for (int i = 1; i <= this.row; i++){
            System.out.println("X" + i + "=" + this.contents[i-1][0]);
        }
    }

    void matrixToFile(File output) {
    /* Menyimpan matrix ke dalam file */
        try{
            FileWriter writer = new FileWriter(output);
            for (int i = 0; i < this.row; i++){
                for (int j = 0; j < this.col; j++){
                    writer.write(Double.toString(this.contents[i][j]) + " ");
                }
                writer.write("\n");
            }
            System.out.println("File berhasil dibuat");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void detToFile(File output, double det) {
    /* Menyimpan hasil perhitungan determinan matriks ke dalam file */
        try{
            FileWriter writer = new FileWriter(output);
            writer.write("Determinan = " + Double.toString(det) + "\n");
            System.out.println("File berhasil dibuat");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void saveToFile(File output, String s){
    /* Menyimpan hasil lain-lain ke dalam file */
        try{
            FileWriter writer = new FileWriter(output);
            writer.write(s + "\n");
            System.out.println("File berhasil dibuat");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* *** TEST *** */
    
    /* *** OPERATIONS *** */
    double detCofactor(){
    /* Mengembalikan nilai determinan yang diperoleh dengan cara ekspansi kofaktor */
        if (this.row == 1){
            return this.contents[0][0];
        }
        else{
            double det = 0;
            int a = 1;
            for (int i = 0; i < this.row; i++) {
                Matrix m1 = new Matrix(this.row - 1, this.col - 1);
                for (int j = 0; j < m1.row; j++){
                    for (int k = 0; k < m1.col; k++){
                        if (k < i){
                            m1.contents[j][k] = this.contents[j+1][k];
                        }
                        else{
                            m1.contents[j][k] = this.contents[j+1][k+1];
                        }
                    }
                }
                det += a*this.contents[0][i]*m1.detCofactor();
                a *= -1;
            }
            return det;
        }
    }

    double detReduction(){
    /* Mengembalikan nilai determinan yang diperoleh dengan cara reduksi baris */
        int i, j, k, idx; 
        double temp, temp1, temp2;
        double[] tempRow = new double[this.row];
        int det = 1;
        int co = 1;
        for (i = 0; i < this.row; i++) {
            idx = i;
            while (this.contents[idx][i] == 0 && idx < this.row) {
                idx++;
            }
            if (idx == this.row) {
                return 0;
            }
            if (i != idx) {
                for (j = 0; j < this.row; j++) {
                    temp = this.contents[i][j];
                    this.contents[i][j] = this.contents[idx][j];
                    this.contents[idx][j] = temp;
                }
                det *= -1;
            }
            for (j = 0; j < this.row; j++) {
                tempRow[j] = this.contents[i][j];
            }
            for (j = i+1; j < this.row; j++) {
                temp1 = tempRow[i];
                temp2 = this.contents[j][i]; 
                for (k = 0; k < this.row; k++) {
                    this.contents[j][k] = temp1 * this.contents[j][k] - temp2 * tempRow[k];
                }
                co *= temp1;
            }
        }
        for (int l = 0; l < this.row; l++) {
            det *= this.contents[l][l];
        }
        return det/co;
    }
}