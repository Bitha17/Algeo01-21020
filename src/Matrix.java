import java.io.*;
import java.util.*;
import java.lang.Math;

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

    /* *** SELECTOR *** */
    double getELMT(int row, int col) {
        /* Mengirimkan element Matrix(row,col) */
        return this.contents[row][col];
    }

    void setELMT(double x, int row, int col) {
        /* Melakukan assignment Matrix(row,col) <- x */
        this.contents[row][col] = x;
    }

    void setMatrixDim(int row, int col) {
        this.row = row;
        this.col = col;
        this.contents = new double[row][col];
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
        for (int i = 0; i < this.row; i++) {
            if (this.contents[row][i] != 0) {
                return false;
            }
        }
        return true;
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
        for (int i = this.row - 1; i >= 0; i--) {
            for (int j = this.row - 1; j >= 0; j--) {
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
    void SPLInverse() {
        if (this.row != this.col - 1) {
            System.out.println("SPL tidak dapat diselesaikan dengan metode invers");
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
            } else {
                mTemp = mTemp.inverseOBE();
                for (int i = 0; i < this.row; i++) {
                    mB.setELMT(this.contents[i][this.col - 1], i, 0);
                }
                mResult = multiplyMatrix(mTemp, mB);
                // mResult.multiplyConst(1/mTemp.detCofactor());
                mResult.displaySPL();
            }
        }
    }

    Matrix mSPL() {
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

    void SPLGauss() {
        // Fungsi menampilkan solusi SPL dengan GaussEquation
        this.GaussOBE();
        Boolean singleSolution, manySolution, noSolution;
        singleSolution = false;
        manySolution = false;
        noSolution = false;
        Matrix mResult = new Matrix(this.row, 1);
        int row, col;
        row = this.row - 1;
        col = this.col - 1;
        // Check for single solution
        if (this.contents[row][col - 1] != 0 && this.contents[row][col] != 0 && this.row == this.col - 1) {
            singleSolution = true;
        } else {
            manySolution = true;
        }

        // Check for no solution
        for (int i = 0; i <= row; i++) {
            if (this.isRowZero(i) && this.contents[i][col] != 0) {
                noSolution = true;
                manySolution = false;
                singleSolution = false; // double check
            }
        }

        // Melakukan penyulihan mundur
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
        } else if (manySolution) {
            this.parametric();
        } else if (noSolution) {
            System.out.println("SPL tidak memiliki solusi");
        }
    }

    void SPLGaussJordan() {
        this.GaussJordanOBE();
        int row = this.row - 1;
        int col = this.col - 1;
        Matrix mResult = new Matrix(this.row, 1);
        if (this.contents[row][col - 1] != 0 && this.contents[row][col] != 0 && this.row == this.col - 1) {
            // single solution
            for (int i = 0; i <= row; i++) {
                mResult.setELMT(this.contents[i][col], i, 0);
            }
            mResult.displaySPL();
        } else if (isRowZero(row) && this.contents[row][col] != 0) {
            // no solution
            System.out.println("SPL tidak memiliki solusi");
        } else {
            // many solution
            this.parametric();
        }
    }

    void SPLKaidahCramer() {
        if (this.row != this.col - 1) {
            System.out.println("SPL tidak dapat diselesaikan dengan kaidah cramer");
        } else if (this.detCofactor() == 0) {
            System.out.println("SPL tidak dapat diselesaikan dengan kaidah cramer");
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
        }
    }

    /* *** Parametric Solution Case Handle *** */
    int countRowOne(int i) {
        int count = 0;
        for (int j = 0; j < this.row; i++) {
            if (this.contents[i][j] == 1) {
                count++;
            }
        }
        return count;
    }

    void parametric() {
        String[] parametrik = { "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
                "v", "w", "x", "y", "z" };
        this.GaussJordanOBE();
        Boolean[] isAllZero = new Boolean[this.row];
        Boolean[] konstanta = new Boolean[this.row];
        for (int i = 0; i < this.row; i++) {
            isAllZero[i] = true;
        }
        for (int i = 0; i < this.row; i++) {
            konstanta[i] = false;
            if (!isRowZero(i)) {
                isAllZero[i] = false;
                break;
            }
        }
        for (int i = 0; i < this.row; i++) {
            konstanta[i] = false;
        }

        // Searching for the column that has leading entry
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.row; j++) {
                if (this.contents[i][j] == 1) {
                    konstanta[j] = true;
                    break;
                }
            }
        }

        for (int i = 0; i < this.row; i++) {
            if (konstanta[i] == true) {
                for (int j = i + 1; j < this.row; j++) {
                    konstanta[j] = false;
                }
            }
        }

        String[] result = new String[this.row];
        for (int k = 0; k < this.row; k++) {
            result[k] = "0.0 ";
        }

        int k = 0;
        for (int j = 0; j < this.row; j++) {
            if (!konstanta[j]) {
                // String elemen = new Character((char) (97 + count)).toString();
                result[j] = parametrik[k];
                k++;
            }
        }

        for (int i = 0; i < this.row; i++) {
            if (!isAllZero[i]) {
                int notZero = -9999;
                for (int j = 0; j < this.row; j++) {
                    if (this.contents[i][j] == 1) {
                        notZero = j;
                        break;
                    }
                }

                result[notZero] = (this.contents[i][this.col - 1] != 0 ? (this.contents[i][this.col - 1] + " ")
                        : "0.0 ");
                for (int j = notZero + 1; j < this.row; j++) {
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
        for (int i = 0; i < this.row; i++) {
            System.out.println("x" + (i + 1) + " = " + result[i]);
        }
    }

    /* *** DETERMINAN *** */
    double detCofactor() {
        /*
         * Mengembalikan nilai determinan yang diperoleh dengan cara ekspansi kofaktor
         */
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
                                mSmall.setELMT(this.contents[k + 1][l], k, l);
                            } else {
                                mSmall.setELMT(this.contents[k + 1][l + 1], k, l);
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

    /* Interpolasi Polinom */
    void interpolasi(double x) {
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
        Matrix X = new Matrix(16, 16);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                X.setELMT(Math.pow((i % 4) - 1, j % 4) * Math.pow((i / 4) - 1, j / 4), i, j);
            }
        }
        X.inverseOBE();
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

    /* Regresi Linier Berganda */
    void regresi(Double[] peubah) {
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