import neu.MatrixPriorityFirstAlgo;
import neu.Node;
import neu.Tools;
import org.junit.Test;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

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
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 4},
            {0, 1, 0, 1, 2},
            {0, 0, 1, 0, 3},
            {0, 4, 2, 3, 0}
    };

    @Test
    public void testDasDing() {
        Tools.printMatrix(myAdm, "vorher");
        MatrixPriorityFirstAlgo tested = new MatrixPriorityFirstAlgo(myAdm);
        tested.matrixPriorityFirst(MatrixPriorityFirstAlgo.DIJKSTRA);

        Tools.printArray(tested.getParent(), "parent");
        Tools.printArray(tested.getPriority(), "priority");
        Tools.printMatrix(tested.buildAdm(), "nachher");
        Node[] adjacencylist = Tools.buildAdjacencylist(myAdm, tested.getN(), tested.getParent());
        Tools.printAdjacencyList(adjacencylist, "nachher");
    }
}