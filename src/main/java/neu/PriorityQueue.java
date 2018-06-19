package neu;

/**
 * Organisation als MinHeap, d.h. das Element mit der niedrigsten Priorität steht an der Wurzel
 * niedrige Prio = wichtigste Prio
 */
public class PriorityQueue {
    
    // Die eigentliche Priority Queue
    
    private QueueEntry[] prioQu;
    // pos[e] gibt den index von Element e im Array prioQu, oder 0, wenn das Element nicht in der PQ ist
    
    private int[] pos;
    // Speichert die aktuelle Anzahl der Elemente in der PQ
    
    // und ist gleichzeitig der Pointer auf das letzte gültige Item in der PQ (falls != 0)
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
     * <p>
     * Fügt in bestimmten Fällen das gegebene Element mit der gegebenen Priorität in die PQ ein:
     * <p>
     * Fall 1: Element ist noch nicht in der PQ enthalten:
     * <p>
     * Fügt das Element zunächst hinten an, und führt auf dessen Index i upHeap(i) aus, sodass die Heapbdingung wiederhergestellt ist.
     * <p>
     * Ergebnis: Element wird mit der angegebenen Priorität in die PQ eingereiht.
     * <p>
     * return: true
     * <p>
     * Fall 2:
     * <p>
     * Element ist in PQ vorhanden, neue Priorität ist besser = kleiner
     * <p>
     * Ändert die Priorität des Elements. Die neue Priorität ist kleiner und verletzt damit eventuell die Heapbedingung zur Wurzel hin  -> Heilung mit upHeap()
     * <p>
     * Ergebnis: Die Priorität des Elements wird aktualisiert.
     * <p>
     * return: true
     * <p>
     * Fall 3:
     * <p>
     * Element ist in PQ vorhanden, neue Priorität ist schlechter
     * <p>
     * Ergebnis: PQ bleibt wie sie ist.
     * <p>
     * return: false
     */
    public boolean update(int element, int newPriority) {
        // Fall 1: Element noch nicht in PQ: hinten einfügen, Heapbedingung mit upHeap(letzterIndex) bis wiederhergestellt
        if (pos[element] == 0) {
            // PQ-Größe wächst
            nrElem++;
            // nrElem ist letzter Index im Heap
            // Element wird hinten (am Index nrElem) eingefügt
            prioQu[nrElem].value = element;
            prioQu[nrElem].prio = newPriority;
            pos[element] = nrElem;
            // Heapbedingung unter Umständen ab dem neuen Element aufwärts verletzt, Heilung mit upHeap(indexNeuesElement)
            upHeap(nrElem);
            return true;
        }
        // Fall 2: Die neue Priorität ist niedriger = besser, Priorität wird aktualisiert
        else if (newPriority < prioQu[pos[element]].prio) {
            prioQu[pos[element]].prio = newPriority;
            // Durch Änderung der Priorität evtl. Verletzung der Heapbedingung, Heilung mit upHeap(indexBestehendesElement)
            upHeap(pos[element]);
            return true;
        }
        // Fall 3: Das Element ist mit einer besseren Priorität in der PQ enthalten, PQ bleibt unverändert
        return false;
    }
    
    /**
     * <p>
     * Die Wurzel wird zwischengespeichert, das letzte Element wird auf die Wurzel vorgezogen, die damit verletzte Heapbedingung wird wiederhergestellt.
     *
     * @return Extrahiert das Element mit der niedrigsten = wichtigsten Priorität aus der PQ.
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
     * jedoch die Heapordnung verletzt.
     * <p> Das jeweilige Kind-Vater Knotenpaar wird getauscht,
     * entlang des Pfades nach oben so lange bis die Heapordnung wieder hergestellt ist (also spätestens an der Wurzel)
     *
     * @param indexDerHeapverletzung Index, von dem angenommen wird, dass er und sein Vater die Heapbedingung verletzen
     */
    public void upHeap(int indexDerHeapverletzung) {
        int child = indexDerHeapverletzung;
        int parent = child / 2;           // ganzzahlige Division mit Abrunden
        // bis zur Wurzel iterieren ODER
        // terminieren, wenn Heapbedingung zwischen dem Knoten und seinem Vater stimmt erfüllt ist
        while (parent > 0 && heapBedingungVerletzt(child, parent)) {
            // Heapbedingung verletzt, tausche Vater und Kind
            swap(child, parent);
            // Indizes um eine Ebene nach oben verschieben
            child = parent;
            parent = parent / 2;           // ganzzahlige Division mit Abrunden
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























