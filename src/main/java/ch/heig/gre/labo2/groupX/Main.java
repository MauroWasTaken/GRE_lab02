package ch.heig.gre.labo2.groupX;

import ch.heig.gre.labo2.graph.SSSPResult;
import ch.heig.gre.labo2.graph.WeightedDigraph;
import ch.heig.gre.labo2.graph.WeightedDigraphReader;

import java.io.IOException;

public class Main {

    public static final String DATA_PATH = "data/";

    public static void main(String[] args) throws IOException {
        System.out.println(":]");

        WeightedDigraph graph = WeightedDigraphReader.fromFile("data/reseau2.txt");

        SPFA truc = new SPFA();
        SSSPResult result = truc.compute(graph,1);
        System.out.println(graph);
        System.out.println(result);
        System.out.println("END");
    }
}
