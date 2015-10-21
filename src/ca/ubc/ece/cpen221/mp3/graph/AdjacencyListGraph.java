package ca.ubc.ece.cpen221.mp3.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
    // TODO: Implement this class

    private Map<Vertex, List<Vertex>> listGraph = new HashMap<Vertex, List<Vertex>>();

    /**
     * Adds a vertex to the graph.
     *
     * Precondition: v is not already a vertex in the graph
     */
    public void addVertex(Vertex v) {
        List<Vertex> following = new LinkedList<Vertex>();
        listGraph.put(v, following);
    }

    /**
     * Adds an edge from v1 to v2.
     *
     * Precondition: v1 and v2 are vertices in the graph
     */
    public void addEdge(Vertex v1, Vertex v2) {
        // get list mapped to v1 and add v2
        // v1 follows v2
        listGraph.get(v1).add(v2);
    }

    /**
     * Check if there is an edge from v1 to v2.
     *
     * Precondition: v1 and v2 are vertices in the graph Postcondition: return
     * true iff an edge from v1 connects to v2
     */
    public boolean edgeExists(Vertex v1, Vertex v2) {
        // check list mapped to v1 for v2
        // does v1 follow v2?
        return listGraph.get(v1).contains(v2);
    }

    /**
     * Get an array containing all downstream vertices adjacent to v.
     *
     * Precondition: v is a vertex in the graph
     * 
     * Postcondition: returns a list containing each vertex w such that there is
     * an edge from v to w. The size of the list must be as small as possible
     * (No trailing null elements). This method should return a list of size 0
     * iff v has no downstream neighbors.
     */
    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        // return list mapped to v
        // who does v follow
        return listGraph.get(v);

    }

    /**
     * Get an array containing all upstream vertices adjacent to v.
     *
     * Precondition: v is a vertex in the graph
     * 
     * Postcondition: returns a list containing each vertex u such that there is
     * an edge from u to v. The size of the list must be as small as possible
     * (No trailing null elements). This method should return a list of size 0
     * iff v has no upstream neighbors.
     */
    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        // return a list of followers

        List<Vertex> upstreamNeighbours = new LinkedList<Vertex>();
        for (Vertex vertex : listGraph.keySet()) {
            if (listGraph.get(vertex).contains(v))
                upstreamNeighbours.add(vertex);
        }

        return upstreamNeighbours;

    }

    /**
     * Get all vertices in the graph.
     *
     * Postcondition: returns a list containing all vertices in the graph. This
     * method should return a list of size 0 iff the graph has no vertices.
     */
    public List<Vertex> getVertices() {
        List<Vertex> allVertices = new LinkedList<Vertex>();
        allVertices.addAll(listGraph.keySet());
        return allVertices;

    }
}
