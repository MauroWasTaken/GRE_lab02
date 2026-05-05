package ch.heig.gre.labo2.groupA;

import ch.heig.gre.labo2.graph.SSSPResult;
import ch.heig.gre.labo2.graph.WeightedDigraph;
import ch.heig.gre.labo2.graph.WeightedDigraphReader;

import java.io.IOException;

public class Main {

    public static final String DATA_PATH = "data/";

    public static void computeSPFA_test(String path) throws IOException {
        WeightedDigraph graph = WeightedDigraphReader.fromFile(path );
        System.out.println("File : "+path);
        // SPFA
        System.out.println("SPFA");
        SPFA spfa = new SPFA();
        Recorder.start();                                       //start recording statistics
        SSSPResult result = spfa.compute(graph, from);
        RecorderOutput out = Recorder.end();                    //save statistics

        // prints results
        System.out.println(out);
        System.out.println(result);

        System.out.println("SPFA SLF");
        SPFA_SLF slf = new SPFA_SLF();
        Recorder.start();                                       //start recording statistics
        result = slf.compute(graph, 0);
        out = Recorder.end();                                   //save statistics

        // prints results
        System.out.println(out);
        System.out.println(result);
        System.out.println("---------------");
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Start Tests");
        //computeSPFA_test("data/reseau1.txt");
        //computeSPFA_test("data/reseau2.txt");
        //computeSPFA_test("data/reseau3.txt");
        computeSPFA_test("data/reseau4.txt");
        //computeSPFA_test("data/geneve.txt");
        //computeSPFA_test("data/geneve_big.txt");
        //computeSPFA_test("data/geneve_big_neg.txt");

    }
}
