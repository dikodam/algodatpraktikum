import org.junit.Test;

public class MatrixPriorityFirstAlgoTest {
    
    /**
     * # 1 -(1)- 2
     * # |     / |
     * #(4) (2) (1)
     * # | /     |
     * # 4 -(3)- 3
     */
    int[][] myAdm = {
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 4},
        {0, 1, 0, 1, 1},
        {0, 0, 1, 0, 3},
        {0, 4, 1, 3, 0}
    };
    
    @Test
    public void testDasDing() {
        print(myAdm);
        MatrixPriorityFirstAlgo tested = new MatrixPriorityFirstAlgo(myAdm);
        tested.matrixPriorityFirst(MatrixPriorityFirstAlgo.PRIM);
        print(myAdm);
        
    }
    
    private void print(int[][] myAdm) {
        System.out.println("Array:");
        for (int i = 1; i < myAdm.length; i++) {
            for (int j = 1; j < myAdm.length; j++) {
                System.out.print(myAdm[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}