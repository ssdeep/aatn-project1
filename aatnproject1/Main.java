/*
 * This source code is designed and written by Saideep Sambaraju
 * It is to be used for non commercial purposes only
 * DO NOT copy this code without permission from the author
 * Contact Info : 408-203-0492
 */

package aatnproject1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author esamsai
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    // For each experiment plot the degree and total cost of the network against k, for this we write the fields to an output file
        static FileWriter es ;
        static ArrayList<Integer> totalCosts = new ArrayList<Integer>();
        static ArrayList<Double> densities = new ArrayList<Double>();
        //statoc es.write("k,density,cost\n");
    public static void main(String[] args) throws IOException {
        // The number of nodes is N=40 for all test cases
        int V = 40;
        // Read from standard input the k value for each input case, all others are generated in run time
        int k = 0;
        int maxK = 25;
        int minK = 3;
        // write all the answers to an output file called experimentStats.dat
        
        es = new FileWriter(".\\/data\\/experimentStats.dat");
        es.write("k,density,cost\n");
        
        // for the inputs
        //Scanner sc = new Scanner(System.in);
        //k = sc.nextInt();
        densities.clear();
        totalCosts.clear();
        
        for(k=minK; k <= maxK ; k++ ){
            /* for each test case*/
                        
            System.out.println("For k="+k+":");
            // unit cost matrix for potential links
            int[][] a = new int[V][V];
            // traffic demand values for flows between nodes
            int[][] b = new int[V][V];
            // A Random number generator for all randomizing purposes
            Random rand = new Random();
            // Edge vector for the graph to be sent for the Graph constructor
            ArrayList<Edge> edgeList = new ArrayList<>();
            
            
            for(int i=0; i < V ; i++){
                for(int j = 0 ; j < V ; j++){
                    if(i==j)
                        continue;
                    // setting bij to a random integer in [0,1,2,3]
                    b[i][j] = Math.abs(rand.nextInt()%4);
                    // we fill out aij to 30 initially
                    a[i][j] = 30;
                    Edge e = new Edge(i, j, a[i][j]);
                    edgeList.add(e);
                    
                }
            }
            
            // for each i, fill out k random j's with 1 i.e ai[j] = 1
            for(int i = 0 ; i < V ; i++){
                for(int j=k; j>0 ;){
                    int randIndex = Math.abs(rand.nextInt()%V);
                    if(i!=randIndex && a[i][randIndex]!=1){
                        //System.out.println("--");
                        a[i][randIndex] = 1;
                        Edge e = new Edge(i, randIndex, a[i][randIndex]);
                        edgeList.add(e);
                        j--;
                    }
                }
            }
            
            //System.out.println("Printing a[][]");
            
           // printMatrix(a, V);
            
            //System.out.println("Printing b[][]");
           
           // printMatrix(b, V);
            
            // create the network Graph
            Graph network = new Graph(V, edgeList);
            // perform the shortest path calculation
             network.floydWarshall();
            // System.out.println("After Floyd Warshall");
            // printMatrix(network.allPairs, V);
            // System.out.println("The next matrix");
            // printMatrix(network.nextNode, V);
             // After performing the shortest path calculation, compute cost and capacity for links
             int[][] edgeCapacities = new int[V][V];// for storing the edge capacities
             int[][] c = doCostComputation(network, a, b, V, edgeCapacities, k);
            // System.out.println("Cost Matrix");
            // printMatrix(c, V);
             //System.out.println("Edge Capacities");
            // printMatrix(edgeCapacities, V);
             int nonZeroEdges=0;
             for(int i = 0 ; i < V ; i++){
                 for(int j = 0 ; j < V ; j++){
                     if(i==j)
                         continue;
                     if(edgeCapacities[i][j]>0){
                         nonZeroEdges++;
                     }
                 }
             }
             densities.add((double)nonZeroEdges/(V*(V-1)));
             System.out.println("Density of the network:"+densities.get(densities.size()-1));
             /*
             The edge capacities matrix is the primary form of output for this project
             Print out the edge capacities to a file and use the file for graph visualization
             
             */
             // print the results to a file kioutput.dat for each k value = 3..15
             FileWriter fw = new FileWriter("./data/k"+k+"output.dat");
             for(int i = 0 ; i < V ; i++){
                 String line="";
                 for(int j = 0 ; j < V ; j++){
                     line=line+edgeCapacities[i][j]+"\t";
                 }
                 fw.write(line.substring(0, line.length()-1)+"\n");
                         
             }
             fw.close();
            //k = sc.nextInt();
            
            
        }
       
        /*
        write out the densities and total costs for each of the cases
        */
        for( k=minK; k<=maxK ; k++){
            es.write(k+","+densities.get(k-minK)+","+totalCosts.get(k-minK)+"\n");
        }
        es.close();
        
    }
    /*
    a print utility for printing out matrix m of dimensions VxV
    */
    private static void printMatrix(int[][] m, int V){
        String line="";
        for(int i = 0 ; i < V ; i++){
            line="";
            for(int j = 0 ; j < V ; j++){
                line=line+"\t"+m[i][j];
            }
            System.out.println(line);
        }
    }

    private static int[][] doCostComputation(Graph network, int[][] a, int[][] b, int V, int[][] e, int k) throws IOException {

            // For the computation we have the graph with the all pairs shortest paths computed
            
            // We also have the matrices unit cost matrix and the trafic demand 
            // we compute a cost matrix and return it as answer
            int[][] c = new int[V][V];
            // for density computations
            //int nonZeroEdges = 0;
            // compute the capacities for all links and sum them
            int totalCost = 0;
            for(int i= 0 ; i < V ; i++){
                for(int j = 0 ; j < V ; j++){
                    if(i==j)
                        continue;
                    c[i][j] = pathCost(i, j, network, a, b, e);
                    
                    totalCost+=c[i][j];
                    
                }
            }
            // total cost of the network
            System.out.println("Total Cost of the network:"+totalCost);
            totalCosts.add(totalCost);
            // degree of the network
            //double density = nonZeroEdges/(double)(V*(V-1));
            //System.out.println("Density of the network:"+density);
            // write these down to a file
           // es.write(k+","+density+","+totalCost+"\n");
            
            // output the final capacity graph to a file for later use
            return c;
    }

    private static int pathCost(int i, int j, Graph network, int[][] a, int[][] b, int[][] e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
        int start = i ;
        int dest = j;
        int pathUnitCost = 0;
        while(start!=dest){
            int nextNode = network.nextNode[start][dest];
            pathUnitCost = pathUnitCost + a[start][nextNode];
            e[start][nextNode]+= b[i][j];
            start = network.nextNode[start][dest];
            
        }
        pathUnitCost = pathUnitCost*b[i][j];
        
        return pathUnitCost;
    }
    
}

