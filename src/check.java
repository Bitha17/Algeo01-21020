import java.util.*;
import java.io.*;

public class check {
    public static void main(String[] args) {
        int m, n;
        Scanner in = new Scanner(System.in);
        System.out.println("Masukan jumlah baris pada matriks: ");
        m = in.nextInt();
        System.out.println("Masukan jumlah kolom pada matriks: ");
        n = in.nextInt();
        Matrix M = new Matrix(m, n);
        M.readMatrix(); // RIGHT
        M.matrixToFile(menuSave());
        in.close();
        // int o;
        // System.out.println("Masukan jumlah baris pada matriks: ");
        // o = in.nextInt();
        // Matrix MInterpolasi = new Matrix(3, 2);
        // MInterpolasi.readMatrix(); // RIGHT
        // in.close();
        /*System.out.println(M.detCofactor()); // RIGHT
        System.out.println(M.detReduction()); // Array Out of Bounds
        M.GaussOBE(); // RIGHT 
        M.GaussJordanOBE(); // RIGHT
        M.inverseCofactor(); // RIGHT
        M.displayMatrix(); // RIGHT */
        // M.SPLKaidahCramer();
        // Matrix mTemp = new Matrix(M.getMatrixRow(), M.getMatrixCol());
        // mTemp = M.inverseOBE(); 
        // mTemp.displayMatrix();
        // M.GaussJordanOBE();
        // M.displayMatrix();
        // M.parametric();
        // M.GaussJordanOBE();
        // MInterpolasi();

        
        // Matrix matrix = new Matrix(0, 0);        
        // System.out.print("Masukkan n jumlah peubah x: ");
        // int col = in.nextInt() + 1;
        // // System.out.println(col);
        // System.out.print("Masukkan m jumlah sampel: ");
        // int row = in.nextInt();
        // // System.out.println(row);
        // matrix.setMatrixDim(row, col);
        // System.out.println(matrix.getMatrixRow());
        // System.out.println(matrix.getMatrixCol());
        // matrix.readMatrix();
        // System.out.println("Matriks yang dimasukkan: ");
        // matrix.displayMatrix();
        // Double[] variables = new Double[matrix.getMatrixCol()-1];
        // for (int i = 1; i < matrix.getMatrixCol(); i++){
        //     System.out.print("Masukkan nilai x" + i + ":");
        //     variables[i-1] = in.nextDouble();
        // }
        // matrix.regresi(variables);
        
    }
    public static int menuSave() {
        Scanner sv = new Scanner(System.in);
        System.out.print("Apakah anda ingin menyimpan hasil dalam file(1:ya, 2:tidak): ");
        int choice = sv.nextInt();
        sv.close();
        return choice; 
    }
}
