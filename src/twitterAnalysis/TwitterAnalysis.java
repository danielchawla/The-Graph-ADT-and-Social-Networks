package twitterAnalysis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * Takes in files with common influencers and/or retweets queries and returns
 * file with query answers.  
 * 
 * @author Annabelle Harvey and Daniel Chawla
 * 
 */
public class TwitterAnalysis {
    
    // for reading queries
    private static final int QUERYTYPE = 0;
    private static final int USER1 = 1;
    private static final int USER2 = 3;
    private static final int QUESTIONMARK = 3;
    private static final int STRINGSPERLINE = 4;
    private static final String COMMONINFLUENCERS = "commonInfluencers";
    private static final String NUMRETWEETS = "numRetweets";
    
    /**
     * 
     * @param Args
     */
    public static void main (String[] Args){
        Scanner console = new Scanner(System.in);
        FileInputStream queryStream;
        Boolean exit = false;
        
        System.out.println("Welcome to Twitter Analysis. ");
        
        while (exit == false){ 
            System.out.println("Enter name of file containing queries or enter 'exit' to quit.");
            String inFile = console.nextLine().trim();
            
            if (inFile == "exit"){
                break;
            }
            
            if (inFile.length() > 0){
                try {
                    queryStream = new FileInputStream(inFile);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                

                try {
                    BufferedReader fileReader = new BufferedReader(new InputStreamReader(queryStream));
                    String line;
                    
                    System.out.println("Getting data from twitter database. This could take a couple minutes...");
                    AdjacencyListGraph listGraph = FileReader.adjacencyList();
                    System.out.println("Received data successfully. ");
                    System.out.println("Enter file name to print data to.");
                    String resultFile = console.nextLine().trim();
                    
                    // TODO: create out file

                    while ((line = fileReader.readLine()) != null) {
                        
                        String[] columns = line.split(" "); // splits data in each line between the "->" characters
                        
                        // checks to see if query has question at end of each line and each line has 4 strings seperated by spaces.
                        if(! (columns[QUESTIONMARK].equals("?") && columns.length == STRINGSPERLINE) ){
                            continue;
                        }
                        
                        Vertex user1 = new Vertex(columns[USER1]);
                        Vertex user2 = new Vertex(columns[USER2]);
                        
                        if( columns[QUERYTYPE].equals(COMMONINFLUENCERS)){
                            List<Vertex> commonInfluencers = new LinkedList<Vertex>();
                            commonInfluencers = Algorithms.commonDownstreamVertices(listGraph, user1, user2);
                            //TODO: write to out file
                            
                        } else if( columns[QUERYTYPE].equals("NUMRETWEETS")){
                            int numRetweets = Algorithms.shortestDistance(listGraph, user1, user2);
                            //TODO: write to out file
                        }
                        
                    }
                    fileReader.close();
                    queryStream.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
    
