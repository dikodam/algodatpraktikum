package neu;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Tools {

    public static <T> Node[] buildAdjacencylist(T[] adl, int n, int[] parent) {
        Node[] adjacencyList = new Node[adl.length];

        for (int i = 1; i <= n; i++) {
            int tempValue = parent[i];
            if (tempValue != 0) {
                if (adjacencyList[tempValue] == null) {
                    adjacencyList[tempValue] = new Node(i);
                } else {
                    Node value = adjacencyList[tempValue];

                    while (value.getNext() != null) {
                        value = value.getNext();
                    }
                    value.setNext(new Node(i));
                }
            }
        }
        return adjacencyList;
    }

    public static void printAdjacencyList(Node[] adjacencylist, String addition) {
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

    public static void printArray(int[] array, String name) {
        System.out.print("Array " + name + ":[");
        for (int i = 1; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    public static void printMatrix(int[][] myAdm, String addition) {
        System.out.println("Matrix " + addition + ":");
        for (int i = 1; i < myAdm.length; i++) {
            for (int j = 1; j < myAdm.length; j++) {
                System.out.print(myAdm[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void wirklichKleinsterWert(int[][] myAdm) {
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
