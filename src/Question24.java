/**
 * 面试题24：反转链表
 *
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 */
public class Question24 {

    // 我们在调整节点i的next指针时，除了需要知道节点i本身，还需要知道节点i的前一个节点h，因为我们
    // 需要把节点i的next指针指向节点h。同时，我们还需要事先保存节点i的下一个节点j，以防止链表断开。
    // 因此，我们需要定义3个指针，分别指向当前遍历到的节点、它的前一个节点和后一个节点。
    static MyLinkedList.ListNode reverseList(MyLinkedList.ListNode pHead) {
        MyLinkedList.ListNode pReversedHead = null;
        MyLinkedList.ListNode pNode = pHead;
        MyLinkedList.ListNode pPrev = null;
        while (pNode != null) {
            MyLinkedList.ListNode pNext = pNode.next;
            if (pNext == null) {
                pReversedHead = pNode;
            }
            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        return pReversedHead;
    }

}