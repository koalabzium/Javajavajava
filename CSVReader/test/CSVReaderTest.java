import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

import static org.junit.Assert.*;


public class CSVReaderTest {
    @Test
    public void parseHeader() throws Exception {

    }

    @Test
    public void moj_test(){
        String tekst = "a,b,c,d\ntekścik,4,6.5,1234567123457123456";
        try {
            CSVReader reader = new CSVReader(new StringReader(tekst), ",", true);
            reader.next();
            assertEquals("tekścik", reader.get("a"));
            assertEquals(4, reader.getInt("b"));
            assertEquals(6.5D, reader.getDouble("c"), 0.1);
            assertEquals(1234567123457123456L, reader.getLong("d"));



        }
        catch(IOException e){
            fail("Nananannana błąąąąąąąąąąąąąąąąąąąąąąąad");
        }



    }

    @Test
    public void next() throws Exception {
    }

    @Test
    public void getColumnLabels() throws Exception {
    }

    @Test
    public void getRecordLength() throws Exception {
    }

    @Test
    public void isMissing() throws Exception {
    }

    @Test
    public void isMissing1() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void get1() throws Exception {
    }

    @Test
    public void getInt() throws Exception {
    }

    @Test
    public void getInt1() throws Exception {
    }

    @Test
    public void getLong() throws Exception {
    }

    @Test
    public void getLong1() throws Exception {
    }

    @Test
    public void getDouble() throws Exception {
    }

    @Test
    public void getDouble1() throws Exception {
    }


}