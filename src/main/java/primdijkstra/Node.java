package primdijkstra;

public class Node {
    final int value;
    final int weight; //weigth
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
    
    public Node withNext(Node next) {
        this.next = next;
        return this;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    /*
    public List<Node> toList() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(this);
        Node tempNext = next;
        while (tempNext != null) {
            nodes.add(next);
            tempNext = tempNext.next;
        }
        return nodes;
    }
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
}