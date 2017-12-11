import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {

    List<ListItem> lista = new ArrayList<>();

    UnorderedList addItem(ListItem item){
        this.lista.add(item);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<ul>\n");
        for(ListItem tmp : lista){
            tmp.writeHTML(out);
        }
        out.printf("</ul>\n");
    }

}
