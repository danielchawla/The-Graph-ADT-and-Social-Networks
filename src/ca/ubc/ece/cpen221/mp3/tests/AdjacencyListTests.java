package ca.ubc.ece.cpen221.mp3.tests;
import static org.junit.Assert.*;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListTests {
    private final static Vertex bob = new Vertex("Bob");
    private final static Vertex mary = new Vertex("Mary");
    private final static Vertex frank = new Vertex("Frank");

    /**
     * Tests addVertext method and getVertices method of AdjencyListTests.
     */
    @Test
    public void addVertextAndGetVerticesTests() {
        AdjacencyListGraph listGraph = new AdjacencyListGraph();
        
        assertEquals(0, listGraph.getVertices().size());

        listGraph.addVertex(bob);
        listGraph.addVertex(frank);
        listGraph.addVertex(null);

        assertFalse(listGraph.getVertices().isEmpty());
        assertFalse(listGraph.getVertices().contains(mary));
        assertTrue(listGraph.getVertices().contains(bob));
        assertTrue(listGraph.getVertices().contains(frank));
        assertEquals(3, listGraph.getVertices().size());
    }
    
    /**
     * Tests addEdge method and edgeExists method of AdjencyListTests.
     */
    @Test
    public void addEdgeAndEdgeExistsTests() {
        AdjacencyListGraph listGraph = new AdjacencyListGraph();

        listGraph.addVertex(bob);
        listGraph.addVertex(mary);
        listGraph.addVertex(frank);
        listGraph.addEdge(bob, mary);

        assertTrue(listGraph.edgeExists(bob, mary));
        assertFalse(listGraph.edgeExists(mary, bob));
        assertFalse(listGraph.edgeExists(mary, bob));
    }
    
    /**
     * Tests getUpstreamNeighbors method of AdjencyListTests.
     */
    @Test
    public void getUpstreamNeighborsTest() {
        AdjacencyListGraph listGraph = new AdjacencyListGraph();

        listGraph.addVertex(bob);
        listGraph.addVertex(mary);
        listGraph.addVertex(frank);        
        listGraph.addEdge(mary, bob);
        listGraph.addEdge(frank, bob);

        assertTrue(listGraph.getUpstreamNeighbors(bob).contains(mary));
        assertTrue(listGraph.getUpstreamNeighbors(bob).contains(frank));
        assertFalse(listGraph.getUpstreamNeighbors(bob).contains(bob));
        assertFalse(listGraph.getUpstreamNeighbors(mary).contains(bob));
    }
    
    /**
     * Tests getDownstreamNeighbor method of AdjencyListTests.
     */
    @Test
    public void getDownstreamNeighborTest() {
        AdjacencyListGraph listGraph = new AdjacencyListGraph();

        listGraph.addVertex(bob);
        listGraph.addVertex(mary);
        listGraph.addVertex(frank);        
        listGraph.addEdge(frank, mary);
        listGraph.addEdge(frank, bob);
        listGraph.addEdge(bob, frank);
        

        assertTrue(listGraph.getDownstreamNeighbors(frank).contains(mary));
        assertTrue(listGraph.getDownstreamNeighbors(frank).contains(bob));
        assertTrue(listGraph.getDownstreamNeighbors(bob).contains(frank));
        assertFalse(listGraph.getDownstreamNeighbors(mary).contains(frank));
    }

}
