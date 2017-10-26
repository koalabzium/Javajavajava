package lab2;

import java.util.Random;

public class Matrix {
    private double[] data;
    private int rows;
    private int cols;

    Matrix(int r, int c) {
        rows = r;
        cols = c;
        data = new double[rows * cols];
    }

    Matrix(double[][] d) {
        int maxRow = 0;
        this.rows = d.length;
        for (double row[] : d) {
            if (row.length > maxRow) {
                maxRow = row.length;
            }
        }
        this.cols = maxRow;
        this.data = new double[rows * cols];
        int k = 0;
        for (double[] aD : d) {
            for (double anAD : aD) {
                data[k] = anAD;
                k++;
            }
            if (aD.length < maxRow) {
                for (int l = 0; l < (maxRow - aD.length); l++) {
                    data[k] = 0;
                    k++;
                }
            }
        }
    }

    double[][] asArray() {
        double[][] tab = new double[rows][cols];
        int k = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                tab[i][j] = data[k]; //data[i*cols+j]
                k++;
            }
        }
        return tab;
    }

    double get(int r, int c) {
        return data[r * cols + c];
    }

    void set(int r, int c, double value) {
        data[r * cols + c] = value;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < rows; i++) {
            buf.append("[");
            for (int j = 0; j < (cols - 1); j++) {
                buf.append(data[i * rows + j]);
                buf.append(", ");
            }
            buf.append(data[i * rows + cols]);
            buf.append("]");
        }
        return buf.toString();
    }

    void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));

    }

    Matrix add(Matrix m) {

        Matrix n = new Matrix(this.rows, this.cols);
        for (int i = 0; i < m.rows; i++) {
            n.data[i] = data[i] + m.data[i];
        }
        return n;
    }

    Matrix div(Matrix m) {
        Matrix n = new Matrix(rows, cols);
        for (int i = 0; i < m.rows; i++) {
            n.data[i] = data[i] / m.data[i];
        }
        return n;
    }

    Matrix sub(Matrix m) {
        Matrix n = new Matrix(rows, cols);
        for (int i = 0; i < m.rows; i++) {
            n.data[i] = data[i] - m.data[i];
        }
        return n;
    }

    Matrix mul(Matrix m) {
        Matrix n = new Matrix(rows, cols);
        for (int i = 0; i < m.rows; i++) {
            n.data[i] = data[i] * m.data[i];
        }
        return n;
    }

    Matrix add(double w) {
        Matrix n = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            n.data[i] = data[i] + w;
        }
        return n;
    }

    Matrix sub(double w) {
        Matrix n = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            n.data[i] = data[i] - w;
        }
        return n;
    }

    Matrix mul(double w) {
        Matrix n = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            n.data[i] = data[i] * w;
        }
        return n;
    }

    Matrix div(double w) {
        Matrix n = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            n.data[i] = data[i] / w;
        }
        return n;
    }

    int[] shape() {
        int[] tab = {rows, cols};
        return tab;
    }


    Matrix dot(Matrix m) {
        Matrix n = new Matrix(this.rows, m.cols);
        double buf = 0;
        int j = 0;
        for (int i = 0; i < (n.rows * n.cols); i++) {
            for (int k = 0, ; k < m.rows; k += m.cols) {
                buf += data[j] * data[k];
                j++;
            }
            n.data[i] = buf;
            buf = 0;
        }
        return n;
    }

    double forbenius() {
        double buf = 0;
        for (double aData : data) {
            buf += aData * aData;
        }
        buf = Math.sqrt(buf);
        return buf;
    }

    public static Matrix random(int rows, int cols) {
        Matrix m = new Matrix(rows, cols);
        Random r = new Random();
        m.set(0, 0, r.nextDouble());
        return m;
    }

}
