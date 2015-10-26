package ca.ubc.ece.cpen221.mp3.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * An adjacency list representation of a graph made up of vertices and edges.
 * 
 * @author Annabelle Harvey and Daniel Chawla
 */
public class AdjacencyListGraph implements Graph {

    private Map<Vertex, List<Vertex>> listGraph;
    
    /**
     * Constructs new adjacency list graph.
     */
    public AdjacencyListGraph(){
        listGraph = new HashMap<Vertex, List<Vertex>>();
    }

    /**
     * Adds a vertex to the graph.
     *
     * Precondition: v is not already a vertex in the graph
     */
    public void addVertex(Vertex v) {
        List<Vertex> followers = new LinkedList<Vertex>();
        listGraph.put(v, followers);
    }

    /**
     * Adds an edge from v1 to v2.
     *
     * Precondition: v1 and v2 are vertices in the graph
     */
    public void addEdge(Vertex follower, Vertex celeb) {

        listGraph.get(celeb).add(follower);
    }

    /**
     * Check if there is an edge from v1 to v2.
     *
     * Precondition: v1 and v2 are vertices in the graph Postcondition: return
     * true iff an edge from v1 connects to v2
     */
    public boolean edgeExists(Vertex follower, Vertex celeb) {
        return listGraph.get(celeb).contains(follower);
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
    public List<Vertex> getDownstreamNeighbors(Vertex follower) {
        List<Vertex> celebrities = new LinkedList<Vertex>();
        for (Vertex celeb : listGraph.keySet()) {
            if (listGraph.get(celeb).contains(follower))
                celebrities.add(celeb);
        }

        return celebrities;
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
    public List<Vertex> getUpstreamNeighbors(Vertex celeb) {
        return listGraph.get(celeb);
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
