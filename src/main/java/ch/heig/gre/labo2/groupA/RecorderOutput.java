package ch.heig.gre.labo2.groupA;

public record RecorderOutput(int nVertexFromFIFO,int nEdgeCompute,int nRelaxation,int nVertextMissing,double computeTime) {
    public String toString(){
        return "number of vertices removed from FIFO : "+nVertexFromFIFO+"\nnumber of edges computed : "+nEdgeCompute+"\nnumber of successful relaxations : "+nRelaxation+"\nnumber of vertices missing from FIFO: "+nVertextMissing+"\ncomputing Time : "+computeTime+" ms\n";
    }
}
