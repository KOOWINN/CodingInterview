/**
 * 面试题53_3：数组中数值和下标相等的元素
 *
 * 假设一个单调递增的数组里的每个元素都是整数并且是唯一的。请编程实现一个函数，
 * 找出数组中任意一个数值等于其下标的元素。例如，在数组{-3，-1，1，3，5}中，
 * 数字3和它的下标相等。
 * 
 */
public class Question53_3 {
    // 由于数组是单调递增排序的，因此我们可以尝试用二分查找算法来进行优化。假设我们某一步抵达数组中的第i个数字。
    // 如果该数字等于i，那么就找到了一个数值和其下标相等的元素。
    //
    // 如果该数字大于i，假设数字的值为m，即数字的值大于它的下标。由于数组中的所有数字都唯一并且单调递增，
    // 那么对于任意大于0的k，位于下标i+k的数字的值一定大于等于m+k。另外，因为m>i，所以m+k>i+k。因此，
    // 位于下标i+k的数字的值一定大于它的下标。这意味着如果第i个数字的值大于i，那么它右边的数字都大于对应
    // 的下标，我们都可以忽略。下一轮查找我们只需要从它左边的数字中查找即可。
    // 数字的值m小于它的下标i的情形和上面类似。它左边的所有数字的值都小于对应的下标，我们也可以忽略。
    static int getNumberSameAsIndex(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (numbers[middle] == middle) {
                return numbers[middle];
            } else if (numbers[middle] > middle) {
                right = middle - 1;
            } else {
                left = middle+1;
            }
        }

        // 无效的输入，比如数组不是按要求排序的，或者有数字不在0~n-1范围之内
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = {-3, -1, 1, 3, 5};
        System.out.println("The number which is the same as index: " + getNumberSameAsIndex(numbers));
    }

}