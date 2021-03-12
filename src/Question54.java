/**
 * 面试题54：二叉搜索树的第k大节点
 *
 * 给定一棵二叉搜索树，请找出其中第k大的节点。例如，在下面这棵二叉搜索树里，按节点数值大小顺序，第三大节点的值是4。
 *           5
 *      3        7
 *    2   4    6  8
 * 
 */
public class Question54 {
    // 如果按照中序遍历的顺序遍历一课二叉搜索树，则遍历序列的数值是递增排序的。因此，
    // 只需要用中序遍历算法遍历一棵二叉搜索树，我们就很容器找出它的第k大节点。
    static BinaryTreeNode KthNode(BinaryTreeNode pRoot, int k) {
        if (pRoot == null || k == 0) {
            return null;
        }
        return KthNodeCore(pRoot, k);
    }

    private static BinaryTreeNode KthNodeCore(BinaryTreeNode pRoot, int k) {
        BinaryTreeNode target = null;
        if (pRoot.m_pLeft != null) {
            target = KthNodeCore(pRoot.m_pLeft, k);
        }
        if (target == null) {
            if (k == 1) {
                target = pRoot;
            }
            k--;
        }
        if (target == null && pRoot.m_pRight != null) {
            target = KthNodeCore(pRoot.m_pRight, k);
        }
        return target;
    }

}