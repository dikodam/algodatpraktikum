import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PriorityQueue {
    
    private Map<Node, Integer> queue;
    
    public PriorityQueue(int size) {
        queue = new HashMap<>(size);
    }
    
    /**
     * Fügt das Element mit der Priorität in die PriorityQueue ein.
     * Befindet sich das Element in der PriorityQueue und besitzt
     * das Element höhere Priorität als die gegebene, dann wird die
     * Priorität erniedrigt
     *
     * @param element  Das Element, das in die PriorityQueue eingefügt
     *                 werden soll bzw. dessen Priorität aktualisiert
     *                 werden soll
     * @param priority Die (neue) Priorität
     * @return gibt true zurück, wenn das Element neu eingefügt wurde oder seine Priorität aktualisiert wurde und false
     * wenn das Element nicht eingefügt wurde oder seine Priorität nicht verändert wurde
     */
    public boolean update(Node element, int priority) {
        Integer altePrio = queue.get(element);
        boolean elementNochNichtDa = altePrio == null;
        boolean neuePrioNiedriger = altePrio > priority;
        if (elementNochNichtDa || neuePrioNiedriger) {
            queue.put(element, priority);
            return true;
        }
        return false;
    }
    
    /**
     * @return liefert das Element mit der geringsten Priorität
     * und entfernt dieses aus der PriorityQueue
     */
    public Node remove() {
        Map.Entry<Node, Integer> first = Collections.min(queue.entrySet(), Comparator.comparing(Map.Entry::getValue));
        queue.remove(first.getKey());
        return first.getKey();
    }
    
    /**
     * @return gibt an, ob die PriorityQueue leer ist (=keine Elemente enthält)
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
