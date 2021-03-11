/**
 * 面试题53_1：数字在排序数组中出现的次数
 *
 * 统计一个数字在排序数组中出现的次数。例如，输入排序数组{1，2，3，3，3，3，4，5}和数字{3}，由于3在这个数组中出现了4次，因此输出4。
 * 
 */
public class Question53_1 {
    // 假设我们要统计数字k在排序数组中出现的次数，我们可以利用利用二分查找算法直接找到第一个k及最后一个k。
    //
    // 先看如何找到第一个k。二分查找算法总是先拿数组中间的数字和k作比较。如果中间的数字比k大，那么k只有可能出现在数组的前半段，
    // 下一轮我们只在数组的前半段查找就可以了。如果中间的数字比k小，那么k只可能出现在数组的后半段，下一轮我们只在数组的后半段
    // 查找就可以了。如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。如果中间数字的前一个数字不是k，那么此时中间的数字
    // 刚好就是第一个k；如果中间数字的前面一个数字也是k，那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
    //
    // 我们可以用同样的思路在排序数组中找到最后一个k。
    static int getNumberOfK(int[] data, int k) {
        int number = 0;
        if (data != null && data.length > 0) {
            int first = getFirstK(data, k,0, data.length - 1);
            int last = getLastK(data, k, 0, data.length - 1);
            if (first > -1 && last > -1) {
                number = last - first + 1;
            }
        }
        return number;
    }

    // 如果数组中不包含数字k，那么返回-1；如果数组中包含至少一个k，那么返回第一个k在数组中的下标
    private static int getFirstK(int[] data, int k, int start, int end) {
        if (start > end) {
            return -1;
        }
        int midIndex = (start + end) / 2;
        if (data[midIndex] == k) {
            if ((midIndex > 0 && data[midIndex - 1] != k) || (midIndex == 0)) {
                return midIndex;
            } else {
                end = midIndex-1;
            }
        } else if (data[midIndex] > k) {
            end = midIndex - 1;
        } else {
            start = midIndex + 1;
        }
        return getFirstK(data, k, start, end);
    }

    // 和函数getFirstK一样，如果数组中不包含数字k，那么getLastK返回-1；否则返回最后一个k在数组中的下标
    private static int getLastK(int[] data, int k, int start, int end) {
        if (start > end) {
            return -1;
        }
        int midIndex = (start + end) / 2;
        int midData = data[midIndex];
        if (midData == k) {
            if ((midIndex < data.length - 1 && data[midIndex + 1] != k) || midIndex == data.length - 1) {
                return midIndex;
            } else {
                start = midIndex+1;
            }
        } else if (midData > k) {
            end = midIndex - 1;
        } else {
            start = midIndex + 1;
        }
        return getLastK(data, k, start, end);
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 3, 3, 3, 4, 5};
        int k = 3;
        System.out.println(k + " appears " + getNumberOfK(data, k) + " times in the array");
    }

}