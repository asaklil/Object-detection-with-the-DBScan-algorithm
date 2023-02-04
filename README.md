# Object-detection-with-the-DBScan-algorithm
Part1: Implementation a data clustering algorithm named DBSCAN -(Density-Based Spatial Clustering of Applications with Noise) & Creation of a program that  
       detects objects (clusters of points in fact) in a scene captured by a LiDAR (a laser scanner):
       . Creating an algorithm that group points that are neighbors into a cluster (Given a large set of data points in a space of arbitrary dimension and given          a distance metric, this algorithm can discover clusters of different shapes and sizes, marking as outliers isolated points in low-density regions (i.e.          points whose nearest neighbors are too far away).)
    
Part 2: Improve the efficiency of this algorithm by using a KD-Tree:
        .Use an abstract data type called k-d tree in order to obtain a better runtime complexity for the neighborhood search. The k-d tree partitions a point
        set using a binary tree representation. There will be two programming steps:
          1. Building the k-d tree by inserting all points in this binary tree. This will be done in the constructor of the new NearestNeighborsKD class.
          2. Finding the neighbors of a given point by searching in the binary k-d tree that should have a depth of O(logN). This will be done in the rangeQuery
          method.
          
        This part is divided into 3 experiences:
              .Exp1: Make sure our algorithm works, to do so compare the outputs of Part1 with Part2.
              .Exp2: Run a program that will print the time complexity of the algorithms and compare them. Since the goal is to have a faster algorithm.
              
