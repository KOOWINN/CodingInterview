import java.util.Stack;

/**
 * 面试题27：二叉树的镜像
 *
 * 请完成一个函数，输入一颗二叉树，该函数输出它的镜像。
 *
 */
public class Question27 {

    // 先前序遍历这棵树的每个子节点，如果遍历到的节点有子节点，就交换它的两个子节点。
    // 当交换完所有非叶节点的左、右子节点之后，就得到了树的镜像。
    // 递归实现
    static void mirrorRecursively(BinaryTreeNode pRoot) {
        if (pRoot == null) {
            return;
        }
        if (pRoot.m_pLeft == null && pRoot.m_pRight == null) {
            return;
        }
        
        BinaryTreeNode pTemp = pRoot.m_pLeft;
        pRoot.m_pLeft = pRoot.m_pRight;
        pRoot.m_pRight = pTemp;

        if (pRoot.m_pLeft != null) {
            mirrorRecursively(pRoot.m_pLeft);
        }
        if (pRoot.m_pRight != null) {
            mirrorRecursively(pRoot.m_pRight);
        }
    }

    //非递归实现
    static void mirrorIteratively(BinaryTreeNode pRoot) {
        if (pRoot == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(pRoot);
        while (!stack.isEmpty()) {
            BinaryTreeNode pNode = stack.pop();

            if (pNode.m_pLeft == null && pNode.m_pRight == null) {
                continue;
            }

            BinaryTreeNode pTemp = pNode.m_pLeft;
            pNode.m_pLeft = pNode.m_pRight;
            pNode.m_pRight = pTemp;

            if (pNode.m_pRight != null) {
                stack.push(pNode.m_pRight);
            }

            if (pNode.m_pLeft != null) {
                stack.push(pNode.m_pLeft);
            }
        }
    }
}