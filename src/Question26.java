/**
 * 面试题26：树的子结构
 *
 * 输入两颗二叉树A和B，判断B是不是A的子结构。
 *
 */
public class Question26 {

    // 我们可以分为两步：第一步，在树A中找到和树B的根节点的值一样的节点R；
    // 第二步，判断树A中以R为根节点的子树是不是包含和树B一样的结构。
    //
    // 第一步实际上就是树的遍历，可以用递归去做。
    static boolean hasSubTree(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
        boolean result = false;
        if (pRoot1 != null && pRoot2 != null) {
            if (pRoot1.m_nValue == pRoot2.m_nValue) {
                result = DoesTree1HaveTree2(pRoot1, pRoot2);
            }
            if (!result) {
                hasSubTree(pRoot1.m_pLeft, pRoot2);
            }
            if (!result) {
                hasSubTree(pRoot2.m_pRight, pRoot2);
            }
        }
        return result;
    }

    // 第二步也可以用递归的思路：如果节点R的值和树B的根节点不相同，则以R为根节点的子树和树B肯定不具有相同的节点；
    // 如果它们的值相同，则递归地判断它们各自的左右节点的值是不是相同。递归的终止条件是我们达到了树A或树B的叶子节点。
    private static boolean DoesTree1HaveTree2(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
        if (pRoot2 == null) {
            return true;
        }
        if (pRoot1 == null) {
            return false;
        }
        if (pRoot1.m_nValue != pRoot2.m_nValue) {
            return false;
        }
        return DoesTree1HaveTree2(pRoot1.m_pLeft, pRoot2.m_pLeft) && DoesTree1HaveTree2(pRoot1.m_pRight, pRoot2.m_pRight);
    }
}