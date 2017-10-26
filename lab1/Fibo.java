package lab1;

import java.util.Scanner;

public class Fibo {
    public static void main(String[] args ){
        Scanner nana = new Scanner(System.in);
        int n = nana.nextInt();
        if (n>=45 || n<=0){
            return;
        }
        int tab[] = new int[n];
        tab[0] = 1;
        tab[1] = 1;
        System.out.printf("1, 1, ");
        for (int i=2;i<n;i++){
            tab[i] = tab[i-1]+tab[i-2];
            System.out.printf("%d, ", tab[i]);
        }

    }
}
