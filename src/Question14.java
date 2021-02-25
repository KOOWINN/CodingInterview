/**
 * 面试题14：剪绳子
 *
 * 给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，且n>1并且m>1），每段绳子的长度记为
 * k[0],k[1],...,k[m]。请问k[0]xk[1]x...k[m]可能的最大乘积是多少？例如，当绳子的长度是
 * 8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 */
public class Question14 {

    //动态规划类问题的四个特点：
    //① 目标时求最优解
    //② 整体问题的最优解是依赖于各个子问题的最优解
    //③ 把大问题分解成若干个小问题，这些小问题之间还有相互重叠的更小的子问题
    //④ 为了避免重复求解子问题，分析问题可以从上往下，但是求解是按照从下往上的顺序先计算小问题的最优解并存储下来，再以此为基础求取大问题的最优解
    static int maxProductAfterCutting(int length){
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }

        int[] products = new int[length + 1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;
        int max = 0;
        //第一个循环：自下而上依次递增求出每个长度的最优解，最后再求出长度为length时的最优解
        for (int i = 4; i <= length; i++) {
            max = 0;
            //尝试所有可能，求出f(j)*f(i-j)的最大值，并存储在products[i]里面
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[i - j];
                if (max < product) {
                    max = product;
                }
                products[i] = max;
            }
        }
        max = products[length];
        return max;
    }

    public static void main(String[] args) {
        System.out.println("max=" + maxProductAfterCutting(8));
    }

}