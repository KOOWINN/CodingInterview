/**
 * 面试题52：两个链表的第一个公共节点
 *
 * 输入两个链表，找出它们的第一个公共子节点。
 * 
 */
public class Question52 {
    // 因为是单向链表，因此从第一个公共节点开始，之后它们所有的节点都是重合的，不可能再出现分叉。
    // 经过分析我们发现，如果两个链表有公共节点，那么公共节点出现在两个链表的尾部。如果我们从两个
    // 链表的尾部开始往前比较，那么最后一个相同的节点就是我们要找得的节点。可问题是，在单向链表中
    // 我们只能从头节点开始按顺序遍历，最后才能到达尾节点。最后到达的尾节点却要最先被比较，这听起来是不是像“后进先出”？
    // 于是我们可以把两个链表的节点放入两个栈里，这样两个链表的尾节点就位于两个栈的栈顶，接下来比较两个栈顶的节点是否相同。
    // 如果相同，则把栈顶弹出接着比较下一个栈顶，直到找到最后一个相同的节点。但是这种方法需要两个辅助栈，如果链表的长度分
    // 别为m和n，那么空间复杂度是O(m+n)，时间复杂度也是O(m+n)。
    //
    // 还有一种更简单的办法，首先遍历两个链表得到它们的长度，就能知道哪个链表比较长，以及长的链表比短的链表多几个节点。
    // 在第二次遍历的时候，在较长的链表上先走若干步，接着同时在两个链表上遍历，找到的第一个相同的节点就是它们的第一个公共节点。
    // 这种方法时间复杂度也是O(m+n)，但是不需要辅助栈，因此提高了空间效率。
    public static MyLinkedList.ListNode findFirstCommonNode(MyLinkedList.ListNode pHead1, MyLinkedList.ListNode pHead2) {
        //得到两个链表的长度
        int nLength1 = getListLength(pHead1);
        int nLength2 = getListLength(pHead2);
        int nLengthDif = nLength1 - nLength2;
        MyLinkedList.ListNode pListHeadLong = pHead1;
        MyLinkedList.ListNode pListHeadShort = pHead2;
        if (nLength2 > nLength1) {
            pListHeadLong = pHead2;
            pListHeadShort = pHead1;
            nLengthDif = nLength2 - nLength1;
        }

        // 先在长链表上走几步，再同时在两个链表上遍历
        for (int i = 0; i < nLengthDif; i++) {
            pListHeadLong = pListHeadLong.next;
        }
        while (pListHeadLong.next != null && pListHeadShort.next != null && pListHeadLong != pListHeadShort) {
            pListHeadLong = pListHeadLong.next;
            pListHeadShort = pListHeadShort.next;
        }

        // 得到第一个公共节点
        return pListHeadLong;
    }

    private static int getListLength(MyLinkedList.ListNode pHead) {
        int nLength = 0;
        MyLinkedList.ListNode pNode = pHead;
        while (pNode != null) {
            ++nLength;
            pNode = pNode.next;
        }
        return nLength;
    }
}