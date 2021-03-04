import java.util.Arrays;

/**
 * 面试题38：字符串的排列
 *
 * 输入一个字符串，打印出该字符串中字符的所有排列。例如，输入字符串abc，则打印出由字符a、b、c所能
 * 排列出来的所有字符串abc、acb、bac、bca、cab和cba。
 * 
 */
public class Question38 {

    // 求整个字符串的排列，可以看成两步。第一步求所有可能出现在第一个位置的字符，即把第一个字符和后面所有的字符交换；
    // 第二步固定第一个字符，求后面所有字符的排列。这时候我们仍把后面的所有字符分成两部分：后面字符的第一个字符以及
    // 这个字符之后的所有字符。然后把第一个字符逐一和它后面的字符交换。
    static void permutation(char[] pStr) {
        if (pStr == null) {
            return;
        }
        permutation(pStr, 0);
    }

    static void permutation(char[] pStr, int begin) {
        if (begin == pStr.length) {
            System.out.println(Arrays.toString(pStr));
        } else {
            for (int i = begin; i < pStr.length; i++) {

                char temp = pStr[i];
                pStr[i] = pStr[begin];
                pStr[begin] = temp;

                // 每次递归都从begin向后扫描每一个字符。在交换begin和i指向的字符之后，
                // 我们再对begin后面的字符串递归地进行排列操作，直到begin指向字符串的末尾。
                permutation(pStr, begin+1);

                temp = pStr[i];
                pStr[i] = pStr[begin];
                pStr[begin] = temp;
            }

        }
    }

    public static void main(String[] args) {
        permutation("abcd".toCharArray());
    }

}