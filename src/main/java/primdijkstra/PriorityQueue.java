package primdijkstra;

/**
 * Die PriorityQueue wird als MinHeap organisiert, wobei niedrige Priorität höhere Bedeutung hat.
 * Damit stehen weiter vorne im Array Elemente niedrigerer, also "besserer" Priorität
 */
public class PriorityQueue {
    
    // Die eigentliche Priority Queue
    private QueueEntry[] prioQu;
    
    // pos[e] gibt den index von e im Array prioQu an bzw 0, wenn das Element nicht in der PQ ist
    private int[] pos;
    
    // Speichert die aktuelle Anzahl der Elemente in der PQ
    private int nrElem;
    
    /**
     * @param n Die maximale Größe der PQ
     */
    public PriorityQueue(int n) {
        prioQu = new QueueEntry[n + 1];
        // um NullPpointerExceptions zu verhindern,
        // muss die PQ mit "leeren" QueueEntry-Containern initialisiert werden
        for (int i = 0; i < prioQu.length; i++) {
            prioQu[i] = new QueueEntry(0, 0);
        }
        // Arrays vom primitiven Datentyp int sind automatisch mit 0 initialisiert
        pos = new int[n + 1];
        nrElem = 0;
    }
    
    /**
     * Fügt in bestimmten Fällen das gegebene Element mit der gegebenen Priorität in die PQ ein:
     * Fall 1: Element ist noch nicht in der PQ enthalten:
     * Fügt das Element zunächst hinten an, und führt auf dessen Index i upHeap(i) aus,
     * sodass die Heapbdingung wiederhergestellt ist.
     * Ergebnis: Element wird mit der angegebenen Priorität in die PQ eingereiht.
     * return: true
     * Fall 2:
     * Element ist in PQ vorhanden, neue Priorität ist besser = kleiner
     * Ändert die Priorität des Elements.
     * Die neue Priorität ist kleiner und verletzt damit eventuell die Heapordnung im Teilbaum zur Wurzel hin
     * -> Heilung mit upHeap()
     * Ergebnis: Die Priorität des Elements wird aktualisiert.
     * return: true
     * Fall 3:
     * Element ist in PQ vorhanden, neue Priorität ist schlechter
     * Ergebnis: PQ bleibt wie sie ist.
     * return: false
     */
    public boolean update(int element, int newPriority) {
        // Fall 1: Element noch nicht in PQ: hinten einfügen, Heapordnung mit upHeap sicherstellen
        if (pos[element] == 0) {
            // PQ-Größe wächst
            nrElem++;
            // nrElem ist letzter Index im Heap
            // Element wird hinten (=am Index nrElem) eingefügt
            prioQu[nrElem].value = element;
            prioQu[nrElem].prio = newPriority;
            pos[element] = nrElem;
            // Heapbedingung unter Umständen ab dem neuen Element aufwärts verletzt, Heilung mit upHeap
            upHeap(nrElem);
            return true;
        }
        // Fall 2: Die neue Priorität ist niedriger = besser, Priorität wird aktualisiert
        else if (newPriority < prioQu[pos[element]].prio) {
            prioQu[pos[element]].prio = newPriority;
            // Durch Änderung der Priorität evtl. Verletzung der Heapbedingung, Heilung mit upHeap
            upHeap(pos[element]);
            return true;
        }
        // Fall 3: Das Element ist mit einer besseren Priorität in der PQ enthalten, PQ bleibt unverändert
        return false;
    }
    
    /**
     * @return Extrahiert das Element mit der niedrigsten = wichtigsten Priorität aus der PQ.
     * <p>
     * Die Wurzel wird zwischengespeichert,
     * das letzte Element wird auf die Wurzel vorgezogen,
     * die verletzte Heapbedingung wird wiederhergestellt.
     */
    public int remove() {
        // Wurzel zwischenspeichern
        int root = prioQu[1].value;
        // root logisch aus dem Heap löschen
        pos[root] = 0;
        QueueEntry ehemalsLetztes = prioQu[nrElem];
        // Wurzel mit letzem Queue-Eintrag überschreiben
        prioQu[1].value = ehemalsLetztes.value;
        prioQu[1].prio = ehemalsLetztes.prio;
        // Positions-Pointer auf das ehemals letzte Element umbiegen, ist jetzt Wurzel
        pos[ehemalsLetztes.value] = 1;
        // PQ schrumpft
        nrElem--;
        if (isEmpty()) {
            for (int i = 0; i < pos.length; i++) {
                pos[i] = 0;
            }
        }
        // Verletzung der Heapbedingung ab der Wurzel abwärts heilen
        downHeap(1);
        return root;
    }
    
