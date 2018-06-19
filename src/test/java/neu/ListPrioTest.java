package neu;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListPrioTest {
    
    
    Node[] myAdl = prepareAdl();
    
    public Node[] prepareAdl() {
        Node[] adl = new Node[5];
        
        Node no14 = new Node(4, 4);
        Node no124 = new Node(2, 1, no14);
        
        Node no24 = new Node(4, 2);
        Node no234 = new Node(3, 1, no24);
        Node no213 = new Node(1, 1, no234);
        
        Node no34 = new Node(4, 3);
        Node no324 = new Node(2, 1, no34);
        
        Node no43 = new Node(3, 3);
        Node no423 = new Node(2, 2, no43);
        Node no412 = new Node(1, 4, no423);
        
        adl[1] = no124;
        adl[2] = no213;
        adl[3] = no324;
        adl[4] = no412;
        
        return adl;
    }
    
    @Test
    public void listPriorityFirst() {
        Tools.printAdjacencyList(myAdl, "vorher");
        ListPrio tested = new ListPrio(myAdl);
        tested.listPriorityFirst(ListPriorityFirstAlgo.DIJKSTRA);
    
        Tools.printArray(tested.getParent(), "parent");
        Tools.printArray(tested.getPriority(), "priority");
        Node[] adjacencylist = Tools.buildAdjacencylist(myAdl, tested.getN(), tested.getParent());
        Tools.printAdjacencyList(adjacencylist, "nachher");
    }
}