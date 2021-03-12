/**
 * 面试题55_2：平衡二叉树
 *
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左、右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * 
 */
public class Question55_2 {

    private static class Result{
        int nDepth = 0;
        boolean isBalanced = false;

        public Result(int nDepth, boolean isBalanced) {
            this.nDepth = nDepth;
            this.isBalanced = isBalanced;
        }
    }
    // 解法一：需要重复遍历节点多次，简单但不足以打动面试官
    // 根据求二叉树深度的经验，我们可以在遍历树的每个节点的时候，调用函数TreeDepth得到它的左、右子树的深度。
    // 如果每个节点的左、右子树的深度相差都不超过1，那么按照定义它就是一棵平衡二叉树。
    static boolean isBalancedSolution1(BinaryTreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        int nLeft = Question55_1.treeDepth(pRoot.m_pLeft);
        int nRight = Question55_1.treeDepth(pRoot.m_pRight);
        int diff = nLeft - nRight;
        if (diff > 1 || diff < -1) {
            return false;
        }
        return isBalancedSolution1(pRoot.m_pLeft) && isBalancedSolution1(pRoot.m_pRight);
    }

    // 解法二：每个节点只遍历一次的解法，正是面试官喜欢的
    // 如果我们用后序遍历的方式遍历二叉树的每个节点，那么在遍历到一个节点之前我们就已经遍历了它的左右子树。
    // 只要在遍历每个节点的时候记录它的深度（某一节点的深度等于它到叶节点的路径的长度），我们就可以一边遍历
    // 一边判断每个节点是不是平衡的。
    static Result isBalancedSolution2(BinaryTreeNode pRoot) {
        if (pRoot == null) {
            return new Result(0, true);
        }
        Result left = isBalancedSolution2(pRoot.m_pLeft);
        Result right = isBalancedSolution2(pRoot.m_pRight);
        int nDepth = 1 + Math.max(left.nDepth, right.nDepth);
        if (left.isBalanced && right.isBalanced) {
            int diff = left.nDepth - right.nDepth;
            if (diff >= -1 && diff <= 1) {
                return new Result(nDepth, true);
            }
        }
        return new Result(nDepth, false);
    }

}