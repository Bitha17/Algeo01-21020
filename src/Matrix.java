import java.io.*;
import java.util.*;
// import java.lang.Math;

public class Matrix {
    private int row, col;
    private double[][] contents;
    
    /* *** CONSTRUCTOR *** */
    Matrix(int m, int n) {
        this.row = m;
        this.col = n;
        this.contents = new double[m][n];
    }

    /* *** SELECTOR *** */
    double getELMT(int row, int col) {
    /* Mengirimkan element Matrix(row,col) */
        return this.contents[row][col];
    }

    void setELMT(double x, int row, int col) {
    /* Melakukan assignment Matrix(row,col) <- x */
        this.contents[row][col] = x;
    }

    int getMatrixRow() {
    /* Mengirimkan jumlah baris pada Matrix */
        return this.row;
    }

    int getMatrixCol() {
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

    Matrix transpose() {
        int Mrows, Mcols, i, j;
        Mrows = this.row;
        Mcols = this.col;
        Matrix m = new Matrix(Mrows, Mcols);
        for (i = 0; i < Mrows; i++) {
            for (j = 0; j < Mcols; j++) {
                m.setELMT(this.getELMT(j, i), i, j);
            }
        }
        return m;
    }

    /* *** OBE *** */
    void GaussOBE() {
        int i, j; 
        i = 0;
        j = 0;       
        while (j < this.row) {
            Boolean searchForOne = false;
            if (this.contents[i][j] == 0) {
                Boolean seacrhForZero = false;
                int nextRow = i + 1;
                while (nextRow < this.row && !seacrhForZero) {
                    if (this.contents[nextRow][j] != 0) {
                        seacrhForZero = true;
                        for (int k = 0; k < this.col; k++) {
                            double temp = this.contents[nextRow][k];
                            this.contents[nextRow][k] = this.contents[i][k];
                            this.contents[i][k] = temp; 
                        }
                    } 
                    nextRow++;
                }
            }
            if (this.contents[i][j] != 0) {
                double temp = this.contents[i][j];
                for (int col = 0; col < this.col; col++) {
                    this.contents[i][col] /= temp;
                }
                searchForOne = true;

                double factor;
                int nextRow = i + 1;
                while (nextRow < this.row) {
                    factor = this.contents[nextRow][j];
                    double val;
                    for (int k = 0; k < this.col; k++) {
                        val = this.contents[i][k] * factor;
                        this.contents[nextRow][k] -= val;
                    }
                    nextRow++;
                }
            }
            if (searchForOne) {
                i++;
            }
            if (i >= this.row) {
                break;
            }
            j++;
        }
    }

    void GaussJordanOBE() {
        /* Membentuk matrix eselon dengan 1 utama */
        this.GaussOBE();
        for (int i = this.row - 1; i >= 0; i++) {
            for (int j = this.row - 1; j >= 0; j++) {
                if (this.contents[i][j] == 1) {
                    double factor;
                    int nextRow = i - 1;
                    while (nextRow >= 0) {
                        factor = this.contents[nextRow][j];
                        double val;
                        for (int k = 0; k < this.col; k++) {
                            val = this.contents[i][k] * factor;
                            this.contents[nextRow][k] -= val;
                        }
                        nextRow--;
                    }
                }
            }
        }
    }

    /* *** Inverse Matrix *** */
    Matrix inverseOBE() {
        Matrix m = new Matrix(this.row, this.col * 2);
        for (int i = 0; i < m.getMatrixRow(); i++) {
            for (int j = 0; j < m.getMatrixCol(); j++) {
                if (j < this.col) {
                    m.setELMT(this.contents[i][j], i, j);
                } 
                else if (i == j - this.col) {
                    m.setELMT(1, i, j);   
                } else {  
                    m.setELMT(0, i, j);
                }
            }
        }
        m.GaussJordanOBE();
        Matrix mResult = new Matrix(this.row, this.col);
        for (int i = 0; i < m.getMatrixRow(); i++) {
            for (int j = m.getMatrixRow(); j < m.getMatrixCol(); j++) {
                mResult.setELMT(m.getELMT(i, j), i, j - this.col);
            }
        }
        return mResult; 
    }

    Matrix inverseCofactor() {
        Matrix mResult, mCofactor, mTemp; 
        /* Membuat matrix kofaktor */
        mCofactor = new Matrix(this.row, this.col);
        /* Menyimpan hasil transpose matriks kofaktor */
        mTemp = new Matrix(this.row, this.col);
        /* mResult menyimpan hasil inverse */
        mResult = new Matrix(this.row, this.col);
        if (this.detCofactor() == 0) {
            System.out.println("Matrix tidak memiliki invers");
            return null;
        } else {
            double onePerDet = 1 / this.detCofactor();
            int sign = 1;
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    Matrix mSmall = new Matrix(this.row - 1, this.col - 1);
                    double a = this.contents[i][j];
                    for (int k = 0; k < mSmall.getMatrixRow(); k++) {
                        for (int l = 0; l < mSmall.getMatrixCol(); l++) {
                            if (l < i) {
                                mSmall.setELMT(this.contents[k+1][l], k, l); 
                            } else {
                                mSmall.setELMT(this.contents[k+1][l+1], k, l); 
                            }
                        }
                    }
                    mCofactor.setELMT(sign * a * mSmall.detCofactor(), i, j);
                    sign *= (-1);
                }
            }
            // pMultiplyConst
            mTemp = mCofactor.transpose(); /* Membentuk matriks adjoin */
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    mResult.setELMT(onePerDet * mTemp.getELMT(i, j), i, j);
                }
            }
            return mResult;
        }
    }
    
    /* Solusi SPL dengan metode Inverse, GaussEquation, GaussJordanEquation, dan Kaidah Crammer */
    void SPLInverse() {
        if (this.row != this.col - 1) {
            System.out.println("Matrix tidak valid! Dibutuhkan " + (this.col - 1) + " buah persamaan untuk " + (this.col - 1) + " peubah");
        } else {
            Matrix mTemp, mB, mResult;
            /* Menyimpan matriks A */
            mTemp = new Matrix(this.row, this.col-1);
            /* Menyimpan array B */
            mB = new Matrix(this.row, 1);
            /* Array hasil solusi x */
            mResult = new Matrix(this.row, 1); 
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col-1; j++) {
                    mTemp.setELMT(this.contents[i][j], i, j);
                }
            } 
            if (mTemp.detCofactor() == 0.0) {
                System.out.println("Matrix tidak memiliki inverse, tidak dapat menemukan solusi SPL dengan inverse!");
            // else {
            //     mTemp = mTemp.inverseCofactor();
            //     for (int i = 0; i < this.row; i++) {
            //         int j = this.col-1;
            //         mB.setElmt;
            //     }
                // multiplyMatrix -> result;
            }
        }
    }
}

