package CV.src;

import CV.src.Paragraph;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {

    UnorderedList lista = new UnorderedList();

    ParagraphWithList(){
        super("");
    }

    ParagraphWithList(String nana) {
        super(nana);
    }


    ParagraphWithList setContent(String lala){
        this.tekst = lala;
        return  this;
    }

    ParagraphWithList addListItem(String item){
        ListItem a = new ListItem(item);
        lista.addItem(a);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p> %s", tekst);
        lista.writeHTML(out);
        out.printf("</p>\n");
    }
}
