public class Point3D {
    // Instance variables which represents the coordinates of each Point3D
    private double x;
    private double y;
    private double z;
    int cluster_label=0; //cluster label for each point3D
    int index; // index in order to know the position of each Point3D in the list
    // each point3D has a RGB color
    double R;
    double G;
    double B;

    //Constructor
    public Point3D(double x, double y, double z, int index, int cluster_label,double R,double G,double B ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.R=R;
        this.G=G;
        this.B=B;
        this.index = index;
        this.cluster_label = cluster_label;
    }
    //Get coordinates methods
    public double getX(){
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    // Get RGB color methods
    public double getR() {
        return R;
    }
    public double getG() {
        return G;
    }
    public double getB() {
        return B;
    }
    // Calculate distance between 2 Points
    public double distance(Point3D pt){
        double distance = Math.sqrt(Math.pow(this.x-pt.x,2)+Math.pow(this.y-pt.y,2)+Math.pow(this.z-pt.z,2));
        return distance;
    }

    @Override
    public String toString() {
        return   x +","+ y +","+z +","+ cluster_label+","+R+","+G+","+B;
    }
}
