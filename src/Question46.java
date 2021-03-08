/**
 * 面试题46：把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0翻译成“a"，1翻译成”b"，……，
 * 11翻译成"l"，……，25翻译成“z"。一个数字可能有多个翻译。例如，12258有5种不同的翻译，
 * 分别是”bccfi"，“bwfi”，“bczi”，“mcfi”和"mzi"。请编程实现一个函数，用来计算一个
 * 数字有多少种不同的翻译方法。
 * 
 */
public class Question46 {
    // 我们定义函数f(i)表示从第i位开始的不同翻译的数目，那么f(i)=f(i+1)+g(i,i+1)xf(i+2)。
    // 当第i位和第i+1位两位数字拼接起来的数字在10~25的范围内时，函数g(i,i+1)的值为1；否则为0。
    // 尽管我们可以用递归的思路分析问题，但由于存在重复的子问题，递归并不是解决这个问题的最佳方法。
    // 递归从最大的问题开始而下地解决问题，我们也可以从最小地子问题开始自下而上解决问题，这样就可以
    // 消除重复的子问题。也就是说，我们从数字的末尾开始，然后从右到左翻译并计算不同翻译的数目。
    static int getTranslationCount(int number) {
        if (number < 0) {
            return 0;
        }
        String numberInString = Integer.toString(number);
        return getTranslationCount(numberInString);
    }

    static int getTranslationCount(String number) {
        char[] chars = number.toCharArray();
        int length = chars.length;
        int[] counts = new int[length];
        int count = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (i < length - 1) {
                count = counts[i + 1];
            } else {
                count = 1;
            }
            if (i < length - 1) {
                int digit1 = chars[i] - '0';
                int digit2 = chars[i + 1] - '0';
                int converted = digit1 * 10 + digit2;
                if (converted >= 10 && converted <= 25) {
                    if (i < length - 2) {
                        count += counts[i + 2];
                    } else {
                        count += 1;
                    }
                }
            }
            counts[i] = count;
        }
        count = counts[0];
        return count;
    }

    public static void main(String[] args) {
        int number = 12258;
        System.out.println("count=" + getTranslationCount(number));
    }
}