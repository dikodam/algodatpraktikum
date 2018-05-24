public class MatrixPriorityFirstAlgo {
    
    private static final int infinite = Integer.MAX_VALUE - 1;
    public static final String PRIM = "p";
    public static final String DIJKSTRA = "d";
    
    private int[][] adm;
    private int[] parent;
    private int[] priority;
    private int n;
    
    public MatrixPriorityFirstAlgo(int[][] adm) {
        this.adm = adm;
        n = adm[0].length - 1;
        parent = new int[n + 1];
        priority = new int[n + 1];
    }
    
    public void matrixPriorityFirst(String mode) {
        int min = 1;
        int k;
        while (min != 0) {
            k = min;
            priority[k] = -priority[k];
            min = 0;
            if (priority[k] == infinite) {
                priority[k] = 0;
            }
            for (int t = 1; t < n; t++) {
                if (priority[t] < 0) {
                    if (adm[k][t] > 0 && priority[t] < -prio(mode, k, t)) {
                        priority[t] = -prio(mode, k, t);
                        parent[t] = k;
                    }
                    if (priority[t] > priority[min]) {
                        min = t;
                    }
                }
            }
        }
    }
    
    private int prio(String mode, int k, int t) {
        if (PRIM.equals(mode)) {
            return adm[k][t];
        } else {
            //DIJKSTRA.equals(mode)
            return priority[k] + adm[k][t];
        }
    }
    
    public void init() {
        for (int i = 1; i < n; i++) {
            priority[i] = -infinite;
            parent[i] = 0;
        }
        priority[0] = -(infinite + 1);
    }
}
