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
     * Finds shortest distance between two vertices in a graph.
     * 
     * Requires: Graph is not null, and both vertices are in the graph.
     * 
     * @param graph - the graph to search through.
     * @param a The vertex at which to start the path.
     * @param b The vertex to end at.
     * @return the minimum number of edges required to get from a to b. If no
     *         path exists it will return -1. A path from a vertex to itself is
     *         of 0.
     */
    public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
        Map<Vertex, Boolean> visitTable = new HashMap<Vertex, Boolean>();
        LinkedList<Vertex> vertexQueue = new LinkedList<Vertex>();
        Map<Vertex, Vertex> visitedFrom = new HashMap<Vertex, Vertex>();
        Vertex currentVertexSearch = a;
        Vertex currentVertexCount = b;
        int count = 0;

        for (Vertex v : graph.getVertices()) {
            visitTable.put(v, false);
        }

        // initial cases
        visitedFrom.put(a, a);
        visitTable.replace(a, true);
        vertexQueue.add(currentVertexSearch);

        while (vertexQueue.size() != 0) {
            currentVertexSearch = vertexQueue.remove(0);
            for (Vertex vertexIndex : graph.getUpstreamNeighbors(currentVertexSearch)) {
                if (!visitTable.get(vertexIndex)) {
                    visitTable.replace(vertexIndex, true);
                    vertexQueue.add(vertexIndex);
                    visitedFrom.put(vertexIndex, currentVertexSearch);
                }
                if (vertexIndex.equals(b)){
                    break;
                }
            }
        }

        if (!visitedFrom.keySet().contains(b))
            return -1;

        if (!graph.getUpstreamNeighbors(a).isEmpty()) {
            while (true) {
                count++;
                Vertex prevVertex = visitedFrom.get(currentVertexCount);
                if (prevVertex.equals(a))
                    break;
                currentVertexCount = prevVertex;
            }
        }
        return count;
    }

    /**
     * Performs a breadth first search on a graph.
     * 
     * requires: Graph is not null.
     * 
     * @param graph - The graph to traverse.
     * @return A set of lists of vertices. Each list represents the traversal of
     *         the graph beginning at that vertex ordered according to the 
     *         breadth first search algorithm.
     */
    public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
        Set<List<Vertex>> outputSet = new HashSet<List<Vertex>>();

        for (Vertex v : graph.getVertices()) {
            outputSet.add(Algorithms.bfsHelper(graph, v));
        }
        return outputSet;
    }

    /**
     * Performs a depth first search on a graph.
     * 
     * requires: Graph is not null.
     * 
     * @param graph - The graph to traverse.
     * @return A set of lists of vertices. Each list represents the traversal of
     *         the graph beginning at that vertex ordered according to the 
     *         depth first search algorithm.
     */
    public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
        Set<List<Vertex>> outputSet = new HashSet<List<Vertex>>();

        for (Vertex v : graph.getVertices()) {
            List<Vertex> out = Algorithms.dfsHelper(graph, v);
            outputSet.add(out);
        }

        return outputSet;
    }

    /**
     * Finds common upstream vertices on a graph.
     * 
     * requires: Graph is not null. a and b are vertices in graph.
     * 
     * @param graph - The graph to search through.
     * @param a
     * @param b
     * @return A list of vertices v such that there is an edge from a to v and from b to v.
     */
    public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
        List<Vertex> upstreamVertices = new LinkedList<Vertex>();
        List<Vertex> aUNeighbors = graph.getUpstreamNeighbors(a);
        List<Vertex> bUNeighbors = graph.getUpstreamNeighbors(b);

        if (aUNeighbors.size() < bUNeighbors.size()) {
            for (int i = 0; i < aUNeighbors.size(); i++) {
                if (bUNeighbors.contains(aUNeighbors.get(i))) {
                    upstreamVertices.add(aUNeighbors.get(i));
                }
            }
        } else {
            for (int i = 0; i < bUNeighbors.size(); i++) {
                if (aUNeighbors.contains(bUNeighbors.get(i))) {
                    upstreamVertices.add(bUNeighbors.get(i));
                }
            }
        }
        return upstreamVertices;
    }

    /**
     * Finds common upstream vertices on a graph.
     * 
     * requires: Graph is not null, both vertices a and be are in the graph.
     * 
     * @param graph The graph to search through.
     * @param a A vertex in the graph.
     * @param b A vertex in the graph.
     * @return A list of vertices v such that there is an edge from v to a and from v to b.
     */
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
     * Method to help perform depthFirstSearch(Graph graph)
     * 
     * requires: Graph is valid, v is in the graph.
     * 
     * @param graph The graph to search through.
     * @param v Vertex at which to start the algorithm.
     * @return A list that represents the traversal of
     *         the graph beginning at that vertex ordered according to the 
     *         depth first search algorithm.
     */
    public static List<Vertex> dfsHelper(Graph graph, Vertex v) { // we would make this private but needed it public for JUnit test cases.
        List<Vertex> outputList = new LinkedList<Vertex>();
        Map<Vertex, Boolean> visitTable = new HashMap<Vertex, Boolean>();
        Stack<Vertex> stack = new Stack<Vertex>();
        Vertex currentVertex = v;

        for (Vertex vertex : graph.getVertices()) {
            visitTable.put(vertex, false);
        }

        stack.push(currentVertex);

        while (!stack.isEmpty()) {
            currentVertex = stack.pop();

            if (!visitTable.get(currentVertex)) {
                visitTable.replace(currentVertex, true);
                outputList.add(currentVertex);

                for (Vertex vertex : graph.getUpstreamNeighbors(currentVertex)) {
                    stack.push(vertex);
                    Algorithms.dfsHelper(graph, vertex);

                }
            }
        }
        return outputList;
    }

    /**
     * Method to help perform breadthFirstSearch(Graph graph)
     * 
     * requires: Graph is valid, v is in the graph.
     * 
     * @param graph The graph to search through.
     * @param v Vertex at which to start the algorithm.
     * @return A list that represents the traversal of
     *         the graph beginning at that vertex ordered according to the 
     *         breadth first search algorithm.
     */
    public static List<Vertex> bfsHelper(Graph graph, Vertex v){ // we would make this private but needed it public for JUnit test cases.
        Map<Vertex, Boolean> visitTable = new HashMap<Vertex, Boolean>();
        List<Vertex> outputList = new LinkedList<Vertex>();
        LinkedList<Vertex> queue = new LinkedList<Vertex>();
        Vertex currentVertex = v;
        
        for (Vertex vertex : graph.getVertices()) {
            visitTable.put(vertex, false);
        }

        outputList.add(currentVertex);
        visitTable.replace(currentVertex, true);
        queue.add(currentVertex);

        while (queue.size() != 0) {
            currentVertex = queue.removeFirst();
            for (Vertex indexVertex : graph.getUpstreamNeighbors(currentVertex)) {
                queue.add(indexVertex);

                if (!visitTable.get(indexVertex)) {
                    outputList.add(indexVertex);
                    visitTable.replace(indexVertex, true);
                }
            }
        }
        return outputList;
    }
}
