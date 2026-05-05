package ch.heig.gre.labo2.groupA;

import ch.heig.gre.labo2.graph.SSSPAlgorithm;
import ch.heig.gre.labo2.graph.SSSPResult;
import ch.heig.gre.labo2.graph.WeightedDigraph;

import java.util.ArrayList;

/**
 * Shortest Path Faster Algorithm (SPFA).
 */
public class SPFA implements SSSPAlgorithm {


    @Override
    public SSSPResult compute(WeightedDigraph graph, int from) {
        // initialisation
        int[] distances = new int[graph.getNVertices()];
        int[] parent = new int[graph.getNVertices()];
        int[] updates = new int[graph.getNVertices()];
        ArrayList<Integer> queue = new ArrayList<>(graph.getNVertices());
        for (int i = 0; i < graph.getNVertices(); i++) {
            distances[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            updates[i] = 0;
        }
        distances[from] = 0;
        queue.addLast(from);
        updates[from] = 1;

        while (!queue.isEmpty()) {
            Recorder.addVertexFromFIFO(); // increases addVertexFromFIFO counter

            for (WeightedDigraph.Edge edge : graph.getOutgoingEdges(queue.removeFirst())) {
                int distanceToOrigin = distances[edge.from()] + edge.weight();
                Recorder.addEdgeCompute(); // increases addEdgeCompute counter

                if (distances[edge.to()] > distanceToOrigin) {
                    Recorder.addRelaxation(); // increases addRelaction counter

                    distances[edge.to()] = distanceToOrigin; //updates distances
                    parent[edge.to()] = edge.from(); // updates parent
                    if (!queue.contains(edge.to())) { // if element isn't on the list
                        Recorder.addVertextMissing(); //increases AddVertexMissing counter

                        queue.addLast(edge.to());  //adds it to the end of the list
                        updates[edge.to()]++; // increases number of updates
                        if (updates[edge.to()] >= graph.getNVertices()) { // found negative cycle
                            //initialise the cycle
                            ArrayList<Integer> values = new ArrayList<>(graph.getNVertices() + 1);
                            int current = edge.from();
                            values.addLast(edge.to()); // adds first value
                            // going up the tree until conflict
                            do {
                                values.addLast(current);
                                current = parent[current];
                            } while (!values.contains(current));
                            values.addLast(edge.to()); // adds last value
                            // compute the length
                            int length = 0; // init length
                            int i = 0;
                            do {
                                for (WeightedDigraph.Edge e : graph.getOutgoingEdges(values.get(i + 1))) {
                                    if (e.to() == values.get(i)) {
                                        length += e.weight();
                                        break;
                                    }
                                }
                                i++;
                            } while (values.get(i).intValue() != values.getFirst().intValue());
                            return new SSSPResult.NegativeCycle(values.subList(0, i + 1).reversed(), length); // returns the negative cycle
                        }
                    }
                }
            }
        }
        return new SSSPResult.ShortestPathTree(from, distances, parent); // returns the shortest path tree
    }
}