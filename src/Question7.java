import java.util.Stack;

/**
 * 面试题7：重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如，输入前序遍历{1，2，4，7，3，5，6，8}和中序遍历{4，7，2，1，5，3，8，6}，则重建如图所示的二叉树并输出它的头节点。
 *
 *           1
 *      2         3
 *  4          5     6
 *    7            8
 *
 *
 * 二叉树节点的定义如下：
 * struct BinaryTreeNode{
 *     int              m_nValue;
 *     BinaryTreeNode*  m_pLeft;
 *     BinaryTreeNode*  m_pRight;
 * }
 *
 */
public class Question7 {

    static BinaryTreeNode construct(int[] preOrder, int[] inOrder, int length) throws Exception {
        if (preOrder == null || inOrder == null || length <= 0) {
            return null;
        }
        return constructCore(preOrder, inOrder, 0, length - 1, 0, length - 1);
    }

    private static BinaryTreeNode constructCore(int[] preOrder, int[] inOrder,
                                                int startPreOrder, int endPreOrder,
                                                int startInOder, int endInOrder) throws Exception {
        //前序遍历的第一个数字是根节点的值
        int rootValue = preOrder[startPreOrder];
        BinaryTreeNode root = new BinaryTreeNode();
        root.m_nValue = rootValue;
        root.m_pLeft = root.m_pRight = null;

        if (startPreOrder == endPreOrder) {
            if (startInOder == endInOrder && preOrder[startPreOrder] == inOrder[startInOder]) {
                return root;
            }
        }

        //在中序遍历中找到根节点的值
        int rootInOrder = startInOder;
        while (startInOder <= endInOrder && inOrder[rootInOrder] != rootValue) {
            rootInOrder++;
        }

        int leftLength = rootInOrder - startInOder;
        int leftPreOrderEnd = startPreOrder + leftLength;
        if (leftLength > 0) {
            //构建左子树
            root.m_pLeft = constructCore(preOrder, inOrder,
                    startPreOrder + 1, leftPreOrderEnd,
                    startInOder, rootInOrder - 1);
        }
        if (leftLength < endPreOrder - startPreOrder) {
            //构建右子树
            root.m_pRight = constructCore(preOrder, inOrder,
                    leftPreOrderEnd + 1, endPreOrder, rootInOrder + 1, endInOrder);
        }
        return root;
    }

    public static void main(String[] args) {
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        try {
            BinaryTreeNode treeNode = construct(preOrder, inOrder, 8);
            postOrder(treeNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void postOrder(BinaryTreeNode treeNode) {
        if (treeNode != null) {
            if (treeNode.m_pLeft != null) {
                postOrder(treeNode.m_pLeft);
            }
            if (treeNode.m_pRight != null) {
                postOrder(treeNode.m_pRight);
            }
            System.out.print(String.format("%d\t", treeNode.m_nValue));
        }
    }
}