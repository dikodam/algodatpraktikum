package neu;

/**
 * elem: Element value
 * <p>
 * prio: Priorität des Elements
 */
public class QueueEntry {
    
    public int elem;
    public int prio;
    
    public QueueEntry(int elem, int prio) {
        this.elem = elem;
        this.prio = prio;
    }
    
    public QueueEntry() {
    
    }
}
