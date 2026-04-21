package ch.heig.gre.labo2.groupX;

import ch.heig.gre.labo2.graph.WeightedDigraph;
import ch.heig.gre.labo2.graph.WeightedDigraphReader;

import java.io.IOException;

public class Main {

    public static final String DATA_PATH = "data/";

    public static void main(String[] args) throws IOException {
        System.out.println(":]");

        WeightedDigraph graph = WeightedDigraphReader.fromFile("data/reseau2.txt");

        System.out.println(graph);
        System.out.println("END");
    }
}
