package ca.ubc.ece.cpen221.mp3.tests;
import static org.junit.Assert.*;
import org.junit.Test;


import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;


/**
 * JUnit test cases for AdjacencyMatrixGraph
 * 
 * @author Annabelle Harvey and Daniel Chawla
 */
public class AdjacencyMatrixTests {
    private final static Vertex BOB = new Vertex("Bob");
    private final static Vertex MARY = new Vertex("Mary");
    private final static Vertex FRANK = new Vertex("Frank");

    /**
     * Tests addVertext method and getVertices method of AdjacencyMatrixGraph.
     */
    @Test
    public void addVertextAndGetVerticesTests() {
        AdjacencyMatrixGraph listGraph = new AdjacencyMatrixGraph();
        
        assertEquals(0, listGraph.getVertices().size());

        listGraph.addVertex(BOB);
        listGraph.addVertex(FRANK);
        listGraph.addVertex(null);

        assertFalse(listGraph.getVertices().isEmpty());
        assertFalse(listGraph.getVertices().contains(MARY));
        assertTrue(listGraph.getVertices().contains(BOB));
        assertTrue(listGraph.getVertices().contains(FRANK));
        assertEquals(3, listGraph.getVertices().size());
    }
    
    /**
     * Tests addEdge method and edgeExists method of AdjacencyMatrixGraph.
     */
    @Test
    public void addEdgeAndEdgeExistsTests() {
        AdjacencyMatrixGraph listGraph = new AdjacencyMatrixGraph();

        listGraph.addVertex(BOB);
        listGraph.addVertex(MARY);
        listGraph.addVertex(FRANK);
        listGraph.addEdge(BOB, MARY);

        assertTrue(listGraph.edgeExists(BOB, MARY));
        assertFalse(listGraph.edgeExists(MARY, BOB));
        assertFalse(listGraph.edgeExists(MARY, BOB));
    }
    
    /**
     * Tests getUpstreamNeighbors method of AdjacencyMatrixGraph.
     */
    @Test
    public void getUpstreamNeighborsTest() {
        AdjacencyMatrixGraph listGraph = new AdjacencyMatrixGraph();

        listGraph.addVertex(BOB);
        listGraph.addVertex(MARY);
        listGraph.addVertex(FRANK);        
        listGraph.addEdge(MARY, BOB);
        listGraph.addEdge(FRANK, BOB);

        assertTrue(listGraph.getUpstreamNeighbors(BOB).contains(MARY));
        assertTrue(listGraph.getUpstreamNeighbors(BOB).contains(FRANK));
        assertFalse(listGraph.getUpstreamNeighbors(BOB).contains(BOB));
        assertFalse(listGraph.getUpstreamNeighbors(MARY).contains(BOB));
    }
    
    /**
     * Tests getDownstreamNeighbor method of AdjacencyMatrixGraph.
     */
    @Test
    public void getDownstreamNeighborTest() {
        
        AdjacencyMatrixGraph listGraph = new AdjacencyMatrixGraph();

        listGraph.addVertex(BOB);
        listGraph.addVertex(MARY);
        listGraph.addVertex(FRANK);        
        listGraph.addEdge(FRANK, MARY);
        listGraph.addEdge(FRANK, BOB);
        listGraph.addEdge(BOB, FRANK);

        assertTrue(listGraph.getDownstreamNeighbors(FRANK).contains(MARY));
        assertTrue(listGraph.getDownstreamNeighbors(FRANK).contains(BOB));
        assertTrue(listGraph.getDownstreamNeighbors(BOB).contains(FRANK));
        assertFalse(listGraph.getDownstreamNeighbors(MARY).contains(FRANK));
    }

}
