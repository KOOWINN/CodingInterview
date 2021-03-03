/**
 * 面试题33：二叉搜索树的后序遍历
 *
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。
 * 假设输入的数组的任意两个数字都互不相同。
 *
 */
public class Question33 {

    // 在二叉搜索树的后序遍历得到的序列中，最后一个数字是树的根节点的值。数组中前面的数字可以分为两部分：
    // 第一部分是左子树节点的值，它们都比根节点的值小；第二部分是右子树节点的值，它们都比根节点的值大。
    static boolean verifySequenceOfBST(int[] sequence) {

        if (sequence == null || sequence.length <= 0) {
            return false;
        }

        return verifySequenceOfBSTCore(sequence, 0, sequence.length - 1);

    }

    private static boolean verifySequenceOfBSTCore(int[] sequence, int start, int end) {

        // 二叉搜索树的根节点的值
        int root = sequence[end-1];

        // 在二叉搜索树中左子树节点的值都小于根节点的值
        int i = start;
        for (; i < end - 1; i++) {
            if (sequence[i] > root) {
                break;
            }
        }

        // 在二叉搜索树中右子树节点的值都大于根节点的值
        int j = i;
        for (; j < end-1; j++) {
            if (sequence[j] < root) {
                return false;
            }
        }

        // 判断左子树是不是二叉搜索树
        boolean left = true;
        if (i > 0) {
            left = verifySequenceOfBSTCore(sequence, start, i);
        }

        // 判断右子树是不是二叉搜索树
        boolean right = true;
        if (i < sequence.length - 1) {
            right = verifySequenceOfBSTCore(sequence, i, sequence.length - 1 - i);
        }

        return left && right;
    }

}