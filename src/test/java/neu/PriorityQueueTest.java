package neu;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {
    
    PriorityQueue tested;
    
    @Before
    public void setup() {
        tested = new PriorityQueue(10);
    }
    
    @Test
    public void update() {
        tested.update(2, 1);
        tested.update(3, -1);
        tested.update(1, 2);
//        tested.update(1, 1);
//        tested.update(2, 1);
//        tested.update(4, 4);
        assertPQ("", 1, "");
    }
    
    private void assertPQ(String expectedStringRepresentation, int nrElements, String expectedPosArray) {
        System.out.println("PQ: " + tested.toString());
        System.out.println();
    }
    
    @Test
    public void remove() {
    }
    
    @Test
    public void isEmpty() {
    }
}