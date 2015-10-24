package ca.ubc.ece.cpen221.mp3.graph;

import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {

    // Matrix is essentially an ArrayList of ArrayLists
    List<LinkedList<Boolean>> matrixRows;
    List<Vertex> vertices;
    private static final int invalidIndex = -1;

    AdjacencyMatrixGraph() {
        matrixRows = new LinkedList<LinkedList<Boolean>>();
        vertices = new LinkedList<Vertex>();
    }

    public void addVertex(Vertex v) {

            for (int i = 0; i < vertices.size(); i++) {
                    matrixRows.get(i).add(false);
                }
  
            LinkedList<Boolean> newRow = new LinkedList<Boolean>();
            for (int i = 0; i <= vertices.size(); i++) {
                newRow.add(false);
            }

            matrixRows.add(newRow);

        vertices.add(v);

    }

    public void addEdge(Vertex v1, Vertex v2) {
        // REP INVARIANT
        // EDGE FROM v1 to v2 means "v1 follows v2"
        // matrix ROW v1 COLUMN v2 = true

        // find index v1 and v2 in labels
        int indexV1 = vertices.indexOf(v1);
        int indexV2 = vertices.indexOf(v2);

        matrixRows.get(indexV1).set(indexV2, true);
    }

    public boolean edgeExists(Vertex v1, Vertex v2) {
        // find index v1 and v2 in labels

        int indexV1 = vertices.indexOf(v1);
        int indexV2 = vertices.indexOf(v2);

        if (! (indexV1 == invalidIndex && indexV2 == invalidIndex)){
            return false;
        }
        
        return matrixRows.get(indexV1).get(indexV2);
    }

    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        // looking through row v
        // make an empty list to return
        List<Vertex> downstreamNeighbours = new LinkedList<Vertex>();
        // find vertex index
        int indexV = vertices.indexOf(v);

        for (int i = 0; i < vertices.size(); i++) {

            if (matrixRows.get(indexV).get(i)) {
                downstreamNeighbours.add(vertices.get(i));
            }
        }
        return downstreamNeighbours;

    }

    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        // looking through column v
        // make an empty list to return
        List<Vertex> upstreamNeighbours = new LinkedList<Vertex>();
        // find vertex index
        int indexV = vertices.indexOf(v);

        for (int i = 0; i < vertices.size(); i++) {

            if (matrixRows.get(i).get(indexV)) {
                upstreamNeighbours.add(vertices.get(i));
            }
        }
        return upstreamNeighbours;

    }

    public List<Vertex> getVertices() {
        return vertices;
    }
}
