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
    private int nrElem;
    
    /**
     * @param n Die maximale Größe der PQ
     */
    public PriorityQueue(int n) {
        prioQu = new QueueEntry[n + 1];
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
     * Element ist in PQ vorhanden, neue Priorität ist besser
     * <p>
     *
     * <p>
     * Ergebnis: Die PRiorität des Elements wird aktualisiert
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
    public boolean update(int element, int priority) {
        // TODO
        return true;
    }
    
    /**
     * <p>
     * Die Wurzel wird zwischengespeichert, das letzte Element wird auf die Wurzel vorgezogen, die damit verletzte Heapbedingung wird wiederhergestellt.
     * @return Extrahiert das Element mit der niedrigsten = wichtigsten Priorität aus der PQ.
     */
    public int remove() {
        int root = prioQu[1].elem;              // Wurzel zwischenspeichern
        pos[root] = 0;                          // root ist nicht mehr im Heap
        QueueEntry ehemalsLetztes = prioQu[nrElem];
        prioQu[1] = ehemalsLetztes;             // Wurzel mit letzem Queue-Eintrag überschreiben
        pos[ehemalsLetztes.elem] = 1;           // Pointer auf das ehemals letzte Element umbiegen, ist jetzt Wurzel
        nrElem--;                               // PQ hat ein Element weniger, nämlich ihre ehemalige Wurzel
                                                // TODO gilt damit jetzt wohl das letzte Element als nicht drin, obwohl != null?
        downHeap(1);                      // Verletzung der Heapbedingung ab der Wurzel abwärts heilen
        return root;
    }
    
    public boolean isEmpty() {
        return nrElem == 0;
    }
    
    public void upHeap(int index) {
        // TODO
    }
    
    public void downHeap(int index) {
        // TODO
    }
    
}