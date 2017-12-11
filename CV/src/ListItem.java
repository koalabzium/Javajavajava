package CV.src;

import java.io.PrintStream;

public class ListItem {
    String content;
    ListItem(String content_){
        this.content = content_;
    }

    void writeHTML(PrintStream out){
        out.printf("<li> %s </li>\n", content);
    }
}
