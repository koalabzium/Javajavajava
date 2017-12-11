package CSVReader.src;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(String filename, String delimiter) throws IOException {
        this(new FileReader(filename), delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(new FileReader(filename), ",", true);
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;

        if (hasHeader) {
            this.parseHeader();
        }
    }


    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            columnLabelsToInt.put(header[i], i);
            columnLabels.add(header[i]);
        }
    }

    String[] current;

    boolean next() throws IOException {

        String line = reader.readLine();
        if (line == null) {
            return false;
        }

        current = line.split(delimiter);

        return true;

    }

    public List<String> getColumnLabels(){
        return this.columnLabels;
    }

    public int getRecordLength(){
        return current.length;
    }

    public boolean isMissing(int columnIndex){
        if (columnIndex>this.getRecordLength() || current[columnIndex].isEmpty()) return true;
        return false;
    }

    public boolean isMissing(String columnLabel) {
        if (!this.columnLabelsToInt.containsKey(columnLabel)) return true;
        return false;
    }

    String get(int columnIndex){
        return this.current[columnIndex];
    }

    String get(String columnLabel){
        return this.current[columnLabelsToInt.get(columnLabel)];
    }

    public int getInt(int columnIndex)
    {
        return Integer.parseInt(current[columnIndex]);
    }

    public int getInt(String columnLabel){
        return Integer.parseInt(get(columnLabel));
    }

    public long getLong(int columnIndex){
        return Long.parseLong(get(columnIndex));
    }

    public  long getLong(String columnLabel){
        return Long.parseLong(get(columnLabel));
    }

    public double getDouble(int columnIndex){

        return Double.parseDouble(get(columnIndex));
    }

    public  double getDouble(String columnLabel){
        return Double.parseDouble(get(columnLabel));
    }



}
