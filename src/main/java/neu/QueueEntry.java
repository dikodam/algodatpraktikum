package neu;

import java.util.Objects;

/**
 * value: Element value
 * <p>
 * prio: Priorität des Elements
 */
public class QueueEntry {
    
    public int value;
    public int prio;
    
    public QueueEntry(int value, int prio) {
        this.value = value;
        this.prio = prio;
    }
    
    public QueueEntry() {
    
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value, prio);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueEntry that = (QueueEntry) o;
        return value == that.value &&
            prio == that.prio;
    }
    
    @Override
    public String toString() {
        return String.format("(%d|%d)", value, prio);
    }
}
