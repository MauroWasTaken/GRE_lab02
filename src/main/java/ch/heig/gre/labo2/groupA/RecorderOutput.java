package ch.heig.gre.labo2.groupA;

public record RecorderOutput(int nVertexFromFIFO,int nEdgeCompute,int nRelaxation,int nVertextMissing,long computeTime) {
    public String toString(){
        return "number of vertex remove from FIFO : "+nVertexFromFIFO+"\nnumber of edge compute : "+nEdgeCompute+"\nnumber of successful relaxation : "+nRelaxation+"\nnumber of vertext Missing from FIFO: "+nVertextMissing+"\ncompute Time : "+computeTime+"ms\n";
    }
}
