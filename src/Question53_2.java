/**
 * 面试题53_2：0~n-1中缺失的数字
 *
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0~n-1之内。
 * 在范围0~n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * 
 */
public class Question53_2 {
    // 因为0~n-1这些数字在数组中是排序的，因此数组中开始的一些数字与它们的下标相同。也就是说，0在下标为0的位置，
    // 1在下标为1的位置，以此类推。如果不在数组中的那个数字记为m，那么所有比m小的数字的下标都与它们的值相同。
    // 由于m不在数组中，那么m+1处在下标为m的位置，m+2处在下标为m+1的位置，以此类推。我们发现m正好是数组中第一个
    // 数值和下标不相等的下标，因此这个问题就转换成在排序数组中找出第一个值和下标不相等的元素。
    //
    // 我们可以基于二分查找算法用如下过程查找：如果中间的元素的值和下标相等，那么下一轮查找只需查找右半边；如果中间元素
    // 的值和下标不相等，并且它前面一个元素和它的下标相等，这意味着这个中间的数字正好是第一个值和下标不相等的元素，它的
    // 下标就是在数组中不存在的数字；如果中间元素的值和下标不相等，并且它前面一个元素和它的下标不相等，这意味着下一轮查找
    // 我们只需要在左边查找即可。
    static int getMissingNumber(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (numbers[middle] != middle) {
                if (middle == 0 || numbers[middle - 1] == middle - 1) {
                    return middle;
                }
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        if (left == numbers.length) {
            return numbers.length;
        }

        // 无效的输入，比如数组不是按要求排序的，或者有数字不在0~n-1范围之内
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers1 = {0, 1, 2, 3, 4};
        int[] numbers2 = {0, 1, 3, 4, 5};
        System.out.println("The missing number is: " + getMissingNumber(numbers1));
        System.out.println("The missing number is: " + getMissingNumber(numbers2));
    }

}