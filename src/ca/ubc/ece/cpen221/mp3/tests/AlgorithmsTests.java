package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * JUnit test cases for methods Algorithms class.
 * 
 * @author Annabelle Harvey and Daniel Chawla
 */
public class AlgorithmsTests {
    private static final Vertex A = new Vertex("a");
    private static final Vertex B = new Vertex("b");
    private static final Vertex S = new Vertex("s");
    private static final Vertex C = new Vertex("c");
    private static final Vertex D = new Vertex("d");
    private static final Vertex E = new Vertex("e");
    private static final Vertex F = new Vertex("f");
    private static final Vertex G = new Vertex("g");
    private static final Vertex H = new Vertex("h");
    
    Graph testGraph = new AdjacencyListGraph();
    Graph testGraph2 = new AdjacencyListGraph();
    
    @Before
    public void setup(){        
        testGraph.addVertex(A);
        testGraph.addVertex(B);
        testGraph.addVertex(S);
        testGraph.addVertex(C);
        testGraph.addVertex(D);
        testGraph.addVertex(E);
        testGraph.addVertex(F);
        testGraph.addVertex(G);
        testGraph.addVertex(H);
        
        testGraph.addEdge(A, B);
        testGraph.addEdge(A, S);
        testGraph.addEdge(S, C);
        testGraph.addEdge(S, G);
        testGraph.addEdge(C, D);
        testGraph.addEdge(C, E);
        testGraph.addEdge(C, F);
        testGraph.addEdge(G, F);
        testGraph.addEdge(G, H);
        testGraph.addEdge(E, H);
        
        testGraph2.addVertex(A);
        testGraph2.addVertex(B);
        testGraph2.addVertex(C);
        testGraph2.addVertex(D);
        testGraph2.addVertex(E);
        testGraph2.addVertex(F);
        testGraph2.addVertex(G);
        testGraph2.addEdge(A, B);
        testGraph2.addEdge(A, C);
        testGraph2.addEdge(A, D);
        testGraph2.addEdge(B, C);
        testGraph2.addEdge(B, D);
        testGraph2.addEdge(C, B);
        testGraph2.addEdge(C, E);
        testGraph2.addEdge(C, F);
        testGraph2.addEdge(E, F);
        testGraph2.addEdge(F, G);     
    }
    

