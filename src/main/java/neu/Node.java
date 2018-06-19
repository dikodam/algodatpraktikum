package neu;

import java.util.ArrayList;
import java.util.List;

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
    
    public List<Node> toList() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(this);
        while (next != null) {
            nodes.add(next);
            next = next.next;
        }
        return nodes;
    }
}