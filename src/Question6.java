import java.util.Stack;

/**
 * 面试题6：从尾到头打印链表
 *
 * 输入一个链表的头节点，从尾到头反过来打印每个节点的值。链表节点定义如下：
 *
 * class ListNode{
 *     int       m_nKey;
 *     ListNode  m_pNext;
 * }
 *
 */
public class Question6 {

    //遍历的顺序是从头到尾，可输出的顺序却是从尾到头。也就是说，第一个遍历到的节点最后一个输出，
    //而最后一个遍历到的节点第一个输出。这就是典型的“后进先出”，我们可以用栈实现这种顺序。
    static void printListReversiongly_Iteratively(MyLinkedList.ListNode pHead) {
        Stack<MyLinkedList.ListNode> stack = new Stack<>();
        MyLinkedList.ListNode pNode = pHead;
        while (pNode != null) {
            stack.push(pNode);
            pNode = pNode.next;
        }
        while (!stack.empty()) {
            pNode = stack.pop();
            System.out.print(String.format("%d\t", pNode.data));
        }
    }

    //因为递归本质上就是一个栈结构，所以可以使用递归实现，当访问到一个节点时，先递归输出它后面的节点，再输出该节点自身。
    static void printListReversingly_Recursively(MyLinkedList.ListNode pHead) {
        if (pHead != null) {
            if (pHead.next != null) {
                printListReversingly_Recursively(pHead.next);
            }
            System.out.print(String.format("%d\t", pHead.data));
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList(0);
        for (int i = 1; i <= 10; i++) {
            linkedList.addNode(i);
        }
        printListReversiongly_Iteratively(linkedList.head);
        System.out.println();
        printListReversingly_Recursively(linkedList.head);
    }

}