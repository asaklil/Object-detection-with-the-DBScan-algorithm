# Object-detection-with-the-DBScan-algorithm

![]()

### Description of the project:

The intelligent vehicles of the future will be equipped with a multitude of sensors in order to capture information about the surrounding scene and thus being able to autonomously navigate. One of these sensors is the Laser Scanner or LiDAR (Light Detection And Ranging). Using a LiDAR, the vehicle can scan the scene in front by sweeping few laser beams (typically between 8 to 64 lasers).
![](https://github.com/asaklil/Object-detection-with-the-DBScan-algorithm/blob/main/Lidar.png)

Each time a laser beam hit an object, the laser light bounce back to the LiDAR from which a precise distance can be estimated. A complete scan of the scene with these lasers will therefore generate a large set of 3D points (also called point cloud) that correspond to the structure of the scene. The figure on the next pageshows a typical point cloud captured by a car equipped with a LiDAR; note that to facilitate the visualization, the points are color-coded based on their height with respect to the ground. A view of the same scene captured by a camera is also shown.
![](https://github.com/asaklil/Object-detection-with-the-DBScan-algorithm/blob/main/scanner.png)

![](https://github.com/asaklil/Object-detection-with-the-DBScan-algorithm/blob/main/Frame1.png)

As it can be seen, each object of the scene will be represented by several 3D points. These object’s points should form a cluster. The objective of Part 1 of your programming assignment is therefore to run the DBSCAN algorithm on a LiDAR point cloud in order to detect the objects around the vehicle.


### Part 1: Implementation a data clustering algorithm named DBSCAN -(Density-Based Spatial Clustering of Applications with Noise) & Creation of a program that detects objects (clusters of points in fact) in a scene captured by a LiDAR (a laser scanner):
       . Creating an algorithm that group points that are neighbors into a cluster (Given a large set of data points in a space of arbitrary dimension and given a distance metric, this algorithm can discover clusters of different shapes and sizes, marking as outliers isolated points in low-density regions (i.e.points whose nearest neighbors are too far away).)
    
### Part 2: Improve the efficiency of this algorithm by using a KD-Tree:
        .Use an abstract data type called k-d tree in order to obtain a better runtime complexity for the neighborhood search. The k-d tree partitions a point
        set using a binary tree representation. There will be two programming steps:
          1. Building the k-d tree by inserting all points in this binary tree. This will be done in the constructor of the new NearestNeighborsKD class.
          2. Finding the neighbors of a given point by searching in the binary k-d tree that should have a depth of O(logN). This will be done in the rangeQuery
          method.
          
        This part is divided into 3 experiences:
              .Exp1: Make sure our algorithm works, to do so compare the outputs of Part1 with Part2.
              .Exp2: Run a program that will print the time taken to find all the neighbors using linear method or the KD-tree method.
              .Exp3: Print runtime of your DBScan program created in part 1, then replace the NearestNeighbors class by the NearestNeighborsKD class and compute again the total runtime.
