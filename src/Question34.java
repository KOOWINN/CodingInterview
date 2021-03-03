import java.util.Stack;

/**
 * 面试题34：二叉树中和为某一值的路径
 *
 * 输入一颗二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始
 * 往下一直到叶节点所经过的节点形成一条路径。
 *
 */
public class Question34 {

    // 由于路径是从根节点出发到叶节点，也就是说路径总是以根节点为起始点，因此我们首先需要遍历根节点。在树的
    // 前序、中序、后序3种遍历方式中，只有前序遍历是首先访问根节点的，所以选择前序遍历的方式访问树的节点。
    static void findPath(BinaryTreeNode pRoot, int expectedSum) {

        if (pRoot == null) {
            return;
        }

        Stack<Integer> path = new Stack<>();
        int currentSum = 0;
        findPath(pRoot, expectedSum, path, currentSum);

    }

    // 当用前序遍历的方式访问到某一节点时，我们把该节点添加到路径上，并累加该节点的值。如果该节点为叶节点，
    // 并且路径中节点值的和刚好等于输入的整数，则当前路径符合要求，我们把它打印出来。如果当前节点不是叶节点，
    // 则继续访问它的子节点。当节点访问结束后，递归函数将自动回收它的父节点。因此，我们在函数退出之前要在路径上
    // 删除当前节点并减去当前节点的值，以确保返回父节点时路径刚好是从根节点到父节点。我们不难看出，保存路径的
    // 数据结构实际上是一个栈，因为路径要与递归调用状态一致，而递归调用的本质就是一个压栈和出栈的过程。
    private static void findPath(BinaryTreeNode pRoot, int expectedSum, Stack<Integer> path, int currentSum) {
        currentSum += pRoot.m_nValue;
        path.push(pRoot.m_nValue);

        // 如果是叶节点，并且路径上所有节点值的和等于输入的值，则打印出这条路径
        boolean isLeaf = pRoot.m_pLeft == null && pRoot.m_pRight == null;
        if (isLeaf && currentSum == expectedSum) {
            System.out.print("A path has found: ");
            for (Integer integer : path) {
                System.out.print(integer + "\t");
            }
            System.out.println();
        }

        // 如果不是叶子节点，则遍历它的子节点
        if (pRoot.m_pLeft != null) {
            findPath(pRoot.m_pLeft, expectedSum, path, currentSum);
        }
        if (pRoot.m_pRight != null) {
            findPath(pRoot.m_pRight, expectedSum, path, currentSum);
        }

        // 在返回父节点之前，在路径上删除当前节点
        path.pop();
    }
}