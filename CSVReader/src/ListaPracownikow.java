package CSVReader.src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListaPracownikow {
    List<Pracownicy> units = new ArrayList<>();
    public ListaPracownikow(Stream<Pracownicy> adminUnitStream) {
        units = adminUnitStream.collect(Collectors.toList());
    }

    public ListaPracownikow() { }

    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ";", true);

        while (reader.next()) {
            Pracownicy unit = new Pracownicy();
            unit.setName(reader.get("Imię"));
            unit.setFormaZatrudnienia(reader.get("Forma zatrudnienia"));
            unit.setStanowisko(reader.get("w którym został on pracownikiem"));
            unit.setNazwisko(reader.get("Nazwisko"));
            units.add(unit);
        }
    }

    private ListaPracownikow czyNaUmowe(){
        ListaPracownikow ret = new ListaPracownikow();
        for(Pracownicy unit : units){
            if(unit.getFormaZatrudnienia()=="umowa o pracę"){
                ret.units.add(unit);
            }
        }

        return ret;
    }
    private int ileKobiet(){
        ListaPracownikow ret = new ListaPracownikow();
        int LiczbaKobiet = 0;
        for(Pracownicy unit : units){
            if(unit.getName().endsWith("a")){
                LiczbaKobiet++;
            }
        }

        return LiczbaKobiet;
    }

    private int ileMezczyzn(){
        ListaPracownikow ret = new ListaPracownikow();
        int LiczbaMezczyzn = 0;
        for(Pracownicy unit : units){
            if(!unit.getName().endsWith("a")){
                LiczbaMezczyzn++;
            }
        }

        return LiczbaMezczyzn;
    }

    private void WypiszSpecjalista(){
        ListaPracownikow ret = new ListaPracownikow();
        for(Pracownicy unit : units){
            if(unit.getStanowisko().contains("specjalista")){
                ret.units.add(unit);
            }
        }

        for(Pracownicy u: ret.units){
            System.out.printf(u.getName(),u.getNazwisko());
        }
    }


    private void ZadanieD(){
        ListaPracownikow ret = new ListaPracownikow();
        for(Pracownicy unit : units){
            if(!unit.getStanowisko().contains("specjalista") && !unit.getStanowisko().contains("stażysta") && !unit.getStanowisko().contains("sekretarka") && !unit.getStanowisko().contains("asystent")){
                ret.units.add(unit);
            }
        }

        for(Pracownicy u: ret.units){
            System.out.printf(u.getName(),u.getNazwisko(), u.getStanowisko());
        }
    }

    public static void main(String[] args) throws IOException {
        try {
        ListaPracownikow nowa = new ListaPracownikow();
        nowa.read("info_pracownicy.csv");
        nowa.czyNaUmowe().ileKobiet();
        nowa.czyNaUmowe().ileMezczyzn();
        nowa.WypiszSpecjalista();
        nowa.ZadanieD();}
        catch(FileNotFoundException e){
            System.out.print("Nie można przeczytać pliku :(");
        }
    }


}
