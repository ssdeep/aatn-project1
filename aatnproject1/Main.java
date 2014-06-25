/*
 * This source code is designed and written by Saideep Sambaraju
 * It is to be used for non commercial purposes only
 * DO NOT copy this code without permission from the author
 * Contact Info : 408-203-0492
 */

package aatnproject1;

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
    public static void main(String[] args) {
        // The number of nodes is N=40 for all test cases
        int V = 40;
        // Read from standard input the k value for each input case, all others are generated in run time
        int k = 0;
        // for the inputs
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        
        
        while(k!=-1){
            /* for each test case*/
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
            
           // System.out.println("Printing a[][]");
            
          //  printMatrix(a, V);
            
           // System.out.println("Printing b[][]");
           
           // printMatrix(b, V);
            
            // create the network Graph
            Graph network = new Graph(V, edgeList);
            // perform the shortest path calculation
             network.FloydWarshall();
             // After performing the shortest path calculation, compute cost and capacity for links
             doCostComputation(network, a, b, V);
             
            
            k = sc.nextInt();
            
            
        }
       
        
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

    private static void doCostComputation(Graph network, int[][] a, int[][] b, int V) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

