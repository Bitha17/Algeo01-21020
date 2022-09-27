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
        Matrix matrix = new Matrix(0,0);

        while (choice != 7) {
          switch (choice) {
          case 1:
            inputFrom();
            int pil = in.nextInt();
            if (pil == 1){
                System.out.print("Masukkan ukuran matriks m(baris) n(kolom): ");
                int row = in.nextInt();
                int col = in.nextInt();
                matrix.setMatrixDim(row, col);;                
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
                matrix.setMatrixDim(row, col);;
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
            System.out.print("Masukkan angka untuk memilih menu lain: ");
            choice = in.nextInt();
            break;
            
          case 2:
            inputFrom();
            int pil2 = in.nextInt();
            if (pil2 == 1){
              acceptMatrix(matrix);
              System.out.println("Matriks yang dimasukkan: ");
              matrix.displayMatrix();
            }
            else{
                System.out.print("Masukkan nama file beserta extension(.txt): ");
                Scanner s1 = new Scanner(System.in);
                File text = new File("../test/" + s1.nextLine());
                System.out.print("Masukkan ukuran matriks n(baris & kolom): ");
                int row = in.nextInt();
                matrix.setMatrixDim(row, row);
                matrix.readMatrix2(text);
                System.out.println("Matriks yang dimasukkan: ");
                matrix.displayMatrix();
                s1.close();
            }

            System.out.println("Penyelesaian determinan matriks menggunakan : ");
            System.out.println("1. Metode reduksi baris");
            System.out.println("2. Metode kofaktor ");

            System.out.print("Masukkan angka untuk memilih metode: ");
            int case2Choice = in.nextInt();

            // Initialize Value for Determinant
            double det = 0.0;

            switch (case2Choice) {
              case 1:
                det = matrix.detReduction();
                break;
              case 2:
                det = matrix.detCofactor();
                break;
              default:
                break;
            }

            System.out.print("Determinan dari matriks adalah: ");
            System.out.println(det);

            int temp = menuSave();
            matrix.detToFile(temp,det);

            System.out.print("Masukkan angka untuk memilih menu lain: ");
            choice = in.nextInt();
            break;
            
          case 3:
            inputFrom();
            int pil3 = in.nextInt();
            if (pil3 == 1){
              acceptMatrix(matrix);
              System.out.println("Inverse matriks yang dimasukkan: ");
              matrix.inverseOBE();
              matrix.displayMatrix();
            } else {
              System.out.print("Masukkan nama file beserta extension(.txt): ");
              Scanner s1 = new Scanner(System.in);
              File text = new File("../test/" + s1.nextLine());
              System.out.print("Masukkan ukuran matriks n(baris dan kolom): ");
              int row = in.nextInt();
              int col = in.nextInt();
              matrix.setMatrixDim(row, col);
              matrix.readMatrix2(text);
              System.out.println("Matriks yang dimasukkan: ");
              matrix.displayMatrix();
              matrix.inverseOBE();
              int temp1 = menuSave();
              matrix.matrixToFile(temp1);
              s1.close();
            }
            
          case 4:
            inputFrom();
            int pil4 = in.nextInt();
            if (pil4 == 1){
                System.out.print("Masukkan jumlah sampel: ");
                int row = in.nextInt();
                matrix.setMatrixDim(row, 2);                
                matrix.readMatrix();
                System.out.println("Matriks yang dimasukkan: ");
                matrix.displayMatrix();
                System.out.print("Masukkan nilai x yang mau ditaksir: ");
                Double x = in.nextDouble();
                matrix.interpolasi(x);
            }
            else {
                System.out.print("Masukkan nama file beserta extension(.txt): ");
                Scanner s1 = new Scanner(System.in);
                File text = new File("../test/" + s1.nextLine());
                System.out.print("Masukkan jumlah sampel: ");
                int row = in.nextInt();
                matrix.setMatrixDim(row, row);
                matrix.readMatrix2(text);
                System.out.println("Matriks yang dimasukkan: ");
                matrix.displayMatrix();
                Double x = s1.nextDouble();
                s1.close();
                matrix.interpolasi(x);
            }
            
          case 5:
          case 6:
            inputFrom();
            int pil6 = in.nextInt();
            if (pil6 == 1){
                System.out.print("Masukkan n jumlah peubah x: ");
                int col = in.nextInt() + 1;
                System.out.print("Masukkan m jumlah sampel: ");
                int row = in.nextInt();
                matrix.setMatrixDim(row, col);
                matrix.readMatrix();
                System.out.println("Matriks yang dimasukkan: ");
                matrix.displayMatrix();
                Double[] variables = new Double[matrix.getMatrixCol() - 1];
                for (int i = 1; i < matrix.getMatrixCol(); i++){
                  System.out.print("Masukkan nilai x" + i + ":");
                  variables[i-1] = in.nextDouble();
                }
                matrix.regresi(variables);
            }
            else{
                System.out.print("Masukkan nama file beserta extension(.txt): ");
                Scanner s1 = new Scanner(System.in);
                File text = new File("../test/" + s1.nextLine());
                System.out.print("Masukkan n jumlah peubah x: ");
                int col = in.nextInt() + 1;
                System.out.print("Masukkan m jumlah sampel: ");
                int row = in.nextInt();
                matrix.setMatrixDim(row, col);
                matrix.readMatrix2(text);
                System.out.println("Matriks yang dimasukkan: ");
                matrix.displayMatrix();
                Double[] peubah = new Double[matrix.getMatrixCol()-1];
                try {
                  Scanner sc = new Scanner(text);
                  while (sc.hasNextLine()) {
                    for (int i = 0; i < matrix.getMatrixCol()-1; i++){
                      peubah[i] = sc.nextDouble();
                    }
                  }
                  sc.close();
                }
                  catch (FileNotFoundException e) {
                    e.printStackTrace();
                  }
                matrix.regresi(peubah);
            }

          in.close();
            case 7: 
              System.out.println("Apakah Anda yakin ingin keluar dari kalkulator matrix? (1: ya 2: tidak");
              int pil7 = in.nextInt();
              if (pil7 == 1) {
                choice = 7;
              } else {
                System.out.println("MENU");
                System.out.println("1. Sistem Persamaan Linier");
                System.out.println("2. Determinan");
                System.out.println("3. Matriks balikan");
                System.out.println("4. Interpolasi Polinom");
                System.out.println("5. Interpolasi Bicubic");
                System.out.println("6. Regresi linear berganda");
                System.out.println("7. Keluar");
                System.out.print("Silakan masukkan angka untuk memilih menu lain.");
                choice = in.nextInt();
                in.close();
              }
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

    static void inputFrom() {
        System.out.println("Pilihan masukan");
        System.out.println("1. Manual dari keyboard");
        System.out.println("2. Dari file");
        System.out.print("Masukkan angka pilihanmu:");
    }
    static void acceptMatrix(Matrix m) {
      Scanner in = new Scanner(System.in);
      System.out.print("Masukkan ukuran matriks n(baris dan kolom): ");
      int row = in.nextInt();
      int col = in.nextInt();
      m.setMatrixDim(row, col);                
      m.readMatrix();
      System.out.println("Matriks yang dimasukkan: ");
      m.displayMatrix();
      in.close();
    }
}
