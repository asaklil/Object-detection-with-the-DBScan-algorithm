import java.util.List;
/*
NAME:Mohamed Ismail Asaklil
STUDENT NUMBER: 0300243534
 */
public class Exp2 {
    public static void main(String[] args) throws Exception {

        //not reading args[0]
        double eps= Double.parseDouble(args[1]);

        // reads the csv file
        List<Point3D> points= Exp1.read(args[2]);

        //the step parameter for the query point
        double step=Double.parseDouble(args[3]);

        int counter=0;

        if (args[0].equals("lin")) {
            NearestNeighbors nn = new NearestNeighbors(points);
            long startTime=System.nanoTime();
            // WE LOOP through the list points that contains the inputs points
            // we increment by *step
            while(counter*(int)step<points.size()) {
                nn.rangeQuery(points.get(counter*(int)step), eps);
                counter+=1;
            }
            long endTime=System.nanoTime();
            long duration = (endTime-startTime)/1000000; //in ms
            System.out.println(duration);
        }

        if(args[0].equals("kd")) {
            NearestNeighborsKD nn = new NearestNeighborsKD(points);
            long startTime=System.nanoTime();
            // WE LOOP through the list points that contains the inputs points
            // we increment by *step
            while(counter*(int)step<points.size()) {
                nn.rangeQuery(points.get(counter*(int)step), eps);
                counter+=1;
            }
            long endTime=System.nanoTime();
            long duration = (endTime-startTime)/1000000; //in ms
            System.out.println(duration);

        }
    }
}
