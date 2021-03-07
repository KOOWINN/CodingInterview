import java.util.TreeSet;

/**
 * 面试题42：连续子数组的最大和
 *
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为O(n)。
 * 
 */
public class Question42 {
    // 解法一：举例分析数组的规律
    // 试着从头到尾逐个累加示例数组中的每个数字。初始化和为0。以数组{1，-2，3，10，-4，7，2，-5}为例，
    // 第一步加上第一个数字1，此时和为1。第二步加上数字-2，和就变成了-1。第三步加上数字3。我们注意到由于
    // 此前累加的和时-1，小于0，那如果用-1加上3，得到的和为2，比3本身还小。也就是说，从第一个数字开始的
    // 子数组的和会小于从第三个数字开始的子数组的和。因此，我们不用考虑从第一个数字开始的子数组，之前的累加
    // 的和也被抛弃。我们从第三个数字开始累加，此时得到的和是3。第四步加10，得到的和是13。第五步加上-4，
    // 和为9。我们发现，由于-4是一个负数，因此累加-4之后得到的和比原来的和还要小。因此，我们要把之前得到的
    // 和13保存下来，因为它有可能是最大的子数组的和。第六步加上数字7，9加7的结果是16，此时的和比之前最大的
    // 和13还要大，把最大的子数组的和由13更新为16。第七步加上2，累加得到的和为18，同时更新最大子数组的和。
    // 第八步加上最后一个数字-5，由于得到的和为13，小于此前最大的和18，因此，最终最大的子数组的和为18，
    // 对应的子数组是{3，10，-4，7，2}。
    static int findGreatestSumOfSubArray(int[] pData) {
        if (pData == null || pData.length <= 0) {
            return 0;
        }
        int nCurrentSum = 0;
        int nGreatestSum = 0;
        for (int i = 0; i < pData.length; i++) {
            if (nCurrentSum <= 0) {
                nCurrentSum = pData[i];
            } else {
                nCurrentSum += pData[i];
            }
            if (nCurrentSum > nGreatestSum) {
                nGreatestSum = nCurrentSum;
            }
        }
        return nGreatestSum;
    }

    // 解法二：应用动态规划法
    // 如果用函数f(i)表示以第i个数字结尾的子数组的最大和，那么我们需要求出max[f(i)]，其中0<=i<=n。
    // 我们可以用如下递归公式求f(i):
    // 当i=0或f(i-1)<=0时，f(i)=pData[i]；当i不等于0且f(i-1)>0时，f(i)=f(i-1)+pData[i].
    // 这个公式的意义就是：当以第i-1个数字结尾的子数组中所有数字的和小于0时，如果把这个负数与第i个数累加，
    // 则得到的结果比第i个数字本身还要小，所以这种情况以第i个数字结尾的子数组就是第i个数字本身。
    // 如果以第i-1个数字结尾的子数组中所有数字的和大于0，则与第i个数字累加就得到以第i个数字结尾的子数组
    // 中所有数字的和。
    // 代码和上面一样，代码中的nCurrentSum对应了f(i)，而nGreatestSum就对应了max[f(i)].

    public static void main(String[] args) {
        int[] numbers = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println("The greatest sum is: " + findGreatestSumOfSubArray(numbers));
    }

}