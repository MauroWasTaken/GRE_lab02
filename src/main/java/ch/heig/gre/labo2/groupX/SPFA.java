package ch.heig.gre.labo2.groupX;

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
    int distances[] = new int[graph.getNVertices()];
    int parent[] = new int[graph.getNVertices()];
    int updates[] = new int[graph.getNVertices()];
    ArrayList<Integer> queue = new ArrayList<>(graph.getNVertices());
    for (int i = 0; i < graph.getNVertices(); i++){
      distances[i] = Integer.MAX_VALUE;
      parent[i] = -1; // todo maybe changer par -1?
      updates[i] = 0;
    }
    distances[from] = 0;
    queue.addLast(from);
    updates[from] = 1;

    while (!queue.isEmpty()){
      int vertice = queue.removeFirst();
      for (WeightedDigraph.Edge edge : graph.getOutgoingEdges(vertice)){
        int distanceToOrigin = distances[edge.from()] + edge.weight();
        if (distances[edge.to()] > distanceToOrigin){
          distances[edge.to()] = distanceToOrigin;
          parent[edge.to()] = edge.from();
          if (!queue.contains(edge.to())){
            queue.addLast(edge.to());
            updates[edge.to()]++;
            if (updates[edge.to()] >= graph.getNVertices()){
              ArrayList<Integer> values = new ArrayList<>();
              int length = edge.weight();
              int i = edge.from();
              values.addLast(edge.to());
              while( i != edge.to()){
                values.addLast(i);
                length += distances[i] - distances[parent[i]];
                i = parent[i];
              }
              values.addLast(edge.to());
              length += distances[i] - distances[parent[i]];
              return new SSSPResult.NegativeCycle(values,length);
            }
          }
        }
      }
    }
    return new SSSPResult.ShortestPathTree(from,distances,parent);
  }
}