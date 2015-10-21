package ca.ubc.ece.cpen221.mp3.graph;
import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
// TODO: Implement this class
    public void addVertex(Vertex v){
        
    }

    public void addEdge(Vertex v1, Vertex v2){
        
    }

    public boolean edgeExists(Vertex v1, Vertex v2){
        //dummy return
        return false;
        
    }

    public List<Vertex> getDownstreamNeighbors(Vertex v){
        List<Vertex> dummyreturn = new LinkedList<Vertex>();
        return dummyreturn;
    }

    public List<Vertex> getUpstreamNeighbors(Vertex v){
        List<Vertex> dummyreturn = new LinkedList<Vertex>();
        return dummyreturn;
    }

    public List<Vertex> getVertices(){
        List<Vertex> dummyreturn = new LinkedList<Vertex>();
        return dummyreturn;
    }
}
