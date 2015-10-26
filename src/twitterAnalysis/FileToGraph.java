package twitterAnalysis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * This class contains methods uses reads data from a file and create Adjacency List Graphs
 * and Adjacency Matrix Graphs using that data.
 *
 * @author Annabelle Harvey and Daniel Chawla 
 */
public class FileToGraph {
    private static final int FOLLOWER = 0;
    private static final int FOLLOWING = 1;
    private static FileInputStream INSTREAM;
    
    private static final String INFILE = "datasets/testdatabase.txt"; // file where data is read from 
    
    /**
     * Creates and returns Adjacency List Graph based off data from FileReader.INFILE.
     * 
     * Precondition: follower and following must be separated an "->"
     * @return Adjacency List Graph with data from FileReader.INFILE.
     * @throws Exception if unable to read data from file successfully.
     */
    public static AdjacencyListGraph adjacencyList() throws Exception {
        
        AdjacencyListGraph adjacencyGraph = new AdjacencyListGraph();
        
        try { // tries reading data from the in file
            INSTREAM = new FileInputStream(INFILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(INSTREAM));
            String line;
            
            LinkedList<Vertex> added = new LinkedList<Vertex>();
            
            // main loop that fills adjacency graph as iterating through file line by line.
            while ((line = fileReader.readLine()) != null){
                String[] columns = line.split("->"); // splits data in each line between the "->" characters
                      
                // removes unnecessary white space
                columns[FOLLOWER] = columns[FOLLOWER].trim();
                columns[FOLLOWING] = columns[FOLLOWING].trim();
                
                Vertex follower = new Vertex(columns[FOLLOWER]);
                Vertex following = new Vertex(columns[FOLLOWING]);
                
                if(!added.contains(follower)){
                    adjacencyGraph.addVertex(follower);
                    added.add(follower);
                }
                if(!added.contains(following)){
                    adjacencyGraph.addVertex(following);
                    added.add(following);
                }
            
                if(! adjacencyGraph.edgeExists(follower, following)){
                    adjacencyGraph.addEdge(follower, following);
                }                
            }
            fileReader.close();
            INSTREAM.close();
            
        } catch (FileNotFoundException e) {
            throw new Exception(e);
        }
        return adjacencyGraph;
    }
    
    /**
     * Creates and returns Adjacency Matrix Graph based off data from FileReader.INFILE.
     * 
     * @return Adjacency Matrix Graph with data from FileReader.INFILE.
     * @throws Exception if unable to read data from file successfully.
     */
    public static AdjacencyMatrixGraph adjacencyMatrix() throws Exception {
        
        AdjacencyMatrixGraph matrixGraph = new AdjacencyMatrixGraph();

        try { // tries reading data from the in file
            INSTREAM = new FileInputStream(INFILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(INSTREAM));
            String line;            
            LinkedList<Vertex> added = new LinkedList<Vertex>();
            

            // main loop that fills adjacency graph as iterating through file line by line.
            while ((line = fileReader.readLine()) != null){
                String[] columns = line.split("->"); // splits data in each line between the " -> " characters
                
                // removes unnecessary white space
                columns[FOLLOWER] = columns[FOLLOWER].trim();
                columns[FOLLOWING] = columns[FOLLOWING].trim();
                
                Vertex follower = new Vertex(columns[FOLLOWER]);
                Vertex following = new Vertex(columns[FOLLOWING]);
                
                if(!added.contains(follower)){
                    matrixGraph.addVertex(follower);
                    added.add(follower);
                }
                if(!added.contains(following)){
                    matrixGraph.addVertex(following);
                    added.add(following);
                }
                if(! matrixGraph.edgeExists(follower, following)){
                    matrixGraph.addEdge(follower, following);
                }  
                
            }
            
            fileReader.close();
            INSTREAM.close();
        
        } catch (FileNotFoundException e) {
            throw new Exception(e);
        }
        return matrixGraph;
    }
}