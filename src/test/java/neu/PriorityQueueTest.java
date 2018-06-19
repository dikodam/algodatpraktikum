package neu;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {
    
    PriorityQueue pq;
    
    @Before
    public void setup() {
        pq = new PriorityQueue(10);
    }
    
    @Test
    public void update() {
        pq.update(2, 1);
        Tools.printPQ("update: 2|1", pq, new int[0], new int[0]);
        pq.update(2, -1);
        Tools.printPQ("update: 2|-1", pq, new int[0], new int[0]);
        pq.update(1, 2);
        Tools.printPQ("update: 1|2", pq, new int[0], new int[0]);
        pq.remove();
        Tools.printPQ("removed", pq, new int[0], new int[0]);
        pq.update(2, 5);
        Tools.printPQ("update: 2|5", pq, new int[0], new int[0]);
    }
    
    @Test
    public void remove() {
    }
    
    @Test
    public void isEmpty() {
    }
}