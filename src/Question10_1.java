/**
 * 面试题10_1：斐波那契数列
 *
 * 写一个函数，输入n，求斐波那契数列（Fibonacci）数列的第n项。斐波那契数列的定义如下：
 * 当n=0时，f(n)=0;
 * 当n=1时，f(n)=1;
 * 当n>1时，f(n)=f(n-1)+f(n-2)。
 *
 */
public class Question10_1 {

    static int fibonacci(int n){
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    static int fibonacci_Iteratively(int n){
        int[] result = {0, 1};
        if (n < 2) {
            return result[n];
        }
        int fibNMinusOne = 1;
        int fibNMinusTwo = 0;
        int fibN = 0;
        for (int i = 2; i <= n; i++) {
            fibN = fibNMinusOne + fibNMinusTwo;
            fibNMinusTwo = fibNMinusOne;
            fibNMinusOne =fibN;
        }
        return fibN;
    }

    public static void main(String[] args) {
        System.out.println("ret=" + fibonacci_Iteratively(6));
    }

}