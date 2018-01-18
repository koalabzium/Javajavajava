package Mean;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Predicate;

public class Losowe {
    static int[] array;
    static BlockingQueue<Integer> results = new ArrayBlockingQueue<Integer>(100);
    static void initArray(int size){
        array = new int[size];
        Random r =new Random();
        for(int i=0;i<size;i++){

            array[i]= r.nextInt(100);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        initArray(1000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            parallelLosowa(cnt);
        }
    }


    static class LosowaCalc extends Thread{
        private final int start;
        private final int end;
        double licznik = 0;
        Predicate<Integer> pred;

        LosowaCalc(int start, int end, Predicate<Integer> pred){
            this.start = start;
            this.end=end;
            this.pred = pred;
        }
        LosowaCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            int licznik=0;
            for(int i=start;i<end;i++){
                if(pred.test(array[i])){
                    licznik++;
                }
            }

            try {
                results.put(licznik);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }

    static void parallelLosowa(int cnt) throws InterruptedException {
        LosowaCalc threads[]=new LosowaCalc[cnt];

        int part = array.length/cnt;
        for(int i=0;i<cnt;i++){

            threads[i]=new LosowaCalc(i*part,(i+1)*part-1);
        }

        for(LosowaCalc el : threads){
            el.run();
        }

        int licznik=0;

        for(LosowaCalc el : threads){
            licznik+=results.take();
        }



        double t3 = System.nanoTime()/1e6;
        System.out.print("licznik: "  + licznik);

    }
}
