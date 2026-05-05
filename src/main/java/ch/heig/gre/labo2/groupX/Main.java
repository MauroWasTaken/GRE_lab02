package ch.heig.gre.labo2.groupX;

import ch.heig.gre.labo2.graph.SSSPResult;
import ch.heig.gre.labo2.graph.WeightedDigraph;
import ch.heig.gre.labo2.graph.WeightedDigraphReader;

import java.io.IOException;

public class Main {

    public static final String DATA_PATH = "data/";

    public static void main(String[] args) throws IOException {

        WeightedDigraph graph = WeightedDigraphReader.fromFile("data/reseau1.txt");

        Recorder.start();
        SPFA truc = new SPFA();
        SSSPResult result = truc.compute(graph,2);
        RecorderOutput out = Recorder.end();
        System.out.println(out);

        System.out.println(graph);
        System.out.println(result);
        System.out.println("END");
        Recorder.start();
        SPFA_SLF trucs = new SPFA_SLF();
        SSSPResult results = trucs.compute(graph,2);
        RecorderOutput outs = Recorder.end();
        System.out.println(outs);

        System.out.println(graph);
        System.out.println(results);
        System.out.println("END");
    }
}
