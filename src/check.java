import java.util.Scanner;

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
        M.Interpolasi();
        // MInterpolasi();
    }
}
