/**
 * 面试题57_1：和为s的数字
 *
 * 输入一个递增排序的数组和一个数字，在数组中查找两个数，使得它们的和正好是s。如果又多对数字的和等于s，则输出任意一对即可。
 * 例如，输入数组{1，2，4，7，11，15}和数字15。由于4+11=15，因此输出4和11。
 *
 */
public class Question57_1 {

    // O(n2)的方法：先在数组中固定一个数字，再依次判断数组中其余的n-1个数字与它的和是不是等于s。
    // 时间复杂度为O(n)的方法：我们先在数组中选择两个数字，如果它们的和等于输入的s，那么我们就找到了要找的两个数字。
    // 如果和小于s，我们希望两个数字的和再大一点。由于数组已经排好序了，我们可以考虑选择较小的数字后面的数字。因为排
    // 在后面的数字要大一些，那么两个数字的和也要大一些，就有可能输入的数字是s了。同样，当两个数字的和大于输入的数字
    // 的时候，我们可以选择较大数字前面的数字，因为排在数组前面的数字要小一些。所以，我们需要定义两个指针，第一个指针
    // 开始的时候指向数组的第一个（最小的）数字，第二个指针开始的时候指向数组的最后一个（最大的）数字。
    static int[] findNumbersWithSum(int[] data, int sum) {
        if (data == null || data.length < 2) {
            return null;
        }
        int ahead = 0;
        int behind = data.length - 1;
        int[] results = new int[2];
        while (ahead <= behind) {
            int currentSum = data[ahead] + data[behind];
            if (currentSum == sum) {
                results[0] = data[ahead];
                results[1] = data[behind];
                break;
            } else if (currentSum > sum) {
                behind--;
            } else {
                ahead++;
            }
        }
        return results;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 4, 7, 11, 15};
        int sum = 15;
        int[] result = findNumbersWithSum(data, sum);
        if (result != null && result.length == 2) {
            System.out.println("Find the nums: " + result[0] + ", " + result[1]);
        }
    }
}