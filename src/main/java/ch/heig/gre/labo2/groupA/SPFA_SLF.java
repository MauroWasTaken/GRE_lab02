package ch.heig.gre.labo2.groupA;

import ch.heig.gre.labo2.graph.SSSPAlgorithm;
import ch.heig.gre.labo2.graph.SSSPResult;
import ch.heig.gre.labo2.graph.WeightedDigraph;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Shortest Path Faster Algorithm (SPFA).
 */
public class SPFA_SLF implements SSSPAlgorithm {


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
                Recorder.addEdgeCompute();// increases addEdgeCompute counter

                if (distances[edge.to()] > distanceToOrigin) {
                    Recorder.addRelaxation();// increases addRelaction counter

                    distances[edge.to()] = distanceToOrigin; //updates distances
                    parent[edge.to()] = edge.from(); // updates parent
                    if (!queue.contains(edge.to())) { // if element isn't on the queue
                        Recorder.addVertextMissing(); //increases AddVertexMissing counter

                        if (!queue.isEmpty() && distances[edge.to()] >= distances[queue.getFirst()]) { //if it is farther than the next element of the queue
                            queue.addLast(edge.to());   // adds it to the ends of the queue
                        } else {
                            queue.addFirst(edge.to()); // adds it to the beginning of the queue
                        }
                        updates[edge.to()]++; // increases number of updates
                        if (updates[edge.to()] >= graph.getNVertices()) { // found negative cycle
                            //identify the cycle
                            ArrayList<Integer> values = new ArrayList<>();
                            int current = edge.from();
                            values .addLast(current);
                            do {
                                values .addLast(current);
                                current = parent[current];
                            } while (!values.contains(current));
                            // builds the cycle in the right order
                            int cycleStart = current;
                            values.clear();
                            current = edge.to();
                            while (current != cycleStart) {
                                values.addFirst(current);
                                current = parent[current];
                            }
                            values.addFirst(cycleStart);
                            values.addLast(cycleStart); // adds last value
                            // compute the length
                            int length = 0; // init length
                            for (int i = 0; i < values.size() - 1; i++) {
                                for (WeightedDigraph.Edge e : graph.getOutgoingEdges(values.get(i))) {
                                    if (e.to() == values.get(i + 1)) {
                                        length += e.weight();
                                        break;
                                    }
                                }
                            }
                            return new SSSPResult.NegativeCycle(values, length);// returns the negative cycle
                        }
                    }
                }
            }
        }
        return new SSSPResult.ShortestPathTree(from, distances, parent);// returns the shortest path tree
    }
}