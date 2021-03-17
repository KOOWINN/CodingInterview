/**
 * 面试题64：求1+2+……+n
 *
 * 求1+2+……+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 */
public class Question64 {

    // 通常实现递归的时候我们都会利用条件判断语句来决定递归的出口，但由于题目的限制我们不能使用条件判断语句，
    // 那么我们是否能使用别的办法来确定递归出口呢？答案就是逻辑运算符的短路性质。
    //
    // 以逻辑运算符 && 为例，对于 A && B 这个表达式，如果 A 表达式返回 false，那么 A && B 已经确定为 false，
    // 此时不会去执行表达式 B。同理，对于逻辑运算符 ||， 对于 A || B 这个表达式，如果 A 表达式返回 true，那么
    // A || B 已经确定为 true ，此时不会去执行表达式 B。
    //
    // 利用这一特性，我们可以将判断是否为递归的出口看作 A && B 表达式中的 A 部分，递归的主体函数看作 B 部分。
    // 如果不是递归出口，则返回 true，并继续执行表达式 B 的部分，否则递归结束。
    static int sumNums(int n) {
        boolean flag = n>0 && (n+=sumNums(n-1))>0;
        return n;
    }
    
    public static void main(String[] args) {
        System.out.println("sum=" + sumNums(10));
    }
}