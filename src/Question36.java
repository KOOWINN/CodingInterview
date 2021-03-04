/**
 * 面试题36：二叉搜索树和双向链表
 *
 * 输入一颗二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的节点，
 * 只能调整树中节点指针的指向。
 *
 */
public class Question36 {

    // 在二叉搜索树中，左子节点的值总是小于父节点的值，右子节点的值总是大于父节点的值。因此，
    // 我们在将二叉搜索树转换成排序双向链表时，原先指向左子节点的指针调整为链表中指向前一个
    // 前一个节点的指针，原先指向右子节点的指针调整为链表中指向后一个节点的指针。
    //
    // 我们可以把树分为3部分：根节点、左子树和右子树，然后把左子树中最大的节点、根节点和右子树中最小的节点链接起来。
    // 至于如何把左子树和右子树内部的节点链接成链表，那和原来的问题的实质是一样的，因此可以递归解决。
    static BinaryTreeNode convert(BinaryTreeNode pRootOfTree) {
        BinaryTreeNode pLastNodeInList = null;
        convertNode(pRootOfTree, pLastNodeInList);

        // pLastNodeInList指向双向链表的尾节点，我们需要返回头节点
        BinaryTreeNode pHeadOfList = pLastNodeInList;
        while (pHeadOfList != null && pHeadOfList.m_pLeft != null) {
            pHeadOfList = pHeadOfList.m_pLeft;
        }
        return pHeadOfList;
    }

    // pLastNodeInList指向已将转换好的链表的最后一个节点（值最大的节点）
    private static void convertNode(BinaryTreeNode pNode, BinaryTreeNode pLastNodeInList) {
        if (pNode == null) {
            return;
        }
        BinaryTreeNode pCurrent = pNode;
        if (pCurrent.m_pLeft != null) {
            convertNode(pNode.m_pLeft, pLastNodeInList);
        }
        pCurrent.m_pLeft = pLastNodeInList;
        if (pLastNodeInList != null) {
            pLastNodeInList.m_pRight = pCurrent;
        }

        pLastNodeInList = pCurrent;

        if (pCurrent.m_pRight != null) {
            convertNode(pNode.m_pRight, pLastNodeInList);
        }
    }

}