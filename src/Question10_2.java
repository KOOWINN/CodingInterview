/**
 * 面试题10_2：青蛙跳台阶问题
 *
 * 一只青蛙一次可以跳上一级台阶，也可以跳上2级台阶。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 */
public class Question10_2 {

    //如果只有1级台阶，那显然只有一种跳法。如果有2级台阶，那就有两种跳法：一种是分两次跳，每次跳1级；
    //另一种就是一次跳2级。我们把n级台阶时的跳法看成n的函数，记为f(n)。当n>2时，第一次跳的时候就有
    //两种选择：一是第一次只跳1级，此时跳法数目等于后面剩下的n-1级台阶的跳法数，即为f(n-1)；二是第
    //一次跳2级，此时跳法数目等于后面剩下的n-2级台阶的跳法数目，即为f(n-2)。因此，n级台阶的不同跳法
    //的总数f(n)=f(n-1)+f(n-2)。
    static int jumpScheme(int n){
        int[] result = {1, 2};
        if (n <= 0) {
            return -1;
        }
        if (n <= 2) {
            return result[n-1];
        }
        int fibNMinusOne = result[1];
        int fibNMinusTwo = result[0];
        int fibN = 0;
        for (int i = 3; i <= n; i++) {
            fibN = fibNMinusOne + fibNMinusTwo;
            fibNMinusTwo = fibNMinusOne;
            fibNMinusOne =fibN;
        }
        return fibN;
    }

    public static void main(String[] args) {
        System.out.println("ret=" + jumpScheme(5));
    }

}