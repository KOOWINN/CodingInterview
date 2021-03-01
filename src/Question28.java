/**
 * 面试题28：对称的二叉树
 *
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。如果一颗二叉树和它的镜像一样，那么它是对称的
 *
 */
public class Question28 {

    // 前序遍历的顺序为先遍历父节点，再左子节点，最后遍历右子节点，我们可以针对前序遍历定义中对称的遍历算法，
    // 即先父节点，再遍历右子节点，最后遍历左子节点。我们可以通过比较二叉树的前序遍历序列和对称前序遍历序列
    // 来判断二叉树是不是对称的。如果两个序列是一样的，那么二叉树就是对称的。
    static boolean isSymmetrical(BinaryTreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    static boolean isSymmetrical(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
        if (pRoot1 == null && pRoot2 == null) {
            return true;
        }
        if (pRoot1 == null || pRoot2 == null) {
            return false;
        }
        if (pRoot1.m_nValue != pRoot2.m_nValue) {
            return false;
        }

        return isSymmetrical(pRoot1.m_pLeft, pRoot2.m_pRight)
                && isSymmetrical(pRoot1.m_pRight, pRoot2.m_pLeft);
    }
}