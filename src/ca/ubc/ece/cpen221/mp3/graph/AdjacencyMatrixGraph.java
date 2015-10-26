package ca.ubc.ece.cpen221.mp3.graph;

import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * An adjacency matrix representation of a graph made up of different vertices.
 * 
 * @author Annabelle Harvey and Daniel Chawla
 */
public class AdjacencyMatrixGraph implements Graph {

    List<LinkedList<Boolean>> matrixRows;
    List<Vertex> vertices;
    private static final int invalidIndex = -1;
    
    /**
     * Constructs new adjacency matrix graph.
     */
    public AdjacencyMatrixGraph() {
        matrixRows = new LinkedList<LinkedList<Boolean>>();
        vertices = new LinkedList<Vertex>();  
    }
    
    /**
     * Adds a vertex to the graph.
     *
     * Precondition: v is not already a vertex in the graph
     */
    public void addVertex(Vertex v) {
        vertices.add(v);
        Boolean f = false;
            for (List<Boolean> row : matrixRows) {
                    row.add(f);
                }
  
            LinkedList<Boolean> newRow = new LinkedList<Boolean>();
            for (int i = 0; i < vertices.size(); i++) {
                newRow.add(f);
            }
            matrixRows.add(newRow);
    }
    
    /**
     * Adds an edge from v1 to v2.
     *
     * Precondition: v1 and v2 are vertices in the graph
     */
    public void addEdge(Vertex row, Vertex col) {
        int indexRow = vertices.indexOf(row);
        int indexCol = vertices.indexOf(col);
        matrixRows.get(indexRow).set(indexCol, true);
    }
    
    /**
     * Check if there is an edge from v1 to v2.
     *
     * Precondition: v1 and v2 are vertices in the graph 
     * Postcondition: return true iff an edge from v1 connects to v2
     */
    public boolean edgeExists(Vertex row, Vertex col) {

        int indexRow = vertices.indexOf(row);
        int indexCol = vertices.indexOf(col);

        if (indexRow == invalidIndex || indexCol == invalidIndex){ //checks to see if v1 and v2 are valid even though specified in precondition
            return false;
        }
        
        return matrixRows.get(indexRow).get(indexCol);
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
        List<Vertex> downstreamNeighbours = new LinkedList<Vertex>();
        
        int vertexIndex = vertices.indexOf(v);

        for (int i = 0; i < vertices.size(); i++) {
            if (matrixRows.get(vertexIndex).get(i)) {
                downstreamNeighbours.add(vertices.get(i));
            }
        }
        return downstreamNeighbours;
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
        List<Vertex> upstreamNeighbours = new LinkedList<Vertex>();
        int vertexIndex = vertices.indexOf(v);

        for (int i = 0; i < vertices.size(); i++) {
            if (matrixRows.get(i).get(vertexIndex)) {
                upstreamNeighbours.add(vertices.get(i));
            }
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
        return vertices;
    }
}
