package twitterAnalysis;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;

public class TwitterAnalysis {
    
    public static void main (String[] Args){



        // TODO: Duplicate queries should be ignored.
        // TODO: All queries end with ?; lines in the query input file that do not end with a ? can also be ignored.
        
        try {
            AdjacencyMatrixGraph twitterMatrixGraph = FileReader.twitterAdjacencyMatrix();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