    @Test
    public void breadthFirstSearchTest() {
        List<Vertex> aExpectedOut = new LinkedList<Vertex>();
        List<Vertex> bExpectedOut = new LinkedList<Vertex>();
        List<Vertex> cExpectedOut = new LinkedList<Vertex>();
        List<Vertex> dExpectedOut = new LinkedList<Vertex>();
        List<Vertex> sExpectedOut = new LinkedList<Vertex>();
        List<Vertex> gExpectedOut = new LinkedList<Vertex>();

          aExpectedOut.add(A);
          bExpectedOut.add(B);
          bExpectedOut.add(A);
          sExpectedOut.add(S);
          sExpectedOut.add(A);
          cExpectedOut.add(C);
          cExpectedOut.addAll(sExpectedOut);
          dExpectedOut.add(D);
          dExpectedOut.addAll(cExpectedOut);
          gExpectedOut.add(G);
          gExpectedOut.addAll(sExpectedOut);
          
        assert(Algorithms.bfsHelper(testGraph,A).equals(aExpectedOut));
        assert(Algorithms.bfsHelper(testGraph,B).equals(bExpectedOut));
        assert(Algorithms.bfsHelper(testGraph,S).equals(sExpectedOut));
        assert(Algorithms.bfsHelper(testGraph,C).equals(cExpectedOut));
        assert(Algorithms.bfsHelper(testGraph,D).equals(dExpectedOut));
        assert(Algorithms.bfsHelper(testGraph,G).equals(gExpectedOut));
    }
    
    
    @Test
    public void shortestDistanceTest() {         
        assertEquals(0,Algorithms.shortestDistance(testGraph, A, A));
        assertEquals(-1,Algorithms.shortestDistance(testGraph, A, C));
        assertEquals(1,Algorithms.shortestDistance(testGraph, B, A));
        assertEquals(2,Algorithms.shortestDistance(testGraph, C, A));
        assertEquals(-1,Algorithms.shortestDistance(testGraph, H, B));
        assertEquals(-1,Algorithms.shortestDistance(testGraph, H, F));
        assertEquals(3,Algorithms.shortestDistance(testGraph, H, A));
    }
    
    
    @Test
    public void depthFirstSearchTest() {
        Set<List<Vertex>> DFSout = Algorithms.depthFirstSearch(testGraph);
        List<Vertex> aExpectedOut = new LinkedList<Vertex>();
        List<Vertex> bExpectedOut = new LinkedList<Vertex>();
        List<Vertex> sExpectedOut = new LinkedList<Vertex>();
        List<Vertex> cExpectedOut = new LinkedList<Vertex>();
        List<Vertex> dExpectedOut = new LinkedList<Vertex>();
        List<Vertex> eExpectedOut = new LinkedList<Vertex>();
        List<Vertex> fExpectedOut1 = new LinkedList<Vertex>();
        List<Vertex> fExpectedOut2 = new LinkedList<Vertex>();
        List<Vertex> gExpectedOut = new LinkedList<Vertex>();
        List<Vertex> hExpectedOut1 = new LinkedList<Vertex>();
        List<Vertex> hExpectedOut2 = new LinkedList<Vertex>();
        
        aExpectedOut.add(A);
        bExpectedOut.add(B);
        bExpectedOut.add(A);
        sExpectedOut.add(S);
        sExpectedOut.add(A);
        cExpectedOut.add(C);
        cExpectedOut.add(S);
        cExpectedOut.add(A);
        dExpectedOut.add(D);
        dExpectedOut.addAll(cExpectedOut);
        eExpectedOut.add(E);
        eExpectedOut.addAll(cExpectedOut);
        gExpectedOut.add(G);
        gExpectedOut.addAll(sExpectedOut);
        fExpectedOut1.add(F);
        fExpectedOut1.addAll(cExpectedOut);
        fExpectedOut1.add(G);
        fExpectedOut2.add(F);
        fExpectedOut2.addAll(gExpectedOut);
        fExpectedOut2.add(C);
        hExpectedOut1.add(H);
        hExpectedOut1.addAll(eExpectedOut);
        hExpectedOut1.add(G);
        hExpectedOut2.add(H);
        hExpectedOut2.addAll(gExpectedOut);
        hExpectedOut2.add(E);
        hExpectedOut2.add(C);

        assert(Algorithms.dfsHelper(testGraph, A).equals(aExpectedOut));
        assert(Algorithms.dfsHelper(testGraph, B).equals(bExpectedOut));
        assert(Algorithms.dfsHelper(testGraph, C).equals(cExpectedOut));
        assert(Algorithms.dfsHelper(testGraph, D).equals(dExpectedOut));
        assert(Algorithms.dfsHelper(testGraph, E).equals(eExpectedOut));
        assert(Algorithms.dfsHelper(testGraph, G).equals(gExpectedOut));
        assert(Algorithms.dfsHelper(testGraph, S).equals(sExpectedOut));
        assert(Algorithms.dfsHelper(testGraph, F).equals(fExpectedOut1)
                || Algorithms.dfsHelper(testGraph, F).equals(fExpectedOut2));
        assert(Algorithms.dfsHelper(testGraph, H).equals(hExpectedOut1)
                || Algorithms.dfsHelper(testGraph, H).equals(hExpectedOut2));

        for (List<Vertex> list : DFSout) {
            assert(list.equals(Algorithms.dfsHelper(testGraph, list.get(0))));
        }
    }
    
    
    @Test
    public void commonUpstreamNeighborsTest(){
        assert(Algorithms.commonUpstreamVertices(testGraph,D,E).contains(C)); 
        assert(Algorithms.commonUpstreamVertices(testGraph,B,S).contains(A));
        assert(Algorithms.commonUpstreamVertices(testGraph,C,G).contains(S));
        assert(Algorithms.commonUpstreamVertices(testGraph,F,E).contains(C));
    }
    
    @Test
    public void commonDownstreamNeighborsTest(){
        assert(Algorithms.commonDownstreamVertices(testGraph,A,G).isEmpty());
        assert(Algorithms.commonDownstreamVertices(testGraph,C,G).contains(F));  
        assert(Algorithms.commonDownstreamVertices(testGraph2, A,B).contains(C) &&
                Algorithms.commonDownstreamVertices(testGraph2, A,B).contains(D));
    }

}
