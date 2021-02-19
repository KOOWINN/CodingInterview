/**
 * 不修改数组找出重复的数字
 *
 * 在一个长度为n+1的数组里的所有数字都在1~n的范围内，所以数组中至少有一个数字是重复的。
 * 请找出数组中任意一个重复的数字，但不能修改输入的数组。
 * 例如，如果输出长度为8的数组{2，3，5，4，3，2，6，7}，那么对应的输出是重复的数字2或者3。
 *
 * 时间复杂度O(nlogn)，空间复杂度O(1)
 */
public class Question3_2 {
    //把从1~n的数字从中间的数字m分为两部分，前面一半为1~m，后面一半为m+1~n。如果1~m的数字的数目超过m，
    //那么这一半的区间里一定包含重复的数字；否则，另一半m+1~n的区间里一定包含重复的数字。我们可以继续把
    //包含重复数字的区间一分为二，直到找到一个重复的数字。
    //这个过程和二分查找算法很类似，只是多了一步统计区间里数字的数目。
    static int getDuplication(int[] numbers, int length) {
        if (numbers == null || numbers.length <= 0) {
            return -1;
        }
        int start = 1;
        int end = length - 1;
        while (start <= end) {
            int mid = ((end - start)>>1) + start;
            int count = countRange(numbers, length, start, mid);
            if (start == end) {
                if (count > 1)
                    return start;
                else
                    break;
            }
            if (count > (mid - start + 1)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    static int countRange(int[] numbers, int length, int start, int end) {
        if (numbers == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (numbers[i] >= start && numbers[i] <= end)
                ++count;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] numbers = {2, 3, 5, 4, 3, 2, 6, 7};
        int ret = getDuplication(numbers, 8);
        System.out.println("result: " + ret);
    }

}