package ch.heig.gre.labo2.groupA;

public class Recorder {
    private static int _nVertexFromFIFO = 0;
    private static int _nEdgeCompute = 0;
    private static int _nRelaxation = 0;
    private static int _nVertextMissing = 0;
    private static long _startTimeStamp = -1;


    /**
     * Start the recording stats
     */
    public static void start() {
        // if recording is all ready start -> return
        if (_startTimeStamp != -1) {
            return;
        }
        _nVertexFromFIFO = 0;
        _nEdgeCompute = 0;
        _nRelaxation = 0;
        _nVertextMissing = 0;
        _startTimeStamp = System.nanoTime();
    }

    /**
     * End the recording
     *
     * @return record stats
     */
    public static RecorderOutput end() {
        // if not recording start -> return;
        if (_startTimeStamp == -1) {
            return null;
        }
        RecorderOutput output = new RecorderOutput(_nVertexFromFIFO, _nEdgeCompute, _nRelaxation, _nVertextMissing, (System.nanoTime() - _startTimeStamp) / 1000000);
        _startTimeStamp = -1;
        return output;
    }

    /**
     * Add to the total number of vertext remove from the FIFO
     *
     * @param n
     */
    public static void addVertexFromFIFO(int n) {
        _nVertexFromFIFO += n;
    }

    /**
     * Add 1 to the total number of vertext remove from the FIFO
     */
    public static void addVertexFromFIFO() {
        addVertexFromFIFO(1);
    }

    /**
     * Add to the total number of edge compute
     *
     * @param n
     */
    public static void addEdgeCompute(int n) {
        _nEdgeCompute += n;
    }

    /**
     * Add 1 to the total number of edge compute
     */
    public static void addEdgeCompute() {
        addEdgeCompute(1);
    }

    /**
     * Add to the total number of successful relaxation
     *
     * @param n
     */
    public static void addRelaxation(int n) {
        _nRelaxation += n;
    }

    /**
     * Add 1 to the total number of successful relaxation
     */
    public static void addRelaxation() {
        addRelaxation(1);
    }

    /**
     * Add to the total number of vertext missing from the FIFO
     *
     * @param n
     */
    public static void addVertextMissing(int n) {
        _nVertextMissing += n;
    }

    /**
     * Add 1 to the total number of vertext missing from the FIFO
     */
    public static void addVertextMissing() {
        addVertextMissing(1);
    }


}
