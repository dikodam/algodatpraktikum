package primdijkstra;

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
        this.n = adl.length - 1;
        this.parent = new int[n + 1];
        this.priority = new int[n + 1];
        this.prioQu = new PriorityQueue(n);
    }
    
    public void listPriorityFirst(String mode) {
        this.mode = mode;
        for (int i = 1; i <= n; i++) {
            priority[i] = -infinite;
        }
        for (int key = 1; key <= n; key++) {
            if (priority[key] == -infinite) {
                System.out.println("[VISIT]: " + key);
                visit(key);
            }
        }
    }
    
    private void visit(int elem) {
        if (prioQu.update(elem, infinite)) {
            parent[elem] = 0;
        }
        do {
            elem = prioQu.remove();
            priority[elem] = -priority[elem];   // -infinite wird positiv
            if (priority[elem] == infinite) {
                priority[elem] = 0;
            }
            Node node = adl[elem];
            while (node != null) {
                if (nodeNotVisitedYet(node.value)) {
                    if (prioQu.update(node.value, prio(elem, node))) {
                        priority[node.value] = -prio(elem, node);
                        parent[node.value] = elem;
                    }
                }
                node = node.next;
            }
        } while (!prioQu.isEmpty());
    }
    
    private int prio(int k, Node node) {
        if (PRIM.equals(mode)) {
            return node.weight;
        } else {
            return priority[k] + node.weight;
        }
    }
    
    private boolean nodeNotVisitedYet(int nodeValue) {
        return priority[nodeValue] < 0;
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
