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

        while (choice != 7) {
          switch (choice) {
          case 1:
            inputFrom();
            int pil = in.nextInt();
            if (pil == 1){
                System.out.print("Masukkan ukuran matriks m(baris) n(kolom): ");
                int row = in.nextInt();
                int col = in.nextInt();
                Matrix matrix = new Matrix(row,col);                
                matrix.readMatrix();
                System.out.println("Matriks yang dimasukkan: ");
                matrix.displayMatrix();
            }
            else{
                System.out.print("Masukkan nama file beserta extension(.txt): ");
                Scanner s1 = new Scanner(System.in);
                File text = new File("../test/" + s1.nextLine());
                System.out.print("Masukkan ukuran matriks m(baris) n(kolom): ");
                int row = in.nextInt();
                int col = in.nextInt();
                Matrix matrix = new Matrix(row,col);
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
              
              case 6:
              default:

                break;
            }


          case 6:
            inputFrom();
            int pil = in.nextInt();
            if (pil == 1){
                System.out.print("Masukkan n jumlah peubah x: ");
                int col = in.nextInt() + 1;
                System.out.print("Masukkan m jumlah sampel: ");
                int row = in.nextInt();
                Matrix matrix = new Matrix(row,col);
                matrix.readMatrix();
                System.out.println("Matriks yang dimasukkan: ");
                matrix.displayMatrix();
                Double[] variables = new Double[matrix.getMatrixCol-1];
                for (int i = 1; i < matrix.getMatrixCol; i++){
                  System.out.print("Masukkan nilai x" + i + ":");
                  variables[i-1] = in.nextDouble;
                }
            }
            else{
                System.out.print("Masukkan nama file beserta extension(.txt): ");
                Scanner s1 = new Scanner(System.in);
                File text = new File("../test/" + s1.nextLine());
                System.out.print("Masukkan n jumlah peubah x: ");
                int col = in.nextInt() + 1;
                System.out.print("Masukkan m jumlah sampel: ");
                int row = in.nextInt();
                Matrix matrix = new Matrix(row,col);
                matrix.readMatrix2(text);
                System.out.println("Matriks yang dimasukkan: ");
                matrix.displayMatrix();
                Scanner sc = new Scanner(text);
                Double[] variables = new Double[matrix.getMatrixCol-1];
                for (int i = 0; i < matrix.getMatrixCol-1; i++){
                  variables[i] = sc.nextDouble;
                }
                sc.close();
            }
            matrix.regresi(variables);

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

    void inputFrom() {
        System.out.println("Pilihan masukan");
        System.out.println("1. Manual dari keyboard");
        System.out.println("2. Dari file");
        System.out.print("Masukkan angka pilihanmu:");
    }
}
