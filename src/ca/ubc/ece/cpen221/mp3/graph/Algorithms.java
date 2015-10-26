package ca.ubc.ece.cpen221.mp3.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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

    /**
     * 
     * @param graph
     * @param a
     *            the vertex at which to start the path
     * @param b
     *            the vertex to end at
     * @return the minimum number of edges required to get from a to b. If no
     *         path exists it will return -1. A path from a vertex to itself is
     *         of 0.
     */
    public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
        Map<Vertex, Boolean> visitTable = new HashMap<Vertex, Boolean>();
        LinkedList<Vertex> queue = new LinkedList<Vertex>();
        Map<Vertex, Vertex> visitedFrom = new HashMap<Vertex, Vertex>();
        Vertex currentVSearch = a;
        Vertex currentVCount = b;
        int count = 0;

        for (Vertex v : graph.getVertices()) {
            visitTable.put(v, false);
        }

        // initial cases
        visitedFrom.put(a, a);
        visitTable.replace(a, true);
        queue.add(currentVSearch);

        while (queue.size() != 0) {
            currentVSearch = queue.remove(0);
            for (Vertex indexV : graph.getUpstreamNeighbors(currentVSearch)) {
                if (!visitTable.get(indexV)) {
                    visitTable.replace(indexV, true);
                    queue.add(indexV);
                    visitedFrom.put(indexV, currentVSearch);
                }
                if (indexV.equals(b))
                    break;
            }
        }

        if (!visitedFrom.keySet().contains(b))
            return -1;

        if (!graph.getUpstreamNeighbors(a).isEmpty()) {
            while (true) {
                count++;
                Vertex prevVertex = visitedFrom.get(currentVCount);
                if (prevVertex.equals(a))
                    break;
                currentVCount = prevVertex;
            }
        }

        return count;
    }

    // the way this is written right now makes it so that it treats edges
    // in the wrong direction as not connected
    // so if you start with a vertex at the terminus of a branch you will only
    // visit that vertex
    /**
     * requires: graph is valid
     * 
     * @param graph
     * @return a set of lists of vertices. each list represents the traversal of
     *         the graph beginning at that vertex
     */
    public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
        Set<List<Vertex>> outputSet = new HashSet<List<Vertex>>();
        Map<Vertex, Boolean> visitTable = new HashMap<Vertex, Boolean>();

        for (Vertex v : graph.getVertices()) {
            visitTable.put(v, false);
        }

        for (Vertex v : graph.getVertices()) {
            List<Vertex> outputList = new LinkedList<Vertex>();
            LinkedList<Vertex> queue = new LinkedList<Vertex>();
            Vertex currentVertex = v;

            outputList.add(currentVertex);
            visitTable.replace(currentVertex, true);
            queue.add(currentVertex);

            while (queue.size() != 0) {
                currentVertex = queue.removeFirst();
                for (Vertex indexV : graph.getUpstreamNeighbors(currentVertex)) {
                    queue.add(indexV);

                    if (!visitTable.get(indexV)) {
                        outputList.add(indexV);
                        visitTable.replace(indexV, true);
                    }
                }
            }

            outputSet.add(outputList);
        }

        return outputSet;
    }
    
    /**
     * 
     * @param graph
     * @return
     */
    public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
        Set<List<Vertex>> outputSet = new HashSet<List<Vertex>>();

        for (Vertex v : graph.getVertices()) {
            List<Vertex> out = Algorithms.dfsHelper(graph, v);
            System.out.println(out);
            outputSet.add(out);
        }

        return outputSet;
    }

    /**
     * 
     * @param graph
     * @param a
     * @param b
     * @return
     */
    // COMMON FOLLOWERS
    public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
        List<Vertex> upstreamVertices = new LinkedList<Vertex>();
        List<Vertex> aUNeighbors = graph.getUpstreamNeighbors(a);
        List<Vertex> bUNeighbors = graph.getUpstreamNeighbors(b);

        if (aUNeighbors.size() < bUNeighbors.size()) {
            for (int i = 0; i < aUNeighbors.size(); i++) {
                if (bUNeighbors.contains(aUNeighbors.get(i))) {
                    upstreamVertices.add(new Vertex(aUNeighbors.get(i).getLabel()));
                }
            }
        } else {
            for (int i = 0; i < bUNeighbors.size(); i++) {
                if (aUNeighbors.contains(bUNeighbors.get(i))) {
                    upstreamVertices.add(new Vertex(bUNeighbors.get(i).getLabel()));
                }
            }
        }
        return upstreamVertices;
    }

    /**
     * 
     * @param graph
     * @param a
     * @param b
     * @return
     */
    // COMMON CELEBS FOLLOWED
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
    
    /**
     * 
     * @param graph
     * @param v
     * @return
     */
    public static List<Vertex> dfsHelper(Graph graph, Vertex v) {
        List<Vertex> outputList = new LinkedList<Vertex>();
        Map<Vertex, Boolean> visitTable = new HashMap<Vertex, Boolean>();
        Stack<Vertex> stack = new Stack<Vertex>();
        Vertex currentV = v;

        outputList.add(currentV);
        stack.push(currentV);
        System.out.println("start vert " + currentV);
        
        for (Vertex vert : graph.getVertices()) {
            visitTable.put(vert, false);
        }
        
        while (!stack.isEmpty()) {
            currentV = stack.pop();

            for (Vertex vert : graph.getUpstreamNeighbors(currentV)) {
                if (!visitTable.get(vert)) {
                    visitTable.replace(vert, true);
                    outputList.add(vert);
                    System.out.println("vert " + vert);
                    stack.push(vert);
                    Algorithms.dfsHelper(graph, vert);
                }
            }
        }
        System.out.println("OutputList " + outputList);
        return outputList;
    }

}
