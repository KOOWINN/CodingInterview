import java.util.Arrays;
import java.util.Stack;

/**
 * 面试题31：栈的压入、弹出序列
 *
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如，序列{1，2，3，4，5}是某栈的压栈序列，序列{4，5，3，2，1}
 * 是该压栈序列对应的一个弹出序列，但{4，3，5，1，2}就不可能是该压栈序列的弹出序列。
 *
 */
public class Question31 {

    // 建立一个辅助栈，把输入的第一个序列中的数字依次压入该栈，并按照第二个序列的顺序依次从该栈中弹出数字。
    // 如果下一个弹出的数字刚好是栈顶数字，那么直接弹出；如果下一个弹出的数字不在栈顶，则把压栈序列中还没
    // 入栈的数字压入辅助栈，直到把下一个需要弹出的数字压入栈为止；如果所有数字都压入栈后仍然没有找到下一个
    // 弹出的数字，那么该序列不可能是一个弹出序列。
    static boolean isPopOrder(int[] push, int[] pop) {
        if (push == null || pop == null || push.length != pop.length) {
            return false;
        }
        boolean isPossible= false;
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0;
        int popIndex = 0;
        while (popIndex < pop.length) {
            while (stack.isEmpty() || stack.peek() != pop[popIndex]) {
                if (pushIndex == push.length) {
                    break;
                }
                stack.push(push[pushIndex]);
                pushIndex++;
            }
            if (stack.peek() != pop[popIndex]) {
                break;
            }
            stack.pop();
            popIndex++;
        }
        if (stack.isEmpty() && popIndex == pop.length) {
            isPossible = true;
        }
        return isPossible;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 5, 3, 2, 1};
        System.out.println("Sequence A: " + Arrays.toString(a));
        System.out.println("Sequence B: " + Arrays.toString(b));
        System.out.println("A is a push order, B is a possible pop order: " + isPopOrder(a, b));
    }

}