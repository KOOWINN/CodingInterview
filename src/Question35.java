/**
 * 面试题35：复杂链表的复制
 *
 * 请实现函数ComplexListNode clone(ComplexNode pHead)，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个m_pNext指针指向下一个节点，还有一个m_pSibling
 * 指向链表中的任意节点或null。
 *
 */
public class Question35 {
    static class ComplexListNode{
        int m_nValue;
        ComplexListNode m_pNext;
        ComplexListNode m_pSibling;
    }

    // 第一步：根据原始链表的每个节点N创建对应的新节点N'，再把N'链接到N的后面
    static void cloneNodes(ComplexListNode pHead) {
        ComplexListNode pNode = pHead;
        while (pNode != null) {
            ComplexListNode pCloned = new ComplexListNode();
            pCloned.m_nValue = pNode.m_nValue;
            pCloned.m_pNext = pNode.m_pNext;
            pCloned.m_pSibling = null;

            pNode.m_pNext = pCloned;

            pNode = pCloned.m_pNext;
        }
    }

    // 第二步：设置复制出来的节点的m_pSibling。假设原始链表上的N的m_pSibling指向节点S，
    // 那么其对应复制出来的N'是N的m_pNext指向的节点，同样S’也是S的m_pNext指向的节点。
    static void connectingSiblingNodes(ComplexListNode pHead) {
        ComplexListNode pNode = pHead;
        while (pNode != null) {
            ComplexListNode pCloned = pHead;
            if (pNode.m_pSibling != null) {
                pCloned.m_pSibling = pNode.m_pSibling.m_pNext;
            }
            pNode = pCloned.m_pNext;
        }
    }

    // 第三步：把这个长链表拆分成连个链表：把奇数位置的节点用m_pNext链接起来就是原始链表，
    // 把偶数位置的节点用m_pNext链接起来就是复制出来的链表。
    static ComplexListNode reconnectNodes(ComplexListNode pHead) {
        ComplexListNode pNode = pHead;
        ComplexListNode pClonedHead = null;
        ComplexListNode pClonedNode = null;
        if (pNode != null) {
            pClonedHead = pClonedNode = pNode.m_pNext;
            pNode.m_pNext = pClonedNode.m_pNext;
            pNode = pNode.m_pNext;
        }
        while (pNode != null) {
            pClonedNode.m_pNext = pNode.m_pNext;
            pClonedNode = pClonedNode.m_pNext;
            pNode.m_pNext = pClonedNode.m_pNext;
            pNode = pNode.m_pNext;
        }
        return pClonedHead;
    }

    // 把上面三步合起来，就是复制复杂链表的完整过程
    static ComplexListNode clone(ComplexListNode pHead) {
        cloneNodes(pHead);
        connectingSiblingNodes(pHead);
        return reconnectNodes(pHead);
    }
}