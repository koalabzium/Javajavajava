package CSVReader.src;

public class BoundingBox {
    Double xmin;
    Double ymin;
    Double xmax;
    Double ymax;

    public BoundingBox (double x1,double x3, double y1, double y3){
        xmin = x1;
        xmax = x3;
        ymin = y1;
        ymax = y3;
    }

    void addPoint(double x, double y){
        if(this.isEmpty()){
            xmin=x;
            xmax=x;
            ymin=y;
            ymax=y;
        }
        if(!contains(x,y)){
            if(x<xmin || y<ymin){
                xmin=x;
                ymin=y;
            }
            if(x>xmax || y>ymax){
                xmax=x;
                ymax=y;
            }
        }
    }


    boolean contains(double x, double y){

        return x > xmin && y > ymin && x < xmax && x < xmin;
    }


    boolean contains(BoundingBox bb){

        return bb.xmin > xmin && bb.ymin > ymin && bb.xmax < xmax && bb.xmin < xmin;
    }


    boolean intersects(BoundingBox bb){
        if (bb.xmin < xmin) if (bb.xmax > xmax) if (bb.ymax < ymax || bb.ymin > ymin) return true;
        if ((bb.xmin < xmax && bb.xmin > xmin) || (bb.ymin < ymax && bb.ymin > ymin)) return true;
        if ((bb.xmax < xmax && bb.xmax > xmin) || (bb.ymax < ymax && bb.ymax > ymin)) return true;
        return false;

    }

    BoundingBox add(BoundingBox bb){
        if(bb.xmin<xmin) xmin=bb.xmin;
        if(bb.xmax>xmax) xmax=bb.xmax;
        if(bb.ymin<ymin) ymin=bb.ymin;
        if(bb.ymax>ymax) ymax=bb.ymax;

        return this;
    }

    boolean isEmpty() {
        if( xmin == null || xmax == null || ymin == null || ymax == null) return true;
        return false;
    }

    double getCenterX(){
        if(isEmpty()) throw new RuntimeException("Pusty");
        else return (xmin+xmax)/2;
    }

    double getCenterY(){
        if(isEmpty()) throw new RuntimeException("Pusty");
        else return (ymin+ymax)/2;
    }


    double distanceTo(BoundingBox bbx){
        if(isEmpty()) throw new RuntimeException("Pusty");
        else return Math.sqrt(Math.pow((bbx.getCenterX() + this.getCenterX()),2) + Math.pow((bbx.getCenterY() + this.getCenterY()),2));
    }

    public String toString(){
        return String.format("x1: %d, y1: %d, x2: %d, y2: %d, x3: %d, y3: %d, x4: %d", xmin,ymin,xmax,ymin,xmax,ymax,xmax,ymin);
    }
}
