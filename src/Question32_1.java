import java.util.LinkedList;
import java.util.Queue;

/**
 * 面试题32_1：不分行从上到下打印二叉树
 *
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右顺序打印。
 *
 */
public class Question32_1 {

    // 使用队列实现。每次打印一个节点的时候，如果该节点有子节点，则把该节点的子节点放到队列的末尾。
    // 接下来到队列的头部取出最早进入队列的节点，重复前面的操作，直到队列中所有的节点都被打印处理。
    static void printFromTopToBottom(BinaryTreeNode pRoot) {
        if (pRoot == null) {
            return;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            System.out.print(node.m_nValue + "\t");
            if (node.m_pLeft != null) {
                queue.offer(node.m_pLeft);
            }
            if (node.m_pRight != null) {
                queue.offer(node.m_pRight);
            }
        }
    }

}