/*
 * Incomplete Experiment 1 
 *
 * CSI2510 Algorithmes et Structures de Donnees
 * www.uottawa.ca
 *
 * Robert Laganiere, 2022
 *
*/ 
import java.util.List;
import java.util.ArrayList;

import java.io.*;
import java.util.Scanner;

/*
NAME:Mohamed Ismail Asaklil
STUDENT NUMBER: 0300243534
 */

public class Exp1 {
  
  // reads a csv file of 3D points (rethrow exceptions!)
  public static List<Point3D> read(String filename) throws Exception {
	  
    List<Point3D> points= new ArrayList<Point3D>(); 
	double x,y,z;
	
	Scanner sc = new Scanner(new File(filename));  
	// sets the delimiter pattern to be , or \n \r
	sc.useDelimiter(",|\n|\r");  

	// skipping the first line x y z
	sc.next(); sc.next(); sc.next();
	
	// read points
	while (sc.hasNext())  
	{  
		x= Double.parseDouble(sc.next());
		y= Double.parseDouble(sc.next());
		z= Double.parseDouble(sc.next());
		points.add(new Point3D(x,y,z));  
	}   
	
	sc.close();  //closes the scanner  
	
	return points;
  }

  public static void main(String[] args) throws Exception {  
  
    // not reading args[0]
	double eps= Double.parseDouble(args[1]);
  
    // reads the csv file
    List<Point3D> points= Exp1.read(args[2]);
	
	Point3D query= new Point3D(Double.parseDouble(args[3]),
								Double.parseDouble(args[4]),
								Double.parseDouble(args[5]));

	// The file name is entered as the last parameter in the compilation
	String fileName=args[6];

	if(args[0].equals("lin")) {
		// creates the NearestNeighbor instance
		NearestNeighbors nn = new NearestNeighbors(points);
		List<Point3D> neighbors = nn.rangeQuery(query, eps);
		PrintWriter out = new PrintWriter(fileName);// This step allows us to save all the next println in a .txt file
		// is the easiest way to save a print or println result in a .txt file
		out.println("number of neighbors= " + neighbors.size());
		out.println(neighbors);
		out.close();// we finish creating the .txt file
	}

	if(args[0].equals("kd")) {
		// creates the NearestNeighborKD instance
		NearestNeighborsKD n = new NearestNeighborsKD(points);
		List<Point3D> neighbors2 = n.rangeQuery(query, eps);
		PrintWriter out = new PrintWriter(fileName);// This step allows us to save all the next println in a .txt file
		// is the easiest way to save a print or println result in a .txt file
		out.println("number of neighbors= " + neighbors2.size());
		out.println(neighbors2);
		out.close();// we finish creating the .txt file
	}

  }   
}
