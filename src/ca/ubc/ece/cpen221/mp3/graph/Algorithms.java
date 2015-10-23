package ca.ubc.ece.cpen221.mp3.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
        int counter = 0;
        
        List<Vertex> outputList = new LinkedList<Vertex>();
        LinkedList<Vertex> queue = new LinkedList<Vertex>();
        Vertex currentVertex = a;
        Vertex endVertex = b;
        // do the algorithm

        outputList.add(currentVertex);

        while (true) {
            for (Vertex indexV : graph.getDownstreamNeighbors(currentVertex)) {
                queue.add(indexV);
                if (!outputList.contains(indexV)) {
                    outputList.add(indexV);
                }
            }
            if (queue.size() == 0) break;
            currentVertex = queue.remove(0);
        }
        
        return 0;
    }

    //the way this is written right now makes it so that it treats edges
    //in the wrong direction as not connected
    //so if you start with a vertex  at the terminus of a branch you will only
    //visit that vertex
    public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
        Set<List<Vertex>> outputSet = new HashSet<List<Vertex>>();
        List<Vertex> vertices = graph.getVertices();
        System.out.println(graph.getVertices().toString());

        for (Vertex v : vertices) {
            List<Vertex> outputList = new LinkedList<Vertex>();
            LinkedList<Vertex> queue = new LinkedList<Vertex>();
            Vertex currentVertex = v;
            // do the algorithm

            outputList.add(currentVertex);
            // System.out.println("start vertex " + currentVertex.getLabel());
            while (true) {
                for (Vertex indexV : graph.getDownstreamNeighbors(currentVertex)) {
                    queue.add(indexV);
                    // System.out.println("added to queue "+ indexV.getLabel());
                    if (!outputList.contains(indexV)) {
                        outputList.add(indexV);
                        // System.out.println(indexV.getLabel());
                    }
                }
                if (queue.size() == 0)
                    break;
                currentVertex = queue.removeFirst();
                // System.out.println("next start vertex
                // "+currentVertex.getLabel());
            }

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
        List<Vertex> upstreamVertices = new LinkedList<Vertex>();
        List<Vertex> aDNeighbors = graph.getUpstreamNeighbors(a);
        List<Vertex> bDNeighbors = graph.getUpstreamNeighbors(b);
        if (aDNeighbors.size() < bDNeighbors.size()) {
            for (int i = 0; i < aDNeighbors.size(); i++) {
                if (bDNeighbors.contains(aDNeighbors.get(i))) {
                    upstreamVertices.add(new Vertex(aDNeighbors.get(i).getLabel()));
                }
            }
        } else {
            for (int i = 0; i < bDNeighbors.size(); i++) {
                if (aDNeighbors.contains(bDNeighbors.get(i))) {
                    upstreamVertices.add(new Vertex(bDNeighbors.get(i).getLabel()));
                }
            }
        }
        return upstreamVertices;
    }

    public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b) {
        List<Vertex> downstreamVertices = new LinkedList<Vertex>();
        List<Vertex> aDNeighbors = graph.getDownstreamNeighbors(a);
        List<Vertex> bDNeighbors = graph.getDownstreamNeighbors(b);
        if (aDNeighbors.size() < bDNeighbors.size()) {
            for (int i = 0; i < aDNeighbors.size(); i++) {
                if (bDNeighbors.contains(aDNeighbors.get(i))) {
                    downstreamVertices.add(new Vertex(aDNeighbors.get(i).getLabel()));
                }
            }
        } else {
            for (int i = 0; i < bDNeighbors.size(); i++) {
                if (aDNeighbors.contains(bDNeighbors.get(i))) {
                    downstreamVertices.add(new Vertex(bDNeighbors.get(i).getLabel()));
                }
            }
        }
        return downstreamVertices;
    }

}
