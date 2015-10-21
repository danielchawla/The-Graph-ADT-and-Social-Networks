package ca.ubc.ece.cpen221.mp3.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Algorithms {

    /**
     * *********************** Algorithms ****************************
     * 
     * Please see the README for the machine problem for a more detailed
     * specification of the behavior of each method that one should implement.
     */

    /**
     * This is provided as an example to indicate that this method and other
     * methods should be implemented here.
     * 
     * You should write the specs for this and all other methods.
     * 
     * @param graph
     * @param a
     * @param b
     * @return
     */
    
    public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
        // TODO: Implement this method and others
        return 0;
    }

    public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
        // TODO: Implement this method and others
        List<Vertex> vertices = graph.getVertices();
        //queue for keeping track of what vertex to go to next
        List<Vertex> tracker =  new LinkedList<Vertex>();
      
        Set<List<Vertex>> outputSet = new HashSet<List<Vertex>>();
        
        for (Vertex v : vertices){
            List<Vertex> outputList = new LinkedList<Vertex>();
            //do the algorithm
            outputSet.add(outputList);
        }
        
        return outputSet;

    }

    public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
        // TODO: Implement this method and others
        Set<List<Vertex>> dummyReturn = new HashSet<List<Vertex>>();
        return dummyReturn;

    }

    public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
        // TODO: Implement this method and others
        List<Vertex> dummyReturn = new LinkedList<Vertex>();
        return dummyReturn;

    }

    public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b) {
        // TODO: Implement this method and others
        List<Vertex> dummyReturn = new LinkedList<Vertex>();
        return dummyReturn;

    }

}
