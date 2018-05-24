public class MatrixPriorityFirstAlgo {
    
    private static final int infinite = Integer.MAX_VALUE - 1;
    
    private int[][] adm;
    private int[] parent;
    private int[] priority;
    private int size;
    
    public MatrixPriorityFirstAlgo(int size) {
        adm = new int[size + 1][size + 1];
        parent = new int[size + 1];
        priority = new int[size + 1];
        this.size = size;
    }
    
    public void init() {
        for (int i = 1; i < size; i++) {
            priority[i] = -infinite;
            parent[i] = 0;
        }
        priority[0] = -(infinite + 1);
    }
}
