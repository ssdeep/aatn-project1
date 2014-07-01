/*
 * This source code is designed and written by Saideep Sambaraju
 * It is to be used for non commercial purposes only
 * DO NOT copy this code without permission from the author
 * Contact Info : 408-203-0492
 */

package aatnproject1;

import java.util.ArrayList;

/**
 *
 * @author esamsai
 */
class Graph{
    /*
    Graph is defined as an adjacency matrix of dimensions VxV
    Here V is number of vertices of the graph
    The vertices are numbered 0..V-1
    */
    /*
    The structure that stores the adjacency matrix that created this graph
    */
    int[][] adjMatrix;
    // We have a structure to store the all pairs shortest paths between any two vertices i and j
    int[][] allPairs;
    // We have a structure to store the next node for an all pairs shortest path solution
    int[][] nextNode;
    // Number of vertices in the graph
    int V;
    /*
    The Graph constructor initiates the adjacency matrix with dimensions VxV
    and initialize all edges to infinity or in this case to Integer.MAX_VALUE
    initiates all the shortest pairs to Integer.MAX_VALUE
    and initiates all the next hops to -1
    */
    Graph(int V, ArrayList<Edge> E){
        adjMatrix = new int[V][V];
        allPairs = new int[V][V];
        nextNode = new int[V][V];
            this.V = V;    
            // Initialize all the matrices
                for(int i = 0 ; i < V ; i++){
                    for(int j = 0 ; j < V ; j++){
                        if(i!=j){
                            adjMatrix[i][j] = Integer.MAX_VALUE;
                            allPairs[i][j] = Integer.MAX_VALUE;
                            nextNode[i][j] = -1;
                            }
                        else
                            nextNode[i][j] = j;
                    }
                }
                
            // Initialize all the edges
                for(Edge eij : E){
                    
                    addEdge(eij);
                    
                }
            
        
    }
    /*
    This function adds an edge x-y to the graph. We add all the edges to the graph to initialize the graph
    Note: This remains a directed graph so an edge x-y is not the same as edge y-x
    */
    void addEdge(Edge E){
        adjMatrix[E.x][E.y] = E.weight;
        // In preparation for the Floyd Warshall Algorithm
        allPairs[E.x][E.y]  = E.weight;
        nextNode[E.x][E.y] = E.y;
    }
    void floydWarshall(){
        
        // Perform the shortest path computation for all the pairs using Floyd Warshall Algorithm
        
        for(int k = 0 ; k < V ; k++){
            for(int i = 0 ; i < V ; i++){
                for(int j = 0 ; j < V ; j++){
                    if(allPairs[i][k]+allPairs[k][j]<allPairs[i][j]){
                        allPairs[i][j] = allPairs[i][k] + allPairs[k][j];
                        nextNode[i][j] = nextNode[i][k];
                    }
                }
            }
        }
    }
    
}
