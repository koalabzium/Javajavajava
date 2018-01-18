package CSVReader.src;

public class Pracownicy {
    String name;
    String formaZatrudnienia;
    String stanowisko;
    String nazwisko;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormaZatrudnienia() {
        return formaZatrudnienia;
    }

    public void setFormaZatrudnienia(String formaZatrudnienia) {
        this.formaZatrudnienia = formaZatrudnienia;
    }

    public String toString(){
        return String.format("Name: %s, Forma: %s, stanowisko: %s.", this.name, this.formaZatrudnienia, this.stanowisko);
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Object getNazwisko() {
        return nazwisko;
    }
}
