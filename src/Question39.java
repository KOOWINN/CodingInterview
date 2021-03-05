/**
 * 面试题39：数组中出现次数超过一半的数字
 *
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如，输入一个长度为9的数组{1，2，3，2，2，2，5，4，2}。由于
 * 数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * 
 */
public class Question39 {
    // 解法一：根据数组特点找出时间复杂度为O(n)的算法
    // 数组中有一个数字出现的次数超过数组长度的一半，也就是说它出现的次数比其他所有数字出现的次数的和还要多。
    // 因此，我们可以考虑在遍历数组的时候保存两个值：一个是数组中的一个数字；另一个是次数。
    // 当我们遍历到下一个数字的时候，如果下一个数字和我们之前保存的数字相同，则次数加1，不同则次数减1，
    // 如果次数为0，那么我们需要保存下一个数字，并把次数设为1。
    // 由于我们要找的数字出现的次数比其他所有数字的次数之和还要多，那么要找的数字肯定是最后一次把次数设为1时
    // 对应的数字。
    static int moreThanHalfNum1(int[] numbers) {
        if (checkInvalidArray(numbers)) {
            return 0;
        }
        int result = numbers[0];
        int times = 1;
        for (int i = 1; i < numbers.length; i++) {
            if (times == 0) {
                result = numbers[i];
                times = 1;
            } else if (result == numbers[i]) {
                times++;
            } else {
                times--;
            }
        }
        if (!checkMoreThanHalf(numbers, result)) {
            return 0;
        }
        return result;
    }

    // 解法二：基于Partition函数的时间复杂度为O(n)的算法
    // 如果把上述数组排序，那么排序之后位于数组中间的数字一定就是那个出现次数超过数组长度一半的数字，
    // 也就是说，这个数字就是统计学上的中位数，即长度为n的数组中第n/2大的数字。
    //
    // 在随机快速排序算法中，我们现在数组中随机选择一个数字，然后调整数组中数字的顺序，使得比选中的数字小的数字
    // 都排在它的左边，比选中的数字大的数字都排在它的右边。
    // 如果这个选中的数字的下标刚好时n/2，那么这个数字就是数组的中位数；
    // 如果它的下标大于n/2，那么中位数应该位于它的左边，我们可以接着在它的左边部分的数组中查找；
    // 如果它的下标小于n/2，那么中位数应该位于它的右边，我们可以接着在它的右边部分的数组中查找；
    // 这是一个典型的递归过程。
    static int moreThanHalfNum2(int[] numbers) {
        if (checkInvalidArray(numbers)) {
            return 0;
        }
        int length = numbers.length;
        int middle = length >> 1;
        int start = 0;
        int end = length-1;
        int index = partition(numbers, start, end);
        while (index != middle) {
            if (index > middle) {
                end = index - 1;
            } else {
                start = index+1;
            }
            index = partition(numbers, start, end);
        }
        int result = numbers[middle];
        if (!checkMoreThanHalf(numbers, result)) {
            result = 0;
        }
        return result;
    }

    // 判断输入的数组是否为无效数组
    static boolean checkInvalidArray(int[] numbers) {
        boolean inputInvalid = false;
        if (numbers == null || numbers.length <= 0) {
            inputInvalid = true;
        }
        return inputInvalid;
    }

    // 判断输入的数组中出现频率最高的数字出现的次数是否是否超过了数组长度的一半
    static boolean checkMoreThanHalf(int[] numbers, int number) {
        int times = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == number) {
                times++;
            }
        }
        boolean isMoreThanHalf = true;
        if (times * 2 <= numbers.length) {
            isMoreThanHalf = false;
        }
        return isMoreThanHalf;
    }

    // 把数组分为两部分，比选择的数字小的数字移到数组的左边，比选择的数字大的数字移到数组的右边
    private static int partition(int[] data, int start, int end) {
        if (data == null || data.length <= 0 || start < 0 || end >= data.length) {
            return -1;
        }
        int index = start;
        swap(data, index, end);
        int small = start-1;
        for (index = start; index < end; index++) {
            if (data[index] < data[end]) {
                ++small;
                if (small != index) {
                    swap(data, index, small);
                }
            }
        }
        ++small;
        swap(data, small, end);
        return small;
    }

    // 交换数组中下标为index1和index2的元素
    private static void swap(int[] data, int index1, int index2) {
        int tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;
    }
    
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println("The number is: " + moreThanHalfNum1(numbers));
        System.out.println("The number is: " + moreThanHalfNum2(numbers));
    }

}