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
        printMatrix(myAdm, "vorher");
        MatrixPriorityFirstAlgo tested = new MatrixPriorityFirstAlgo(myAdm);
        tested.matrixPriorityFirst(MatrixPriorityFirstAlgo.DIJKSTRA);

        printArray(tested.getParent(), "parent");
        printArray(tested.getPriority(), "priority");
        printMatrix(tested.buildAdm(), "nachher");
        printAdjacencyList(tested.buildAdjacencylist(), "nachher");
    }

    private void printAdjacencyList(Node[] adjacencylist, String addition) {
        System.out.println("Adjazenzliste " + addition + ": ");

        for (int i = 1; i < adjacencylist.length; i++) {
            if (adjacencylist[i] != null) {
                Node value = adjacencylist[i];
                System.out.print(i + " -> ");

                while (value != null) {
                    if (value.getNext() != null) {
                        System.out.print(value + ", ");
                    } else {
                        System.out.print(value);
                    }
                    value = value.getNext();
                }

                System.out.println();
            }
        }
    }

    private void printArray(int[] array, String name) {
        System.out.print("Array " + name + ":[");
        for (int i = 1; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    private void printMatrix(int[][] myAdm, String addition) {
        System.out.println("Matrix " + addition + ":");
        for (int i = 1; i < myAdm.length; i++) {
            for (int j = 1; j < myAdm.length; j++) {
                System.out.print(myAdm[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public void wirklichKleinsterWert() {
        MatrixPriorityFirstAlgo tested = new MatrixPriorityFirstAlgo(myAdm);
        int minimum = -(MatrixPriorityFirstAlgo.infinite + 1);
        System.out.println("inf: \n" + MatrixPriorityFirstAlgo.infinite);
        System.out.println("inf + 1: \n" + (MatrixPriorityFirstAlgo.infinite + 1));
        System.out.println("minimum: \n" + minimum);
        IntPredicate kleinerMinimum = i -> i < minimum;
        IntStream.rangeClosed(Integer.MIN_VALUE + 1, Integer.MAX_VALUE)
                .filter(kleinerMinimum)
                .forEach(System.out::println);
    }

}