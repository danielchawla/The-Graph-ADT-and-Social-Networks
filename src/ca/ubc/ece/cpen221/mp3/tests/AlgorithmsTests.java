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
    Graph testGraph2 = new AdjacencyListGraph();
    
    @Before
    public void setup(){        
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
        
        testGraph2.addVertex(a);
        testGraph2.addVertex(b);
        testGraph2.addVertex(c);
        testGraph2.addVertex(d);
        testGraph2.addVertex(e);
        testGraph2.addVertex(f);
        testGraph2.addVertex(g);
        
        testGraph2.addEdge(a, b);
        testGraph2.addEdge(a, c);
        testGraph2.addEdge(a, d);
        
        testGraph2.addEdge(b, c);
        testGraph2.addEdge(b, d);
        
        testGraph2.addEdge(c, b);
        testGraph2.addEdge(c, e);
        testGraph2.addEdge(c, f);
        
        testGraph2.addEdge(e, f);
        
        testGraph2.addEdge(f, g);     
    }
    


    @Test
    public void breadthFirstSearchTest() {
        List<Vertex> aExpectedOut = new LinkedList<Vertex>();
        List<Vertex> bExpectedOut = new LinkedList<Vertex>();
        List<Vertex> cExpectedOut = new LinkedList<Vertex>();
        List<Vertex> dExpectedOut = new LinkedList<Vertex>();
        List<Vertex> sExpectedOut = new LinkedList<Vertex>();
        List<Vertex> gExpectedOut = new LinkedList<Vertex>();

          aExpectedOut.add(a);
          
          bExpectedOut.add(b);
          bExpectedOut.add(a);
          
          sExpectedOut.add(s);
          sExpectedOut.add(a);
          
          cExpectedOut.add(c);
          cExpectedOut.addAll(sExpectedOut);
          
          dExpectedOut.add(d);
          dExpectedOut.addAll(cExpectedOut);
          
          gExpectedOut.add(g);
          gExpectedOut.addAll(sExpectedOut);
          
//        aExpectedOut.add(b);
//        aExpectedOut.add(s);
//        aExpectedOut.add(c);
//        aExpectedOut.add(g);
//        aExpectedOut.add(d);
//        aExpectedOut.add(e);
//        aExpectedOut.add(f);
//        aExpectedOut.add(h);
//        these are wrong, need to switch direction
        assert(Algorithms.bfsHelper(testGraph,a).equals(aExpectedOut));
        assert(Algorithms.bfsHelper(testGraph,b).equals(bExpectedOut));
        assert(Algorithms.bfsHelper(testGraph,s).equals(sExpectedOut));
        assert(Algorithms.bfsHelper(testGraph,c).equals(cExpectedOut));
        assert(Algorithms.bfsHelper(testGraph,d).equals(dExpectedOut));
        assert(Algorithms.bfsHelper(testGraph,g).equals(gExpectedOut));
    }
    
    @Test
    public void shortestDistanceTest() {         
        assertEquals(0,Algorithms.shortestDistance(testGraph, a, a));
        assertEquals(-1,Algorithms.shortestDistance(testGraph, a, c));
        assertEquals(1,Algorithms.shortestDistance(testGraph, b, a));
        assertEquals(2,Algorithms.shortestDistance(testGraph, c, a));
        assertEquals(-1,Algorithms.shortestDistance(testGraph, h, b));
        assertEquals(-1,Algorithms.shortestDistance(testGraph, h, f));
        assertEquals(3,Algorithms.shortestDistance(testGraph, h, a));

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
        
        aExpectedOut.add(a);
        
        bExpectedOut.add(b);
        bExpectedOut.add(a);
        
        sExpectedOut.add(s);
        sExpectedOut.add(a);
        
        cExpectedOut.add(c);
        cExpectedOut.add(s);
        cExpectedOut.add(a);
        
        dExpectedOut.add(d);
        dExpectedOut.addAll(cExpectedOut);
        
        eExpectedOut.add(e);
        eExpectedOut.addAll(cExpectedOut);
        
        gExpectedOut.add(g);
        gExpectedOut.addAll(sExpectedOut);
        
        fExpectedOut1.add(f);
        fExpectedOut1.addAll(cExpectedOut);
        fExpectedOut1.add(g);
        
        fExpectedOut2.add(f);
        fExpectedOut2.addAll(gExpectedOut);
        fExpectedOut2.add(c);
        
        hExpectedOut1.add(h);
        hExpectedOut1.addAll(eExpectedOut);
        hExpectedOut1.add(g);
        
        hExpectedOut2.add(h);
        hExpectedOut2.addAll(gExpectedOut);
        hExpectedOut2.add(e);
        hExpectedOut2.add(c);

            assert(Algorithms.dfsHelper(testGraph,a).equals(aExpectedOut));
            assert(Algorithms.dfsHelper(testGraph,b).equals(bExpectedOut));
            assert(Algorithms.dfsHelper(testGraph,c).equals(cExpectedOut));
            assert(Algorithms.dfsHelper(testGraph,d).equals(dExpectedOut));
            assert(Algorithms.dfsHelper(testGraph,e).equals(eExpectedOut));
            assert(Algorithms.dfsHelper(testGraph,g).equals(gExpectedOut));
            assert(Algorithms.dfsHelper(testGraph,s).equals(sExpectedOut));
            assert(Algorithms.dfsHelper(testGraph,f).equals(fExpectedOut1) ||
                    Algorithms.dfsHelper(testGraph,f).equals(fExpectedOut2));
            assert(Algorithms.dfsHelper(testGraph,h).equals(hExpectedOut1) || 
                    Algorithms.dfsHelper(testGraph,h).equals(hExpectedOut2));
            
            for(List<Vertex> list : DFSout){
                assert(list.equals(Algorithms.dfsHelper(testGraph,list.get(0))));
            }

    }
    
    @Test
    public void commonUpstreamNeighborsTest(){
        assert(Algorithms.commonUpstreamVertices(testGraph,d,e).contains(c)); 
        assert(Algorithms.commonUpstreamVertices(testGraph,b,s).contains(a));
        assert(Algorithms.commonUpstreamVertices(testGraph,c,g).contains(s));
        assert(Algorithms.commonUpstreamVertices(testGraph,f,e).contains(c));
    }
    
    @Test
    public void commonDownstreamNeighborsTest(){
        assert(Algorithms.commonDownstreamVertices(testGraph,a,g).isEmpty());
        assert(Algorithms.commonDownstreamVertices(testGraph,c,g).contains(f));  
        assert(Algorithms.commonDownstreamVertices(testGraph2, a,b).contains(c) &&
                Algorithms.commonDownstreamVertices(testGraph2, a,b).contains(d));
        
    }

}
