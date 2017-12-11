package CSVReader.src;

import java.io.IOException;
import java.util.Locale;


public class Main {
    public static void main(String[] args) {
        try {
            CSVReader reader = new CSVReader("with-header.csv", ";", true);


            while (reader.next()) {
                if(!reader.isMissing("id")){
                int id = reader.getInt("id");

                String nazwisko = reader.get("nazwisko");
                double nrdomu = reader.getDouble("nrdomu");
                System.out.printf(Locale.US, "%d %s %f \n", id, nazwisko, nrdomu);}
            }
            System.out.println();
        } catch (IOException b) {
            System.out.println("Błąd, błąd, błąd");
        }

        try {
            CSVReader reader = new CSVReader("no-header.csv", ";", false);


            while (reader.next()) {
                int id = reader.getInt(0);
                String nazwisko = reader.get(2);
                if(!reader.isMissing(4)) {
                    double nrdomu = reader.getDouble(4);

                System.out.printf(Locale.US, "%d %s %f \n", id, nazwisko, nrdomu);}
            }}
        catch(IOException e){
                System.out.println("Błąd, błąd, błąd");

            }



    }



}
