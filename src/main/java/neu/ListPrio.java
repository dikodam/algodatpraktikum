package neu;

public class ListPrio {
    
    public static final int infinite = Integer.MAX_VALUE - 1;
    public static final String PRIM = "p";
    public static final String DIJKSTRA = "d";
    
    private int n;
    
    private int[] priority;
    private int[] parent;
    private Node[] adl;
    private PriorityQueue prioQu;
    private String mode;
    public ListPrio(Node[] adl) {
        this.adl = adl;
        this.n = Tools.countDistinctNodes(adl);
        this.parent = new int[n + 1];
        this.priority = new int[n + 1];
        this.prioQu = new PriorityQueue(n);
    }
    
    public void listPriorityFirst(String mode) {
        this.mode = mode;
        for (int i = 1; i <= n; i++) {
            priority[i] = -infinite;
        }
        for (int i = 1; i <= n; i++) {
            if (priority[i] == -infinite) {
                visit(i);
            }
        }
    }
    
    private void visit(int k) {
        Node node;
        if (prioQu.update(k, infinite)) {
            parent[k] = 0;
        }
        while (!prioQu.isEmpty()) {
            k = prioQu.remove();
            priority[k] = -priority[k];
            if (priority[k] == infinite) {
                priority[k] = 0;
            }
            node = adl[k];
            while (node != null) {
                if (priority[node.getV()] < 0) {
                    if (prioQu.update(node.getV(), prio(k, node))) {
                        priority[node.getV()] = -prio(k, node);
                        parent[node.getV()] = k;
                    }
                }
                node = node.getNext();
            }
        }
    }
    
    private int prio(int k, Node node) {
        if (PRIM.equals(mode)) {
            return node.getW();
        } else {
            return priority[k] + node.getW();
        }
    }
    
    public int getN() {
        return n;
    }
    
    public int[] getPriority() {
        return priority;
    }
    
    public int[] getParent() {
        return parent;
    }
}
