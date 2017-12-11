public class AdminUnit {

    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();


    public String toString(){
        return String.format("Name: %s, populatio: %f, area: %f.", this.name, this.population, this.area);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public void setParent(AdminUnit parent) {
        this.parent = parent;
    }

    public void setBbox(BoundingBox bbox) {
        this.bbox = bbox;
    }



    public String getName() {
        return name;
    }

    public int getAdminLevel() {
        return adminLevel;
    }

    public double getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getDensity() {
        return density;
    }

    public AdminUnit getParent() {
        return parent;
    }

    public BoundingBox getBbox() {
        return bbox;
    }






}
