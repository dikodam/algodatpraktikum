package primdijkstra;

public class Node {
    final int value;
    final int weight;
    Node next;
    
    public Node(int value, int weight, Node next) {
        this.value = value;
        this.weight = weight;
        this.next = next;
    }
    
    public Node(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.next = null;
    }
    
    public Node(int value) {
        this.value = value;
        this.weight = 1;
        this.next = null;
    }
    
    /**
     * Buildermethode für Tests. Die mitgegebenen Nodes werden ineinander als verlinkte Liste verschachtelt
     * Das erste Element ist der return-Wert
     */
    public static Node neighborlist(Node... nodes) {
        Node first = nodes[0];
        Node current = first;
        for (int i = 1; i < nodes.length; i++) {
            current.setNext(nodes[i]);
            current = current.getNext();
        }
        return first;
    }
    
    public int getValue() {
        return value;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public Node getNext() {
        return next;
    }
    
    public void setNext(Node next) {
        this.next = next;
    }
    
    @Override
    public String toString() {
        return String.format("%d(%d)", value, weight);
    }
}