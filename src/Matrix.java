import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class Matrix {
    private int row, col;
    private double[][] contents;
    public static final Scanner in = new Scanner(System.in);

    /* *** CONSTRUCTOR *** */
    Matrix(int m, int n) {
        this.row = m;
        this.col = n;
        this.contents = new double[m][n];
    }

    void setELMT(double x, int row, int col) {
        /* Melakukan assignment Matrix(row,col) <- x */
        this.contents[row][col] = x;
    }

    void setMatrixDim(int row, int col) {
        /* Mengganti ukuran matriks */
        this.row = row;
        this.col = col;
        this.contents = new double[row][col];
    }

    /* *** SELECTOR *** */
    double getELMT(int row, int col) {
        /* Mengirimkan element Matrix(row,col) */
        return this.contents[row][col];
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
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                double x = in.nextDouble();
                this.contents[i][j] = x;
            }
        }
    }

    void readMatrix2(File text) {
        /* Membaca matrix dari file */
        try {
            Scanner in = new Scanner(text);
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    double x = in.nextDouble();
                    setELMT(x, i, j);
                }
            }
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void readMatrix3(File text) {
        /* Membaca input dari file untuk Interpolasi Bicubic */
        try {
            Scanner in = new Scanner(text);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    double x = in.nextDouble();
                    this.setELMT(x, j * 4 + i, 0);
                }
            }
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    Double readDouble(File text, int idx) {
        /* Membaca variabel selain matriks dari File */
        try {
            Scanner in = new Scanner(text);
            Double x = 0.0;
            for (int i = 0; i < idx; i++) {
                x = in.nextDouble();
            }
            in.close();
            return x;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0.0;
        }
    }

    void displayMatrix() {
        /* Menampilkan matrix ke layar */
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.contents[i][j] + " ");
            }
            System.out.println();
        }
    }

    void displaySPL() {
        /* Menampilkan matrix penyelesaian SPL */
        for (int i = 1; i <= this.row; i++) {
            System.out.println("X" + i + "=" + this.contents[i - 1][0]);
        }
    }

    void splToFile(int choice, String s) {
        try {
            if (choice == 1) {
                System.out.print("Masukkan nama file beserta extension(.txt): ");
                FileWriter writer = new FileWriter("../test/" + in.nextLine());
                writer.write(s);
                System.out.println("File telah berhasil dibuat!");
                writer.close();
            } else {
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void matrixToFile(int choice) {
        /* Menyimpan matrix ke dalam file */
        try {
            if (choice == 1) {
                System.out.print("Masukkan nama file beserta extension(.txt): ");
                FileWriter writer = new FileWriter("../test/" + in.nextLine());
                for (int i = 0; i < this.row; i++) {
                    for (int j = 0; j < this.col; j++) {
                        writer.write(Double.toString(this.contents[i][j]) + " ");
                    }
                    writer.write("\n");
                }
                System.out.println("File telah berhasil dibuat!");
                writer.close();
            } else {
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void detToFile(int choice, double det) {
        /* Menyimpan hasil perhitungan determinan matriks ke dalam file */
        try {
            if (choice == 1) {
                String blank = in.nextLine();
                System.out.print("Masukkan nama file beserta extension(.txt): ");
                FileWriter writer = new FileWriter("../test/" + in.nextLine());
                writer.write("Determinan = " + Double.toString(det) + "\n");
                System.out.println("File telah berhasil dibuat!");
                writer.close();
            } else {
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void saveToFile(File output, String s) {
        /* Menyimpan hasil lain-lain ke dalam file */
        try {
            FileWriter writer = new FileWriter(output);
            writer.write(s + "\n");
            System.out.println("File berhasil dibuat");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *** OPERATIONS *** */
    Matrix multiplyMatrix(Matrix m1, Matrix m2) {
        /* Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2 */
        /* Mengirim hasil perkalian matriks: salinan m1 * m2 */
        Matrix m3 = new Matrix(m1.getMatrixRow(), m2.getMatrixCol());
        for (int i = 0; i < m3.row; i++) {
            for (int j = 0; j < m3.col; j++) {
                double temp = 0.0;
                for (int k = 0; k < m1.getMatrixCol(); k++) {
                    temp += (m1.getELMT(i, k) * m2.getELMT(k, j));
                }
                m3.setELMT(temp, i, j);
            }
        }
        return m3;
    }

    void multiplyConst(double konstanta) {
        /* I.S. m terdefinisi, k terdefinisi */
        /* F.S. Mengalikan setiap elemen m dengan k */
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.contents[i][j] *= konstanta;
            }
        }
    }

    Matrix transpose() {
        /* Mentranspose matriks */
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

    Boolean isRowZero(int row) {
        /* Mengembalikan 'true' jika seluruh elemen dalam baris bernilai '0' */
        for (int i = 0; i < this.row; i++) {
            if (this.contents[row][i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* *** OBE *** */
    void GaussOBE() {
        /* Melakukan operasi baris elementer metode Gauss pada matriks */
        int i, j;
        i = 0;
        j = 0;
        while (j < this.col - 1) {
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
        /* Melakukan operasi baris elementer metode Gauss Jordan pada matriks */
        /* Membentuk matriks eselon dengan 1 utama */
        this.GaussOBE();
        for (int i = this.row - 1; i >= 0; i--) {
            for (int j = this.col - 1; j >= 0; j--) {
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
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (this.contents[i][j] == -0.0) {
                    this.contents[i][j] = 0.0;
                }
            }
        }
    }

    /* *** SPL *** */
    /*
     * Solusi SPL dengan metode Inverse, GaussEquation, GaussJordanEquation, dan
     * Kaidah Cramer
     */
    String SPLInverse() {
        /* Menghasilkan solusi SPL dengan metode Inverse Matriks */
        if (this.row != this.col - 1) {
            System.out.println("SPL tidak dapat diselesaikan dengan metode invers");
            return "SPL tidak dapat diselesaikan dengan metode invers \n";
        } else {
            Matrix mTemp, mB, mResult;
            /* Menyimpan matriks A */
            mTemp = new Matrix(this.row, this.col - 1);
            /* Menyimpan array B */
            mB = new Matrix(this.row, 1);
            /* Array hasil solusi x */
            mResult = new Matrix(this.row, 1);
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col - 1; j++) {
                    mTemp.setELMT(this.contents[i][j], i, j);
                }
            }
            if (mTemp.detCofactor() == 0.0) {
                System.out.println("Matrix tidak memiliki inverse, tidak dapat menemukan solusi SPL dengan inverse!");
                return "Matrix tidak memiliki inverse, tidak dapat menemukan solusi SPL dengan inverse!";
            } else {
                mTemp = mTemp.inverseOBE();
                for (int i = 0; i < this.row; i++) {
                    mB.setELMT(this.contents[i][this.col - 1], i, 0);
                }
                mResult = multiplyMatrix(mTemp, mB);
                // mResult.multiplyConst(1/mTemp.detCofactor());
                mResult.displaySPL();
                String s = ("SOLUSI SPL \n");
                for (int i = 1; i <= mResult.row; i++) {
                    s += "X" + i + "=" + mResult.contents[i - 1][0] + "\n";
                }
                return s;
            }
        }
    }

    Matrix mSPL() {
        /* Mengembalikan matriks solusi SPL */
        Matrix mTemp, mB, mResult;
        /* Menyimpan matriks A */
        mTemp = new Matrix(this.row, this.col - 1);
        /* Menyimpan array B */
        mB = new Matrix(this.row, 1);
        /* Array hasil solusi x */
        mResult = new Matrix(this.row, 1);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col - 1; j++) {
                mTemp.setELMT(this.contents[i][j], i, j);
            }
        }
        mTemp = mTemp.inverseOBE();
        for (int i = 0; i < this.row; i++) {
            mB.setELMT(this.contents[i][this.col - 1], i, 0);
        }
        mResult = multiplyMatrix(mTemp, mB);
        // mResult.multiplyConst(1/mTemp.detCofactor());
        return mResult;
    }

    String SPLGauss() {
        /* Menghasilkan solusi SPL dengan GaussEquation */
        this.GaussOBE();
        Boolean singleSolution, manySolution, noSolution;
        singleSolution = false;
        manySolution = false;
        noSolution = false;
        Matrix mResult = new Matrix(this.row, 1);
        int row, col;
        row = this.row - 1;
        col = this.col - 1;

        // Check for no solution
        for (int i = 0; i < this.col - 1; i++) {
            if (this.contents[row][i] == 0) {
                if (this.contents[row][col] != 0) {
                    noSolution = true;
                    manySolution = false;
                }
            } else {
                noSolution = false;
                manySolution = true;
            }
        }

        // Check for single solution
        if (!noSolution) {
            if (this.contents[row][col - 1] != 0 && this.contents[row][col] != 0 && this.row == this.col - 1) {
                singleSolution = true;
                manySolution = false;
            } else {
                manySolution = true;
            }
        }
        String s = "";
        if (singleSolution) {
            double x = this.contents[row][col];
            mResult.setELMT(x, row, 0);
            double[] prevX = new double[this.row];
            prevX[this.row - 1] = x;
            for (int i = row - 1; i >= 0; i--) {
                x = this.contents[i][col];
                for (int j = col - 1; j > i; j--) {
                    x -= this.contents[i][j] * prevX[j];
                    prevX[i] = x;
                }
                mResult.setELMT(x, i, 0);
            }
            mResult.displaySPL();
            s = "SOLUSI SPL \n";
            for (int i = 1; i <= mResult.row; i++) {
                s += "X" + i + "=" + mResult.contents[i - 1][0] + "\n";
            }
            // return s;
        } else if (manySolution) {
            s = this.parametric();
            // return s;
        } else if (noSolution) {
            System.out.println("SPL tidak memiliki solusi");
            s = "SPL tidak memiliki solusi \n";
        }
        return s;
    }

    String SPLGaussJordan() {
        /* Menghasilkan solusi SPL dengan metode Gauss Jordan */
        this.GaussJordanOBE();
        int row = this.row - 1;
        int col = this.col - 1;
        boolean noSolutions = false;
        Matrix mResult = new Matrix(this.row, 1);
        for (int i = 0; i < this.col - 1; i++) {
            if (this.contents[row][i] != 0) {
                noSolutions = false;
                break;
            } else {
                if (this.contents[row][col] != 0) {
                    noSolutions = true;
                }
            }
        }

        String s = "";
        if (noSolutions) {
            System.out.println("SPL tidak memiliki solusi");
            s = "SPL tidak memiliki solusi";
        } else if (this.contents[row][col - 1] != 0 && this.contents[row][col] != 0 && this.row == this.col - 1) {
            // single solution
            for (int i = 0; i <= row; i++) {
                mResult.setELMT(this.contents[i][col], i, 0);
            }
            mResult.displaySPL();
            s = "SOLUSI SPL \n";
            for (int i = 1; i <= mResult.row; i++) {
                s += "X" + i + "=" + mResult.contents[i - 1][0] + "\n";
            }
        } else {
            // many solution
            s = this.parametric();
        }
        return s;
    }

    String SPLKaidahCramer() {
        /* Menghasilkan solusi SPL dengan memanfaatkan kaidah cramer */
        if (this.row != this.col - 1) {
            System.out.println("SPL tidak dapat diselesaikan dengan kaidah cramer");
            return "SPL tidak dapat diselesaikan dengan kaidah cramer";
        } else if (this.detCofactor() == 0) {
            System.out.println("SPL tidak dapat diselesaikan dengan kaidah cramer");
            return "SPL tidak dapat diselesaikan dengan kaidah cramer";
        } else {
            Matrix mDet = new Matrix(this.row, this.row);
            Matrix mResult = new Matrix(this.row, 1);
            double det = this.detCofactor();
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.row; j++) {
                    mDet.setELMT(this.contents[i][j], i, j);
                }
            }
            int j = 0;
            while (j < this.row) {
                for (int i = 0; i < this.row; i++) {
                    mDet.setELMT(this.contents[i][this.col - 1], i, j);
                }
                double detX = mDet.detCofactor();
                double x = detX / det;
                mResult.setELMT(x, j, 0);
                for (int i = 0; i < this.row; i++) {
                    for (int k = 0; k < this.row; k++) {
                        mDet.setELMT(this.contents[i][k], i, k);
                    }
                }
                j++;
            }
            mResult.displaySPL();
            String s = "SOLUSI SPL \n";
            for (int i = 1; i <= mResult.row; i++) {
                s += "X" + i + "=" + mResult.contents[i - 1][0] + "\n";
            }
            return s;
        }
    }

    /* *** Parametric Solution Case Handle *** */
    String parametric() {
        /* Handle case untuk solusi SPL parametrik */
        String[] parametrik = { "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
                "v", "w", "x", "y", "z" };
        this.GaussJordanOBE();
        Boolean[] isAllZero = new Boolean[this.row];
        Boolean[] konstanta = new Boolean[this.col - 1];
        for (int i = 0; i < this.row; i++) {
            isAllZero[i] = true;
        }
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col - 1; j++) {
                if (this.contents[i][j] != 0) {
                    isAllZero[i] = false;
                    break;
                }
            }
        }
        for (int i = 0; i < this.col - 1; i++) {
            konstanta[i] = false;
        }

        // Searching for the column that has leading entry
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col - 1; j++) {
                if (this.contents[i][j] == 1) {
                    konstanta[j] = true;
                    break;
                }
            }
        }

        String[] result = new String[this.col - 1];
        for (int k = 0; k < this.col - 1; k++) {
            result[k] = "0.0 ";
        }

        int k = 0;
        for (int j = 0; j < this.col - 1; j++) {
            if (!konstanta[j]) {
                result[j] = parametrik[k];
                k++;
            }
        }

        for (int i = 0; i < this.row; i++) {
            if (!isAllZero[i]) {
                int notZero = -9999;
                for (int j = 0; j < this.col - 1; j++) {
                    if (this.contents[i][j] == 1) {
                        notZero = j;
                        break;
                    }
                }

                result[notZero] = (this.contents[i][this.col - 1] != 0 ? (this.contents[i][this.col - 1] + " ")
                        : "0.0 ");
                for (int j = notZero + 1; j < this.col - 1; j++) {
                    if (this.contents[i][j] != 0) {
                        if (this.contents[i][j] < 0) {
                            result[notZero] += ("+ " + (this.contents[i][j] * (-1)) + result[j] + " ");
                        } else {
                            result[notZero] += ("- " + (this.contents[i][j]) + result[j] + " ");
                        }
                    }
                }
            }
        }
        // Print solusi
        String s = "SOLUSI SPL \n";
        for (int i = 0; i < this.col - 1; i++) {
            System.out.println("x" + (i + 1) + " = " + result[i]);
            s += "x" + (i + 1) + " = " + result[i] + "\n";
        }
        return s;
    }

    /* *** DETERMINAN *** */
    double detCofactor() {
        /* Mengembalikan nilai determinan dengan metode ekspansi kofaktor */
        if (this.row == 1) {
            return this.contents[0][0];
        } else {
            double det = 0;
            int a = 1;
            for (int i = 0; i < this.row; i++) {
                Matrix m1 = new Matrix(this.row - 1, this.col - 1);
                for (int j = 0; j < m1.getMatrixRow(); j++) {
                    for (int k = 0; k < m1.getMatrixCol(); k++) {
                        if (k < i) {
                            m1.contents[j][k] = this.contents[j + 1][k];
                        } else {
                            m1.contents[j][k] = this.contents[j + 1][k + 1];
                        }
                    }
                }
                det += a * this.contents[0][i] * m1.detCofactor();
                a *= -1;
            }
            return det;
        }
    }

    double detReduction() {
        /* Mengembalikan nilai determinan yang diperoleh dengan cara reduksi baris */
        int i, j, k, idx;
        double temp, temp1, temp2;
        double[] tempRow = new double[this.row];
        int det = 1;
        int co = 1;
        for (i = 0; i < this.row; i++) {
            idx = i;
            for (int m = 0; m < this.row; m++) {
                if (isRowZero(m)) {
                    idx = this.row;
                }
            }
            if (idx == this.row) {
                return 0.0;
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
            for (j = i + 1; j < this.row; j++) {
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
        return det / co;
    }

    /* *** INVERSE MATRIX *** */
    Matrix inverseOBE() {
        /* Mengembalikan balikan matriks yang diolah dengan metode OBE */
        Matrix m = new Matrix(this.row, this.col * 2);
        for (int i = 0; i < m.getMatrixRow(); i++) {
            for (int j = 0; j < m.getMatrixCol(); j++) {
                if (j < this.col) {
                    m.setELMT(this.contents[i][j], i, j);
                } else if (i == j - this.col) {
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
        Matrix mResult, mCofactor;
        /* Membuat matrix kofaktor */
        mCofactor = new Matrix(this.row, this.col);
        /* mResult menyimpan hasil inverse */
        mResult = new Matrix(this.row, this.col);
        if (this.detCofactor() == 0) {
            System.out.println("Matrix tidak memiliki invers");
            return null;
        } else {
            double onePerDet = 1 / this.detCofactor();
            Double sign = 1.0;
            for (int i = 0; i < this.row; i++) {
                sign = Math.pow(-1.0, Double.valueOf(i));
                for (int j = 0; j < this.col; j++) {
                    Matrix m1 = new Matrix(this.row - 1, this.col - 1);
                    for (int k = 0; k < m1.getMatrixRow(); k++) {
                        for (int l = 0; l < m1.getMatrixCol(); l++) {
                            if (k >= i) {
                                if (l < j) {
                                    m1.contents[k][l] = this.contents[k + 1][l];
                                } else {
                                    m1.contents[k][l] = this.contents[k + 1][l + 1];
                                }
                            } else {
                                if (l < j) {
                                    m1.contents[k][l] = this.contents[k][l];
                                } else {
                                    m1.contents[k][l] = this.contents[k][l + 1];
                                }
                            }
                        }
                    }
                    mCofactor.setELMT(sign * m1.detCofactor(), i, j);
                    sign *= (-1);
                }
            }
            mResult = mCofactor.transpose(); /* Membentuk matriks adjoin */
            mResult.multiplyConst(onePerDet);
            return mResult;
        }
    }

    /* Interpolasi Polinom */
    void interpolasi(double x) {
        /* Mengembalikan fungsi hasil interpolasi polinom dan nilai f(x) */
        Matrix mI = new Matrix(this.row, this.row + 1);
        for (int i = 0; i < mI.row; i++) {
            for (int j = 0; j < mI.row; j++) {
                mI.setELMT(Math.pow(this.contents[i][0], j), i, j);
            }
            mI.setELMT(this.contents[i][1], i, this.row);
        }
        Matrix mO = mI.mSPL();
        String ans = "y = ";
        ans += mO.contents[0][0];
        for (int i = 1; i < mI.row; i++) {
            if (i == 1 && mO.contents[i][0] != 0.0) {
                if (mO.contents[i][0] > 0.0) {
                    ans += " +";
                }
                ans += " " + mO.contents[1][0] + "x";
            } else if (mO.contents[i][0] != 0.0) {
                if (mO.contents[i][0] > 0.0) {
                    ans += " +";
                }
                ans += " " + mO.contents[i][0] + "x**" + i;
            }
        }
        double y = 0;
        for (int i = 0; i < mI.row; i++) {
            y += mO.getELMT(i, 0) * Math.pow(x, i);
        }
        System.out.println(ans);
        System.out.println("f(" + x + ")=" + y);

        String s = ans + "\n" + "f(" + x + ")=" + y;
        System.out.print("Apakah anda ingin menyimpan hasil dalam file(1:ya, 2:tidak): ");
        int choice = in.nextInt();
        if (choice == 1) {
            String blank = in.nextLine();
            System.out.print("Masukkan nama file beserta extension(.txt): ");
            File text = new File("../test/" + in.nextLine());
            saveToFile(text, s);
        }
    }

    /* Interpolasi Bikubik */
    void interpolasiBikubik(Double a, Double b) {
        /* Menghasilkan nilai f(a,b) dimana f diperoleh dari interpolasi bikubik */
        Matrix X = new Matrix(16, 16);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                X.setELMT(Math.pow((i % 4) - 1, j % 4) * Math.pow((i / 4) - 1, j / 4), i, j);
            }
        }
        X = X.inverseOBE();
        Matrix mResult = multiplyMatrix(X, this);
        Matrix Xab = new Matrix(1, 16);
        for (int i = 0; i < 16; i++) {
            Xab.setELMT(Math.pow(a, i % 4) * Math.pow(b, i / 4), 0, i);
        }
        Matrix f = multiplyMatrix(Xab, mResult);
        String s = "f(" + a + "," + b + ") = " + f.getELMT(0, 0);
        System.out.println(s);

        System.out.print("Apakah anda ingin menyimpan hasil dalam file(1:ya, 2:tidak): ");
        int choice = in.nextInt();
        if (choice == 1) {
            String blank = in.nextLine();
            System.out.print("Masukkan nama file beserta extension(.txt): ");
            File text = new File("../test/" + in.nextLine());
            saveToFile(text, s);
        }
    }

    /* Scaling Gambar dengan memanfaatkan interpolasi Bikubik */
    void imageScaling() {
        BufferedImage image = null;
        // READ IMAGE
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Masukkan nama file gambar beserta extension: ");
            File input_file = new File("../test/" + s.nextLine());

            BufferedImage image1 = ImageIO.read(input_file);
            int width = 2 * image1.getWidth();
            int height = 2 * image1.getHeight();

            // Padding image
            BufferedImage image2 = new BufferedImage(image1.getWidth() + 4, image1.getHeight() + 4,
                    BufferedImage.TYPE_INT_ARGB);
            Graphics g = image2.getGraphics();
            g.drawImage(image1, 2, 2, null);
            g.dispose();

            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int x = i / 2 + 2;
                    int y = j / 2 + 2;
                    Matrix m = new Matrix(16, 1);
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 4; l++) {
                            m.setELMT(image2.getRGB(y + 1 - l, x + 1 - k), k + l * 4, 0);
                        }
                    }
                    int color = m.interpolasiBikubik2(0.0, 0.0);
                    image.setRGB(j, i, color);
                }
            }

            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        // WRITE IMAGE
        try {
            // Output file path
            Scanner s = new Scanner(System.in);
            System.out.print("Masukkan nama file keluaran beserta extension: ");
            File output_file = new File("../test/" + s.nextLine());

            // Writing to file taking type and path as
            ImageIO.write(image, "png", output_file);

            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    int interpolasiBikubik2(Double a, Double b) {
        /* Menghasilkan nilai f(a,b) dimana f diperoleh dari interpolasi bikubik */
        Matrix X = new Matrix(16, 16);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                X.setELMT(Math.pow((i % 4) - 1, j % 4) * Math.pow((i / 4) - 1, j / 4), i, j);
            }
        }
        X = X.inverseOBE();
        Matrix mResult = multiplyMatrix(X, this);
        Matrix Xab = new Matrix(1, 16);
        for (int i = 0; i < 16; i++) {
            Xab.setELMT(Math.pow(a, i % 4) * Math.pow(b, i / 4), 0, i);
        }
        Matrix f = multiplyMatrix(Xab, mResult);
        // System.out.println((int) f.contents[0][0]);
        return (int) f.contents[0][0];
    }

    /* Regresi Linier Berganda */
    void regresi(Double[] peubah) {
        /* Menghasilkan fungsi hasil regresi linier berganda dan nilai f(xk) */
        Matrix regresi = new Matrix(this.col, this.col + 1);
        for (int i = 0; i < regresi.row; i++) {
            for (int j = 0; j < regresi.col; j++) {
                if (i == 0 && j == 0) {
                    regresi.setELMT(this.row, 0, 0);
                } else if (i == 0) {
                    Double sum = 0.0;
                    for (int k = 0; k < this.row; k++) {
                        sum += this.contents[k][j - 1];
                    }
                    regresi.contents[i][j] = sum;
                    if (j < regresi.row) {
                        regresi.contents[j][0] = sum;
                    }
                } else if (j != 0) {
                    Double sum = 0.0;
                    for (int k = 0; k < this.row; k++) {
                        sum += this.contents[k][j - 1] * this.contents[k][i - 1];
                    }
                    regresi.contents[i][j] = sum;
                }
            }
        }
        Matrix result = regresi.mSPL();
        String ans = "y = ";
        ans += result.contents[0][0];
        for (int i = 1; i < result.row; i++) {
            if (result.contents[i][0] > 0.0) {
                ans += " +";
            }
            ans += " " + result.contents[i][0] + "x" + i;
        }
        System.out.println(ans);
        double y = result.contents[0][0];
        for (int i = 1; i < result.row; i++) {
            y += result.contents[i][0] * peubah[i - 1];
        }
        String ans1 = "f(";
        for (int i = 0; i < this.col - 1; i++) {
            ans1 += peubah[i];
            if (i != this.col - 2) {
                ans1 += ",";
            } else {
                ans1 += ") = " + y;
            }
        }
        System.out.println(ans1);

        String s = ans + "\n" + ans1;
        System.out.print("Apakah anda ingin menyimpan hasil dalam file(1:ya, 2:tidak): ");
        int choice = in.nextInt();
        if (choice == 1) {
            String blank = in.nextLine();
            System.out.print("Masukkan nama file beserta extension(.txt): ");
            File text = new File("../test/" + in.nextLine());
            saveToFile(text, s);
        }
    }
}