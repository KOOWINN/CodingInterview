/**
 * 面试题23：链表中环的入口点
 *
 * 如果一个链表中包含环，如何找出环的入口点？
 *
 */
public class Question23 {

    // 解决这个问题的第一步就是如何确定一个链表中包含环。我们可以用一快一慢两个指针来解决这个问题，一个指针一次走一步，
    // 两一个指针一次走两步，同时从链表的头节点出发。如果走的快的指针追上了走得慢的指针，那么链表就包含环；
    // 如果走得快的指针走到了链表的末尾（next指向null）都没有追上第一个指针，那么链表就不包含环。
    static MyLinkedList.ListNode meetingNode(MyLinkedList.ListNode pListHead) {
        if (pListHead == null) {
            return null;
        }
        MyLinkedList.ListNode pSlow = pListHead.next;
        if (pSlow.next == null) {
            return null;
        }
        MyLinkedList.ListNode pFast = pSlow.next;
        while (pFast != null && pSlow != null) {
            if (pFast == pSlow) {
                return pFast;
            }
            pSlow = pSlow.next;
            pFast = pFast.next;
            if (pFast != null) {
                pFast = pFast.next;
            }
        }
        return null;
    }

    // 如何找到环的入口？我们还是可以用两个指针来解决。先定义两个指针P1和P2。如果链表中的环有n个节点，
    // 则指针P1先在链表上向前移动n步，然后两个指针以相同的速度向前移动。当第二个指针指向环的入口点时，
    // 第一个指针已经围绕着环走了一圈，又回到了入口节点。
    //
    // 如何得到环中节点的数目？我们在前面提到判断一个链表里是否有环时用到了一快一慢两个指针。如果两个指针相遇，
    // 则表明链表中有环。两个指针相遇的节点一定在环中。可以从这个节点出发，一边继续向前移动一边计数，当再次
    // 回到这个节点时，就可以得到环中的节点数目了。
    static MyLinkedList.ListNode entryNodeOfLoop(MyLinkedList.ListNode pHead) {
        MyLinkedList.ListNode meetingNode = meetingNode(pHead);
        if (meetingNode == null) {
            return null;
        }

        // 得到环中节点的数目
        int nodesInLoop = 1;
        MyLinkedList.ListNode pNode1 = meetingNode;
        while (pNode1.next != meetingNode) {
            pNode1 = pNode1.next;
            nodesInLoop++;
        }

        // 先移动pNode1，次数为环中节点的数目
        pNode1 = pHead;
        for (int i = 0; i < nodesInLoop; i++) {
            pNode1 = pNode1.next;
        }

        // 再移动pNode1和pNode2
        MyLinkedList.ListNode pNode2 = pHead;
        while (pNode1 != pNode2) {
            pNode1 = pNode1.next;
            pNode2 = pNode2.next;
        }
        return pNode1;
    }

}