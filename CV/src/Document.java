import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo = new Photo("url");
    List<Section> sections = new ArrayList<>();


    Document(String nana){
        title=nana;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        this.photo.url=photoUrl;
        return this;
    }

    Section addSection(String sectionTitle){
        Section s = new Section(sectionTitle);
        this.sections.add(s);
        return s;
    }

    Document addSection(Section s){
        this.sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        out.printf("<!doctype html>\n" +
                "<head> \n" +
                " <title>CV</title> \n" +
                " </head>\n" + "<body>\n" + "<h1>" + title + "</h1>");
        photo.writeHTML(out);
        for(Section tmp : sections){
            tmp.writeHTML(out);
        }
        out.printf("</body>\n");
    }

    public void write(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer= new FileWriter(fileName);;
            m.marshal(this, writer);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static Document read(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Unmarshaller m = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);
            return (Document) m.unmarshal(reader);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}


