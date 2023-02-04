import java.util.ArrayList;
import java.util.List;

public class NearestNeighborsKD {
    KDtree kdTree;// creating an instance of KDtree inside the NearestNeighborsKD
    // construct with list of points
    public NearestNeighborsKD(List<Point3D> list) {
        kdTree = new KDtree(); //the tree is initially empty (root=null)
        List<Point3D> neighbors= new ArrayList<Point3D>();
        for(Point3D p:list){
            kdTree.add(p);
        }
    }
// the method helps us finding the neighbors
    public List<Point3D> rangeQuery(Point3D p, double eps) {

        // empty list to contain the neighbors
        List<Point3D> neighbors= new ArrayList<Point3D>();
        rangeQuery(p,eps,neighbors,kdTree.root());
        return neighbors;
    }

    private void rangeQuery(Point3D p, double eps, List<Point3D> neighbors, KDtree.KDnode node) {
        if(node==null){
            return;
        }
        if(p.distance(node.point)<eps){
            neighbors.add(node.point);
        }
        if(p.get(node.axis)-eps<=node.value){
            rangeQuery(p,eps,neighbors,node.left);
        }
        if(p.get(node.axis) + eps>node.value){
            rangeQuery(p,eps,neighbors,node.right);
        }
        return;
    }
}

