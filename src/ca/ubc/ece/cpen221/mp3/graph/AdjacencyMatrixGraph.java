package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
    // TODO: Implement this class

    // Matrix is essentially an ArrayList of ArrayLists
    List<ArrayList<Boolean>> matrixRows;
    List<Vertex> vertices;
    int matrixSize;
    private final int EXPAND_MATRIX = 100;

    // Constructor for the class
    // Initializes a matrix of size 10000x10000
    // keeps track of how many things you have in it
    AdjacencyMatrixGraph() {
        matrixSize = 10000;
        matrixRows = new ArrayList<ArrayList<Boolean>>();
        vertices = new ArrayList<Vertex>();
        for (int i = 0; i < matrixSize; i++) {
            ArrayList<Boolean> row = new ArrayList<Boolean>();
            for (int k = 0; k < matrixSize; k++) {
                row.add(false);
            }
            matrixRows.add(row);
        }
    }

    /**
     * Adds a vertex to the graph.
     *
     * Precondition: v is not already a vertex in the graph
     */
    public void addVertex(Vertex v) {
        // if graph is full make the graph bigger then add the vertex
        // size of labels should never be bigger than matrix size because we
        // check every time
        if (vertices.size() == matrixSize) {
            // increase the size of all existing rows
            for (int i = 0; i < matrixSize; i++) {
                for (int k = 0; k < EXPAND_MATRIX; k++) {
                    matrixRows.get(i).add(false);
                }
            }
            // make a new empty list
            ArrayList<Boolean> emptyRow = new ArrayList<Boolean>();
            for (int i = 0; i < (EXPAND_MATRIX + matrixSize); i++) {
                emptyRow.add(false);
            }

            // add empty rows to matrix
            for (int i = 0; i < EXPAND_MATRIX; i++) {
                matrixRows.add(emptyRow);
            }

            matrixSize += EXPAND_MATRIX;
        }

        vertices.add(v);

    }

    /**
     * Adds an edge from v1 to v2.
     *
     * Precondition: v1 and v2 are vertices in the graph
     */
    public void addEdge(Vertex v1, Vertex v2) {
        // REP INVARIANT
        // EDGE FROM v1 to v2 means "v1 follows v2"
        // matrix ROW v1 COLUMN v2 = 1

        // find index v1 and v2 in labels
        int indexV1 = vertices.indexOf(v1);
        int indexV2 = vertices.indexOf(v2);

        matrixRows.get(indexV1).set(indexV2, true);
    }

    /**
     * Check if there is an edge from v1 to v2.
     *
     * Precondition: v1 and v2 are vertices in the graph Postcondition: return
     * true iff an edge from v1 connects to v2
     */
    public boolean edgeExists(Vertex v1, Vertex v2) {
        // find index v1 and v2 in labels
        int indexV1 = vertices.indexOf(v1);
        int indexV2 = vertices.indexOf(v2);

        return matrixRows.get(indexV1).get(indexV2);
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
        // looking through row v
        // make an empty list to return
        List<Vertex> downstreamNeighbours = new LinkedList<Vertex>();
        // find vertex index
        int indexV = vertices.indexOf(v);

        for (int i = 0; i < vertices.size(); i++) {

            if (matrixRows.get(indexV).get(i)) {
                // defensive copying here, not sure if necessary
                downstreamNeighbours.add(new Vertex(vertices.get(i).getLabel()));
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
        // looking through column v
        // make an empty list to return
        List<Vertex> upstreamNeighbours = new LinkedList<Vertex>();
        // find vertex index
        int indexV = vertices.indexOf(v);

        for (int i = 0; i < vertices.size(); i++) {

            if (matrixRows.get(i).get(indexV)) {
                // defensive copying here, not sure if necessary
                upstreamNeighbours.add(new Vertex(vertices.get(i).getLabel()));
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
        // using defensive copying
        List<Vertex> allVertices = new ArrayList<Vertex>();
        for (int i = 0; i < vertices.size(); i++) {
            allVertices.add(new Vertex(vertices.get(i).getLabel()));
        }

        return allVertices;

        // otherwise without defensive copying
        // return vertices;

    }
}
