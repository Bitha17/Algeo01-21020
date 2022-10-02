import java.util.*;
import java.util.function.IntToDoubleFunction;
import java.io.*;

public class Main {

  public static final Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Aplikasi Pengolah Matrix");
    System.out.println();

    System.out.println("MENU");
    System.out.println("1. Sistem Persamaan Linier");
    System.out.println("2. Determinan");
    System.out.println("3. Matriks balikan");
    System.out.println("4. Interpolasi Polinom");
    System.out.println("5. Interpolasi Bicubic");
    System.out.println("6. Regresi linear berganda");
    System.out.println("7. Image Scaling");
    System.out.println("8. Keluar");

    System.out.print("Silakan masukkan angka yang diinginkan untuk mengolah matriks: ");
    int choice = in.nextInt();
    boolean exit = false;
    Matrix matrix = new Matrix(0, 0);

    while (!exit) {
      switch (choice) {
        case 1:
          inputFrom();
          int pil = in.nextInt();
          if (pil == 1) {
            System.out.print("Masukkan ukuran matriks m(baris) n(kolom): ");
            int row = in.nextInt();
            int col = in.nextInt();
            matrix.setMatrixDim(row, col);
            matrix.readMatrix();
            System.out.println("Matriks yang dimasukkan: ");
            matrix.displayMatrix();
          } else {
            String blank = in.nextLine();
            System.out.print("Masukkan nama file beserta extension(.txt): ");
            File text = new File("../test/" + in.nextLine());
            System.out.print("Masukkan ukuran matriks m(baris) n(kolom): ");
            int row = in.nextInt();
            int col = in.nextInt();
            matrix.setMatrixDim(row, col);
            matrix.readMatrix2(text);
            System.out.println("Matriks yang dimasukkan: ");
            matrix.displayMatrix();
          }

          System.out.println("Daftar metode penyelesaian matriks");
          System.out.println("1. Metode eliminasi Gauss");
          System.out.println("2. Metode eliminasi Gauss-Jordan ");
          System.out.println("3. Metode matriks balikan ");
          System.out.println("4. Kaidah Cramer ");
          System.out.print("Masukkan angka untuk memilih metode: ");
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
              matrix.matrixToFile(temp3);
              break;
            default:
              break;
          }
          printMenu2();
          choice = in.nextInt();
          break;

        case 2:
          inputFrom();
          int pil2 = in.nextInt();
          if (pil2 == 1) {
            acceptMatrix(matrix);
          } else {
            String blank = in.nextLine();
            System.out.print("Masukkan nama file beserta extension(.txt): ");
            File text = new File("../test/" + in.nextLine());
            System.out.print("Masukkan ukuran matriks n(baris & kolom): ");
            int row = in.nextInt();
            matrix.setMatrixDim(row, row);
            matrix.readMatrix2(text);
            System.out.println("Matriks yang dimasukkan: ");
            matrix.displayMatrix();
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
          matrix.detToFile(temp, det);

          printMenu2();
          choice = in.nextInt();
          break;

        case 3:
          inputFrom();
          int pil3 = in.nextInt();
          if (pil3 == 1) {
            acceptMatrix(matrix);
          } else {
            String blank = in.nextLine();
            System.out.print("Masukkan nama file beserta extension(.txt): ");
            File text = new File("../test/" + in.nextLine());
            System.out.print("Masukkan ukuran matriks n(baris dan kolom): ");
            int row = in.nextInt();
            int col = in.nextInt();
            matrix.setMatrixDim(row, col);
            matrix.readMatrix2(text);
            System.out.println("Matriks yang dimasukkan: ");
            matrix.displayMatrix();
          }

          System.out.println("Penyelesaian invers matriks menggunakan : ");
          System.out.println("1. Metode invers reduksi baris");
          System.out.println("2. Metode invers kofaktor");

          System.out.print("Masukkan angka untuk memilih metode: ");
          int case3Choice = in.nextInt();

          // Initialize Value for Inverse Matrix
          Matrix invers = new Matrix(0, 0);

          switch (case3Choice) {
            case 1:
              invers = matrix.inverseOBE();
              break;
            case 2:
              invers = matrix.inverseCofactor();
              break;
            default:
              break;
          }

          System.out.println("Invers dari matriks adalah: ");
          invers.displayMatrix();

          int temp2 = menuSave();
          matrix.matrixToFile(temp2);

          printMenu2();
          choice = in.nextInt();
          break;

        case 4:
          inputFrom();
          int pil4 = in.nextInt();
          if (pil4 == 1) {
            System.out.print("Masukkan jumlah sampel: ");
            int row = in.nextInt();
            matrix.setMatrixDim(row, 2);
            matrix.readMatrix();
            System.out.println("Matriks yang dimasukkan: ");
            matrix.displayMatrix();
            System.out.print("Masukkan nilai x yang mau ditaksir: ");
            Double x = in.nextDouble();
            matrix.interpolasi(x);
          } else {
            System.out.print("Masukkan nama file beserta extension(.txt): ");
            String blank = in.nextLine();
            File text = new File("../test/" + in.nextLine());
            System.out.print("Masukkan jumlah sampel: ");
            int row = in.nextInt();
            matrix.setMatrixDim(row, 2);
            matrix.readMatrix2(text);
            System.out.println("Matriks yang dimasukkan: ");
            matrix.displayMatrix();
            Double x = matrix.readDouble(text, matrix.getMatrixRow() * 2 + 1);
            matrix.interpolasi(x);
          }
          printMenu2();
          choice = in.nextInt();
          break;

        case 5:
          inputFrom();
          int pil5 = in.nextInt();
          matrix.setMatrixDim(16, 1);
          if (pil5 == 1) {
            for (int i = 0; i < 4; i++) {
              for (int j = 0; j < 4; j++) {
                System.out.print("f(" + (i - 1) + "," + (j - 1) + "):");
                Double p = in.nextDouble();
                matrix.setELMT(p, j * 4 + i, 0);
              }
            }
            System.out.print("Masukkan nilai a dan b yang akan ditaksir: ");
            Double a = in.nextDouble();
            Double b = in.nextDouble();
            matrix.interpolasiBikubik(a, b);
          } else {
            System.out.print("Masukkan nama file beserta extension(.txt): ");
            String blank = in.nextLine();
            File text = new File("../test/" + in.nextLine());
            matrix.readMatrix3(text);
            Double a = matrix.readDouble(text, 17);
            Double b = matrix.readDouble(text, 18);
            matrix.interpolasiBikubik(a, b);
          }
          printMenu2();
          choice = in.nextInt();
          break;

        case 6:
          inputFrom();
          int pil6 = in.nextInt();
          if (pil6 == 1) {
            System.out.print("Masukkan n jumlah peubah x: ");
            int col = in.nextInt() + 1;
            System.out.print("Masukkan m jumlah sampel: ");
            int row = in.nextInt();
            matrix.setMatrixDim(row, col);
            matrix.readMatrix();
            System.out.println("Matriks yang dimasukkan: ");
            matrix.displayMatrix();
            Double[] variables = new Double[matrix.getMatrixCol() - 1];
            for (int i = 1; i < matrix.getMatrixCol(); i++) {
              System.out.print("Masukkan nilai x" + i + ":");
              variables[i - 1] = in.nextDouble();
            }
            matrix.regresi(variables);
          } else {
            String blank = in.nextLine();
            System.out.print("Masukkan nama file beserta extension(.txt): ");
            File text = new File("../test/" + in.nextLine());
            System.out.print("Masukkan n jumlah peubah x: ");
            int col = in.nextInt() + 1;
            System.out.print("Masukkan m jumlah sampel: ");
            int row = in.nextInt();
            matrix.setMatrixDim(row, col);
            matrix.readMatrix2(text);
            System.out.println("Matriks yang dimasukkan: ");
            matrix.displayMatrix();
            Double[] peubah = new Double[matrix.getMatrixCol() - 1];
            for (int i = 0; i < matrix.getMatrixCol() - 1; i++) {
              peubah[i] = matrix.readDouble(text, matrix.getMatrixCol() * row + i + 1);
            }

            matrix.regresi(peubah);
          }
          printMenu2();
          choice = in.nextInt();
          break;
        case 7:
          matrix.imageScaling();
          printMenu2();
          choice = in.nextInt();
          break;

        case 8:
          System.out.print("Apakah Anda yakin ingin keluar dari kalkulator matrix? (1: ya, 2: tidak): ");
          int pil7 = in.nextInt();
          if (pil7 == 1) {
            System.out.println("Terima kasih sudah menggunakan Aplikasi Pengolah Matriks <3 \n (0w0) (^o^) (>.<)");
            exit = true;
          } else {
            printMenu2();
            choice = in.nextInt();
          }
          break;

        default:
          System.out.println("Pilihan yang Anda masukan tidak ada dalam menu!");
          printMenu2();
          choice = in.nextInt();
          break;
      }
    }
    in.close();
  }

  public static int menuSave() {
    System.out.print("Apakah anda ingin menyimpan hasil dalam file(1:ya, 2:tidak): ");
    int choice = in.nextInt();
    return choice;
  }

  static void inputFrom() {
    System.out.println("Pilihan masukan");
    System.out.println("1. Manual dari keyboard");
    System.out.println("2. Dari file");
    System.out.print("Masukkan angka pilihanmu: ");
  }

  static void acceptMatrix(Matrix m) {
    System.out.print("Masukkan ukuran matriks n(baris dan kolom): ");
    int row = in.nextInt();
    m.setMatrixDim(row, row);
    m.readMatrix();
    System.out.println("Matriks yang dimasukkan: ");
    m.displayMatrix();
  }

  static void printMenu2() {
    System.out.println("MENU");
    System.out.println("1. Sistem Persamaan Linier");
    System.out.println("2. Determinan");
    System.out.println("3. Matriks balikan");
    System.out.println("4. Interpolasi Polinom");
    System.out.println("5. Interpolasi Bicubic");
    System.out.println("6. Regresi linear berganda");
    System.out.println("7. Image Scaling");
    System.out.println("8. Keluar");
    System.out.print("Silakan masukkan angka untuk memilih menu lain: ");
  }
}
