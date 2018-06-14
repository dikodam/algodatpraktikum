import neu.ListPriorityFirstAlgo;
import neu.Node;
import org.junit.Test;

public class ListPriorityFirstAlgoTest {

    int[][] myAdm = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 4},
            {0, 1, 0, 1, 2},
            {0, 0, 1, 0, 3},
            {0, 4, 2, 3, 0}
    };

    /*
     * 1 -> 2, 4
     * 2 -> 1, 3, 4
     * 3 -> 2, 4
     * 4 -> 1, 2, 3
     * */

    Node[] myAdl = prepareAdl();

    //keine ahnung, ob des so passt?
    public Node[] prepareAdl() {
        Node[] adl = new Node[5];

        Node no4 = new Node(4);
        Node no24 = new Node(2, 4, no4);

        Node no34 = new Node(3, 1, no4);
        Node no13 = new Node(1, 2, no34);

        Node no3 = new Node(3);
        Node no23 = new Node(2, 2, no3);
        Node no12 = new Node(1, 4, no23);

        adl[1] = no24;
        adl[2] = no13;
        adl[3] = no24;
        adl[4] = no12;

        return adl;
    }


    @Test
    public void testDasDing() {
        printAdjacencyList(myAdl, "vorher");
        ListPriorityFirstAlgo tested = new ListPriorityFirstAlgo(myAdl);
        tested.listPriorityFirst(ListPriorityFirstAlgo.DIJKSTRA);

        printArray(tested.getParent(), "parent");
        printArray(tested.getPriority(), "priority");
        printAdjacencyList(tested.buildAdjacencylist(), "nachher");
    }

    // tools klasse
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
}