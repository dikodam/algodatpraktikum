import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class VertexTest {
    
    Node tested;
    
    @Before
    public void setUp() throws Exception {
        tested = new Node(42);
    }
    
    @Test
    public void testSmth() {
        assertThat(tested.getWeight(), is(42));
    }
}