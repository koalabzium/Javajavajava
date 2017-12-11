import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main{

public static void main(String [ ] args) throws FileNotFoundException, UnsupportedEncodingException {
    Document cv = new Document("Jana Kowalski - CV");
    cv.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Grosser_Panda.JPG/240px-Grosser_Panda.JPG");
    cv.addSection("Wykształcenie")
            .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
            .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
            .addParagraph("...");
    cv.addSection("Umiejętności")
            .addParagraph(
                    new ParagraphWithList().setContent("Umiejętności")
                            .addListItem("C")
                            .addListItem("C++")
                            .addListItem("Java")
            );
    cv.writeHTML(new PrintStream("cv.html","UTF-8"));
}

}