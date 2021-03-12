/**
 * 面试题56：数组中只出现一次的两个数字
 *
 * 一个整型数组里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * 例如，输入数组{2，4，3，6，3，2，5，5}，因为只有4和6这两个数字只出现了一次，其他数字都出现了两次，所以输出4和6。
 *
 */
public class Question56 {

    // 先考虑如果这个数组中只有一个数字只出现了一次，其他数字都出现了两次，怎么找出这个数字？我们可以想到异或运算的一个性质：
    // 任何一个数字异或它自己都等于0。也就是说，如果我们从头到尾依次异或数组中的每个数字，那么最终的结果刚好是那个只出现一次
    // 的数字，因为那些成对出现的数字全部在异或中抵消了。
    //
    // 回到原问题，如果我们把原数组分成两个子数组，使得每个子数组包含一个只出现一次的数字，而其他数字都成对出现两次。如果能够
    // 这样拆分成两个数组，那么我们就可以按照前面的办法分别找出两个只出现一次的数字了。
    //
    // 我们还是从头到尾依次异或数组中的每个数字，那么最终得到的结果就是两个只出现一次的数字的异或结果，因为其他数字都出现了两次，
    // 在异或中全部抵消了。由于这两个数字肯定不一样，那么异或的结果肯定不为0，也就是说，在这个结果数字的二进制表示中至少有一位
    // 为1。我们在结果数字中找到第一个为1位的位置，记为第n位。现在我们以第n位是不是1为标准把原数组中的数字分成两个子数组，第一
    // 个子数组中每个数字的第n位都是1，而第二个子数组中每个数字的第n位都是0。由于我们分组的标准是数字中的某一位是1还是0，那么
    // 出现了两次的数字肯定被分配到两个子数组中去，于是我们已经把原数组分成了两个子数组，每个子数组都包含一个只出现一次的数字，
    // 而其他数字都出现了两次。我们已经知道如何在数组中找出唯一一个只出现一次的数字，因此，到此为止所有的问题都已经解决了。
    static int[] findNumsAppearOnce(int[] data) {
        if (data == null || data.length < 2) {
            return null;
        }
        int resultExclusiveOR = 0;
        for (int datum : data) {
            resultExclusiveOR ^= datum;
        }
        int indexOf1 = findFirstBitIs1(resultExclusiveOR);
        int[] nums = new int[2];
        for (int datum : data) {
            if (isBit1(datum, indexOf1)) {
                nums[0] ^= datum;
            } else {
                nums[1] ^= datum;
            }
        }
        return nums;
    }

    // 用来在整数num的二进制表示中找到最右边是1的位
    private static int findFirstBitIs1(int num) {
        int indexBit = 0;
        while ((num & 1) == 0) {
            num = num >> 1;
            ++indexBit;
        }
        return indexBit;
    }

    // 用来判断在num的二进制表示中从右边数起的index位是否为1
    private static boolean isBit1(int num, int index) {
        num = num>>index;
        return (num&1)==1;
    }

}