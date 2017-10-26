package lab2;

public class Main {
    public static void main(String[] args) {
        double[][] nana = {{1,2,3},{3,4},{9,0,8,6},{1,2,3,4,5},{1}};
        Matrix lala = new Matrix(nana);
        for(double el : lala.data){
            System.out.println(el);
        }
    }
}
