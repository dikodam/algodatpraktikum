package neu;

public class ListPrio {
    
    public static final int infinite = Integer.MAX_VALUE - 1;
    public static final String PRIM = "p";
    public static final String DIJKSTRA = "d";
    
    private int[] priority;
    private int[] parent;
    private Node[] adl;
    private int n;
    
    public ListPrio(Node[] adl) {
        this.adl = adl;
        this.n = Tools.countDistinctNodes(adl);
        this.parent = new int[n + 1];
        this.priority = new int[n + 1];
    }
    
}
