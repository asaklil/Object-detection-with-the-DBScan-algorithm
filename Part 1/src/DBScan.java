import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;


public class DBScan {

    public double eps; // Radius of the cercle that allows us to determine if a point3D belongs to a cluster group
    public int minPts; // the min number of points in order to form a group
    public List<Point3D> list;// our list that contains the Points
    int number_Of_Clusters=0;// number of clusters

    //Constructor
    public DBScan(List<Point3D> list){
        this.list=list;
    }

    //Set methods of eps and minpts
    public double setEps(double eps){
        this.eps=eps;
        return this.eps;
    }
    public int setMinPts(int minPts){
        this.minPts=minPts;
        return this.minPts;
    }

    // FindCluster method allows us to group certain points into a cluster
    public void findClusters() {
        int cluster = 0;
        for (Point3D x : list) {
            if (x.cluster_label != 0) { //cluster_label=0 means that it's undefinied
                continue;
            }
            List<Point3D> N = RangeQuery(list, x, eps); // Find neighbors
            if(N.size()<minPts){
                x.cluster_label=-1; //if the label cluster is -1 it means that it's a noise
                list.set(x.index, x);//replace the element in the index
                continue;
            }
            cluster += 1;
            x.cluster_label=cluster;
            list.set(x.index, x);
            Stack<Point3D> S = new Stack<>();

            // we're pushing all the elements of list N into the stack S
            for(Point3D y: N){
                S.push(y);
            }
            while(!S.isEmpty()){
                Point3D q=S.pop(); //Take the top point of the stack
                if(q.cluster_label==-1){ // Noise becomes border point
                    q.cluster_label=cluster;
                    list.set(q.index, q);
                }
                if(q.cluster_label!=0){
                    continue;
                }
                q.cluster_label=cluster;
                N=RangeQuery(list,q,eps); // Find neighbors
                if(N.size()>=minPts){// Density check
                    for(Point3D z:N){//traversing the elements of neighbors
                        S.push(z); //add neighbors to stack
                    }
                }
            }
        }
        number_Of_Clusters=cluster;// I have created an instance variable that will take the number of clusters once the loop is over
    }
    public List<Point3D> RangeQuery(List<Point3D> list, Point3D Q,double eps){
        List<Point3D> N=new LinkedList<Point3D>(); // empty list of the neighbors;
        for(Point3D x: list){//traversing all the points of the list
            if(x.distance(Q)<=eps){//check if the point is inside the cercle of radius= eps
                N.add(x);
            }
        }
        return N;
    }
    public int getNumberOfClusters(){
        return number_Of_Clusters;
    }
    public List<Point3D> getPoints(){
        return list;
    }
    public static List<Point3D> read(String fileName) throws IOException{
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] array = new String[3];
        int counter = 0;
        String str = "";
        List<Point3D> list = new LinkedList<>();

        while ((str = bufferedReader.readLine()) != null) {
            array = str.split(",");
            if(counter > 1)
                list.add(new Point3D(Double.parseDouble(array[0]+""), Double.parseDouble(array[1]+""), Double.parseDouble(array[2]+""), counter-2, 0,0,0,0));
            counter++;
        }
        bufferedReader.close();
        fileReader.close();
        return list;
    }
    // generator of RGB calors
    public static double[] rgb(){
        double R=Math.random();
        double G=Math.random();
        double B=Math.random();
        double[] array=new double[3];
        array[0]=R;
        array[1]=G;
        array[2]=B;
        return array;
    }
    public void save(String fileName) throws IOException{
        Path outputs=Path.of(fileName);
        String str ="x,y,z,C,R,G,B"+"\n"+"0,0,0,0,0,0,0"+"\n"; // in order to have the same format of outputs as in the PDF
        int temporary=list.get(0).cluster_label; // in order to know if the points are neighbors or not
        // if they are neighbors we are going to
        double[] array=rgb(); // generate a random color RGB
        for(Point3D x:list){
            if(x.cluster_label!=-1) {
                if (x.cluster_label == temporary) {
                    x.R = array[0];
                    x.G = array[1];
                    x.B = array[2];
                } else {
                    temporary = x.cluster_label;
                    double[] new_generator = rgb();
                    x.R = new_generator[0];
                    x.G = new_generator[1];
                    x.B = new_generator[2];
                    array = new_generator;
                }
            }
            else{
                // without saving the generator into the array
                // this if statement help us give different colors to noise points
                // and also hold the same color for the ones in the same group
                double[] new_generator = rgb();
                x.R = new_generator[0];
                x.G = new_generator[1];
                x.B = new_generator[2];
            }
            str+=x.toString()+"\n";
        }
        Files.writeString(outputs,str);
    }


    public static void main(String[] args) throws IOException {
        //Run of the first file Point_Cloud_1
        String fileName = args[0];// file name is the first argument
        double eps = Double.parseDouble(args[1]);// converting the args because by default they are Strings
        int minPoints = Integer.parseInt(args[2]);


        List<Point3D> myList = read(fileName);// 1st step is to read the excel file that contains the inputs( 3D points)
        DBScan dbscan = new DBScan(myList);// Once we have done the step above, we move to create an object with these inputs

        dbscan.setEps(eps);
        dbscan.setMinPts(minPoints);
        dbscan.findClusters();

        dbscan.save("C:\\Users\\LENOVO\\OneDrive\\Documents\\ETUDE\\2nd year\\FALL TERM\\CSI2510\\Programming exercices\\Part1\\src\\Point_Cloud_1_clusters_1.2_10_57.csv");

        //Run of the first file Point_Cloud_2
        String fileName1 = args[3];// file name is the second argument
        double eps2 = Double.parseDouble(args[4]);// converting the args because by default they are Strings
        int minPoints2 = Integer.parseInt(args[5]);


        List<Point3D> myList1 = read(fileName1); // read Point_Cloud_2.csv
        DBScan dbscan1 = new DBScan(myList1);

        dbscan1.setEps(eps2);
        dbscan1.setMinPts(minPoints2);
        dbscan1.findClusters();

        dbscan1.save("C:\\Users\\LENOVO\\OneDrive\\Documents\\ETUDE\\2nd year\\FALL TERM\\CSI2510\\Programming exercices\\Part1\\src\\Point_Cloud_2_clusters_1.0_10_57.csv");

        //Run of the first file Point_Cloud_3
        String fileName2 = args[6];// file name is the third argument
        // we are going to use the same eps and minPts of Point_Cloud_2.csv !!!!!!!!


        List<Point3D> myList2 = read(fileName2);// read Point_Cloud_3.csv
        DBScan dbscan2 = new DBScan(myList2);

        dbscan2.setEps(eps2);// we are going to use the same eps and minPts of Point_Cloud_2.csv !!!!!
        dbscan2.setMinPts(minPoints2);// we are going to use the same eps and minPts of Point_Cloud_2.csv !!!!!
        dbscan2.findClusters();

        dbscan2.save("C:\\Users\\LENOVO\\OneDrive\\Documents\\ETUDE\\2nd year\\FALL TERM\\CSI2510\\Programming exercices\\Part1\\src\\Point_Cloud_3_clusters_1.0_10_57.csv");

        }
    }


