import java.io.*;
import java.util.*;
import java.lang.Math;

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
                this.contents[i][j] = x;
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
    
    /* *** OPERATIONS *** */
    Matrix multiplyMatrix(Matrix m1, Matrix m2){
    /* Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2 */
    /* Mengirim hasil perkalian matriks: salinan m1 * m2 */
        Matrix m3 = new Matrix(m1.getMatrixRow(), m2.getMatrixCol());
        for (int i = 0; i < m3.row; i++){
            for (int j = 0; j < m3.col; j++){
                double temp = 0.0;
                for (int k = 0; k < m1.getMatrixCol(); k++){
                    temp += (m1.getELMT(i,k) * m2.getELMT(k,j));
                }
                m3.setELMT(temp,i,j);
            }
        }
        return m3;
    }

    void multiplyConst(double konstanta){
    /* I.S. m terdefinisi, k terdefinisi */
    /* F.S. Mengalikan setiap elemen m dengan k */
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.col; j++){
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
    }
    
    /* *** SPL *** */
    /* Solusi SPL dengan metode Inverse, GaussEquation, GaussJordanEquation, dan Kaidah Crammer */
    void SPLInverse() {
        if (this.row != this.col - 1) {
            System.out.println("SPL tidak dapat diselesaikan dengan metode invers");
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
            }
            else {
                mTemp = mTemp.inverseOBE();
                for (int i = 0; i < this.row; i++) {
                    mB.setELMT(this.contents[i][this.col-1], i, 0);
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
        mTemp = mTemp.inverseOBE();
        for (int i = 0; i < this.row; i++) {
            mB.setELMT(this.contents[i][this.col-1], i, 0);
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
        Matrix mResult = new Matrix(this.row, 0);
        int row, col;
        row = this.row - 1;
        col = this.col - 1;
        // Check for single solution
        if (this.contents[row][col-1] != 0 && this.contents[row][col] != 0 && this.row == this.col - 1) {
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
            double xlast = x / this.contents[row][col-1]; 
            mResult.setELMT(xlast, row, 0);
            double prevX = xlast;
            for (int i = row - 1; i >= 0; i--) {
                for (int j = col-1; j > row; j--) {
                    x = this.contents[i][col];
                    x -= this.contents[i][j] * prevX;
                    prevX = x;
                }
                mResult.setELMT(x, i, 0);
            }
            mResult.displaySPL();
        } else if (manySolution) {
            this.GaussJordanOBE();
            Boolean allElZero[] = new Boolean[this.row];
            Boolean colOne[] = new Boolean[this.row];
            for (int i = 0; i < this.row; i++) {
                allElZero[i] = true;
            } 
            for (int i = 0; i < this.row; i++) {
                if (!(this.isRowZero(i))) {
                    allElZero[i] = false;
                    break; 
                }
            }
            for (int i = 0; i < this.row; i++) {
                colOne[i] = false;
            }
            // Searching for the column that has leading entry
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.row; j++) {
                    if (this.contents[i][j] == 1) {
                        colOne[j] = true;
                        break;
                    }
                }
            }
            
        } else if (noSolution) {
            System.out.println("SPL tidak memiliki solusi");
        }
    }

    void SPLGaussJordan() {

    }

    void SPLKaidahCramer() {
        if (this.row != this.col - 1) {
            System.out.println("SPL tidak dapat diselesaikan dengan kaidah cramer");
        } else if (this.detCofactor() == 0) {
            System.out.println("SPL tidak dapat diselesaikan dengan kaidah cramer");
        } else {
            Matrix mDet = new Matrix(this.row, this.row);
            Matrix mResult = new Matrix(this.row, this.row);
            double det = this.detCofactor();
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.row; j++) {
                    mDet.setELMT(this.contents[i][j], i, j);
                }
            }
            int j = 0;
            while (j < this.col-1) {
                for (int i = 0; i < this.row; i++) {
                    mDet.setELMT(this.contents[i][this.col-1], i, j);
                }
                double detX = mDet.detCofactor();
                double x = detX/det;
                mResult.setELMT(x, j, 0); 
                j++;
            }
            mResult.displaySPL();
        }
    }

    /* *** Parametric Solution Case Handle *** */
    void parametric() {
        
    }


    /* *** DETERMINAN *** */
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
                    for (int j = 0; j < m1.getMatrixRow(); j++){
                        for (int k = 0; k < m1.getMatrixCol(); k++){
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

    /* *** INVERSE MATRIX *** */
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

    /* Interpolasi Polinom */
    void Interpolasi(){
        Matrix mI = new Matrix(this.row, this.row+1);
        for (int i = 0; i < mI.row; i++){
            for (int j = 0; j < mI.row; j++){
                mI.setELMT(Math.pow(this.contents[i][0],j), i, j);
            }
            mI.setELMT(this.contents[i][1], i, this.row);
        }
        mI.displayMatrix();
        Matrix mO = mI.mSPL();
        mO.displayMatrix();
        String ans = "y = ";
        ans += mO.contents[0][0];
        for (int i = 1; i < mI.row; i++){
            if(i == 1){
                ans += " + " + mO.contents[1][0] + "x";
            }
            else{
                ans += " + " + mO.contents[i][0] + "x**" + i;
            }
        }
        System.out.println(ans);
    }
}