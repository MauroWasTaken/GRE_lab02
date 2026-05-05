package ch.heig.gre.labo2.groupA;

import ch.heig.gre.labo2.graph.SSSPAlgorithm;
import ch.heig.gre.labo2.graph.SSSPResult;
import ch.heig.gre.labo2.graph.WeightedDigraph;

import java.util.ArrayList;

/**
 * Shortest Path Faster Algorithm (SPFA).
 */
public class SPFA_SLF implements SSSPAlgorithm {


    @Override
    public SSSPResult compute(WeightedDigraph graph, int from) {
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
            Recorder.addVertexFromFIFO();
            for (WeightedDigraph.Edge edge : graph.getOutgoingEdges(queue.removeFirst())) {
                int distanceToOrigin = distances[edge.from()] + edge.weight();
                Recorder.addEdgeCompute();
                if (distances[edge.to()] > distanceToOrigin) {
                    Recorder.addRelaxation();
                    distances[edge.to()] = distanceToOrigin;
                    parent[edge.to()] = edge.from();
                    if (!queue.contains(edge.to())) {
                        Recorder.addVertextMissing();
                        if (!queue.isEmpty() && distances[edge.to()] >= distances[queue.getFirst()]) {
                            queue.addLast(edge.to());
                        } else {
                            queue.addFirst(edge.to());
                        }
                        updates[edge.to()]++;
                        if (updates[edge.to()] >= graph.getNVertices()) {
                            //init
                            ArrayList<Integer> values = new ArrayList<>(graph.getNVertices());
                            int current = edge.from();
                            values.addLast(edge.to()); // adds first value
                            // going up the tree until conflict
                            do {
                                values.addLast(current);
                                current = parent[current];
                            } while (!values.contains(current));
                            values.addLast(edge.to()); // adds last value

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
                            } while (values.get(i) != values.get(0));
                            return new SSSPResult.NegativeCycle(values.subList(0, i + 1).reversed(), length);
                        }
                    }
                }
            }
        }
        return new SSSPResult.ShortestPathTree(from, distances, parent);
    }
}