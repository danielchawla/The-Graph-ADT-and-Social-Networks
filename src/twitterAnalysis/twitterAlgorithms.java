package twitterAnalysis;

import java.util.List;

import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class twitterAlgorithms {
    
    /**
     * requires: vertex a and b are in the graph
     *           graph is a valid representation of twitter data
     * @param graph graph representing influencers and followers from twitter data
     * @param a twitter user
     * @param b twitter user
     * @return a list of users that both a and b follow
     */

    public static List<Vertex> commonInfluencers(Graph graph, Vertex a, Vertex b){
         return Algorithms.commonDownstreamVertices(graph, a, b);
    }
    
    /**
     * requires: vertex a and b are in the graph
     *           graph is a valid representation of twitter data
     * @param graph representing influencers and followers from twitter data
     * @param a user that produces tweet
     * @param b user that reads tweet
     * @return the minimum number of retweets needed for the reader to see the tweet
     *          assuming every user retweets the inital tweet
     */
    public static int retweets (Graph graph, Vertex a, Vertex b){
        return Algorithms.shortestDistance(graph, a, b);
    }

}
