/**
 * 面试题18_1：在O(1)时间内删除链表节点
 *
 * 给定单向链表的头指针和一个节点指针，定义一个函数在O(1)时间内删除该节点。
 *
 */
public class Question18_1 {

    // 如果我们向删除节点i，可以从链表的头节点a开始顺序遍历，发现节点h指向的下一个节点是要删除的节点i，
    // 于是我们就可以把节点h指向i的下一个节点，即节点j。指针调整之后，我们就可以安全地删除节点i并保证
    // 链表没有断开。但是这种思路由于需要顺序查找，时间复杂度自然就是O(n)了。之所以需要顺序查找，是因
    // 为我们需要得到将被删除地节点地前一个节点。再单向链表中，节点中没有指向前一个节点的指针，所以只好
    // 从链表的头节点开始顺序查找。
    //
    // 其实我们不必得到被删除节点的下一个节点，因为我们可以很方便地得到要删除节点的下一个节点，所以只要把下一个
    // 节点的内容复制到需要删除的节点上覆盖原有内容，再把下一个节点删除，这也相当于把当前需要删除的节点删除了。
    static void deleteNode(MyLinkedList.ListNode pListHead, MyLinkedList.ListNode pToBeDeleted){
        if (pListHead == null || pToBeDeleted == null) {
            return;
        }
        if (pToBeDeleted.next != null) { //链表中有多个节点，且要删除的节点不是尾节点
            MyLinkedList.ListNode pNext = pToBeDeleted.next;
            pToBeDeleted.data = pNext.data;
            pToBeDeleted.next = pNext.next;
            pNext = null;
        } else if (pListHead == pToBeDeleted) { //链表中只有一个节点，删除头节点
            pListHead = null;
            pToBeDeleted = null;
        } else {  //链表中有多个节点，删除尾节点
            MyLinkedList.ListNode pNode = pListHead;
            while (pNode.next != null) {
                pNode = pNode.next;
            }
            pNode.next = null;
            pToBeDeleted = null;
        }
    }

}