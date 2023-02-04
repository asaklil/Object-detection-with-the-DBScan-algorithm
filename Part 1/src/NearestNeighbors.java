import java.util.LinkedList;
import java.util.List;

public class NearestNeighbors  {
    List<Point3D> list;
    public NearestNeighbors(List<Point3D> list) {
        this.list=list;
    }
    public List<Point3D> rangeQuery(Point3D p, double eps){
        List<Point3D> Neighbors=new LinkedList<Point3D>(); // empty list
        for(Point3D x: list){
            if(x.distance(p)<=eps){
                Neighbors.add(x);
            }
        }
        return Neighbors;
    }
}
