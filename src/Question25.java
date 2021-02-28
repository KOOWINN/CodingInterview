/**
 * 面试题25：合并两个排序的链表
 *
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然时递增排序的。例如：
 * 链表1： 1->3->5->7
 * 链表2： 2->4->6->8
 * 合并后的链表：1->2->3->4->5->6->7->8
 *
 */
public class Question25 {

    // 当我们得到两个链表中值较小的头节点并把它链接到已经合并的链表之后，两个链表剩余的节点依然是排序的，因此合并的步骤
    // 和之前的步骤是一样的。这就是典型的递归过程，我们可以定义递归函数完成这一过程。
    //
    // 鲁棒性问题：当第一个链表是空链表，也就是它的头节点是一个空指针时，那么把它和第二个链表合并，显然合并的结果就是第二个链表。
    // 同样，当输入的第二个链表的头节点时空指针时，我们把它和第一个链表合并得到的结果就是第一个链表。
    // 如果两个链表都为空，则合并的结果是得到一个空链表。
    static MyLinkedList.ListNode mergeTwoLinkedList(MyLinkedList.ListNode pHead1, MyLinkedList.ListNode pHead2) {
        if (pHead1 == null) {
            return pHead2;
        }
        if (pHead2 == null) {
            return pHead1;
        }
        MyLinkedList.ListNode pMergedHead;
        if (pHead1.data < pHead2.data) {
            pMergedHead = pHead1;
            pMergedHead.next = mergeTwoLinkedList(pHead1.next, pHead2);
        } else {
            pMergedHead = pHead2;
            pMergedHead.next = mergeTwoLinkedList(pHead1, pHead2.next);
        }
        return pMergedHead;
    }

    public static void main(String[] args) {
        MyLinkedList list1 = new MyLinkedList(1);
        list1.addNode(3);
        list1.addNode(5);
        list1.addNode(7);

        MyLinkedList list2 = new MyLinkedList(2);
        list2.addNode(4);
        list2.addNode(6);
        list2.addNode(8);

        MyLinkedList.ListNode mergedNode = mergeTwoLinkedList(list1.head, list2.head);
        while (mergedNode != null) {
            System.out.print(mergedNode.data + "\t");
            mergedNode = mergedNode.next;
        }

    }
}