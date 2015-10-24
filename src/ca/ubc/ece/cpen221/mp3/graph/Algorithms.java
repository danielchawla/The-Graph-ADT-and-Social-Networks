package ca.ubc.ece.cpen221.mp3.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Algorithms {

    /**
     * *********************** Algorithms ****************************
     * 
     * Please see the README for the machine problem for a more detailed
     * specification of the behavior of each method that one should implement.
     */

    /**
     * This is provided as an example to indicate that this method and other
     * methods should be implemented here.
     * 
     * You should write the specs for this and all other methods.
     * 
     * @param graph
     * @param a
     * @param b
     * @return
     */

    /**
     * 
     * @param graph
     * @param a
     * @param b
     * @return
     */
    public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
        Map<Vertex, Boolean> visitTable = new HashMap<Vertex, Boolean>();
        LinkedList<Vertex> queue = new LinkedList<Vertex>();
        Map<Vertex, Vertex> visits = new HashMap<Vertex, Vertex>();
        Vertex currentVertex = a;
        Vertex currentVertex2 = b;
        int count = 0;
        // special case
        if (a.equals(b))
            return count;

        for (Vertex v : graph.getVertices()) {
            visitTable.put(v, false);
        }

        // initial cases
        visits.put(a, a);
        visitTable.replace(a, true);
        queue.add(currentVertex);

        while (queue.size() != 0) {
            currentVertex = queue.remove(0);
            for (Vertex indexV : graph.getUpstreamNeighbors(currentVertex)) {
                if (!visitTable.get(indexV)) {
                    visitTable.replace(indexV, true);
                    queue.add(indexV);
                    visits.put(indexV, currentVertex);
                }
                if(indexV.equals(b)) break;
            }
        }

        if (!graph.getUpstreamNeighbors(a).isEmpty()) {
            while (true) {
                count++;
                Vertex prevVertex = visits.get(currentVertex2);
                if (prevVertex.equals(a))
                    break;
                currentVertex2 = prevVertex;
            }
        }

        return count;
    }

    // the way this is written right now makes it so that it treats edges
    // in the wrong direction as not connected
    // so if you start with a vertex at the terminus of a branch you will only
    // visit that vertex
    /**
     * 
     * @param graph
     * @return
     */
    //TODO fix direction of this
    public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
        Set<List<Vertex>> outputSet = new HashSet<List<Vertex>>();
        List<Vertex> vertices = graph.getVertices();
        // System.out.println(graph.getVertices().toString());
        Map<Vertex, Boolean> visitTable = new HashMap<Vertex, Boolean>();

        for (Vertex v : vertices) {
            visitTable.put(v, false);
        }

        for (Vertex v : vertices) {
            List<Vertex> outputList = new LinkedList<Vertex>();
            LinkedList<Vertex> queue = new LinkedList<Vertex>();
            Vertex currentVertex = v;

            outputList.add(currentVertex);
            visitTable.replace(currentVertex, true);
            queue.add(currentVertex);

            while (queue.size() != 0) {
                currentVertex = queue.removeFirst();
                for (Vertex indexV : graph.getDownstreamNeighbors(currentVertex)) {
                    queue.add(indexV);

                    if (!visitTable.get(indexV)) {
                        outputList.add(indexV);
                        visitTable.replace(indexV, true);
                    }
                }
            }

            outputSet.add(outputList);
        }

        return outputSet;
    }

    //TODO implement this
    public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
        Set<List<Vertex>> outputSet = new HashSet<List<Vertex>>();
        List<Vertex> vertices = graph.getVertices();
        // System.out.println(graph.getVertices().toString());
        Map<Vertex, Boolean> visitTable = new HashMap<Vertex, Boolean>();

//        for (Vertex v : vertices) {
//            visitTable.put(v, false);
//        }
//
//        for (Vertex v : vertices) {
//            List<Vertex> outputList = new LinkedList<Vertex>();
//            Stack<Vertex> stack = new Stack<Vertex>();
//            Vertex currentVertex = v;
//
//            outputList.add(currentVertex);
//            stack.push(currentVertex);
//
//            while(!stack.isEmpty()){
//                currentVertex = stack.pop();
//                System.out.println("currentV: "+ currentVertex);
// 
//                    if (!visitTable.get(currentVertex)) {
//                        outputList.add(currentVertex);
//                        System.out.println("added out: "+ currentVertex);
//                        visitTable.replace(currentVertex, true);
//                        System.out.println("Visited "+currentVertex);
//                        
//                        for(Vertex indexV : graph.getDownstreamNeighbors(currentVertex)){
//                            if (!visitTable.get(indexV)){
//                                stack.push(indexV);
//                                System.out.println("added stack: "+ indexV);
//                                //visitTable.replace(indexV, true);
//                                //System.out.println("Visited "+indexV);
//                            }
//                        }
//                    }
//            }
//            System.out.println(outputList.toString());
//            outputSet.add(outputList);
//        }

        return outputSet;

    }

    /**
     * 
     * @param graph
     * @param a
     * @param b
     * @return
     */
    //COMMON FOLLOWERS
    public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
        List<Vertex> upstreamVertices = new LinkedList<Vertex>();
        List<Vertex> aUNeighbors = graph.getUpstreamNeighbors(a);
        List<Vertex> bUNeighbors = graph.getUpstreamNeighbors(b);

        if (aUNeighbors.size() < bUNeighbors.size()) {
            for (int i = 0; i < aUNeighbors.size(); i++) {
                if (bUNeighbors.contains(aUNeighbors.get(i))) {
                    upstreamVertices.add(new Vertex(aUNeighbors.get(i).getLabel()));
                }
            }
        } else {
            for (int i = 0; i < bUNeighbors.size(); i++) {
                if (aUNeighbors.contains(bUNeighbors.get(i))) {
                    upstreamVertices.add(new Vertex(bUNeighbors.get(i).getLabel()));
                }
            }
        }
        return upstreamVertices;
    }

    /**
     * 
     * @param graph
     * @param a
     * @param b
     * @return
     */
    //COMMON CELEBS FOLLOWED
    public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b) {
        List<Vertex> downstreamVertices = new LinkedList<Vertex>();
        List<Vertex> aDNeighbors = graph.getDownstreamNeighbors(a);
        List<Vertex> bDNeighbors = graph.getDownstreamNeighbors(b);
        if (aDNeighbors.size() < bDNeighbors.size()) {
            for (int i = 0; i < aDNeighbors.size(); i++) {
                if (bDNeighbors.contains(aDNeighbors.get(i))) {
                    downstreamVertices.add(new Vertex(aDNeighbors.get(i).getLabel()));
                }
            }
        } else {
            for (int i = 0; i < bDNeighbors.size(); i++) {
                if (aDNeighbors.contains(bDNeighbors.get(i))) {
                    downstreamVertices.add(new Vertex(bDNeighbors.get(i).getLabel()));
                }
            }
        }
        return downstreamVertices;
    }

}
