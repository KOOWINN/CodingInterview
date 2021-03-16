/**
 * 面试题63：股票的最大利润
 *
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？例如，
 * 一只股票在某些时间节点的价格为{9，11，8，5，7，12，16，14}。如果我们能在价格为5的时候买入并在
 * 价格为16时卖出，则能收获最大的利润11。
 *
 */
public class Question63 {
    // 股票交易的利润来自股票买入和卖出价格的差价。当然，我们只能在买入某只股票之后才能卖出。如果把该股票
    // 的买入价和卖出价两个数字组成一个数对，那么利润就是这个数对的差值。因此，最大利润就是数组中所有数对
    // 的最大差值。
    // 我们可以先定义一个函数diff(i)为当卖出价为数组中第i个数字时可获得的最大利润。显然，在卖出价固定时，
    // 买入价越低获得的利润越大。也就是说，如果在扫描到数组中的第i个数字时，只要我们能够记住之前的i-1个
    // 数字中的最小值，就能算出在当前价位卖出时可能得到的最大利润。
    //
    // 这种方法只需要扫描数组一次，因此该算法的时间复杂度时O(n)。
    static int maxDiff(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return 0;
        }
        int min = numbers[0];
        int maxDiff = numbers[1] - min;
        for (int i = 2; i < numbers.length; i++) {
            if (numbers[i - 1] < min) {
                min = numbers[i - 1];
            }
            int currentDiff = numbers[i] - min;
            if (currentDiff > maxDiff) {
                maxDiff = currentDiff;
            }
        }
        return maxDiff;
    }
    
    public static void main(String[] args) {
        int[] numbers = {9,11,8,5,7,12,16,14};
        System.out.println("maxDiff: " + maxDiff(numbers));
    }
}