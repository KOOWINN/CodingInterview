/**
 * 面试题22：链表中倒数第k个节点
 *
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 */
public class Question22 {

    // 定义两个指针，第一个指针从链表的头指针开始遍历向前走k-1步，第二个指针保持不动；从第k步开始，
    // 第二个指针也开始从链表的头指针开始遍历。由于两个指针的距离保持在k-1，当第一个（走在前面的）
    // 指针到达链表的尾节点时，第二个（走在后面的）指针正好指向倒数第k个节点。
    //
    // 为了代码的鲁棒性，需要考虑到3个特殊情况：
    // ①输入的pListHead头指针为空 ②输入的以pListHead为头节点的链表的节点总数少于k ③输入的参数k为0
    static MyLinkedList.ListNode findKthToTail(MyLinkedList.ListNode pListHead, int k) {
        if (pListHead == null || k == 0) {
            return null;
        }
        MyLinkedList.ListNode pAhead = pListHead;
        MyLinkedList.ListNode pBehind = null;
        for (int i = 0; i < k-1; i++) {
            if (pAhead.next != null) {
                pAhead = pAhead.next;
            } else {
                return null;
            }
        }
        pBehind = pListHead;
        while (pAhead.next != null) {
            pAhead = pAhead.next;
            pBehind = pBehind.next;
        }
        return pBehind;
    }

}