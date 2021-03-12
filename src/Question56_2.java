import java.util.Arrays;

/**
 * 面试题56_2：数组中唯一只出现一次的数字
 *
 * 在一个数组中除第一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 */
public class Question56_2 {

    // 如果一个数字出现三次，那么它的二进制表示的每一位（0或者1）也出现三次。如果把所有出现三次的数字
    // 的二进制表示的每一位分别加起来，那么每一位的和都能被3整除。
    // 我们把数组中所有数字的二进制表示的每一位都加起来。如果某一位的和能被3整除，那么那个只出现一次的
    // 数字二进制表示中对应的那一位是0；否则就是1。
    //
    // 这种解法的时间效率是O(n)。我们需要一个长度为32的辅助数组存储二进制表示的每一位的和。由于数组的长度是固定的，
    // 因此空间效率是O(1)。该解法比其他两种直观的解法效率都要高：
    // ① 我们很容易就能从排序的数组中找到只出现一次的数字，但排序需要O(nlogn)的时间
    // ② 我们也可以用也给哈希表来记录数组中每个数字出现的次数，但这个哈希表需要O(n)的空间。
    static int findNumberAppearingOnce(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            return -1;
        }
        int[] bitSum = new int[32];
        Arrays.fill(bitSum, 0);
        for (int number : numbers) {
            int bitMask = 1;
            for (int i = 31; i >= 0; --i) {
                int bit = number & bitMask;
                if (bit != 0) {
                    bitSum[i] += 1;
                }
                bitMask = bitMask >> 1;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = result << 1;
            result += bitSum[i] % 3;
        }
        return result;
    }
}