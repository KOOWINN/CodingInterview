/**
 * 面试题57_2：和为s的连续正数序列
 *
 * 输入一个正数s，打印出所有和为s的连续正数序列（至少含有两个数）。例如，输入15，由于1+2+3+4+5=4+5+6=7+8=15，
 * 所以打印出3个连续序列1~5，4~6和7~8。
 *
 */
public class Question57_2 {

    // 有了解决57_1的经验，我们也考虑用两个数small和big分别表示序列的最小值和最大值。首先small初始化为1，big初始化为2。
    // 如果从small到big的序列的和大于s，则可以从序列中去掉较小的值，也就是增大small的值。
    // 如果从small到big的序列的和小于s，则可以增大big，让这个序列中包含更多的数字。
    // 因为这个序列至少要有两个数字，我们一直增加small到(1+s)/2为止。
    static void findContinuousSequence(int sum) {
        if (sum < 3) {
            return;
        }
        int small = 1;
        int big = 2;
        int middle = (1 + sum) / 2;
        int currentSum = small + big;
        while (small < middle) {
            if (currentSum == sum) {
                printContinuousSequence(small, big);
            }
            while (currentSum > sum && small < middle) {
                currentSum -= small;
                small++;
                if (currentSum == sum) {
                    printContinuousSequence(small, big);
                }
            }
            big++;
            currentSum += big;
        }
    }

    private static void printContinuousSequence(int small, int big) {
        for (int i = small; i <= big; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        findContinuousSequence(15);
        System.out.println();
        findContinuousSequence(9);
    }
}