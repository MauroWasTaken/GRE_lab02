package ch.heig.gre.labo2.groupA;

import ch.heig.gre.labo2.graph.SSSPResult;
import ch.heig.gre.labo2.graph.WeightedDigraph;
import ch.heig.gre.labo2.graph.WeightedDigraphReader;

import java.io.IOException;

public class Main {

    public static final String DATA_PATH = "data/";

    public static void main(String[] args) throws IOException {
        final int from = 0;
        WeightedDigraph graph = WeightedDigraphReader.fromFile("data/reseau4.txt");

        System.out.println("SPFA");
        SPFA spfa = new SPFA();
        Recorder.start();                                       //start recording statistics
        SSSPResult result = spfa.compute(graph, from);
        RecorderOutput out = Recorder.end();                    //save statistics

        // prints results
        System.out.println(out);
        System.out.println(graph);
        System.out.println(result);

        System.out.println("SPFA SLF");
        SPFA_SLF slf = new SPFA_SLF();
        Recorder.start();                                       //start recording statistics
        SSSPResult results = slf.compute(graph, from);
        out = Recorder.end();                                   //save statistics

        // prints results
        System.out.println(out);
        System.out.println(graph);
        System.out.println(results);
        System.out.println("END");
    }
}
