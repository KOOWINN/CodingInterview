public class MyLinkedList {

    ListNode head;

    public MyLinkedList(int initialValue) {
        this.head = new ListNode(initialValue);
    }

    public void addNode(int value) {
        ListNode newNode = new ListNode(value);
        if (head == null) {
            head = newNode;
        } else {
            ListNode tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
    }

    public void removeNode(int value) {
        if (head == null) {
            return;
        }
        if (head.data == value) {
            head = head.next;
        } else {
            ListNode pNode = head;
            while (pNode.next != null && pNode.next.data != value) {
                pNode = pNode.next;
            }
            if (pNode.next != null) {
                pNode.next = pNode.next.next;
            }
        }
    }

    static class ListNode{
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
