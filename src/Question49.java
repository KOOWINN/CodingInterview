/**
 * 面试题49：丑数
 * 我们把只包含因子2、3和5的数称作丑数（Ugly Number)。求按总小到大的顺序的第1500个丑数。
 * 例如，6、8都是丑数，但14不是，因为它包含因子7.习惯上我们把1当作第1个丑数。
 * 
 */
public class Question49 {
    // 解法一：逐个判断每个整数是不是丑数的解法，直观但不够高效
    static int getUglyNumberSolution1(int index) {
        if (index<=0)
            return 0;
        int number = 0;
        int uglyFound = 0;
        while (uglyFound < index) {
            ++number;
            if (isUgly(number))
                ++uglyFound;
        }
        return number;
    }

    // 解法二：创建数组保存已经找到的丑数，用空间换时间
    // 解法一之所以效率低，很大程度上时因为不管一个数是不是丑数，我们都要对它进行计算。
    // 根据丑数的定义，丑数应该是另一个丑数乘以2、3或者5的结果（1除外）。因此，我们
    // 可以创建一个数组，里面的数字是排好序的丑数，每个丑数都是前面的丑数乘以2、3或者5得到的。
    static int getUglyNumberSolution2(int index) {
        if (index <= 0) {
            return 0;
        }
        int[] pUglyNumbers = new int[index];
        pUglyNumbers[0] = 1;
        int nextUglyIndex = 1;

        int multiply2Index = 0;
        int multiply3Index = 0;
        int multiply5Index = 0;
        while (nextUglyIndex < index) {
            pUglyNumbers[nextUglyIndex] = Math.min(
                    Math.min(pUglyNumbers[multiply2Index] * 2, pUglyNumbers[multiply3Index] * 3),
                    pUglyNumbers[multiply5Index] * 5);
            // 因为已有的丑数是按顺序存放在数组中的，对于乘以2而言，肯定存在某一个丑数T2，
            // 排在它之前的每个丑数乘以2得到的结果都会小于已有的最大丑数，在它之后的每个
            // 丑数乘以2得到的结果都会太大，我们只需记下这个丑数的位置，同时每次生成新的
            // 丑数时去更新T2即可。对于乘以3和5而言，也存在同样的T3和T5;
            while (pUglyNumbers[multiply2Index] * 2 <= pUglyNumbers[nextUglyIndex]) {
                ++multiply2Index;
            }
            while (pUglyNumbers[multiply3Index] * 3 <= pUglyNumbers[nextUglyIndex]) {
                ++multiply3Index;
            }
            while (pUglyNumbers[multiply5Index] * 5 <= pUglyNumbers[nextUglyIndex]) {
                ++multiply5Index;
            }
            ++nextUglyIndex;
        }
        return pUglyNumbers[nextUglyIndex - 1];
    }

    // 判断一个数是否为丑数
    static boolean isUgly(int number) {
        while (number%2==0)
            number /= 2;
        while (number % 3 == 0)
            number /= 3;
        while (number % 5 == 0)
            number /= 5;
        return number == 1;
    }

    public static void main(String[] args) {
        int index = 1500;
        System.out.println("The " + index + "th ugly number is: " + getUglyNumberSolution1(index));
        System.out.println("The " + index + "th ugly number is: " + getUglyNumberSolution2(index));
    }
}