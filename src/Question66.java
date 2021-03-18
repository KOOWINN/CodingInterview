import java.util.Arrays;

/**
 * 面试题66：构建乘积数组
 *
 * 给定一个数组A[0,1,...,n-1]，请构建一个数组B[0,1,...,n-1]，其中B中的元素
 * B[i]=A[0]xA[1]x...xA[i-1]xA[i+1]x...xA[n-1]。不能使用除法。
 *
 */
public class Question66 {

    // 可以把B[i]=A[0]xA[1]x...xA[i-1]xA[i+1]x...xA[n-1]看成A[0]xA[1]x...xA[i-1]和
    // A[i+1]x...xA[n-2]xA[n-1]两部分的乘积。不妨定义C[i]=A[0]xA[1]x...xA[i-1]，
    // D[i]=A[i+1]x...xA[n-2]xA[n-1]。C[i]可以用自上而下的顺序计算出来，即C[i]=C[i-1]xA[i-1]。
    // 类似地，D[i]也可以用自下而上的顺序计算出来，即D[i]=D[i+1]xA[i+1]。
    static void multiply(int[] array1, int[] array2) {
        if (array1 == null || array2 == null || array1.length != array2.length || array1.length < 2) {
            return;
        }
        array2[0] = 1;
        for (int i = 1; i < array1.length; i++) {
            array2[i] = array1[i - 1] * array2[i - 1];
        }
        double temp = 1;
        for (int i = array1.length-2; i >= 0; i--) {
            temp *= array1[i + 1];
            array2[i]*=temp;
        }
    }
    
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = new int[array1.length];
        multiply(array1, array2);
        System.out.println("array1=" + Arrays.toString(array1));
        System.out.println("array2=" + Arrays.toString(array2));
    }
}