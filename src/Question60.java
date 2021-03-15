/**
 * 面试题60：n个骰子的点数
 *
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *
 */
public class Question60 {
    private static int g_maxValue = 6;
    // 骰子一共有6面，每个面上都有一个点数，对应的是1~6之间的一个数字。所以n个骰子的点数和的最小值为n，最大值为6n。
    // 另外，根据排列组合的知识，我们还知道n个骰子的所有点数大排列数为6的n次方。要解决这个问题，我们需要先统计处每个
    // 点数出现的次数，然后把每个点数出现的次数除以6的n次方，就能求出每个点数出现的概率。
    // 重点就是统计每个点数出现的概率。要想求出n个骰子的点数和，可以先把n个骰子分为两堆：第一堆只有1个；另一堆有n-1个。
    // 单独的那一个有可能出现1~6的点数。我们需要计算1~6的每一种点数和剩下的n-1个骰子来计算点数和；接下来把剩下的n-1个
    // 骰子仍然分成两堆：第一堆只有一个；第二堆有n-2个。我们把上一轮那个单独骰子的点数和这一轮单独骰子的点数相加，再和
    // 剩下的n-2个骰子来计算点数和。分析到这，我们不难发现这是一种递归的思路，递归结束的条件就是最后只剩下一个骰子。
    //
    // 我们可以考虑用两个数组来存储骰子点数的每个总数出现的次数。在一轮循环中，第一个数组中的第n个数字表示骰子和为n出现的次数。
    // 在下一轮循环中，我们加上一个新的骰子，此时和为n的骰子出现的次数应该等于上一轮循环中骰子点数和为n-1、n-2、n-3、n-4、
    // n-5与n-6的次数的总和，所以我们把另一个数组的第n个数字设为前一个数组对应的第n-1、n-2、n-3、n-4、n-5与n-6个数字之和。
    static void printProbability(int number) {
        if (number < 1) {
            return;
        }
        int[][] pProbabilities = new int[2][g_maxValue*number+1];
        for (int i = 0; i < g_maxValue * number + 1; i++) {
            pProbabilities[0][i] = 0;
            pProbabilities[1][i] = 0;
        }

        int flag = 0;
        for (int i = 1; i <= g_maxValue; i++) {
            pProbabilities[flag][i] = 1;
        }
        for (int k = 2; k <= number; k++) {
            for (int i = 0; i < k; i++) {
                pProbabilities[1-flag][i]=0;
            }
            for (int i = k; i <= g_maxValue*k; i++) {
                pProbabilities[1-flag][i] = 0;
                for (int j = 1; j <= i & j<= g_maxValue; j++) {
                    pProbabilities[1 - flag][i] += pProbabilities[flag][i - j];
                }
            }
            flag = 1 - flag;
        }
        double total = Math.pow(g_maxValue, number);
        for (int i = number; i <= g_maxValue*number; i++) {
            double ratio = pProbabilities[flag][i] / total;
            System.out.println(String.format("%d: %e\n", i, ratio));
        }
    }
    // 在上述代码中我们定义了两个数组pProbabilities[0]和pProbabilities[1]来存储骰子的点数之和。
    // 在一轮循环中，一个数组的第n项等于另一个数组的第n-1、n-2、n-3、n-4、n-5及n-6项的和。在下一轮
    // 循环中，我们交换这两个数组（通过改变变量flag实现）再重复这一计算过程。

    public static void main(String[] args) {
        printProbability(2);
    }
}