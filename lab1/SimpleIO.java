package lab1;
import java.util.Scanner;

public class SimpleIO {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int i = scan.nextInt();
        double d = scan.nextDouble();
        System.out.printf("Pacz jakie dane wczytałam! \n %s, %d, %f",s,i,d);
    }
}

