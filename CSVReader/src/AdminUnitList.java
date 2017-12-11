package CSVReader.src;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();


    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename,",",true);

        while (reader.next()){
            AdminUnit unit = new AdminUnit();
            if (!reader.isMissing("admin_level"))
            unit.setAdminLevel(reader.getInt("admin_level"));
            if (!reader.isMissing("name"))
            unit.setName(reader.get("name"));
            if (!reader.isMissing("population"))
            unit.setPopulation(reader.getDouble("population"));
            if (!reader.isMissing("area"))
            unit.setArea(reader.getDouble("area"));
            if (!reader.isMissing("dencity"))
            unit.setDensity(reader.getDouble("dencity"));
            //unit.setParent(reader.get(""));

            this.units.add(unit);
        }
    }

    void list(PrintStream out){
        this.units.forEach(unit->out.println(unit.toString()));

//        for(AdminUnit unit: units){
//            out.println(unit.toString());
//        }

    }

    void list(PrintStream out,int offset, int limit ){
        for (int i = offset; i < offset + limit; i++) {
        try {

                out.println(this.units.get(i).toString());
            }

        catch(IndexOutOfBoundsException e){ // klikasz ctrl B i patrzysz co throwuje w interfejsie! :o :o :o :o ;O ;O ;O
            break;
        }}
    }


    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for(AdminUnit unit : units){
            if((regex && unit.getName().matches(pattern)) || (!regex && unit.getName().contains(pattern))){
                ret.units.add(unit);
            }
        }

        return ret;
    }




}
