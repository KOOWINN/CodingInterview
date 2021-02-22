/**
 * 面试题8：二叉树的下一个节点
 *
 * 给定一颗二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点？树中的节点除了有两个分别指向左、右两个指针，
 * 还有一个指向父节点的指针。
 *
 *
 */
public class Question8 {

    //如果一个节点有右子树，那么它的下一个节点就是它的右子树的最左子节点；
    //如果一个节点没有右子树，当节点是它父节点的左子节点时，那么它的下一个节点就是它的父节点；
    //当一个节点既没有右子树，并且它还是它父节点的右子节点，这种情形比较复杂。我们可以沿着指向
    //父节点的指针一直向上遍历，直到找到一个是它父节点的左子节点的节点。如果这样的节点存在，
    //那么这个节点的父节点就是我们要找的下一个节点。
    static BinaryTreeNode getNext(BinaryTreeNode pNode){
        if (pNode == null) {
            return null;
        }
        BinaryTreeNode pNext = null;
        if (pNode.m_pRight != null) {
            BinaryTreeNode pRight = pNode.m_pRight;
            while (pRight.m_pLeft != null) {
                pRight = pRight.m_pLeft;
            }
            pNext = pRight;
        } else if (pNode.m_pParent != null) {
            BinaryTreeNode pCurrent = pNode;
            BinaryTreeNode pParent = pNode.m_pParent;
            while (pParent != null && pCurrent == pParent.m_pRight) {
                pCurrent = pParent;
                pParent = pParent.m_pParent;
            }
            pNext = pParent;
        }
        return pNext;
    }

    public static void main(String[] args) {
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        try {
            BinaryTreeNode treeNode = Question7.construct(preOrder, inOrder, 8);
            BinaryTreeNode ret = getNext(treeNode.m_pRight);
            System.out.println("result=" + ret.m_nValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}