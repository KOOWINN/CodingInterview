import java.util.LinkedList;
import java.util.Queue;

/**
 * 面试题32_2：分行从上到下打印二叉树
 *
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右顺序打印，每一层打印一行。
 *
 */
public class Question32_2 {

    // 跟题目32_1类似，不过为了把二叉树的每一行单独打印到一行里，我们需要两个变量：
    // 一个变量表示在当前层中还没有打印的节点数；另一个变量表示下一层节点的数目。
    static void printFromTopToBottomInRows(BinaryTreeNode pRoot) {
        if (pRoot == null) {
            return;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        int nextLevel = 0;
        int toBePrinted = 1;
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            --toBePrinted;
            System.out.print(node.m_nValue + "\t");
            if (node.m_pLeft != null) {
                queue.offer(node.m_pLeft);
                ++nextLevel;
            }
            if (node.m_pRight != null) {
                queue.offer(node.m_pRight);
                ++nextLevel;
            }
            if (toBePrinted == 0) {
                System.out.println();
                toBePrinted = nextLevel;
                nextLevel = 0;
            }
        }
    }

}