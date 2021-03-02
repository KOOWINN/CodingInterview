import java.util.Stack;

/**
 * 面试题30：包含min函数的栈
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数。在该栈中，调用min、push及pop的时间复杂度都是O(1)。
 *
 */
public class Question30 {

    // 使用两个栈，每次新压入一个元素到数据栈时，都将其与之前最小元素这两者的较小值压入辅助栈，
    // 从而保证辅助栈的栈顶一直都是最小元素。当最小元素从数据栈内被弹出后，同时弹出辅助栈的栈顶元素，
    // 此时辅助栈的新栈顶元素就是下一个最小值。
    public static class StackWithMin{
        Stack<Integer> m_data;
        Stack<Integer> m_min;

        public void push(int value) {
            m_data.push(value);
            if (m_min.size() == 0 || value < m_min.peek()) {
                m_min.push(value);
            } else {
                m_min.push(m_min.peek());
            }
        }

        public void pop() {
            if (m_data.size() <= 0 || m_min.size() <= 0) {
                return;
            }
            m_data.pop();
            m_min.pop();
        }

        public int min() {
            if (m_data.size() <= 0 || m_min.size() <= 0) {
                return -1;
            }
            return m_min.peek();
        }
    }

}