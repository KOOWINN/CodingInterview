/**
 * 面试题11：旋转数组的最小数字
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，
 * 输出旋转数组的最小元素。例如，数组{3，4，5，1，2}为{1，2，3，4，5}的一个旋转，该数组的最小值为1。
 *
 */
public class Question11 {

    static int min(int[] numbers){
        if (numbers == null || numbers.length <= 0) {
            return -1;
        }
        int index1 = 0;
        int index2 = numbers.length - 1;
        int midIndex = index1;
        while (numbers[index1] >= numbers[index2]) {
            if (index2 - index1 == 1) {
                midIndex = index2;
                break;
            }
            midIndex = (index1 + index2)/2;

            //如果下标为index1、index2和midIndex指向的三个数字相等，则只能顺序查找
            if (numbers[index1] == numbers[index2] && numbers[midIndex] == numbers[index1]) {
                return minInOrder(numbers, index1, index2);
            }
            if (numbers[midIndex] >= numbers[index1]) {
                index1 = midIndex;
            }else if (numbers[midIndex] <= numbers[index2]) {
                index2 = midIndex;
            }
        }
        return numbers[midIndex];
    }

    static int minInOrder(int[] numbers, int index1, int index2) {
        int result = numbers[index1];
        for (int i = index1 + 1; i <= index2; i++) {
            if (result > numbers[i]) {
                result = numbers[i];
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] numbers = {3, 4, 5, 1, 2};
        System.out.println("The minimum number is: " + min(numbers));
    }

}