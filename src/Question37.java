import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 面试题37：序列化二叉树
 *
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 
 */
public class Question37 {

    // 如果二叉树的序列化是从根节点开始的，那么相应的反序列化在根节点的数值读出来的时候就可以开始了。
    // 因此，我们可以根据前序遍历的顺序来序列化二叉树，因为前序遍历是从根节点开始的。在遍历二叉树碰到
    // null指针时，这些null指针序列化为一个特殊的字符（如'$'）。另外，节点的数值之间要用一个特殊字符
    // （如','）隔开。
    //
    // 把二叉树分为三部分：根节点、左子树和右子树。我们在处理（序列化和反序列化）它的根节点之后再分别
    // 处理它的左、右子树。这是典型的把问题递归分解然后逐个解决的过程。
    static void serialize(BinaryTreeNode pRoot, OutputStream os) {
        try {
            if (pRoot == null) {
                os.write('$');
                return;
            }
            os.write(pRoot.m_nValue);
            os.write(',');
            serialize(pRoot.m_pLeft, os);
            serialize(pRoot.m_pRight, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void deSerialize(BinaryTreeNode pRoot, InputStream is){
        try {
            int number = is.read();
            if (number >= 0 && number <= 9) {
                pRoot = new BinaryTreeNode();
                pRoot.m_nValue = number;
                pRoot.m_pLeft = null;
                pRoot.m_pRight = null;

                deSerialize(pRoot.m_pLeft, is);
                deSerialize(pRoot.m_pRight, is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}