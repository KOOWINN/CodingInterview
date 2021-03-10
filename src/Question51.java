import java.util.Arrays;

/**
 * 面试题51：数组中的逆序对
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，
 * 求出这个数组中的逆序对的总数。例如，在数组{7，5，6，4}中，一共存在5个逆序对，分别是(7,6),
 * (7,5)、(7,4)、(6,4)和(5,4)。
 * 
 */
public class Question51 {
    // 先把数组分隔成子数组，统计出子数组内部的逆序对的数目，然后再统计出两个相邻子数组之间的逆序对的数目。
    // 先用两个指针分别指向两个子数组的末尾，并每次比较两个指针指向的数字。如果第一个子数组中的数字大于第二个子数组中的数字，
    // 则构成逆序对，并且逆序对的数目等于第二个子数组中剩余数字的个数。如果第一个子数组中的数字小于或等于第二个子数组中的数字，
    // 则不构成逆序对。每次比较的时候，我们都把较大的数字从后往前复制到一个辅助数组，确保辅助数组中的数字时递增排序的。
    // 把较大的数字复制到辅助数组之后，把对应的指针向前移动一位，接下来进行下一轮比较。这个排序过程是一个归并排序。
    //
    // 归并排序的时间复杂度是O(nlogn)，而按顺序扫描数组再逐个比较该数字和它后面的数字的大小的时间复杂度为O(n2)。
    // 但同时归并排序需要一个长度为n的辅助数组，相当于我们用O(n)的空间消耗换来了时间效率的提升。
    public static int inversePairs(int[] data) {
        if (data == null || data.length <= 0) {
            return 0;
        }
        int[] copy = Arrays.copyOf(data, data.length);
        return inversePairsCore(data, copy, 0, data.length - 1);
    }

    private static int inversePairsCore(int[] data, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = data[start];
            return 0;
        }
        int length = (end - start) / 2;
        int left = inversePairsCore(data, copy, start, start + length);
        int right = inversePairsCore(data, copy, start + length + 1, end);

        // i初始化为前半段最后一个数字的下标
        int i = start + length;
        // j初始化为后半段最后一个数字的下标
        int j = end;

        int indexCopy = end;
        int count = 0;
        data = copy.clone();
        while (i >= start && j >= start + length + 1) {
            if (data[i] > data[j]) {
                copy[indexCopy--] = data[i--];
                count += j - start - length;
            } else {
                copy[indexCopy--] = data[j--];
            }
        }
        for (; i >= start; --i) {
            copy[indexCopy--] = data[i];
        }
        for (; j >= start + length + 1; --j) {
            copy[indexCopy--] = data[j];
        }
        return left + right + count;
    }

    public static void main(String[] args) {
        int[] numbers = {7, 5, 6, 4};
        System.out.println("The count of inverse pairs: " + inversePairs(numbers));
    }
}