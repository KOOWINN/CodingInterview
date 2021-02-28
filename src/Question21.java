import java.util.Arrays;

/**
 * 面试题21：调整数组顺序使奇数位于偶数的前面
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 */
public class Question21 {

    // 因为所有的奇数应该位于偶数的前面，所以我们可以在扫描这个数组的时候，如果发现有偶数出现在奇数的前面，则交换它们的顺序。
    //
    // 为此，我们可以维护两个指针：第一个指针初始化时指向数组的第一个数字，它只是向后移动；第二个指针初始化时指向数组的最后
    // 一个数字，它只是向前移动。在两个指针相遇之前，第一个指针总是位于第二个指针的前面。如果第一个指针指向的数字是偶数，
    // 并且第二个指针指向的数字是奇数，则交换这两个数字。
    static void reOrderOddEvent(int[] pData) {
        if (pData == null || pData.length <= 0) {
            return;
        }
        int start = 0;
        int end = pData.length - 1;
        while (start < end) {
            //向后移动start下标，直到它指向偶数
            while (start < end && (pData[start] & 0x1) != 0) {
                start++;
            }
            //向前移动end下标，直到它指向奇数
            while (start < end && (pData[end] & 0x1)==0){
                end--;
            }
            int tmp = pData[start];
            pData[start] = pData[end];
            pData[end] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        reOrderOddEvent(numbers);
        System.out.println("new number array: " + Arrays.toString(numbers));
    }
}