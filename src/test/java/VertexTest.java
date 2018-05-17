import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class VertexTest {
    
    Vertex tested;
    
    @Before
    public void setUp() throws Exception {
        tested = new Vertex(42);
    }
    
    @Test
    public void testSmth() {
        assertThat(tested.getWeight(), is(42));
    }
}