    public boolean isEmpty() {
        return nrElem == 0;
    }
    
    /**
     * Es wird angenommen, dass der Baum unterhalb des Index-Parameters ein Heap ist, der Pfad vom Index aufwärts
     * jedoch möglicherweise die Heapordnung verletzt.
     * Das jeweilige Kind-Vater Knotenpaar wird so lange entlang des Pfades nach oben (spätestens bis zur Wurzel)
     * getauscht, bis die Heapordnung wiederhergestellt ist
     *
     * @param indexDerHeapverletzung Index, von dem Knoten, von dem angenommen wird,
     *                               dass er und sein Vater die Heapbedingung verletzen
     */
    public void upHeap(int indexDerHeapverletzung) {
        int child = indexDerHeapverletzung;
        int parent = child / 2;
        // bis zur Wurzel iterieren ODER
        // terminieren, wenn Heapbedingung zwischen dem Knoten und seinem Vater stimmt erfüllt ist
        while (parent > 0 && heapBedingungVerletzt(child, parent)) {
            // Heapbedingung verletzt, tausche Vater und Kind
            swap(child, parent);
            // Indizes um eine Ebene nach oben verschieben
            child = parent;
            parent = parent / 2;
        }
    }
    
    private boolean heapBedingungVerletzt(int childIndex, int parentIndex) {
        return prioQu[childIndex].prio < prioQu[parentIndex].prio;
    }
    
    public void downHeap(int index) {
        int parent = index;
        int child = 2 * parent;                                 // child ist linkes kind
        while (child <= nrElem) {                               // wenn kind existiert,
            if (existiertKleinererRechterNachbar(child)) {      // prüfe ob ein kleineres rechtes kind existiert
                child += 1;                                     // und wenn ja, ist child jetzt rechtes kind
            }
            if (heapBedingungVerletzt(child, parent)) {         // wenn Heapbedingung verletzt
                swap(child, parent);                            // Vater und Kind tauschen
                parent = child;
                child = 2 * parent;
            } else {
                break;
            }
        }
        // Wenn kein Kind mehr existiert oder die Heapordnung beim aktuellen Paar erfüllt ist,
        // ist die Heapbedingung wiederhergestellt
    }
    
    /**
     * Gibt einen Wahrheitswert zurück über die Aussage,
     * ob im Heap-Array hinter dem mitgegebenen Element ein weiteres enthalten ist
     */
    private boolean existiertKleinererRechterNachbar(int child) {
        return (child + 1 <= nrElem) && (prioQu[child + 1].prio < prioQu[child].prio);
    }
    
    /**
     * tauscht die Inhalte der PQ an den gegebenen Indizen und passt die pos-Pointer an
     */
    private void swap(int childIndex, int parentIndex) {
        // Inhalt von child temporär speichern
        int formerChildPrio = prioQu[childIndex].prio;
        int formerChildValue = prioQu[childIndex].value;
        
        // child = parent
        prioQu[childIndex].prio = prioQu[parentIndex].prio;
        prioQu[childIndex].value = prioQu[parentIndex].value;
        
        // parent = child
        prioQu[parentIndex].prio = formerChildPrio;
        prioQu[parentIndex].value = formerChildValue;
        
        // Pointer korrigieren
        pos[formerChildValue] = parentIndex;
        pos[prioQu[childIndex].value] = childIndex;
    }
    
    public QueueEntry[] getPrioQu() {
        return prioQu;
    }
    
    public int[] getPos() {
        return pos;
    }
    
    public int getNrElem() {
        return nrElem;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 1; i < prioQu.length; i++) {
            sb.append(prioQu[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}























