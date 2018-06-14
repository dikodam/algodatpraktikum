package neu;

public class PriorityQueue {
    
    private QueueEntry[] prioQu;
    private int[] pos;
    
    public PriorityQueue(int n) {
        prioQu = new QueueEntry[n + 1];
        pos = new int[n + 1];
    }
    
    /**
     *  Fügt element in die PQ mit der gegebenen priority ein, falls noch nicht darin enthalten.
     *  Falls element schon in PQ enthalten ist, wird priority angepasst, falls neue priority niedriger ist
     *  in obigen Fällen returnt die Methode true, falls die priority des Elements nicht verändert wurde, returnt false
     */
    public boolean update(int element, int priority) {
        // TODO
        return true;
    }
    
    public int remove() {
        // TODO
        return -1;
    }
    
    public boolean isEmpty() {
        // TODO
        return true;
    }
    
    public void upHeap() {
    
    }
    
    public void downHeap() {
    
    }
    
}