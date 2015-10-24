package twitterAnalysis;

import java.util.List;

import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class twitterAlgorithms {
    
    /**
     * 
     * @param graph graph representing influencers and followers from twitter data
     * @param a twitter user
     * @param b twitter user
     * @return a list of users that both a and b follow
     */
    public static List<Vertex> commonInfluencers(Graph graph, Vertex a, Vertex b){
         return Algorithms.commonUpstreamVertices(graph, a, b);
    }
    
    /**
     * 
     * @param graph
     * @param a
     * @param b
     * @return
     */
    public static int retweets (Graph graph, Vertex a, Vertex b){
        return Algorithms.shortestDistance(graph, a, b);
    }

}
