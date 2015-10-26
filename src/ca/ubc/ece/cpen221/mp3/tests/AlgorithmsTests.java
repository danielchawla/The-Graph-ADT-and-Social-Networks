package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AlgorithmsTests {
    
    private Vertex a = new Vertex("a");
    private Vertex b = new Vertex("b");
    private Vertex s = new Vertex("s");
    private Vertex c = new Vertex("c");
    private Vertex d = new Vertex("d");
    private Vertex e = new Vertex("e");
    private Vertex f = new Vertex("f");
    private Vertex g = new Vertex("g");
    private Vertex h = new Vertex("h");
    
    Graph testGraph = new AdjacencyListGraph();


//    @Test
//    public void breadthFirstSearchTest() {
//        testGraph.addVertex(a);
//        testGraph.addVertex(b);
//        testGraph.addVertex(s);
//        testGraph.addVertex(c);
//        testGraph.addVertex(d);
//        testGraph.addVertex(e);
//        testGraph.addVertex(f);
//        testGraph.addVertex(g);
//        testGraph.addVertex(h);
//        
//        testGraph.addEdge(a, b);
//        testGraph.addEdge(a, s);
//        
//        testGraph.addEdge(s, c);
//        testGraph.addEdge(s, g);
//        
//        testGraph.addEdge(c, d);
//        testGraph.addEdge(c, e);
//        testGraph.addEdge(c, f);
//        
//        testGraph.addEdge(g, f);
//        testGraph.addEdge(g, h);
//        
//        testGraph.addEdge(e, h);
//        
//        Set<List<Vertex>> BFSout = Algorithms.breadthFirstSearch(testGraph);
//        List<Vertex> aExpectedOut = new LinkedList<Vertex>();
////        aExpectedOut.add(a);
////        aExpectedOut.add(b);
////        aExpectedOut.add(s);
////        aExpectedOut.add(c);
////        aExpectedOut.add(g);
////        aExpectedOut.add(d);
////        aExpectedOut.add(e);
////        aExpectedOut.add(f);
////        aExpectedOut.add(h);
////        these are wrong, need to switch direction
//        assert(BFSout.contains(aExpectedOut));
//        
//    }
    
    @Test
    public void shortestDistanceTest() {
        testGraph.addVertex(a);
        testGraph.addVertex(b);
        testGraph.addVertex(s);
        testGraph.addVertex(c);
        testGraph.addVertex(d);
        testGraph.addVertex(e);
        testGraph.addVertex(f);
        testGraph.addVertex(g);
        testGraph.addVertex(h);
        
        testGraph.addEdge(a, b);
        testGraph.addEdge(a, s);
        
        testGraph.addEdge(s, c);
        testGraph.addEdge(s, g);
        
        testGraph.addEdge(c, d);
        testGraph.addEdge(c, e);
        testGraph.addEdge(c, f);
        
        testGraph.addEdge(g, f);
        testGraph.addEdge(g, h);
        
        testGraph.addEdge(e, h);        
        
        assertEquals(0,Algorithms.shortestDistance(testGraph, a, a));
        assertEquals(-1,Algorithms.shortestDistance(testGraph, a, c));
        assertEquals(1,Algorithms.shortestDistance(testGraph, b, a));
        assertEquals(2,Algorithms.shortestDistance(testGraph, c, a));

    }
    
    @Test
    public void depthFirstSearchTest() {
        testGraph.addVertex(a);
        testGraph.addVertex(b);
        testGraph.addVertex(s);
        testGraph.addVertex(c);
        testGraph.addVertex(d);
        testGraph.addVertex(e);
        testGraph.addVertex(f);
        testGraph.addVertex(g);
        testGraph.addVertex(h);
        
        testGraph.addEdge(a, b);
        testGraph.addEdge(a, s);
        
        testGraph.addEdge(s, c);
        testGraph.addEdge(s, g);
        
        testGraph.addEdge(c, d);
        testGraph.addEdge(c, e);
        testGraph.addEdge(c, f);
        
        testGraph.addEdge(g, f);
        testGraph.addEdge(g, h);
        
        testGraph.addEdge(e, h);
        Set<List<Vertex>> DFSout = Algorithms.depthFirstSearch(testGraph);
        List<Vertex> aExpectedOut = new LinkedList<Vertex>();
        List<Vertex> bExpectedOut = new LinkedList<Vertex>();
        aExpectedOut.add(a);
        System.out.println("aExpectedOut "+aExpectedOut);
        
        bExpectedOut.add(b);
        aExpectedOut.add(a);

        assert(DFSout.contains(aExpectedOut));
        assert(DFSout.contains(bExpectedOut));
    }

}
