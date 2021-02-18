/**
 * 找出数组中重复的数字：
 *
 * 在一个长度为n的数组里的所有数字都在0~n-1的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个
 * 重复的数字。例如，如果输入长度为7的数组{2，3，1，0，2，5，3}，那么对应的
 * 输出是重复的数字2或者3。
 *
 * 时间复杂度O(n)，空间复杂度O(1)
 */
public class Question3_1 {
    static int duplicate(int[] numbers, int length) {
        if (numbers == null || numbers.length <= 0) {
            return -1;
        }
        for (int i = 0; i < length; i++) {
            if (numbers[i] < 0 || numbers[i] > length - 1) {
                return -1;
            }
        }

        //数组中的数字都在0~n-1范围内，如果这个数组中没有重复的数字，那么当数组排序后数字i将出现在下标为i的位置。
        //由于数组中有重复的数字，有些位置可能存在多个数字，同时有些位置可能没有数字
        for (int i = 0; i < length; i++) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    return numbers[i];
                }

                int tmp = numbers[i];
                numbers[i] = numbers[tmp];
                numbers[tmp] = tmp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = {2, 3, 1, 0, 2, 5, 3};
        int ret = duplicate(numbers, 7);
        System.out.println("result: " + ret);
    }

}