import java.util.List;
/*
NAME:Mohamed Ismail Asaklil
STUDENT NUMBER: 0300243534
 */
public class Exp3 {
    public static void main(String[] args) throws Exception {
        //THESE PARAMETERS ARE THE SAME USED IN BOTH OF DBSCAN AND DBSCAN USING KDtree
        String fileName = args[0];// file name is the first argument
        double eps = Double.parseDouble(args[1]);// converting the args because by default they are Strings


        //THE TIME EXECUTION OF THE NORMAL DBSCAN
        //Start the time counter;
        long startTime=System.nanoTime();
        List<Point3D> myList = Exp1.read(fileName);// 1st step is to read the excel file that contains the inputs( 3D points)
        NearestNeighbors nn = new NearestNeighbors(myList);
        for (Point3D point3D : myList) {
            nn.rangeQuery(point3D, eps);
        }
        long endTime=System.nanoTime();
        long duration = (endTime-startTime)/1000000; //in ms
        System.out.println("Time execution of the normal DBScan: "+duration);

        //TIME EXECUTION OF THE DBScan using KD-tree
        long startTime1=System.nanoTime();
        List<Point3D> myList1 = Exp1.read(fileName);// 1st step is to read the excel file that contains the inputs( 3D points)
        NearestNeighborsKD kdtree=new NearestNeighborsKD(myList1);
        for (Point3D point3D : myList1) {
            kdtree.rangeQuery(point3D, eps);
        }
        long endTime1=System.nanoTime();
        long duration1 = (endTime1-startTime1)/1000000; //in ms
        System.out.println("Time execution of the DBScan using KD-tree: "+duration1);
    }
}
