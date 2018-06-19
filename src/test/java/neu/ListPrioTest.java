package neu;

import org.junit.Test;

public class ListPrioTest {
    
    /*
     * # 1 -(1)- 2
     * # |     / |
     * #(4) (2) (1)
     * # | /     |
     * # 4 -(3)- 3
     */

//    [0, 1, 0, 4, 0]
//    [1, 0, 1, 2, 2]
//    [0, 1, 0, 3, 5]
//    [4, 2, 3, 0, 4]
//    [0, 2, 5, 4, 0]
    
    /**
     * 1 -> 2(1), 4(4)
     * 2 -> 1(1), 3(1), 4(2), 5(2)
     * 3 -> 2(1), 4(3), 5(5)
     * 4 -> 1(4), 2(2), 3(3), 5(4)
     * 5 -> 2(2), 3(5), 4(4)
     */
    public Node[] prepareListeMit5Ungerichtet() {
        Node[] adl = new Node[6];
        
        adl[1] = new Node(2, 1).withNext(new Node(4, 4));
        adl[2] = new Node(1, 1).withNext(new Node(3, 1)
                                             .withNext(new Node(4, 2).withNext(new Node(5, 2))));
        adl[3] = new Node(2, 1).withNext(new Node(4, 3));
        adl[4] = new Node(1, 4).withNext(new Node(2, 2).withNext(new Node(3, 3)));
        adl[5] = new Node(2, 2).withNext(new Node(3, 5).withNext(new Node(4, 4)));
        Tools.printAdjacencyList(adl, "VORHER UNGERICHTET");
        return adl;
    }
    
    public Node[] prepareListeMit4Ungerichtet() {
        Node[] adl = new Node[5];
        
        adl[1] = new Node(2, 1).withNext(new Node(4, 4));
        adl[2] = new Node(1, 1).withNext(new Node(3, 1).withNext(new Node(4, 2)));
        adl[3] = new Node(2, 1).withNext(new Node(4, 3));
        adl[4] = new Node(1, 4).withNext(new Node(2, 2).withNext(new Node(3, 3)));
        Tools.printAdjacencyList(adl, "VORHER UNGERICHTET");
        return adl;
    }
    
    /**
     * 1 -> 2(1), 4(4)
     * 2 -> 3(1), 4(2)
     * 3 -> 4(3)
     * 4 ->
     */
    public Node[] prepareListeMit4Gerichtet() {
        Node[] adl = new Node[5];
        
        adl[1] = new Node(2, 1).withNext(new Node(4, 4));
        adl[2] = new Node(3, 1).withNext(new Node(4, 2));
        adl[3] = new Node(4, 3);
        adl[4] = new Node(1, 4);
        
        return adl;
    }
    
    Node[] myAdl;
    
    @Test
    public void listPriorityFirst() {
        
        myAdl = prepareListeMit5Ungerichtet();
//        myAdl = prepareListeMit4Ungerichtet();
        
        // Tools.printAdjacencyList(myAdl, "vorher");
        ListPrio tested = new ListPrio(myAdl);
        tested.listPriorityFirst(ListPriorityFirstAlgo.PRIM);
        
        Tools.printArray(tested.getParent(), "parent");
        Tools.printArray(tested.getPriority(), "priority");
        Node[] adjacencylist = Tools.buildAdjacencylist(myAdl, tested.getN(), tested.getParent());
        Tools.printAdjacencyList(adjacencylist, "nachher");
    }
}