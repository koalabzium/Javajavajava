package Clock;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;


public class DownloadExample {

    static Semaphore sem = new Semaphore(0);
    static AtomicInteger count =new AtomicInteger(0);

    static void sequentialDownload(){
        double t1 = System.nanoTime()/1e6;
        for(String url:toDownload){
            new Downloader(url).run();
        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"SEQ t2-t1=%f\n",t2-t1);
    }

    static void concurrentDownload(){
        double t1 = System.nanoTime()/1e6;
        for(String url:toDownload){
            Downloader nana = new Downloader(url);
            nana.setName("vol. 1");
            nana.start();

        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf("t2 - t1 = %f\n", t2 - t1);
    }

    static void concurrentDownload3() throws InterruptedException {
        double t1 = System.nanoTime()/1e6;
        for(String url:toDownload){
            Downloader nana = new Downloader(url);
            nana.setName("vol. 3");
            nana.start();


        }
        sem.acquire(toDownload.length);
        double t2 = System.nanoTime()/1e6;

        System.out.printf("t2 - t1 = %f\n", t2 - t1);

    }

    static void concurrentDownload2() throws InterruptedException {


        double t1 = System.nanoTime()/1e6;
        for(String url:toDownload){
            Downloader nana = new Downloader(url);
            nana.setName("vol.2 ");

            nana.start();


        }

        while (count.get() != toDownload.length) {
            Thread.yield();
        }

        double t2 = System.nanoTime()/1e6;
        System.out.printf("t2 - t1 = %f\n", t2 - t1);

        }





    public static void main(String[]args) throws InterruptedException {
        concurrentDownload2();
        concurrentDownload();
        concurrentDownload3();

    }


    static String [] toDownload = {
            "http://home.agh.edu.pl/pszwed/wyklad-c/01-jezyk-c-intro.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/02-jezyk-c-podstawy-skladni.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/03-jezyk-c-instrukcje.pdf",

    };

    static class Downloader extends Thread implements Runnable{
        private final String url;

        Downloader(String url){
            this.url = url;
        }

        public void run(){
            String[] nana = url.split("/");
            String fileName = nana[nana.length-1];

            try(InputStream in = new URL(url).openStream(); FileOutputStream out = new FileOutputStream(fileName) ){
                for(;;){
                    int c = in.read();
                    if(c<0)break;
                    out.write((char)c);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done:"+fileName+currentThread());
            DownloadExample.sem.release();
            if (DownloadExample.count != null) DownloadExample.count.addAndGet(1);
        }

    }



}
