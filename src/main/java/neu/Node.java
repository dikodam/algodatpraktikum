package neu;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final int v;
    private final int w; //weigth
    private Node next;
    
    public Node(int v, int w, Node next) {
        this.v = v;
        this.w = w;
        this.next = next;
    }
    
    public Node(int v, int w) {
        this.v = v;
        this.w = w;
        this.next = null;
    }
    
    public Node(int v) {
        this.v = v;
        this.w = 1;
        this.next = null;
    }
    
    public int getV() {
        return v;
    }
    
    public int getW() {
        return w;
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
        return String.valueOf(v);
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