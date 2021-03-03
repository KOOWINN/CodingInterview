import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 面试题32_3：之字形打印二叉树
 *
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二行按照从右到左的顺序打印，
 * 第三行再按照从左到右的顺序打印，其他行依此类推。
 *
 */
public class Question32_3 {

    // 按之字形顺序打印二叉树需要两个栈。我们在打印某一层的节点时，把下一层的子节点保存到相应的栈里。
    // 如果当前打印的时奇数层（第一层、第三层等），则先保存左子节点再保存右子节点到第一个栈里；如果
    // 如果当前打印的时偶数层（第二层、第四层等），则先保存右子节点再保存左子节点到第二个栈里。
    static void printFromTopToBottomInShapeZ(BinaryTreeNode pRoot) {
        if (pRoot == null) {
            return;
        }
        Stack[] stacks = new Stack[2];
        int current = 0;
        int next = 1;
        stacks[current] = new Stack();
        stacks[next] = new Stack();
        stacks[current].push(pRoot);
        while (!stacks[current].isEmpty() || !stacks[next].isEmpty()) {
            BinaryTreeNode pNode = (BinaryTreeNode) stacks[current].pop();
            System.out.print(pNode.m_nValue + "\t");
            if (current == 0) {
                if (pNode.m_pLeft != null) {
                    stacks[next].push(pNode.m_pLeft);
                }
                if (pNode.m_pRight != null) {
                    stacks[next].push(pNode.m_pRight);
                }
            } else {
                if (pNode.m_pRight != null) {
                    stacks[next].push(pNode.m_pRight);
                }
                if (pNode.m_pLeft != null) {
                    stacks[next].push(pNode.m_pLeft);
                }
            }
            if (stacks[current].isEmpty()) {
                System.out.println();
                current = 1 - current;
                next = 1 - next;
            }
        }
    }

}