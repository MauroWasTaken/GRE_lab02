package ch.heig.gre.labo2.groupX;

public class Recorder {

    private static int _nVertexFromFIFO=0;
    private static int _nEdgeCompute=0;
    private static int _nRelaxation=0;
    private static int _nVertextMissing=0;
    private static long _startTimeStamp=-1;





    public static void start(){
        if(_startTimeStamp!=-1)return;
        _nVertexFromFIFO=0;
        _nEdgeCompute=0;
        _nRelaxation=0;
        _nVertextMissing=0;
        _startTimeStamp=System.nanoTime();
    }

    public static RecorderOutput end(){
        RecorderOutput output = new RecorderOutput(_nVertexFromFIFO,_nEdgeCompute,_nRelaxation,_nVertextMissing,System.nanoTime()-_startTimeStamp);
        _startTimeStamp=-1;
        return output;
    }

    public static void addVertexFromFIFO(int n){
        _nVertexFromFIFO+=n;
    }
    public static void addVertexFromFIFO(){
        addVertexFromFIFO(1);
    }
    public static void addEdgeCompute(int n){
        _nEdgeCompute+=n;
    }
    public static void addEdgeCompute(){
        addEdgeCompute(1);
    }
    public static void addRelaxation(int n){
        _nRelaxation+=n;
    }
    public static void addRelaxation(){
        addRelaxation(1);
    }
    public static void addVertextMissing(int n){
        _nVertextMissing+=n;
    }
    public static void addVertextMissing(){
        addVertextMissing(1);
    }


}
