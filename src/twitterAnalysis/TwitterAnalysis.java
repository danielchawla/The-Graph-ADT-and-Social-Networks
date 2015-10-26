package twitterAnalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * Takes in user specified files with common influencers and/or retweets queries and returns
 * files with query answers.  
 * 
 * @author Annabelle Harvey and Daniel Chawla
 * 
 */
public class TwitterAnalysis {
    
    private static final int INVALID = -2;
    // CONSTANTS used when reading and writing queries
    private static final int QUERYTYPE = 0;
    private static final int USER1 = 1;
    private static final int USER2 = 2;
    private static final int QUESTIONMARK = 3;
    private static final int STRINGSPERLINE = 4;
    private static final String COMMONINFLUENCERS = "commonInfluencers";
    private static final String NUMRETWEETS = "numRetweets";
    private static final String TAB = "    ";
    
    /**
     * Main method. Method that takes in user specified files with common influencers and/or retweets 
     * queries and returns files with query answers. 
     */
    public static void main (String[] Args){
        
        FileInputStream queryStream;
        Scanner console = new Scanner(System.in);
        Boolean exit = false;
        
        System.out.println("Welcome to Twitter Analysis. ");
        
        
        // main twitter analysis loop. will continue until user inputs "exit" when prompted
        while (exit == false){ 
            System.out.println("Enter name of file containing queries or enter 'exit' to quit.");
            String inFile = console.nextLine().trim();
            
            if (inFile.equals("exit")){ // exit if user inputs "exit"
                System.out.println("Program terminating...");
                break;
            }
            
            if (inFile.length() > 0){
                try { // tries reading from file
                    queryStream = new FileInputStream(inFile);
                } catch (FileNotFoundException e) {
                    System.out.println("Error finding file. File may not exist in specified location.");
                    continue;
                }
                
                try {
                    BufferedReader fileReader = new BufferedReader(new InputStreamReader(queryStream));
                    String line;
                    
                    System.out.println("Getting data from twitter database. This could take a couple minutes...");
                    AdjacencyListGraph listGraph = FileToGraph.adjacencyList(); // builds adjacency list with database

                    System.out.println("Received data successfully.");
                    System.out.println("");
                    
                    String resultFile = "";
                    while (resultFile.length() < 1){ 
                        System.out.println("Enter file name to print data to. Make sure to include a .txt at the"
                                + " end of file name (e.g. filename.txt).");
                        System.out.println("WARNING: Ensure there is no existing file with that name in current folder"
                                + " or it will be overwritten.");
                        resultFile = console.nextLine().trim();
                    }
                    
                    List<String> processedQueries = new LinkedList<String>();
                    BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(resultFile), "utf-8")); // initializes writer for output file

                    while ((line = fileReader.readLine()) != null) { //goes through inputed query file line by line                        
                        String[] columns = line.split(" "); // splits each string separated by space character
                        
                        // checks if query has appropriate number of strings per line in file
                        // and remove unnecessary white space
                        if (columns.length == STRINGSPERLINE){
                            columns[QUERYTYPE] = columns[QUERYTYPE].trim();
                            columns[USER1] = columns[USER1].trim();
                            columns[USER2] = columns[USER2].trim();
                            columns[QUESTIONMARK] = columns[QUESTIONMARK].trim();
                        } else {
                            continue;
                        }
                        
                        // checks to see if query has question mark at end of each line 
                        if(! columns[QUESTIONMARK].equals("?")){
                            continue;
                        }
                        
                        Vertex user1 = new Vertex(columns[USER1]);
                        Vertex user2 = new Vertex(columns[USER2]);
                        String query = columns[QUERYTYPE] + columns[USER1] + columns[USER2];
                        
                        if( columns[QUERYTYPE].equals(COMMONINFLUENCERS) && (! processedQueries.contains(query))){
                            processedQueries.add(query); 
                            List<Vertex> commonInfluencers = new LinkedList<Vertex>();
                            
                            // calls algorithm to find common influencers
                            commonInfluencers = findCommonInfluencers(listGraph, user1, user2); 
                            
                            // Write results to out file.
                            fileWriter.write("query: " + COMMONINFLUENCERS + " " + user1 + " " + user2);
                            fileWriter.newLine();
                            fileWriter.write("<result>");
                            fileWriter.newLine();
                            for (Vertex commonInfluencer : commonInfluencers) {
                                if (commonInfluencer != null){
                                    fileWriter.write(TAB + commonInfluencer);
                                    fileWriter.newLine();
                                }
                            }
                            fileWriter.write("</result>");
                            fileWriter.newLine();
                            fileWriter.newLine();
                            
                        } else if( columns[QUERYTYPE].equals(NUMRETWEETS) && (!processedQueries.contains(query))){ 
                            processedQueries.add(query); 
                            
                            int numRetweets = findNumRetweets(listGraph, user1, user2); 
                            
                            // Write results to out file.
                            fileWriter.write("query: " + NUMRETWEETS + " " + user1 + " " + user2);
                            fileWriter.newLine();
                            fileWriter.write("<result>");
                            fileWriter.newLine();
                            if (numRetweets == INVALID) {
                                fileWriter.write(TAB + "Tweet will never reach user " + user2 + ".");
                            } else if (numRetweets >= 0){
                                fileWriter.write(TAB + Integer.toString(numRetweets));
                            }
                            else{
                                fileWriter.write(TAB + user1 + " and " + user2 + " are the same user.");
                            }
                            
                            fileWriter.newLine();
                            fileWriter.write("</result>");
                            fileWriter.newLine();
                            fileWriter.newLine();
                        } 
                    }
                    
                    System.out.println("Output file created...");
                    System.out.println("");
                    
                    fileWriter.close();
                    fileReader.close();
                    queryStream.close();
                    
                } catch (Exception e) {
                    
                    e.printStackTrace();
                }
            }
        }
        
    }
    /**
     * Finds minimum number of retweets before user1's tweet shows up in user2's feed.
     * @param Adjacency List Graph with users who follow other users. 
     * @param user1 - person who sends tweet.
     * @param user2 - person who may user1's tweet in their feed.
     * @return min number of retweets before user1's tweet shows up in user2's feed.
     *         -1 if user1 and user2 are at same vertex .
     *         -2 if user1's tweet will never show up in user2's feed.       
     */
    private static int findNumRetweets(AdjacencyListGraph listGraph, Vertex user1, Vertex user2){
        int distance = Algorithms.shortestDistance(listGraph, user1, user2);
        int numRetweets = -2;
        if (distance >= 0){
            numRetweets = distance - 1;
        }
        return numRetweets;
    }
    
    /**
     * Finds and returns the users who both user1 and user2 follow. 
     * @param Adjacency List Graph with users who follow other users. 
     * @param user1 - person who sends tweet.
     * @param user2 - person who may user1's tweet in their feed.
     * @return List of users vertex's who both user1 and user2 follow
     */
    private static List<Vertex> findCommonInfluencers(AdjacencyListGraph listGraph, Vertex user1, Vertex user2){
        return Algorithms.commonDownstreamVertices(listGraph, user1, user2);
    }
}
    
