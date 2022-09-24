public class Main {

    public static void main(String[] args) {
        Matrix m = new Matrix(5,5);
        m.readMatrix();
        m.GaussJordanOBE();
        m.displayMatrix();
    }
}
