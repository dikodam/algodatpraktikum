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
        for (int key = 1; key <= n; key++) {
            if (priority[key] == -infinite) {
                System.out.println("[VISIT]: " + key);
                visit(key);
            }
        }
    }
    
    private void visit(int elem) {
        if (prioQu.update(elem, infinite)) {
            Tools.printPQ(String.format("UPDATE: (%d|%d)", elem, infinite), prioQu, parent, priority);
            parent[elem] = 0;
        }
        do {
            elem = prioQu.remove();
            Tools.printPQ("REMOVE:", prioQu, parent, priority);
            priority[elem] = -priority[elem];   // -infinite wird positiv
            if (priority[elem] == infinite) {
                priority[elem] = 0;
            }
            Node node = adl[elem];
            while (node != null) {
                if (nodeNotVisitedYet(node.value)) { // positive priority heißt knoten ist schon in neuem Graph
                    if (prioQu.update(node.value, prio(elem, node))) {
                        Tools.printPQ(String.format("UPDATE: (%d|%d)", node.value, prio(elem, node)),
                                      prioQu,
                                      parent,
                                      priority);
                        priority[node.value] = -prio(elem, node);
                        parent[node.value] = elem;
                        Tools.printPQ("updated priority and parent for " + node.value,
                                      prioQu,
                                      parent,
                                      priority);
                    }
                }
                node = node.next;
            }
        } while (!prioQu.isEmpty());
    }
    
    private int prio(int k, Node node) {
        if (PRIM.equals(mode)) {
            return node.getWeight();
        } else {
            return priority[k] + node.getWeight();
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
