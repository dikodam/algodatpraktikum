package old;

public class Edge {
    
    private final Node start;
    private final Node end;
    private final int weight;
    
    public Edge(Node start, Node end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    
    public Node getStart() {
        return start;
    }
    
    public Node getEnd() {
        return end;
    }
    
    public int getWeight() {
        return weight;
    }
}
