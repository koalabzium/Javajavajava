import java.io.PrintStream;

public class Paragraph {
    String tekst;

    Paragraph(String nana){
        tekst = nana;
    }

    Paragraph setContent(String content){
        this.tekst = content;
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p> %s </p>\n", tekst);
    }

}
