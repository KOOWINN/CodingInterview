import sun.security.jgss.spnego.SpNegoContext;

/**
 * 面试题18_2：删除链表中重复的节点
 *
 * 在一个排序的链表中，如何删除重复的节点？例如在下图(a)中，重复的节点被删除之后，链表如(b)所示。
 *
 * (a)    1 —> 2 -> 3 -> 3 -> 4 -> 4 -> 5
 * (b)    1 -> 2 -> 5
 *
 */
public class Question18_2 {

    static void deleteDuplication(MyLinkedList.ListNode pHead) {
        if (pHead == null) {
            return;
        }
        MyLinkedList.ListNode pPreNode = null;
        MyLinkedList.ListNode pNode = pHead;
        while (pNode != null) {
            MyLinkedList.ListNode pNext = pNode.next;
            boolean needDelete = false;
            if (pNext != null && pNode.data == pNext.data) {
                needDelete = true;
            }
            if (!needDelete) {
                pPreNode = pNode;
                pNode = pNode.next;
            } else {
                int value = pNode.data;
                MyLinkedList.ListNode pToBeDel = pNode;
                while (pToBeDel != null && pToBeDel.data == value) {
                    pNext = pToBeDel.next;
                    pToBeDel = pNext;
                }
                if (pPreNode == null) {
                    pHead = pNext;
                } else {
                    pPreNode.next = pNext;
                }
                pNode = pNext;
            }
        }
    }

}