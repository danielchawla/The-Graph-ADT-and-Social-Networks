package twitterAnalysis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class FileReader {
    private static final int FOLLOWER = 0;
    private static final int FOLLOWING = 1;
    
    private static FileInputStream twitterStream;
    private static String inFile = "datasets/twitter.txt";

    public static AdjacencyListGraph twitterAdjacencyList() throws Exception {
 
        AdjacencyListGraph twitterAdjacencyGraph = new AdjacencyListGraph();

        try {
            twitterStream = new FileInputStream(inFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader twitterReader = new BufferedReader(new InputStreamReader(twitterStream));
            String line;
            
            LinkedList<Vertex> added = new LinkedList<Vertex>();
            
            while ((line = twitterReader.readLine()) != null){
                String[] columns = line.split("->");
                Vertex follower = new Vertex(columns[FOLLOWER]);
                Vertex following = new Vertex(columns[FOLLOWING]);
                
                if(!added.contains(follower)){
                    twitterAdjacencyGraph.addVertex(follower);
                }
                if(added.contains(following)){
                    twitterAdjacencyGraph.addVertex(following);
                }
            
                if(! twitterAdjacencyGraph.edgeExists(follower, following)){
                    twitterAdjacencyGraph.addEdge(follower, following);
                }                
            }
            twitterReader.close();
            twitterStream.close();
        } catch (FileNotFoundException e) {
            throw new Exception(e);
        }
        return twitterAdjacencyGraph;
    }
    
    public static AdjacencyMatrixGraph twitterAdjacencyMatrix() throws Exception {
        
        AdjacencyMatrixGraph twitterMatrixGraph = new AdjacencyMatrixGraph();

        try {
            twitterStream = new FileInputStream(inFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader twitterReader = new BufferedReader(new InputStreamReader(twitterStream));
            String line;            
            LinkedList<Vertex> added = new LinkedList<Vertex>();
            
            double count = 0.0;
            while ((line = twitterReader.readLine()) != null){
                String[] columns = line.split("->");
                Vertex follower = new Vertex(columns[FOLLOWER]);
                Vertex following = new Vertex(columns[FOLLOWING]);
                
                if(!added.contains(follower)){
                    twitterMatrixGraph.addVertex(follower);
                }
                if(added.contains(following)){
                    twitterMatrixGraph.addVertex(following);
                }
                if(! twitterMatrixGraph.edgeExists(follower, following)){
                    twitterMatrixGraph.addEdge(follower, following);
                }  
                
                System.out.println( (int)(++count/1762504*100) + count);
            }
            
            twitterReader.close();
            twitterStream.close();
        
        } catch (FileNotFoundException e) {
            throw new Exception(e);
        }
        return twitterMatrixGraph;
    }
}