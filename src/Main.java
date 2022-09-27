import java.util.*;
import java.io.*;


public class Main 
{
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Aplikasi Pengolah Matrix");
        System.out.println();

        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi linear berganda");
        System.out.println("7. Keluar");

        
        System.out.print("Silakan masukkan angka yang diinginkan untuk mengolah matriks.");
        int choice = in.nextInt();
        Matrix matrix = new Matrix(0, 0);

        while (choice != 7) {
        switch (choice) {
        case 1:
          System.out.println("Pilihan masukan");
          System.out.println("1. Manual dari keyboard");
          System.out.println("2. Dari file");
          System.out.print("Masukkan angka pilihanmu:");
          int pil = in.nextInt();
          if (pil == 1){
              System.out.print("Masukkan ukuran matriks m(baris) n(kolom): ");
              int row = in.nextInt();
              int col = in.nextInt();
              matrix.setMatrixDim(row,col);
              matrix.readMatrix();
              System.out.println("Matriks yang dimasukkan: ");
              matrix.displayMatrix();
          }
          else{
              System.out.print("Masukkan nama file beserta extension(.txt): ");
              File text = new File("../test/" + in.nextLine());
              System.out.print("Masukkan ukuran matriks m(baris) n(kolom): ");
              int row = in.nextInt();
              int col = in.nextInt();
              matrix.setMatrixDim(row,col);
              matrix.readMatrix2(text);
              System.out.println("Matriks yang dimasukkan: ");
              matrix.displayMatrix();
          }
          
          System.out.println("Daftar metode penyelesaian matriks.");
          System.out.println("1. Metode eliminasi Gauss");
          System.out.println("2. Metode eliminasi Gauss-Jordan ");
          System.out.println("3. Metode matriks balikan ");
          System.out.println("4. Kaidah Cramer ");
          System.out.print("Masukkan angka untuk memilih metode! ");
          int case1Choice = in.nextInt();

          switch (case1Choice) {

            case 1:
              matrix.SPLGauss();
              int temp = menuSave();
              matrix.matrixToFile(temp);
              break;

            case 2:
              matrix.SPLGaussJordan();
              int temp1 = menuSave();
              matrix.matrixToFile(temp1);
              break;

            case 3:
              // Metode invers
              matrix.SPLInverse();
              int temp2 = menuSave();
              matrix.matrixToFile(temp2);
              break;

            case 4:
              matrix.SPLKaidahCramer();
              int temp3 = menuSave();
              matrix.matrixToFile(temp3);;
              break;
            default:

              break;
          }



        in.close();
   
        }
        }
    }

    public static int menuSave() {
        Scanner sv = new Scanner(System.in);
        System.out.print("Apakah anda ingin menyimpan hasil dalam file(1:ya, 2:tidak): ");
        int choice = sv.nextInt();
        sv.close();
        return choice; 
    }
}
