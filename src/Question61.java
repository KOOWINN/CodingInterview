import java.util.Arrays;

/**
 * 面试题61：扑克牌中的顺子
 *
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这五张牌是不是连续的。2~10为数字本身，A为1，J为11，Q为12，K为13，
 * 而大、小王可以看成任意数字。
 *
 */
public class Question61 {
    // 我们需要把扑克牌的背景抽象成计算机语言。不难想象，我们可以把5张牌看成由5个数字组成的数组。大、小王是特殊的数字，
    // 我们不妨把它们定义为0，这样就能和其他扑克牌区分开来了。
    //
    // 接下来我们可以通过数组排序来判断5个数字是不是连续的。由于0可以当成任意数字，我们可以用0去补满数组中的空缺。
    // 如果排序之后的数组不是连续的，即相邻的两个数字相隔若干个数字，那么只要我们有足够的0可以补满这两个数字的空缺，
    // 这个数字实际上还是连续的。举个例子，数组排序之后为{0，1，3，4，5}，在1和3之间空缺了一个2，刚好我们有一个0，
    // 也就是我们可以把它当成2去填补这个空缺。
    //
    // 于是，我们需要做3件事情：首先把数组排序；其次统计数组中0的个数；最后统计排序之后的数组中相邻数字之间的空缺总数。
    // 如果空缺的总数小于或者等于0的个数，那么这个数组就是连续的；反之则不连续。
    //
    // 最后我们还需要注意一点：如果数组中的非0数字重复出现，则该数组不是连续的。换成扑克牌的描述方式就是：如果一副牌里还有对子，则不可能是顺子。
    static boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            return false;
        }

        // 对数组进行排序
        Arrays.sort(numbers);

        int numberOfZero = 0;
        int numberOfGap = 0;

        // 统计数组中0的个数
        for (int i = 0; i < numbers.length && numbers[i] == 0; i++) {
            numberOfZero++;
        }

        // 统计数组中的间隔数目
        int small = numberOfZero;
        int big = small+1;
        while (big < numbers.length) {
            // 两个数相等，有对子，不可能是顺子
            if (numbers[small] == numbers[big]) {
                return false;
            }
            numberOfGap += numbers[big] - numbers[small] - 1;
            small = big;
            ++big;
        }
        return numberOfGap <= numberOfZero;
    }

    public static void main(String[] args) {
        int[] numbers = {0, 1, 3, 4, 5};
        System.out.println("isContinuous: " + isContinuous(numbers));
    }
}