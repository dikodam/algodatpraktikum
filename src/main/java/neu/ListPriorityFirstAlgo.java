package neu;

public class ListPriorityFirstAlgo {

    private QueueEntry[] prioQu;
    private int[] pos;

    private int[] priority;
    private int[] parent;
    private Node[] adl;
    private int n;
    public static final int infinite = Integer.MAX_VALUE - 1;
    public static final String PRIM = "p";
    public static final String DIJKSTRA = "d";
    private int nrElem;

    //dafuq sin die ganzen felder und wie muss man die implementieren
    // TODO Mode in Konstruktor auslagern, ist beim pqUpdate noch hardcoded
    public ListPriorityFirstAlgo(Node[] adl) {
        this.n = adl.length - 1;
        this.adl = adl;
        this.parent = new int[n + 1];
        this.priority = new int[n + 1];
        this.prioQu = new QueueEntry[adl.length];
        this.pos = new int[prioQu.length];
        this.nrElem = 0;

        for (int i = 1; i < adl.length; i++) {
            prioQu[i] = new QueueEntry();
            pqUpdate(i, prio(ListPriorityFirstAlgo.DIJKSTRA, i, adl[i]));
        }
    }

    //dafuq is this, kann das weg?
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

    private int prio(String mode, int k, Node no) {
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
        return nrElem == 0;
//        return prioQu.length == 0;
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

    private void upHeap(int r) {
        int i;
        int j;
        QueueEntry x;

        i = r;
        j = i / 2;
        x = prioQu[i];
//        so stehts im Skript, aber nonsens?!
//        while (j <= 1) {
        while (j >= 1) {
            if (x.prio <= prioQu[j].prio) {
                break;
            }
            //TODO swap oder nur zuweisung wie im skript?
            swap(i, j);

            i = j;
            j = i / 2;
        }
        prioQu[i].prio = x.prio;
//        prioQu[i] = x;
    }

    //oder downHeap(int[l...r]), aber was des?
    private void downHeap(int l) {
        int i, j, r;
        QueueEntry x;

        i = l;
        j = 2 * i;
        x = prioQu[i];
        r = prioQu.length - 1;

        while (j <= r) {
            if (j < r) {
                if (prioQu[j].prio > prioQu[j + 1].prio) {
                    j += 1;
                }
            }
            if (x.prio <= prioQu[j].prio) {
                break;
            }
            //TODO swap oder nur zuweisung wie im skript?
            swap(i, j);

            i = j;
            j = 2 * i;
        }
        prioQu[i].prio = x.prio;
//        prioQu[i] = x;
    }

    private void swap(int i, int j) {
        QueueEntry temp = prioQu[i];
        prioQu[i] = prioQu[j];
        prioQu[j] = temp;
        pos[prioQu[i].elem] = i;
        pos[prioQu[j].elem] = j;
    }

    public int[] getParent() {
        return parent;
    }

    public int[] getPriority() {
        return priority;
    }

    public int getN() {
        return n;
    }
}
