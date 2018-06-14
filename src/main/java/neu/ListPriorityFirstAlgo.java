package neu;

public class ListPriorityFirstAlgo {

    private QueueEntry[] prioQu;
    private int[] pos;
    private int[] a;

    private int[] priority;
    private int[] parent;
    private Node[] adl;
    private int n;
    public static final int infinite = Integer.MAX_VALUE - 1;
    public static final String PRIM = "p";
    public static final String DIJKSTRA = "d";
    private int nrElem;

    //dafuq sin die ganzen felder und wie muss man die implementieren
    public ListPriorityFirstAlgo(Node[] adl) {
        this.n = adl.length - 1;
        this.adl = adl;
        this.parent = new int[n + 1];
        this.priority = new int[n + 1];
        this.prioQu = new QueueEntry[getAdlLength()];
        this.pos = new int[getAdlLength()];
        this.a = new int[getAdlLength()];
        this.nrElem = 1;

        for (int i = 0; i < prioQu.length; i++) {
            prioQu[i] = new QueueEntry();
        }
    }

    //dafuq is this
    private int getAdlLength() {
        int counter = 0;
        for (int i = 1; i < adl.length; i++) {
            if (adl[i] != null) {
                Node value = adl[i];
                counter++;
                while (value != null) {
                    value = value.getNext();
                    counter++;
                }
            }
        }
        return counter;
    }

    public void listPriorityFirst(String mode) {
        for (int k = 1; k <= n; k++) {
            priority[k] = -infinite;
        }
        for (int k = 1; k <= n; k++) {
            if (priority[k] == -infinite) {
                visit(k, mode);
            }
        }
    }

    private void visit(int k, String mode) {
        Node no;
        if (pqUpdate(k, infinite)) {
            parent[k] = 0;
        }
        do {
            k = pqRemove();
            priority[k] = -priority[k];
            if (priority[k] == infinite) {
                priority[k] = 0;
            }
            no = adl[k];
            while (no != null) {
                if (priority[no.getV()] < 0) {
                    if (pqUpdate(no.getV(), prio(mode, k, no))) {
                        priority[no.getV()] = -prio(mode, k, no);
                        parent[no.getV()] = k;
                    }
                }
                no = no.getNext();
            }
        }
        while (!pqEmpty());
    }

    public int prio(String mode, int k, Node no) {
        if (PRIM.equals(mode)) {
            return no.getW();
        } else {
            //DIJKSTRA.equals(mode)
            return priority[k] + no.getW();
        }
    }

    private int pqRemove() {
        int ret = prioQu[1].elem;
        pos[prioQu[1].elem] = 0;
        prioQu[1] = prioQu[nrElem];
        pos[prioQu[nrElem].elem] = 1;
        nrElem -= 1;
        downHeap(1);
        return ret;
    }

    private boolean pqEmpty() {
        return prioQu.length == 0;
    }

    private boolean pqUpdate(int k, int prio) {
        if (pos[k] == 0) {
            nrElem += 1;
            prioQu[nrElem].elem = k;
            prioQu[nrElem].prio = prio;
            upHeap(nrElem);
            return true;
        } else if (prioQu[pos[k]].prio > prio) {
            prioQu[pos[k]].prio = prio;
            upHeap(pos[k]);
            return true;
        }
        return false;
    }

    //evtl double nutzen?
    public void upHeap(int r) {
        updateAAndPos();
        int i, j, x;

        i = r;
        j = i / 2;
        x = a[i];

        while (j <= 1) {
            if (x <= a[j]) {
                break;
            }
            a[i] = a[j];
            i = j;
            j = i / 2;
        }
        a[i] = x;
    }

    //dafuq is this
    private void updateAAndPos() {
        for (int i = 0; i < prioQu.length; i++) {
            a[i] = prioQu[i].prio;
            pos[prioQu[i].elem] = i;
        }

    }

    //oder downHeap(int[l...r]), aber was des?
    public void downHeap(int l) {
        int i, j, x, r;

        i = l;
        j = 2 * i;
        x = a[i];
        r = a.length - 1;

        while (j <= r) {
            if (j < r) {
                if (a[j] > a[j + 1]) {
                    j += 1;
                }
            }
            if (x <= a[j]) {
                break;
            }
            a[i] = a[j];
            i = j;
            j = 2 * i;
        }
        a[i] = x;
    }

    //Tools Klasse erstellen
    public Node[] buildAdjacencylist() {
        Node[] adjacencyList = new Node[adl.length];

        for (int i = 1; i <= n; i++) {
            int tempValue = parent[i];
            if (tempValue != 0) {
                if (adjacencyList[tempValue] == null) {
                    adjacencyList[tempValue] = new Node(i);
                } else {
                    Node value = adjacencyList[tempValue];

                    while (value.getNext() != null) {
                        value = value.getNext();
                    }
                    value.setNext(new Node(i));
                }
            }
        }
        return adjacencyList;
    }

    public int[] getParent() {
        return parent;
    }

    public int[] getPriority() {
        return priority;
    }
}
