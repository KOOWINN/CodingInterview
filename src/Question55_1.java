/**
 * 面试题55_1：二叉树的深度
 *
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径为树的深度。
 * 
 */
public class Question55_1 {
    // 如果一棵树只有一个节点，那么它的深度为1。如果根节点只有左子树而没有右子树，那么树的深度应该是其左子树的深度加1；
    // 同样，如果根节点只有右子树而没有左子树，那么树的深度应该是其右子树的深度加1。
    static int treeDepth(BinaryTreeNode pRoot) {
        if (pRoot == null) {
            return 0;
        }
        int nLeft = treeDepth(pRoot.m_pLeft);
        int nRight = treeDepth(pRoot.m_pRight);
        return (nLeft > nRight) ? (nLeft + 1) : (nRight + 1);
    }

}