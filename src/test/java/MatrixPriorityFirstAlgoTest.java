import primdijkstra.MatrixPriorityFirstAlgo;
import primdijkstra.Node;
import primdijkstra.Tools;
import org.junit.Test;

public class MatrixPriorityFirstAlgoTest {
    
    /**
     * # 1 -(1)- 2
     * # |     / |
     * #(4) (2) (1)
     * # | /     |
     * # 4 -(3)- 3
     */
    /**
     * Lösung
     * # 1 -(1)- 2
     * #       / |
     * #    (2) (1)
     * #   /     |
     * # 4       3
     */
    int[][] myAdm = {
        {0, 0, 0, 0, 0, 0},
        {0, 0, 1, 0, 4, 0},
        {0, 1, 0, 1, 2, 2},
        {0, 0, 1, 0, 3, 5},
        {0, 4, 2, 3, 0, 4},
        {0, 0, 2, 5, 4, 0}
    };
    
    @Test
    public void testDasDing() {
        Tools.printMatrix(myAdm, "vorher");
        MatrixPriorityFirstAlgo tested = new MatrixPriorityFirstAlgo(myAdm);
        tested.matrixPriorityFirst(MatrixPriorityFirstAlgo.DIJKSTRA);
        
        Tools.printArray(tested.getParent(), "parent");
        Tools.printArray(tested.getPriority(), "priority");
        Tools.printMatrix(tested.buildAdm(), "nachher");
        Node[] adjacencylist = Tools.buildAdjacencylist(myAdm, tested.getN(), tested.getParent(), tested.getPriority());
        Tools.printAdjacencyList(adjacencylist, "nachher");
    }
}