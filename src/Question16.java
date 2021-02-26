/**
 * 面试题16：数值的整数次方
 *
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 */
public class Question16 {

    static double power(double base, int exponent){
        if (base == 0 && exponent < 0) {
            return 0;
        }
        int absExponent = exponent;
        if (exponent < 0) {
            absExponent = - exponent;
        }
        double result = powerWithUnsignedExponent(base, absExponent);
        if (exponent < 0) {
            result = 1.0 / result;
        }
        return result;
    }

    static double powerWithUnsignedExponent(double base, int exponent){
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        //用右移运算符代替除以2，因为位运算的效率比乘除法以及求余运算的效率高很多
        double result = powerWithUnsignedExponent(base, exponent >> 1);
        result *= result;
        //用位与运算代替求余运算符（%）来判断一个数是奇数还是偶数
        if ((exponent & 0x1) == 1) {
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(String.format("Power(%d, %d): %f", 2, 10, power(2, 10)));
    }

}