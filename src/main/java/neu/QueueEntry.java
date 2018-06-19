package neu;

import java.util.Objects;

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
    
    @Override
    public int hashCode() {
        return Objects.hash(elem, prio);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueEntry that = (QueueEntry) o;
        return elem == that.elem &&
            prio == that.prio;
    }
    
    @Override
    public String toString() {
        return String.format("(%d|%d)", elem, prio);
    }
}
