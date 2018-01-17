package CSVReader.src;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Double.compare;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();


    public AdminUnitList(Stream<AdminUnit> adminUnitStream) {
        units = adminUnitStream.collect(Collectors.toList());
    }

    public AdminUnitList() { }

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
            else unit.setPopulation(-1);
            if (!reader.isMissing("area"))
            unit.setArea(reader.getDouble("area"));
            if (!reader.isMissing("density"))
            unit.setDensity(reader.getDouble("density"));
            else unit.setDensity(-1);
            if (!reader.isMissing("x1") && !reader.isMissing("x3") && !reader.isMissing("y1") && !reader.isMissing("y3"))
                unit.setBbox(new BoundingBox(reader.getDouble("x1"),reader.getDouble("x3"),reader.getDouble("y1"),reader.getDouble("y3")));
            //unit.setParent(reader.get(""));

            List<AdminUnit> children = new ArrayList<>();

            for(AdminUnit nana : units){
                if(nana.getParent() == unit){
                    unit.children.add(nana);
                }
            }
            this.parentid2child.put(unit.id,unit.children);
            unit.fixMissingValues();
            this.units.add(unit);

        }
    }



    void list(PrintStream out){
        this.units.forEach(unit->out.println(unit.toString()));

        for(AdminUnit unit: units){
            out.println(unit.toString());
        }

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


    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){

        AdminUnitList n = new AdminUnitList();
        for(AdminUnit u: units){
            if (unit.adminLevel == u.adminLevel){
                if(unit.adminLevel==8){
                    if(unit.getBbox().distanceTo(u.getBbox())<maxdistance) n.units.add(u);
                }
                else{
                    if(unit.getBbox().intersects(u.getBbox())) n.units.add(u);
                }
            }
        }
        return n;
    }

    class MojPierwszyKomperator implements Comparator<AdminUnit>{
        @Override
        public int compare(AdminUnit a1, AdminUnit a2){
            return a1.getName().compareTo(a2.getName());
        }
    }
   AdminUnitList sortInplaceByName(){
       AdminUnitList tmp = new AdminUnitList();
       units.sort(new MojPierwszyKomperator());
       this.units = tmp.units;
       return this;

   }

    AdminUnitList sortInplaceByArea(){

        class MojDrugiKomperator implements Comparator<AdminUnit>{
            @Override
            public int compare(AdminUnit a1, AdminUnit a2){
                return Double.compare(a1.getArea(),a2.getArea());
            }
        }

        units.sort(new MojDrugiKomperator());

        return this;
    }

    AdminUnitList sortInplaceByPopulation(){
        this.units.sort((p1,p2)->Double.compare(p1.getPopulation(),p2.getPopulation()));
        return this;
    }


    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        this.units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList outowaList = new AdminUnitList();
        outowaList.units=this.units;
        outowaList.sortInplace(cmp);
        return outowaList;
    }


    AdminUnitList mójPierwszyFilter(Predicate<AdminUnit> pred){
        return new AdminUnitList(units.stream().filter(pred));
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        return new AdminUnitList(units.stream().filter(pred).limit(limit));
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        return new AdminUnitList(units.stream().filter(pred).skip(offset).limit(limit));
    }


}
